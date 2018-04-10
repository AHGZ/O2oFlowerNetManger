package com.huafan.huafano2omanger.view.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.ShopGridViewAdapter;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.fragment.groupon.GrouponActivity;
import com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.CashManagementActivity;
import com.huafan.huafano2omanger.view.fragment.shop.evaluate.EvaluateMangerActivity;
import com.huafan.huafano2omanger.view.fragment.shop.financingsituation.FinancingSituationActivity;
import com.huafan.huafano2omanger.view.fragment.shop.fullmanagement.FullManagementActivity;
import com.huafan.huafano2omanger.view.fragment.shop.messagemanagement.MessageManagementActivity;
import com.huafan.huafano2omanger.view.fragment.shop.shop_refund.ShopRefundMangerActivity;
import com.huafan.huafano2omanger.view.fragment.shop.shopmanger.ShopMangerActivity;
import com.huafan.huafano2omanger.view.fragment.shop.shoporder.ShopOrderMangerActivity;
import com.huafan.huafano2omanger.view.fragment.shop.shopunit.ShopUnitActivity;
import com.huafan.huafano2omanger.view.fragment.shop.sortmanagement.SortManagementActivity;
import com.huafan.huafano2omanger.view.fragment.shop.teamexamine.TeamExamineActivity;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by caishen on 2017/12/19.
 * by--门店
 */

public class ShopFragment extends KFragment<IShopView, IShopPrenter> implements AdapterView.OnItemClickListener {
    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //绑定门店管理gradview
    @BindView(R.id.shop_gridView)
    GridView shopGridView;
    Unbinder unbinder;

    @Override
    public IShopPrenter createPresenter() {
        return new IShopPrenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("店铺管理");
        normalTop.getLeftImage().setVisibility(View.GONE);

        //给gridview设置适配器
        ShopGridViewAdapter shopGridViewAdapter = new ShopGridViewAdapter(getActivity());
        shopGridView.setAdapter(shopGridViewAdapter);
        shopGridView.setOnItemClickListener(this);
    }

    public static KFragment newInstance() {
        ShopFragment shopFragment = new ShopFragment();
        Bundle bundle = new Bundle();
        shopFragment.setArguments(bundle);
        return shopFragment;
    }

    //设置gridview的item点击事件，及每个界面的接入点
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            //分类管理入口：
            case 0:
                startActivity(new Intent(getActivity(), SortManagementActivity.class));
                break;
            //商品管理入口：
            case 1:

                Intent intent = new Intent(getActivity(), ShopMangerActivity.class);
                intent.putExtra("cate_id", "0");
                startActivity(intent);

                break;
            //评价管理入口：
            case 2:
                startActivity(new Intent(getActivity(), EvaluateMangerActivity.class));
                break;
            //商品单位入口：
            case 3:
                startActivity(new Intent(getActivity(), ShopUnitActivity.class));
                break;
            //订单管理入口：
            case 4:

                startActivity(new Intent(getActivity(), ShopOrderMangerActivity.class));

                break;
            //退款管理入口：
            case 5:

                startActivity(new Intent(getActivity(), ShopRefundMangerActivity.class));

                break;
            //团购管理入口：
            case 6:

                intent = new Intent(getActivity(), GrouponActivity.class);
                intent.putExtra("tag", "0");
                startActivity(intent);

                break;
            //团购核销入口：
            case 7:
                startActivity(new Intent(getActivity(), TeamExamineActivity.class));
                break;
            //财务概况入口：
            case 8:
                startActivity(new Intent(getActivity(), FinancingSituationActivity.class));
                break;
            //满返管理入口：
            case 9:
                startActivity(new Intent(getActivity(), FullManagementActivity.class));
                break;
            //消息管理入口：
            case 10:
                startActivity(new Intent(getActivity(), MessageManagementActivity.class));
                break;
            //提现管理入口：
            case 11:
                startActivity(new Intent(getActivity(), CashManagementActivity.class));
                break;
        }
    }
}
