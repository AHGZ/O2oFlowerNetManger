package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.EvaluateBean;
import com.huafan.huafano2omanger.utils.GlideImageLoader;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.RatingBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;

/**
 * Created by caishen on 2017/12/26.
 * by--评价管理适配器
 */

public class EvluateAdapter extends BaseExpandableListAdapter {
    private final FragmentActivity mContext;
    private final List<EvaluateBean.ListBean> data;
    private final String type;
    private OnEvulateShowClickLitener onEvulateShowClickLitener;
    private OnReplyClickLitener onReplyClickLitener;
    private ImageView img;

    public EvluateAdapter(FragmentActivity activity, List<EvaluateBean.ListBean> evulateMangerBeen, String type) {
        this.mContext = activity;
        this.data = evulateMangerBeen;
        this.type = type;
    }

    public void setOnEvulateShowClickLitener(OnEvulateShowClickLitener onEvulateShowClickLitener) {
        this.onEvulateShowClickLitener = onEvulateShowClickLitener;
    }

    public void setOnReplyClickLitener(OnReplyClickLitener onReplyClickLitener) {
        this.onReplyClickLitener = onReplyClickLitener;
    }

    public interface OnEvulateShowClickLitener {

        void evulateClickLitener(View view, int position, boolean isShow);
    }

    public interface OnReplyClickLitener {

        void replyClickLitener(String reply, String eval_id, int position);
    }

    @Override
    public int getGroupCount() {
        return data.size();
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

            convertView = View.inflate(mContext, R.layout.item_evluate_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);

        } else {

            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        new GlideImageLoader().displayImage(mContext, ApiUrlConstant.API_IMG_URL
                + data.get(groupPosition).getImgs(), groupViewHolder.ivImg);
        String score = data.get(groupPosition).getScore();
        String distrib_service_score = data.get(groupPosition).getDistrib_service_score();

        if (type.equals("0")) {//订单评价

            groupViewHolder.distribution.setVisibility(View.GONE);

        } else {//商家评价

            groupViewHolder.distribution.setVisibility(View.VISIBLE);
            groupViewHolder.msDistribution.setStar(Integer.parseInt(distrib_service_score), false);
        }

        //设置数据
        groupViewHolder.tvDate.setText(data.get(groupPosition).getCreated());
        groupViewHolder.tvFoodName.setText("菜品名：" + data.get(groupPosition).getName());
        groupViewHolder.tvUser.setText("评价人：" + data.get(groupPosition).getNickname());
        groupViewHolder.msFood.setStar(Integer.parseInt(score), false);
        groupViewHolder.msFood.setEnabled(false);

        boolean isshow = data.get(groupPosition).getIsshow();
        if (isshow == true) {
            groupViewHolder.ivShow.setImageResource(R.drawable.evulate_hide);
        } else {
            groupViewHolder.ivShow.setImageResource(R.drawable.evluate_show);
        }

        //设置点击事件
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

            convertView = View.inflate(mContext, R.layout.item_evluate_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        } else {

            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //是否显示订单编号
        if (type.equals("0")) {//订单

            childViewHolder.orderNum.setVisibility(View.VISIBLE);
            childViewHolder.orderNum.setText("订单编号:" + data.get(groupPosition).getOrder_num());

        } else {//商家

            childViewHolder.orderNum.setVisibility(View.GONE);
        }

        //设置数据
        childViewHolder.llPhotos.removeAllViews();
        childViewHolder.tvUserEvluate.setText(data.get(groupPosition).getNickname());
        String content = data.get(groupPosition).getContent();
        childViewHolder.tvEluate.setText(content);

        String reply = data.get(groupPosition).getReply();
        //是否显示回复数据
        if (TextUtils.isEmpty(reply)) {

            childViewHolder.llReply.setVisibility(View.GONE);
            childViewHolder.tvReply.setVisibility(View.VISIBLE);

        } else {

            childViewHolder.llReply.setVisibility(View.VISIBLE);
            childViewHolder.tvReply.setVisibility(View.GONE);
        }

        childViewHolder.tvUserEvluate.setText(reply.equals("") ? "" : reply);

        //设置商家回复的点击事件
        childViewHolder.tvReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = data.get(groupPosition).getId();
                showAlertEdittextDialog("回复评价", "确定", "取消", id, childPosition);
            }
        });

        List<String> imgurl = data.get(groupPosition).getImgurl();

        //设置商品的图片
        if (imgurl != null && imgurl.size() > 0) {

            childViewHolder.hsPhotos.setVisibility(View.VISIBLE);
            for (int i = 0; i < imgurl.size(); i++) {

                //使用inflate获取phtoview布局里面的myGallery视窗
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_detail_item_photos,
                        childViewHolder.llPhotos, false);
                img = (ImageView) view.findViewById(R.id.imageview_1);
                GlideImageLoader glideImageLoader = new GlideImageLoader();
                if (imgurl.get(i) != null) {
                    glideImageLoader.displayImage(mContext, ApiUrlConstant.API_IMG_URL + imgurl.get(i), img);
                } else {
                    glideImageLoader.displayImage(mContext, R.mipmap.default_image, img);
                }
                childViewHolder.llPhotos.addView(view);  //把添加过资源后的view视图重新添加到myGallery中

                //设置图片的点击事
                int finalI = i;
                img.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        ArrayList<String> imgUrls = new ArrayList<>();
                        for (int i = 0; i < imgurl.size(); i++) {
                            String s = ApiUrlConstant.API_IMG_URL + imgurl.get(i);
                            imgUrls.add(s);
                        }

                        mContext.startActivity(BGAPhotoPreviewActivity.newIntent(mContext, null, imgUrls, finalI));
                    }
                });
            }

        } else {

            childViewHolder.hsPhotos.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertEdittextDialog(String title, String positive, String negative, String id, int childPosition) {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(mContext);
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
            @Override
            public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                if (onReplyClickLitener != null) {

                    onReplyClickLitener.replyClickLitener(trim, id, childPosition);
                }
                alertDialog.hide();
            }
        })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

        alertDialog.et_name.setHint("请输入回复");
        alertDialog.remove(alertDialog.et_price);
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.show();
    }

    static class ChildViewHolder {
        @BindView(R.id.order_num)
        TextView orderNum;
        @BindView(R.id.tv_reply)
        TextView tvReply;
        @BindView(R.id.tv_eluate)
        TextView tvEluate;
        @BindView(R.id.ll_photos)
        LinearLayout llPhotos;
        @BindView(R.id.hs_photos)
        HorizontalScrollView hsPhotos;
        @BindView(R.id.tv_user_evluate)
        TextView tvUserEvluate;
        @BindView(R.id.ll_reply)
        LinearLayout llReply;

        ChildViewHolder(View view) {

            ButterKnife.bind(this, view);
        }
    }

    static class GroupViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.ms_food)
        RatingBarView msFood;
        @BindView(R.id.ms_distribution)
        RatingBarView msDistribution;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_user)
        TextView tvUser;
        @BindView(R.id.tv_food_name)
        TextView tvFoodName;
        @BindView(R.id.iv_show)
        ImageView ivShow;
        @BindView(R.id.ll_distribution)
        LinearLayout distribution;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
