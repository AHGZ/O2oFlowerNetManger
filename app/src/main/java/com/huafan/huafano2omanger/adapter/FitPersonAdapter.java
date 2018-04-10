package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2018/1/15.
 * by--添加团购适合人数的适配器
 */

public class FitPersonAdapter extends BaseAdapter {
    private final FragmentActivity mContext;
    private int indexPosition;
    private OnCheckedLitener onCheckedLitener;

    public FitPersonAdapter(FragmentActivity activity) {

        this.mContext = activity;
    }

    public void setOnCheckedLitener(OnCheckedLitener onCheckedLitener) {
        this.onCheckedLitener = onCheckedLitener;
    }

    public interface OnCheckedLitener {

        void onCheckLitener(String name, int position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    public void setIndexPosition(int position) {

        indexPosition = position;
        notifyDataSetChanged();
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

            convertView = View.inflate(mContext, R.layout.item_group_detail_person, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        if (position == 0) {
            viewHolder.tvName.setText("单人餐");
        } else if (position == 1) {
            viewHolder.tvName.setText("双人餐");
        } else if (position == 2) {
            viewHolder.tvName.setText("3-4人餐");
        } else if (position == 3) {
            viewHolder.tvName.setText("5-10人餐");
        } else {
            viewHolder.tvName.setText("10人以上餐");
        }


        viewHolder.cbIstrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (onCheckedLitener != null) {

                        onCheckedLitener.onCheckLitener(viewHolder.tvName.getText().toString(), position);
                        setIndexPosition(position);
                    }
                }
            }
        });

        //设置是否选中
        if (indexPosition == position) {//选中
            viewHolder.cbIstrue.setChecked(true);
        } else {//未选中
            viewHolder.cbIstrue.setChecked(false);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.cb_istrue)
        CheckBox cbIstrue;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
