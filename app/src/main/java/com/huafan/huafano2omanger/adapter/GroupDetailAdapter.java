package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.DetailGroupBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2018/1/15.
 * by--团单内容适配器
 */

public class GroupDetailAdapter extends BaseAdapter {

    private final FragmentActivity mContext;
    private final List<DetailGroupBean.GroupContentBean> data;
    private OnitemDeleteClickListener onitemDeleteClickListener;

    public GroupDetailAdapter(FragmentActivity activity, List<DetailGroupBean.GroupContentBean> arrData) {

        this.mContext = activity;
        this.data = arrData;

    }

    /**
     * 删除item
     *
     * @param onitemDeleteClickListener
     */
    public void setOnitemDeleteClickListener(OnitemDeleteClickListener onitemDeleteClickListener) {
        this.onitemDeleteClickListener = onitemDeleteClickListener;
    }


    public interface OnitemDeleteClickListener {

        void itemDeleteClick(View view, int position);
    }

    @Override
    public int getCount() {
        return data.size() == 0 ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_group_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        viewHolder.tvName.setText(data.get(position).getGoods_name());
        viewHolder.tvNumber.setText(data.get(position).getNum());
        viewHolder.tvPrice.setText(data.get(position).getPrice()+"");

        //设置删除的点击事件
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onitemDeleteClickListener != null) {

                    onitemDeleteClickListener.itemDeleteClick(v, position);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
