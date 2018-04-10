package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.utils.UIUtils;

import java.util.List;

/**
 * Created by heguozhong on 2017/12/23.
 * 商品单位适配器
 */

public class ShopUnitAdapter extends RecyclerView.Adapter {
    private FragmentActivity activity;
    private List<ShopUnitBean.ListBean> list;

    public ShopUnitAdapter(FragmentActivity activity, List<ShopUnitBean.ListBean> list) {
        this.activity = activity;
        this.list=list;
    }
    //修改按钮接口
    OnUpdateClickListener onUpdateClickListener;
    //删除按钮接口
    OnDeleteClickListener onDeleteClickListener;

    public interface OnUpdateClickListener {
        void onUpdateClick(View v, int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(View v, int position);
    }

    //设置修改按钮接口调用方法
    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }

    //设置删除按钮接口调用方法
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定给菜品份数布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shop_unit_listview_item, parent, false);
        RecyclerView.ViewHolder holder = new MyViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder1 holder1 = (MyViewHolder1) holder;

        //当merch_id为0时，不能删除不能修改
        if (list.get(position).getMerch_id().equals("0")){
            holder1.shopItemDelete.setVisibility(View.GONE);
            holder1.shopItemCompile.setVisibility(View.GONE);
            holder1.shopItemView.setVisibility(View.GONE);
        }else{
            holder1.shopItemDelete.setVisibility(View.VISIBLE);
            holder1.shopItemCompile.setVisibility(View.VISIBLE);
            holder1.shopItemView.setVisibility(View.VISIBLE);
        }
        //给菜品份数赋值
        holder1.shopUnitItemNum.setText(list.get(position).getUnit());
        //扩大修改删除按钮点击事件
        UIUtils.setTouchDelegate(holder1.shopItemCompile, 20);
        UIUtils.setTouchDelegate(holder1.shopItemDelete, 20);
        //删除监听
        holder1.shopItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(v, position);
                }
            }
        });
        //修改监听
        holder1.shopItemCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUpdateClickListener != null) {
                    onUpdateClickListener.onUpdateClick(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    //菜品份数Viewholder
    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView shopUnitItemNum;//份数
        private final ImageView shopItemCompile;//修改按钮
        private final ImageView shopItemDelete;//删除按钮
        private final View shopItemView;//

        public MyViewHolder1(View itemView) {
            super(itemView);
            shopUnitItemNum = (TextView) itemView.findViewById(R.id.shop_unit_item_num);
            shopItemCompile = (ImageView) itemView.findViewById(R.id.shop_unit_item_compile);
            shopItemDelete = (ImageView) itemView.findViewById(R.id.shop_unit_item_delete);
            shopItemView = (View) itemView.findViewById(R.id.shop_unit_item_view);
        }
    }
}
