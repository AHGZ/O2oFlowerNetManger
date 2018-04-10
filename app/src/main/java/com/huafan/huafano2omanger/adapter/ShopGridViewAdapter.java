package com.huafan.huafano2omanger.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.huafan.huafano2omanger.utils.ConvertUtils.getResources;

/**
 * Created by heguozhong on 2017/12/23/023.
 * 门店主页网格状布局适配器
 */

public class ShopGridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> shopName;
    private ArrayList<Integer> shopIcon;

    public ShopGridViewAdapter(Context context) {
        this.context = context;
        shopName = new ArrayList<>();
        shopIcon = new ArrayList<>();
        shopName.add("分类管理");
        shopName.add("商品管理");
        shopName.add("评价管理");
        shopName.add("商品单位");
        shopName.add("订单管理");
        shopName.add("退款管理");
        shopName.add("团购管理");
        shopName.add("团购核销");
        shopName.add("财务概况");
        shopName.add("满返管理");
        shopName.add("消息管理");
        shopName.add("提现管理");
        shopIcon.add(R.mipmap.icon_flgl);
        shopIcon.add(R.mipmap.icon_spgl);
        shopIcon.add(R.mipmap.icon_sppj);
        shopIcon.add(R.mipmap.icon_spdw);
        shopIcon.add(R.mipmap.icon_ddgl);
        shopIcon.add(R.mipmap.icon_tkgl);
        shopIcon.add(R.mipmap.icon_tggl);
        shopIcon.add(R.mipmap.icon_tghx);
        shopIcon.add(R.mipmap.icon_cwzk);
        shopIcon.add(R.mipmap.icon_mfgl);
        shopIcon.add(R.mipmap.icon_xxgl);
        shopIcon.add(R.mipmap.icon_txgl);
    }

    @Override
    public int getCount() {
        return shopIcon.size();
    }

    @Override
    public Object getItem(int position) {
        return shopIcon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.fragment_shop__gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给每个imageview动态设置高度
        int height = screenHeight();
        ViewGroup.LayoutParams layoutParams = viewHolder.shopItemImg.getLayoutParams();
        layoutParams.height=height/24;
        viewHolder.shopItemImg.setLayoutParams(layoutParams);
        viewHolder.shopItemText.setText(shopName.get(position));
        viewHolder.shopItemImg.setImageResource(shopIcon.get(position));
        return convertView;
    }
    //获取屏幕高度
    private int screenHeight() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        return heightPixels;
    }

    static class ViewHolder {
        @BindView(R.id.shop_item_img)
        ImageView shopItemImg;
        @BindView(R.id.shop_item_text)
        TextView shopItemText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
