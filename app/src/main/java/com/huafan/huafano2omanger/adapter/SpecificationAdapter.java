package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.SpecificationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by caishen on 2017/12/25.
 * by--添加商品规格的适配器
 */

public class SpecificationAdapter extends BaseAdapter {
    private final FragmentActivity mContext;
    private final List<SpecificationBean> data;
    private OnitemDeleteClickListener onitemDeleteClickListener;

    public SpecificationAdapter(FragmentActivity activity, List<SpecificationBean> arrData) {
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
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_specification, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText("规格："+data.get(position).getGoods_spec());
        viewHolder.tvPrice.setText("价格：¥" + data.get(position).getO_spec_price());
        viewHolder.tvRlPrice.setText("让利价：¥" + data.get(position).getSpec_price());
        viewHolder.tvKcNum.setText("库存：" + data.get(position).getSpec_stock());

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
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_rl_price)
        TextView tvRlPrice;
        @BindView(R.id.tv_kc_num)
        TextView tvKcNum;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
