package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.base.HFWBaseAdapter;
import com.huafan.huafano2omanger.callback.BaseHolder;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.SelGoodsListBean;
import com.huafan.huafano2omanger.utils.GlideImageLoader;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.SwipeListLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2017/12/25.
 * by--商品管理的适配器
 */

public class ShopMangerAdapter extends HFWBaseAdapter<SelGoodsListBean.ListBean> {
    private final FragmentActivity mContext;
    private ItemDeleteClickListener itemDeleteClickListener;
    private ItemStickClickListener itemStickClickListener;
    private ItemSortClickListener itemSortClickListener;
    private OnItemClicksListener onItemClicksListener;
    private int tag;

    public ShopMangerAdapter(FragmentActivity activity, int tag) {

        this.mContext = activity;
        this.tag=tag;

    }

    @Override
    public BaseHolder<SelGoodsListBean.ListBean> onViewHolderCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopmanger, parent, false);
        return new ShopMangerViewHolder(view);
    }

    @Override
    public void onViewHolderBind(BaseHolder<SelGoodsListBean.ListBean> holder, int position) {
        SelGoodsListBean.ListBean shopMangerBean = list.get(position);
        ((ShopMangerViewHolder) holder).bindDateView(shopMangerBean, position, mContext);
    }


    public void setOnItemClicksListener(OnItemClicksListener onItemClicksListener) {
        this.onItemClicksListener = onItemClicksListener;
    }

    public interface OnItemClicksListener {

        void ItemClickLitener(View view, int position);
    }

    /**
     * 删除的点击事件
     */
    public interface ItemDeleteClickListener {

        void ItemDeleteClick(View view, int position);
    }

    /**
     * 置顶
     *
     * @param
     */
    public interface ItemStickClickListener {

        void ItemStickClick(View view, int position);
    }

    /**
     * 上下排序
     */
    public interface ItemSortClickListener {

        void ItemSortUpClick(View view, int position);

        void ItemSortDownClick(View view, int position);
    }


    public void setItemStickClickListener(ItemStickClickListener itemStickClickListener) {
        this.itemStickClickListener = itemStickClickListener;
    }

    public void setItemSortClickListener(ItemSortClickListener itemSortClickListener) {
        this.itemSortClickListener = itemSortClickListener;
    }

    public void setOnDelItemClickListener(ItemDeleteClickListener listener) {
        this.itemDeleteClickListener = listener;
    }

    class ShopMangerViewHolder extends BaseHolder<SelGoodsListBean.ListBean> {

        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.ll_delete)
        LinearLayout llDelete;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_inventory)
        TextView tvInventory;
        @BindView(R.id.iv_zd)
        ImageView ivZd;
        @BindView(R.id.iv_up)
        ImageView ivUp;
        @BindView(R.id.iv_down)
        ImageView ivDown;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.swp_layout)
        SwipeListLayout swpLayout;

        ShopMangerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindDateView(SelGoodsListBean.ListBean shopMangerBean, int position, FragmentActivity mContext) {

            if (tag==1){
                ivZd.setVisibility(View.VISIBLE);
                ivUp.setVisibility(View.VISIBLE);
                ivDown.setVisibility(View.VISIBLE);
                if (position == 0) {
                    ivZd.setVisibility(View.GONE);
                } else {
                    ivZd.setVisibility(View.VISIBLE);
                }
            }
            tvTitle.setText(shopMangerBean.getName());
            tvInventory.setText("库存：" + shopMangerBean.getStock());
            String file_path = ApiUrlConstant.API_IMG_URL + shopMangerBean.getFile_path();
            GlideImageLoader glideImageLoader = new GlideImageLoader();
            glideImageLoader.displayImage(mContext, file_path, ivImg);

            UIUtils.setTouchDelegate(ivUp, 50);
            UIUtils.setTouchDelegate(ivDown, 50);

            //删除的点击事件
            llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (itemDeleteClickListener != null) {
                        itemDeleteClickListener.ItemDeleteClick(v, position);
                    }
                }
            });

            //上移的点击事件
            ivUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (itemSortClickListener != null) {
                        itemSortClickListener.ItemSortUpClick(v, position);
                    }
                }
            });

            //下移的的点击事件
            ivDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemSortClickListener != null) {
                        itemSortClickListener.ItemSortDownClick(v, position);
                    }
                }
            });

            //置顶的点击事件
            ivZd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemStickClickListener != null) {
                        itemStickClickListener.ItemStickClick(v, position);
                    }
                }
            });

            //点击事件
            llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClicksListener != null) {
                        onItemClicksListener.ItemClickLitener(v, position);
                    }
                }
            });
        }
    }
}
