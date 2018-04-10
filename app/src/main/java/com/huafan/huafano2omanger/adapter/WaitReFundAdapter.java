package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.WaitReFundBean;
import com.huafan.huafano2omanger.utils.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2018/1/10.
 * by--
 */

public class WaitReFundAdapter extends BaseExpandableListAdapter {

    private final FragmentActivity mContext;
    private final List<WaitReFundBean.ListBean> data;
    private final String reState;
    private OnEvulateShowClickLitener onEvulateShowClickLitener;
    private OnLeftButtonStateClickLitener onLeftButtonStateClickLitener;
    private OnRightButtonStateClickLitener onRightButtonStateClickLitener;

    public WaitReFundAdapter(FragmentActivity activity, List<WaitReFundBean.ListBean> datas, String state) {

        this.mContext = activity;
        this.data = datas;
        this.reState = state;
    }


    /**
     * 左边按钮状态
     *
     * @param onLeftButtonStateClickLitener
     */
    public void setOnLeftButtonStateClickLitener(OnLeftButtonStateClickLitener onLeftButtonStateClickLitener) {
        this.onLeftButtonStateClickLitener = onLeftButtonStateClickLitener;
    }

    /**
     * 右边按钮状态
     *
     * @param onRightButtonStateClickLitener
     */
    public void setOnRightButtonStateClickLitener(OnRightButtonStateClickLitener onRightButtonStateClickLitener) {
        this.onRightButtonStateClickLitener = onRightButtonStateClickLitener;
    }

    public interface OnLeftButtonStateClickLitener {

        void leftButtonStateLitener(View view, int position, String id);
    }

    public interface OnRightButtonStateClickLitener {

        void rightButtonStateLitener(View view, int position, String id);
    }


    /**
     * 展开收起点击事件
     *
     * @param onEvulateShowClickLitener
     */
    public void setOnEvulateShowClickLitener(OnEvulateShowClickLitener onEvulateShowClickLitener) {
        this.onEvulateShowClickLitener = onEvulateShowClickLitener;
    }

    public interface OnEvulateShowClickLitener {

