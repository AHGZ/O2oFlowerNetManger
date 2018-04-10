package com.huafan.huafano2omanger.adapter;

/**
 * Created by heguozhong on 2017/12/26.
 * 满返管理适配器
 */

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.FullManagementBean;
import com.huafan.huafano2omanger.utils.UIUtils;

import java.util.List;

public class FullManagementAdapter extends RecyclerView.Adapter {
    private FragmentActivity activity;
    private List<FullManagementBean.ListBean> list;

    public FullManagementAdapter(FragmentActivity activity, List<FullManagementBean.ListBean> list) {
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
        //绑定满返信息布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_full_management_listview_item, parent, false);
        RecyclerView.ViewHolder holder = new MyViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder1 holder1 = (MyViewHolder1) holder;
        //给满...返钱数赋值
        holder1.content.setText("满"+list.get(position).getOrder_amount()+"返"+list.get(position).getRebate()+"元");
        //扩大修改删除按钮点击事件
        UIUtils.setTouchDelegate(holder1.delete, 20);
        UIUtils.setTouchDelegate(holder1.compile, 20);
        //删除满返监听
        holder1.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(v, position);
                }
            }
        });
        //修改满返监听
        holder1.compile.setOnClickListener(new View.OnClickListener() {
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

    //满返Viewholder
    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView content;//展示满返内容
        private final ImageView compile;//修改满返按钮
        private final ImageView delete;//删除满返按钮

        public MyViewHolder1(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.full_management_item_text);
            compile = (ImageView) itemView.findViewById(R.id.full_management_item_compile);
            delete = (ImageView) itemView.findViewById(R.id.full_management_item_delete);
        }
    }
}
