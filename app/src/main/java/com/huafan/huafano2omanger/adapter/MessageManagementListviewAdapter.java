package com.huafan.huafano2omanger.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.MessaTypeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heguozhong on 2017/12/27/027.
 * 消息管理适配器
 */

public class MessageManagementListviewAdapter extends BaseAdapter {
    private final List<MessaTypeBean.ListsBean> data;
    private Context context;

    public MessageManagementListviewAdapter(Context context, List<MessaTypeBean.ListsBean> lists) {
        this.context = context;
        this.data = lists;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.activity_message_management_listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        String title = data.get(position).getTitle() == null ? "" : data.get(position).getTitle();
        String is_read = data.get(position).getIs_read();
        String content = data.get(position).getContent() == null ? "" : data.get(position).getContent();
        String create = data.get(position).getCreated() == null ? "" : data.get(position).getCreated();

        //消息是否已读（0未读，1已读）
        if (is_read.equals("1")) {
            viewHolder.messageManagementItemImg.setBackgroundResource(R.drawable.message_icon_yck);
        } else {
            viewHolder.messageManagementItemImg.setBackgroundResource(R.drawable.message_icon_new);
        }

        viewHolder.messageManagementItemText.setText(title);
        viewHolder.messageManagementItemDate.setText(create);
        viewHolder.messageManagementItemMessage.setText(content);


        return convertView;
    }

    static class ViewHolder {
        //消息日期
        @BindView(R.id.message_management_item_date)
        TextView messageManagementItemDate;
        //消息状态（已读为灰色图标，未读为红色图标）
        @BindView(R.id.message_management_item_img)
        ImageView messageManagementItemImg;
        //消息标题
        @BindView(R.id.message_management_item_title)
        TextView messageManagementItemText;
        //消息内容
        @BindView(R.id.message_management_item_message)
        TextView messageManagementItemMessage;

        ViewHolder(View view) {

            ButterKnife.bind(this, view);
        }
    }
}