        void evulateClickLitener(View view, int position, boolean isshow);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getGoods().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getGoods().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewViewHolder groupViewViewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_waitdispos, null);
            groupViewViewHolder = new GroupViewViewHolder(convertView);
            convertView.setTag(groupViewViewHolder);

        } else {

            groupViewViewHolder = (GroupViewViewHolder) convertView.getTag();
        }

        //设置数据
        groupViewViewHolder.tvNum.setText("#" + data.get(groupPosition).getOrder_num());
        groupViewViewHolder.tvNickName.setText(data.get(groupPosition).getCustomer_name());
        groupViewViewHolder.tvTimes.setText("(" + "下单" + data.get(groupPosition).getOrder_count() + "次" + ")");
        groupViewViewHolder.tvPhone.setText(data.get(groupPosition).getCustomer_tel());
        groupViewViewHolder.tvAdress.setText(data.get(groupPosition).getLocation() + data.get(groupPosition).getDetail_address());
        groupViewViewHolder.tvDistence.setText(data.get(groupPosition).getDistance() + "m");
        groupViewViewHolder.tvOpinionTime.setText("\r" + "(" + "建议" + data.get(groupPosition).getBooking_time() + "送达" + ")");

        String state = data.get(groupPosition).getRefund_state();

        //0-待处理 1 同意退款 2 拒绝退款 3取消退款
        if (state.equals("0")) {
            groupViewViewHolder.tvState.setText("待处理");
        } else if (state.equals("1")) {
            groupViewViewHolder.tvState.setText("已完成");
        } else if (state.equals("2")) {
            groupViewViewHolder.tvState.setText("拒绝退款");
        } else {
            groupViewViewHolder.tvState.setText("取消退款");
        }

        //展开收起
        boolean isshow = data.get(groupPosition).getIsshow();
        if (isshow == true) {
            groupViewViewHolder.ivShow.setImageResource(R.drawable.evulate_hide);
        } else {
            groupViewViewHolder.ivShow.setImageResource(R.drawable.evluate_show);
        }

        //设置点击事件
        groupViewViewHolder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onEvulateShowClickLitener != null) {

                    if (data.get(groupPosition).getIsshow() == false) {//隐藏
                        data.get(groupPosition).setIsshow(true);
                        groupViewViewHolder.ivShow.setImageResource(R.drawable.evluate_show);
                    } else {//展开
                        data.get(groupPosition).setIsshow(false);
                        groupViewViewHolder.ivShow.setImageResource(R.drawable.evulate_hide);
                    }

                    onEvulateShowClickLitener.evulateClickLitener(v, groupPosition, data.get(groupPosition).getIsshow());
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_waitrefund_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        } else {

            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //设置数据、
        childViewHolder.tvOrderCreate.setText(data.get(groupPosition).getCreated());
        childViewHolder.containerPrice.setText("¥" + data.get(groupPosition).getBox_cost());
        childViewHolder.postagePrice.setText("¥" + data.get(groupPosition).getDistrib_cost());
        childViewHolder.tvEstimatePrice.setText("¥" + data.get(groupPosition).getPrice());
        childViewHolder.tvDdbz.setText(data.get(groupPosition).getNote());
        childViewHolder.tvOrderNum.setText(data.get(groupPosition).getOrder_num());
        childViewHolder.tvRefundMessage.setText(data.get(groupPosition).getExplain() + "\n" + data.get(groupPosition).getReason());

        //0-不开发票 1-开发票
        String need_invoice = data.get(groupPosition).getNeed_invoice();
        if (need_invoice.equals("0")) {
            childViewHolder.tvInvoice.setText("不开发票");
        } else {
            childViewHolder.tvInvoice.setText("开发票");
        }

        String pay_channel = data.get(groupPosition).getPay_channel();
        //0-未支付 1-微信 2-支付宝 3-银联 4-账户余额
        if (pay_channel.equals("1")) {
            childViewHolder.tvPayType.setText("微信");
        } else if (pay_channel.equals("2")) {
            childViewHolder.tvPayType.setText("支付宝");
        } else if (pay_channel.equals("3")) {
            childViewHolder.tvPayType.setText("银联");
        } else if (pay_channel.equals("4")) {
            childViewHolder.tvPayType.setText("账户余额");
        } else {
            childViewHolder.tvPayType.setText("未支付");
        }

        //根据状态显示按钮
        String state = data.get(groupPosition).getRefund_state();
        //0-待处理 1 同意退款 2 拒绝退款 3取消退款
        if (state.equals("0")) {
            childViewHolder.llBtnState.setVisibility(View.VISIBLE);
        } else if (state.equals("1")) {
            childViewHolder.llBtnState.setVisibility(View.GONE);
        } else {
            childViewHolder.llBtnState.setVisibility(View.GONE);
        }

        //显示商品信息
        List<WaitReFundBean.ListBean.GoodsBean> goods = data.get(groupPosition).getGoods();
        if (goods != null && goods.size() > 0) {

            childViewHolder.goodName.setText(goods.get(childPosition).getGoods_name());
            childViewHolder.goodNum.setText("x" + goods.get(childPosition).getGoods_num());
            childViewHolder.goodPrice.setText("¥" + goods.get(childPosition).getGoods_price());
            String file_path = ApiUrlConstant.API_IMG_URL + goods.get(childPosition).getFile_path();
            new GlideImageLoader().displayImage(mContext, file_path, childViewHolder.ivImg);
        }

        //是否显示商品字样
        if (childPosition == 0) {
            childViewHolder.tvHintGood.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.tvHintGood.setVisibility(View.GONE);
        }

        //是否显示订单详情以及接单按钮
        if (childPosition == goods.size() - 1) {
            childViewHolder.llDetail.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.llDetail.setVisibility(View.GONE);
        }


        //设置拒绝退款的点击事件
        childViewHolder.tvLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onLeftButtonStateClickLitener != null) {

                    onLeftButtonStateClickLitener.leftButtonStateLitener(v, groupPosition, data.get(groupPosition).getRefund_id());
                }
            }
        });

        //设置同意退款的点击事件
        childViewHolder.tvRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onRightButtonStateClickLitener != null) {

                    onRightButtonStateClickLitener.rightButtonStateLitener(v, groupPosition, data.get(groupPosition).getRefund_id());
                }
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }

    static class GroupViewViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_nick_name)
        TextView tvNickName;
        @BindView(R.id.tv_times)
        TextView tvTimes;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_adress)
        TextView tvAdress;
        @BindView(R.id.tv_distence)
        TextView tvDistence;
        @BindView(R.id.tv_opinion_time)
        TextView tvOpinionTime;
        @BindView(R.id.iv_show)
        ImageView ivShow;

        GroupViewViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.tv_hint_good)
        TextView tvHintGood;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.good_name)
        TextView goodName;
        @BindView(R.id.good_num)
        TextView goodNum;
        @BindView(R.id.good_price)
        TextView goodPrice;
        @BindView(R.id.container_price)
        TextView containerPrice;
        @BindView(R.id.postage_price)
        TextView postagePrice;
        @BindView(R.id.tv_123)
        TextView tv123;
        @BindView(R.id.tv_estimate_price)
        TextView tvEstimatePrice;
        @BindView(R.id.tv_ddbz)
        TextView tvDdbz;
        @BindView(R.id.tv_invoice)
        TextView tvInvoice;
        @BindView(R.id.tv_refund_message)
        TextView tvRefundMessage;
        @BindView(R.id.ll_refund)
        LinearLayout llRefund;
        @BindView(R.id.tv_distribution_message)
        TextView tvDistributionMessage;
        @BindView(R.id.ll_distribution)
        LinearLayout llDistribution;
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.tv_order_create)
        TextView tvOrderCreate;
        @BindView(R.id.tv_pay_type)
        TextView tvPayType;
        @BindView(R.id.tv_left_button)
        Button tvLeftButton;
        @BindView(R.id.tv_right_button)
        Button tvRightButton;
        @BindView(R.id.ll_btn_state)
        LinearLayout llBtnState;
        @BindView(R.id.ll_detail)
        LinearLayout llDetail;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
