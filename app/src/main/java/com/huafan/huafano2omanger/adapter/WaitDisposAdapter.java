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
import com.huafan.huafano2omanger.entity.WaitDisposeBean;
import com.huafan.huafano2omanger.utils.DateUtils;
import com.huafan.huafano2omanger.utils.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2018/1/9.
 * by--待处理订单适配器
 */

public class WaitDisposAdapter extends BaseExpandableListAdapter {

    private final FragmentActivity mContext;
    private final List<WaitDisposeBean.ListBean> data;
    private OnEvulateShowClickLitener onEvulateShowClickLitener;
    private OnButtonClickLitener onButtonClickLitener;
    private OnLeftButtonStateClickLitener onLeftButtonStateClickLitener;
    private OnRightButtonStateClickLitener onRightButtonStateClickLitener;
    private OnCenterButtonStateClickLitener onCenterButtonStateClickLitener;
    private OnRefuseButtonClickLitener onRefuseButtonClickLitener;

    public WaitDisposAdapter(FragmentActivity activity, List<WaitDisposeBean.ListBean> data) {

        this.mContext = activity;
        this.data = data;
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

    /***
     * 中间按钮状态
     * @param onCenterButtonStateClickLitener
     */
    public void setOnCenterButtonStateClickLitener(OnCenterButtonStateClickLitener onCenterButtonStateClickLitener) {
        this.onCenterButtonStateClickLitener = onCenterButtonStateClickLitener;
    }

    public interface OnCenterButtonStateClickLitener {

        void centerButtonStateLitener(View view, int position, String id);
    }

    public interface OnLeftButtonStateClickLitener {

        void leftButtonStateLitener(View view, int position, String id);
    }

    public interface OnRightButtonStateClickLitener {

        void rightButtonStateLitener(View view, int position, String id);
    }

    /***
     * 接单点击事件
     * @param onButtonClickLitener
     */
    public void setOnButtonClickLitener(OnButtonClickLitener onButtonClickLitener) {
        this.onButtonClickLitener = onButtonClickLitener;
    }

    public interface OnButtonClickLitener {

        void buttonClickLitener(View view, int position, String id);
    }

    /***
     * 拒绝接单点击事件
     * @param onButtonClickLitener
     */
    public void setOnRefuseButtonClickLitener(OnRefuseButtonClickLitener onButtonClickLitener) {
        this.onRefuseButtonClickLitener = onButtonClickLitener;
    }

    public interface OnRefuseButtonClickLitener {

        void refuseButtonClickLitener(View view, int position, String id);
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
        return data == null ? 0 : data.size();
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

        String state = data.get(groupPosition).getState();

        //0-待付款 1-待接单 2-待发货/跑腿取餐 3-待收货 4-已收货  10-交易完成 11-交易关闭（会员取消）
        // 12-交易关闭（客服取消） 13-交易关闭(运营后台取消) 14-交易关闭（支付超时自动关闭）'

        if (state.equals("0")) {
            groupViewViewHolder.tvState.setText("待付款");
        } else if (state.equals("1")) {
            groupViewViewHolder.tvState.setText("待接单");
        } else if (state.equals("2")) {
            groupViewViewHolder.tvState.setText("待发货");
        } else if (state.equals("3")) {
            groupViewViewHolder.tvState.setText("待收货");
        } else if (state.equals("4")) {
            groupViewViewHolder.tvState.setText("已收货");
        } else if (state.equals("10")) {
            groupViewViewHolder.tvState.setText("交易完成");
        } else if (state.equals("11")) {
            groupViewViewHolder.tvState.setText("交易关闭");
        } else if (state.equals("12")) {
            groupViewViewHolder.tvState.setText("交易关闭");
        } else if (state.equals("13")) {
            groupViewViewHolder.tvState.setText("交易关闭");
        } else {
            groupViewViewHolder.tvState.setText("交易关闭");
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

            convertView = View.inflate(mContext, R.layout.item_waitdispos_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        } else {

            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //设置数据、
        DateUtils dateUtils = new DateUtils();

        childViewHolder.tvOrderCreate.setText(dateUtils.timetodate(data.get(groupPosition).getCreated()));
        childViewHolder.containerPrice.setText("¥" + data.get(groupPosition).getBox_cost());
        childViewHolder.postagePrice.setText("¥" + data.get(groupPosition).getDistrib_cost());
        childViewHolder.tvEstimatePrice.setText("¥" + data.get(groupPosition).getPrice());
        childViewHolder.tvDdbz.setText(data.get(groupPosition).getNote());
        childViewHolder.tvOrderNum.setText(data.get(groupPosition).getOrder_num());

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

        //0-待付款 1-待接单 2-待发货/跑腿取餐 3-待收货 4-已收货  10-交易完成 11-交易关闭（会员取消）
        // 12-交易关闭（客服取消） 13-交易关闭(运营后台取消) 14-交易关闭（支付超时自动关闭）'
        String state = data.get(groupPosition).getState();

        //1-外卖配送 2-商家配送 3-到店自提
        String distrib_mode = data.get(groupPosition).getDistrib_mode();

        if (state.equals("0")) {//待付款

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);

        } else if (state.equals("1")) {//待接单

            childViewHolder.llBtnState.setVisibility(View.GONE);
            childViewHolder.llReceivingState.setVisibility(View.VISIBLE);

        } else if (state.equals("2")) {//待发货/跑腿取餐

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.VISIBLE);

            if (distrib_mode.equals("1")) {//外卖配送

                childViewHolder.tvRightButton.setVisibility(View.VISIBLE);
                childViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
                childViewHolder.tvLeftButton.setVisibility(View.GONE);

                childViewHolder.tvRightButton.setTag("0");
                childViewHolder.tvRightButton.setText("打印餐单");
                childViewHolder.tvCenterButton.setTag("1");
                childViewHolder.tvCenterButton.setText("取消订单");


            } else if (distrib_mode.equals("2")) {//商家配送

                childViewHolder.tvRightButton.setVisibility(View.VISIBLE);
                childViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
                childViewHolder.tvLeftButton.setVisibility(View.VISIBLE);
                childViewHolder.tvRightButton.setText("打印餐单");
                childViewHolder.tvRightButton.setTag("0");
                childViewHolder.tvCenterButton.setText("确认发货");
                childViewHolder.tvCenterButton.setTag("2");
                childViewHolder.tvLeftButton.setText("取消订单");
                childViewHolder.tvLeftButton.setTag("1");

            } else {//到店自取

                childViewHolder.tvRightButton.setVisibility(View.VISIBLE);
                childViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
                childViewHolder.tvLeftButton.setVisibility(View.VISIBLE);
                childViewHolder.tvRightButton.setText("打印餐单");
                childViewHolder.tvRightButton.setTag("0");
                childViewHolder.tvCenterButton.setText("确认发货");
                childViewHolder.tvCenterButton.setTag("2");
                childViewHolder.tvLeftButton.setText("取消订单");
                childViewHolder.tvLeftButton.setTag("1");
            }

        } else if (state.equals("3")) {//待收货

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.VISIBLE);

            if (distrib_mode.equals("1")) {//外卖配送

                childViewHolder.tvRightButton.setVisibility(View.VISIBLE);
                childViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
                childViewHolder.tvLeftButton.setVisibility(View.GONE);

                childViewHolder.tvRightButton.setText("打印餐单");
                childViewHolder.tvRightButton.setTag("0");
                childViewHolder.tvCenterButton.setText("取消订单");
                childViewHolder.tvCenterButton.setTag("1");


            } else if (distrib_mode.equals("2")) {//商家配送

                childViewHolder.tvRightButton.setVisibility(View.VISIBLE);
                childViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
                childViewHolder.tvLeftButton.setVisibility(View.VISIBLE);
                childViewHolder.tvRightButton.setText("打印餐单");
                childViewHolder.tvRightButton.setTag("0");
                childViewHolder.tvCenterButton.setText("确认收货");
                childViewHolder.tvCenterButton.setTag("3");
                childViewHolder.tvLeftButton.setText("取消订单");
                childViewHolder.tvLeftButton.setTag("1");

            } else {//到店自取

                childViewHolder.tvRightButton.setVisibility(View.GONE);
                childViewHolder.tvCenterButton.setVisibility(View.GONE);
                childViewHolder.tvLeftButton.setVisibility(View.GONE);
            }

        } else if (state.equals("4")) {//已收货

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);


        } else if (state.equals("10")) {//交易完成

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);


        } else if (state.equals("11")) {//交易关闭（会员取消）

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);


        } else if (state.equals("12")) {//交易关闭（客服取消）

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);


        } else if (state.equals("13")) {//交易关闭(运营后台取消)

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);


        } else {//交易关闭（支付超时自动关闭）

            childViewHolder.llReceivingState.setVisibility(View.GONE);
            childViewHolder.llBtnState.setVisibility(View.GONE);
        }

        List<WaitDisposeBean.ListBean.GoodsBean> goods = data.get(groupPosition).getGoods();
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

        //设置接单的点击事件
        childViewHolder.commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onButtonClickLitener != null) {

                    onButtonClickLitener.buttonClickLitener(v, childPosition, data.get(groupPosition).getId());
                }
            }
        });

        //设置拒绝接单的点击事件
        childViewHolder.refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onRefuseButtonClickLitener != null) {

                    onRefuseButtonClickLitener.refuseButtonClickLitener(v, childPosition, data.get(groupPosition).getId());
                }
            }
        });


        //左
        childViewHolder.tvLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onLeftButtonStateClickLitener != null) {

                    onLeftButtonStateClickLitener.leftButtonStateLitener(v, groupPosition, data.get(groupPosition).getId());
                }
            }
        });

        //中
        childViewHolder.tvCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCenterButtonStateClickLitener != null) {

                    onCenterButtonStateClickLitener.centerButtonStateLitener(v, groupPosition, data.get(groupPosition).getId());
                }

            }
        });

        //右
        childViewHolder.tvRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightButtonStateClickLitener != null) {

                    onRightButtonStateClickLitener.rightButtonStateLitener(v, groupPosition, data.get(groupPosition).getId());
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
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.tv_order_create)
        TextView tvOrderCreate;
        @BindView(R.id.tv_pay_type)
        TextView tvPayType;
        @BindView(R.id.tv_left_button)
        TextView tvLeftButton;
        @BindView(R.id.tv_center_button)
        TextView tvCenterButton;
        @BindView(R.id.tv_right_button)
        TextView tvRightButton;
        @BindView(R.id.ll_btn_state)
        LinearLayout llBtnState;
        @BindView(R.id.refuse_btn)
        Button refuseBtn;
        @BindView(R.id.commit_btn)
        Button commitBtn;
        @BindView(R.id.ll_receiving_state)
        LinearLayout llReceivingState;
        @BindView(R.id.ll_detail)
        LinearLayout llDetail;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
