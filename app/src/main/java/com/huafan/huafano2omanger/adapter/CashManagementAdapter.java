package com.huafan.huafano2omanger.adapter;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.utils.DateUtils;

import java.util.List;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 提现管理适配器
 */

public class CashManagementAdapter extends RecyclerView.Adapter {

    private FragmentActivity activity;
    private List<CashManagementBean.ListBean> list;

    public CashManagementAdapter(FragmentActivity activity, List<CashManagementBean.ListBean> list) {
        this.activity = activity;
        this.list = list;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定提现布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cash_management_listview_item, parent, false);
        RecyclerView.ViewHolder holder = new MyViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder1 holder1 = (MyViewHolder1) holder;

        //设置申请日期
        String timedate = DateUtils.timedate(list.get(position).getCreated());
        holder1.cashManagementItemData.setText(timedate);
        //设置提现金额
        holder1.cashManagementItemTxje.setText("¥ "+list.get(position).getAmount());
        //设置申请状态(0-提现进行中 1-提现成功 2-提现失败)
        if (list.get(position).getState()==0){
            holder1.cashManagementItemState.setText("提现进行中");
        }else if (list.get(position).getState()==1){
            holder1.cashManagementItemState.setText("提现成功");
        }else if (list.get(position).getState()==2){
            holder1.cashManagementItemState.setText("提现失败");
            holder1.cashManagementItemState.setTextColor(Color.parseColor("#FF1E00"));
        }
        //赋值提现方式
        if (list.get(position).getWithdraw_mode()==1){
            holder1.cashManagementItemTxfs.setText("银行卡");
        }else if (list.get(position).getWithdraw_mode()==2){
            holder1.cashManagementItemTxfs.setText("支付宝");
        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //提现Viewholder
    public class MyViewHolder1 extends RecyclerView.ViewHolder {


        private final TextView cashManagementItemData;//申请日期
        private final TextView cashManagementItemTxje; //提现金额
        private final TextView cashManagementItemTxfs; //提现方式
        private final TextView cashManagementItemState;//申请状态

        public MyViewHolder1(View itemView) {
            super(itemView);
            cashManagementItemData = (TextView) itemView.findViewById(R.id.cash_management_item_data);
            cashManagementItemTxje = (TextView) itemView.findViewById(R.id.cash_management_item_txje);
            cashManagementItemTxfs = (TextView) itemView.findViewById(R.id.cash_management_item_txfs);
            cashManagementItemState = (TextView) itemView.findViewById(R.id.cash_management_item_state);
        }
    }
}
