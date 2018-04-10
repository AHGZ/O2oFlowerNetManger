package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heguozhong on 2018/1/16/016.
 * 分类管理适配器
 */

public class SortManagementAdapter extends RecyclerView.Adapter{
    private FragmentActivity activity;
    private List<SortManagementBean.ListBean> list;
    private List<String> xuHao;

    public SortManagementAdapter(FragmentActivity activity, List<SortManagementBean.ListBean> list) {
        this.activity = activity;
        this.list = list;
        xuHao = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            xuHao.add(i + "");
        }
    }

    //修改按钮接口
    OnUpdateClickListener onUpdateClickListener;
    //删除按钮接口
    OnDeleteClickListener onDeleteClickListener;
    //每个item的点击事件
    OnEveryItemClickListener onEveryItemClickListener;

    public interface OnUpdateClickListener {
        void onUpdateClick(View v, int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(View v, int position);
    }
    public interface OnEveryItemClickListener {
        void onEveryItemClick(View v, int position);
    }

    //设置修改按钮接口调用方法
    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }

    //设置删除按钮接口调用方法
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
    //设置每个item的接口调用方法
    public void setOnEveryItemClickListener(OnEveryItemClickListener onEveryItemClickListener) {
        this.onEveryItemClickListener = onEveryItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定给菜品份数布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_management_listview_item, parent, false);
        RecyclerView.ViewHolder holder = new MyViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder1 holder1 = (MyViewHolder1) holder;
        //给序号赋值
        holder1.sortManagementOrderNumber.setText(xuHao.get(position));
        //给名称赋值
        holder1.sortManagementShopname.setText(list.get(position).getName());
        //给名称数量
        holder1.sortManagementShopnum.setText("菜品数："+list.get(position).getNum());
        //给排序赋值
        holder1.sortManagementRank.setText(list.get(position).getSort());
        //扩大修改删除按钮点击事件
        UIUtils.setTouchDelegate(holder1.sortManagementItemCompile, 50);
        UIUtils.setTouchDelegate(holder1.sortManagementItemDelete, 50);
        //设置分类管理删除按钮点击事件
        holder1.sortManagementItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(v, position);
                }
            }
        });
        //设置分类管理修改按钮点击事件
        holder1.sortManagementItemCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUpdateClickListener != null) {
                    onUpdateClickListener.onUpdateClick(v, position);
                }
            }
        });
        //设置每个item的点击事件
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEveryItemClickListener != null) {
                    onEveryItemClickListener.onEveryItemClick(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //菜品份数Viewholder
    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView sortManagementRank;//排序
        private final TextView sortManagementShopnum;//数量
        private final TextView sortManagementShopname;//名称
        private final TextView sortManagementOrderNumber;//序号
        private final ImageView sortManagementItemCompile;//修改按钮
        private final ImageView sortManagementItemDelete;//删除按钮
        private View itemView;
        public MyViewHolder1(View itemView) {
            super(itemView);
            this.itemView=itemView;
            sortManagementRank = (TextView) itemView.findViewById(R.id.sort_management_rank);
            sortManagementShopnum = (TextView) itemView.findViewById(R.id.sort_management_shopnum);
            sortManagementShopname = (TextView) itemView.findViewById(R.id.sort_management_shopname);
            sortManagementOrderNumber = (TextView) itemView.findViewById(R.id.sort_management_order_number);
            sortManagementItemCompile = (ImageView) itemView.findViewById(R.id.sort_management_item_compile);
            sortManagementItemDelete = (ImageView) itemView.findViewById(R.id.sort_management_item_delete);
        }
    }
}
