package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.utils.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2018/1/15.
 * by--上传多张图片显示适配器
 */

public class AddBannerAdapter extends BaseAdapter {
    private final FragmentActivity mContext;
    private final List<String> data;
    private final LayoutInflater inflater;
    private GlideImageLoader glideImageLoader;
    private OndeleteImgClickLitener ondeleteImgClickLitener;
    private OnItemClickLiteners onItemClickLiteners;
    /**
     * 可以动态设置最多上传几张，之后就不显示+号了，用户也无法上传了
     * 默认9张
     */
    private int maxImages = Integer.MAX_VALUE;

    public AddBannerAdapter(FragmentActivity activity, List<String> imgData) {
        this.mContext = activity;
        this.data = imgData;
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * 删除图片数据
     *
     * @param ondeleteImgClickLitener
     */
    public void setOndeleteImgClickLitener(OndeleteImgClickLitener ondeleteImgClickLitener) {
        this.ondeleteImgClickLitener = ondeleteImgClickLitener;
    }

    public interface OndeleteImgClickLitener {

        void deleteImgClickLitener(View view, int position);
    }

    public void setOnItemClickLiteners(OnItemClickLiteners onItemClickLiteners) {
        this.onItemClickLiteners = onItemClickLiteners;
    }

    public interface OnItemClickLiteners {

        void onItemClickLitener(View view, int position);
    }

    @Override
    public int getCount() {
        int count = data == null ? 1 : data.size() + 1;

        if (count >= maxImages) {

            return data.size();

        } else {

            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取最大上传张数
     *
     * @return
     */
    public int getMaxImages() {
        return maxImages;
    }

    /**
     * 设置最大上传张数
     *
     * @param maxImages
     */
    public void setMaxImages(int maxImages) {
        this.maxImages = maxImages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.index_node_uploadgridview_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //加载数据
        glideImageLoader = new GlideImageLoader();
        if (data != null && position < data.size()) {
            glideImageLoader.displayImage(mContext, ApiUrlConstant.API_IMG_URL + data.get(position), viewHolder.itemGridaImage);
            viewHolder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            if (position >= maxImages) {
                viewHolder.itemGridaImage.setVisibility(View.GONE);
                viewHolder.ivDelete.setVisibility(View.GONE);
            } else {
                glideImageLoader.displayImage(mContext, R.drawable.icon_sczp, viewHolder.itemGridaImage);
                viewHolder.itemGridaImage.setScaleType(ImageView.ScaleType.FIT_XY);
                viewHolder.ivDelete.setVisibility(View.GONE);
            }
        }

        //设置删除按钮的点击事件
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ondeleteImgClickLitener != null) {
                    ondeleteImgClickLitener.deleteImgClickLitener(v, position);
                }
            }
        });

        //设置item的点击事件
        viewHolder.itemGridaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickLiteners != null) {

                    onItemClickLiteners.onItemClickLitener(v, position);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_grida_image)
        ImageView itemGridaImage;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
