package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.base.HFWBaseAdapter;
import com.huafan.huafano2omanger.callback.BaseHolder;
import com.huafan.huafano2omanger.entity.FinancingTakeDetailBean;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务外卖明细适配器
 */

public class FinancingTakeOutDetailAdapter extends HFWBaseAdapter<FinancingTakeDetailBean.ListBean> {
    private final FragmentActivity mContext;

    public FinancingTakeOutDetailAdapter(FragmentActivity activity) {

        this.mContext = activity;
    }

    @Override
    public BaseHolder<FinancingTakeDetailBean.ListBean> onViewHolderCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.financing_detail_listview_item, parent, false);
        return new FinancingTakeViewHolder(view);
    }

    @Override
    public void onViewHolderBind(BaseHolder<FinancingTakeDetailBean.ListBean> holder, int position) {
        FinancingTakeDetailBean.ListBean listBean = list.get(position);
        ((FinancingTakeViewHolder) holder).bindDateView(listBean, position, mContext);
    }


    class FinancingTakeViewHolder extends BaseHolder<FinancingTakeDetailBean.ListBean> {
        @BindView(R.id.financing_detail_item_order_number)
        TextView financingDetailItemOrderNumber;//订单号
        @BindView(R.id.financing_detail_item_total_order_amount)
        TextView financingDetailItemTotalOrderAmount;//订单总额
        @BindView(R.id.financing_detail_item_the_store_sales)
        TextView financingDetailItemTheStoreSales; //店铺营收
        @BindView(R.id.financing_detail_item_finish_time)
        TextView financingDetailItemFinishTime; //完成时间
        @BindView(R.id.financing_detail_item_way)
        TextView financingDetailItemWay;//方式（团购or外卖）

        FinancingTakeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindDateView(FinancingTakeDetailBean.ListBean shopMangerBean, int position, FragmentActivity mContext) {

            //赋值订单号
            financingDetailItemOrderNumber.setText("订单号: "+list.get(position).getOrder_num());
            //订单总额
            financingDetailItemTotalOrderAmount.setText("订单总额: ¥ "+list.get(position).getOrder_amount());
            //店铺营收
            financingDetailItemTheStoreSales.setText("店铺营收: ¥ "+list.get(position).getMerch_income());
            //完成时间
            financingDetailItemFinishTime.setText("完成时间: "+list.get(position).getCompleted());
            //设置订单状态
            financingDetailItemWay.setText("外卖");

        }
    }
}

