package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.DobusinessBean;
import com.huafan.huafano2omanger.utils.TimeTools;

import java.util.List;

/**
 * Created by heguozhong on 2018/1/9/009.
 * 营业设置适配器
 */

public class DoBusinessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentActivity activity;
    private List<DobusinessBean.ListsBean> lists;
    private DobusinessBean dobusinessBean;
    private static final int TYPE_ZERO = 0;//营业时间
    private static final int TYPE_ONE = 1;//保存

    //营业状态
    OnStateClickListener onStateClickListener;
    //删除营业结束时间按钮
    OnDeleteEndTimeClickListener onDeleteEndTimeClickListener;
    //开始结束营业时间linearlayout
    OnLinearClickListener onLinearClickListener;

    //保存按钮接口
    public interface OnSaveClickListener {
        void onSaveClick(View v);
    }

    //营业状态接口
    public interface OnStateClickListener {
        void onStateClick(View v);
    }

    //删除营业时间按钮接口
    public interface OnDeleteEndTimeClickListener {
        void onDeleteEndTimeClick(View v, int position);
    }

    //开始结束营业时间linearlayout
    public interface OnLinearClickListener  {
        void onLinearClick(View v, int position);
    }

    //设置营业状态点击回调方法
    public void setOnStateClickListener(OnStateClickListener onStateClickListener) {
        this.onStateClickListener = onStateClickListener;
    }

    //设置开始结束营业时间linearlayout点击回调方法
    public void setOnLinearClickListener(OnLinearClickListener onLinearClickListener) {
        this.onLinearClickListener = onLinearClickListener;
    }

    //设置删除营业时间点击回调方法
    public void setOnDeleteEndTimeClickListener(OnDeleteEndTimeClickListener onDeleteEndTimeClickListener) {
        this.onDeleteEndTimeClickListener = onDeleteEndTimeClickListener;
    }

    public DoBusinessAdapter(FragmentActivity activity, List<DobusinessBean.ListsBean> lists, DobusinessBean dobusinessBean) {
        this.activity = activity;
        this.lists = lists;
        this.dobusinessBean = dobusinessBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            //营业时间
            case TYPE_ZERO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dobusiness_recyclerview_item, parent, false);
                holder = new MyViewHolder1(view);
                break;
            //营业状态
            case TYPE_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dobusiness_recyclerview_item_save, parent, false);
                holder = new MyViewHolder2(view);

                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            //营业时间
            case TYPE_ZERO:
                MyViewHolder1 holder1 = (MyViewHolder1) holder;
                //营业开始时间
                String startTime = TimeTools.getCountTimeByLong(Long.parseLong(String.valueOf(lists.get(position).getStarttime())));
                holder1.businessStartTime.setText("开始时间："+startTime);
                //营业结束时间
                String endTime = TimeTools.getCountTimeByLong(Long.parseLong(String.valueOf(lists.get(position).getEndtime())));
                holder1.businessEndTime.setText("结束时间："+endTime);
                //该营业时间段配送费
                holder1.doBusinessMoney.setText("配送费:" + lists.get(position).getDistrib_money() + "");

                //开始结束营业时间linearlayout监听
                holder1.lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onLinearClickListener!=null){
                            onLinearClickListener.onLinearClick(v,position);
                        }
                    }
                });
                //删除营业结束时间监听
                holder1.businessDeleteTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onDeleteEndTimeClickListener != null) {
                            onDeleteEndTimeClickListener.onDeleteEndTimeClick(v, position);
                        }
                    }
                });
                break;
            //保存
            case TYPE_ONE:
                MyViewHolder2 holder2 = (MyViewHolder2) holder;
                // 营业状态:0-休息 1-营业
                if (dobusinessBean.getState() == 0) {
                    holder2.doBusinessStateImg.setBackgroundResource(R.drawable.close);
                } else if (dobusinessBean.getState() == 1) {
                    holder2.doBusinessStateImg.setBackgroundResource(R.drawable.open);
                }
                //营业状态监听
                holder2.doBusinessStateImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onStateClickListener != null) {
                            onStateClickListener.onStateClick(v);
                        }
                    }
                });
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position > lists.size() - 1) {
            return TYPE_ONE;
        } else {
            return TYPE_ZERO;
        }
    }

    @Override
    public int getItemCount() {
        return lists.size() + 1;
    }

    //营业时间布局
    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView businessStartTime; //绑定营业开始时间id
        private final TextView businessEndTime;//绑定营业结束时间id
        private final Button businessDeleteTime; //删除结束时间按钮
        private final TextView doBusinessMoney;//配送费
        private final LinearLayout lin;

        public MyViewHolder1(View itemView) {
            super(itemView);
            lin = (LinearLayout) itemView.findViewById(R.id.business_lin);
            businessStartTime = (TextView) itemView.findViewById(R.id.business_start_time);
            businessEndTime = (TextView) itemView.findViewById(R.id.business_end_time);
            businessDeleteTime = (Button) itemView.findViewById(R.id.business_delete_time);
            doBusinessMoney = (TextView) itemView.findViewById(R.id.doBusiness_money);
        }
    }


    //营业状态布局
    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        private final CheckBox doBusinessStateImg;//营业状态按钮

        public MyViewHolder2(View itemView) {
            super(itemView);
            doBusinessStateImg = (CheckBox) itemView.findViewById(R.id.doBusinessState_img);
        }
    }

    //移除条目方法
//    public void removeItem(int position) {
//        lists.remove(position);
//        notifyDataSetChanged();
//    }
}
