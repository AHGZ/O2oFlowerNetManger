package com.huafan.huafano2omanger.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.WeekBean;
import com.huafan.huafano2omanger.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caishen on 2017/12/25.
 * by--星期的适配器
 */

public class WeekAdapter extends BaseAdapter {
    private final FragmentActivity mContext;
    private final List<WeekBean> data;
    private OnCheckClickLitener onCheckClickLitener;

    public WeekAdapter(FragmentActivity activity, List<WeekBean> weekData) {
        this.mContext = activity;
        this.data = weekData;
    }

    /***
     * 选中
     * @param onCheckClickLitener
     */
    public void setOnCheckClickLitener(OnCheckClickLitener onCheckClickLitener) {
        this.onCheckClickLitener = onCheckClickLitener;
    }

    public interface OnCheckClickLitener {

        void CheckClickLitener(View view, boolean isChoose, int position);
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

            convertView = View.inflate(mContext, R.layout.item_week, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        if (position == 0) {
            viewHolder.tvName.setText("星期一");
        } else if (position == 1) {
            viewHolder.tvName.setText("星期二");
        } else if (position == 2) {
            viewHolder.tvName.setText("星期三");
        } else if (position == 3) {
            viewHolder.tvName.setText("星期四");
        } else if (position == 4) {
            viewHolder.tvName.setText("星期五");
        } else if (position == 5) {
            viewHolder.tvName.setText("星期六");
        } else {
            viewHolder.tvName.setText("星期日");
        }

        //扩大点击面积
        UIUtils.setTouchDelegate(viewHolder.checkbox, 50);

        //设置cb的选中点击事件
        viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                onCheckClickLitener.CheckClickLitener(buttonView, isChecked, position);
            }
        });


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.checkbox)
        CheckBox checkbox;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
