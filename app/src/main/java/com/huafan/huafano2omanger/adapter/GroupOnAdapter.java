package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.GroupOnBean;
import com.huafan.huafano2omanger.utils.DateUtils;
import com.huafan.huafano2omanger.utils.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by caishen on 2018/1/15.
 * by--团购列表数据
 */

public class GroupOnAdapter extends BaseExpandableListAdapter {
    private final FragmentActivity mContext;
    private final List<GroupOnBean.ListBean> data;
    private OnEvulateShowClickLitener onEvulateShowClickLitener;
    private OnCheckIssaleClickLitener onCheckIssaleClickLitener;

    public GroupOnAdapter(FragmentActivity activity, List<GroupOnBean.ListBean> list1) {
        this.mContext = activity;
        this.data = list1;
    }

    public void setOnEvulateShowClickLitener(OnEvulateShowClickLitener onEvulateShowClickLitener) {
        this.onEvulateShowClickLitener = onEvulateShowClickLitener;
    }

    public interface OnEvulateShowClickLitener {

        void evulateClickLitener(View view, int position, boolean isShow);
    }


    public void setOnCheckIssaleClickLitener(OnCheckIssaleClickLitener onCheckIssaleClickLitener) {
        this.onCheckIssaleClickLitener = onCheckIssaleClickLitener;
    }

    public interface OnCheckIssaleClickLitener {

        void onCheckIsSaleClickLitener(View view, int position, boolean ischeck);
    }


    @Override
    public int getGroupCount() {
        return data.size() == 0 ? 0 : data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
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

        GroupViewHolder groupViewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_groupon_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);

        } else {

            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        DateUtils dateUtils = new DateUtils();
        //设置数据
        if (data.get(groupPosition).getFile_path() != null) {

            String file_path = ApiUrlConstant.API_IMG_URL + data.get(groupPosition).getFile_path();
            new GlideImageLoader().displayImage(mContext, file_path, groupViewHolder.ivImg);
        }

        groupViewHolder.tvMerchandiseName.setText(data.get(groupPosition).getTitle());
        groupViewHolder.tvPrice.setText("¥" + data.get(groupPosition).getPrice());
        groupViewHolder.ctNum.setText(data.get(groupPosition).getTotal_num());
        groupViewHolder.tvDate.setText("有效期：" + dateUtils.timetodates(data.get(groupPosition).getStarttime())
                + "-" + dateUtils.timetodates(data.get(groupPosition).getEndtime()));

        String appro_state = data.get(groupPosition).getAppro_state();
        if (appro_state.equals("1")) {//待审核
            groupViewHolder.tvState.setText("进行中");
        } else if (appro_state.equals("2")) {//已通过
            groupViewHolder.tvState.setText("已通过");
        } else {//未通过
            groupViewHolder.tvState.setText("未通过");
        }

        boolean isshow = data.get(groupPosition).getIsshow();
        if (isshow == true) {
            groupViewHolder.ivShow.setImageResource(R.drawable.evulate_hide);
        } else {
            groupViewHolder.ivShow.setImageResource(R.drawable.evluate_show);
        }

        //设置展开/收起事件
        groupViewHolder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onEvulateShowClickLitener != null) {

                    if (data.get(groupPosition).getIsshow() == false) {//隐藏
                        data.get(groupPosition).setIsshow(true);
                        groupViewHolder.ivShow.setImageResource(R.drawable.evluate_show);
                    } else {//展开
                        data.get(groupPosition).setIsshow(false);
                        groupViewHolder.ivShow.setImageResource(R.drawable.evulate_hide);
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

            convertView = View.inflate(mContext, R.layout.item_groupon_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        } else {

            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //设置数据
        DateUtils dateUtils = new DateUtils();
        childViewHolder.tvCreate.setText(data.get(groupPosition).getCreated());
        childViewHolder.tvNeotec.setText("已售出：" + data.get(groupPosition).getSold_num());
        childViewHolder.tvNoSpending.setText("未消费：" + data.get(groupPosition).getUnconsumed());
        childViewHolder.tvSpending.setText("已消费：" + data.get(groupPosition).getUsed_num());
        childViewHolder.tvRefund.setText("已退款：" + data.get(groupPosition).getRefund_num());
        childViewHolder.tvCreate.setText(dateUtils.timetodate(data.get(groupPosition).getCreated()));
        String appro_state = data.get(groupPosition).getAppro_state();

        if (appro_state.equals("1")) {//待审核
            childViewHolder.tvState.setText("待审核");
            childViewHolder.cbSale.setVisibility(View.GONE);
        } else if (appro_state.equals("2")) {//已通过
            childViewHolder.tvState.setText("已通过");
            childViewHolder.cbSale.setVisibility(View.VISIBLE);
        } else {//未通过
            childViewHolder.tvState.setText("未通过");
            childViewHolder.cbSale.setVisibility(View.GONE);
        }

        //是否上架
        String is_sale = data.get(groupPosition).getIs_sale();
        if (is_sale.equals("1")) {
            childViewHolder.cbSale.setChecked(true);
        } else {
            childViewHolder.cbSale.setChecked(false);
        }

        //设置是否上架的点击事件
        childViewHolder.cbSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isChecked = true;
                if (is_sale.equals("0")) {
                    isChecked = true;
                    data.get(groupPosition).setIs_sale("1");
                } else {
                    isChecked = false;
                    data.get(groupPosition).setIs_sale("0");
                }
                if (onCheckIssaleClickLitener != null) {
                    onCheckIssaleClickLitener.onCheckIsSaleClickLitener(v, groupPosition, isChecked);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_merchandise_name)
        TextView tvMerchandiseName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.ct_num)
        TextView ctNum;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.ll_title)
        LinearLayout llTitle;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.iv_show)
        ImageView ivShow;
        @BindView(R.id.ll_one)
        LinearLayout llOne;
        @BindView(R.id.ll_detail)
        LinearLayout llDetail;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.tv_neotec)
        TextView tvNeotec;
        @BindView(R.id.tv_no_spending)
        TextView tvNoSpending;
        @BindView(R.id.tv_spending)
        TextView tvSpending;
        @BindView(R.id.tv_refund)
        TextView tvRefund;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_create)
        TextView tvCreate;
        @BindView(R.id.cb_sale)
        CheckBox cbSale;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
