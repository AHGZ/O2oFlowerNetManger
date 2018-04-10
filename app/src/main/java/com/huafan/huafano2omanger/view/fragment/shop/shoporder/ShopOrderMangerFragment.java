package com.huafan.huafano2omanger.view.fragment.shop.shoporder;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.HomeFragmentAdapter;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.fragment.pending.waitdispose.WaitDisposeFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by caishen on 2018/1/10.
 * by--订单管理
 */

public class ShopOrderMangerFragment extends KFragment<IShopOrderMangerView, IShopOrderMangerPrenter>
        implements NormalTopBar.normalTopClickListener {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.tab_hometitle)
    TabLayout tabHometitle;
    @BindView(R.id.vp_homepager)
    ViewPager vpHomepager;
    private ArrayList<Fragment> list_fragment;
    private ArrayList<String> list_title;

    @Override
    public IShopOrderMangerPrenter createPresenter() {
        return new IShopOrderMangerPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_shoporder_manger;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("待处理");
        normalTop.setTitleTextColor(Color.WHITE);
        normalTop.setBackgroundResource(R.drawable.app_statusbar_bg);
        Utils.setStatusBar(getActivity(), 0, false);
        normalTop.setLeftImageId(R.mipmap.icon_back_white);
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setTopClickListener(this);

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(WaitDisposeFragment.newIntence("1"));//待处理
        list_fragment.add(WaitDisposeFragment.newIntence("2"));//进行中
        list_fragment.add(WaitDisposeFragment.newIntence("3"));//已完成
        list_fragment.add(WaitDisposeFragment.newIntence("4"));//已取消

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("待处理");
        list_title.add("进行中");
        list_title.add("已完成");
        list_title.add("已取消");

        //设置TabLayout的模式
        tabHometitle.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(0)));
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(1)));
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(2)));
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(3)));

        HomeFragmentAdapter fAdapter = new HomeFragmentAdapter(getActivity().getSupportFragmentManager(),
                list_fragment, list_title);

        //viewpager加载adapter
        vpHomepager.setAdapter(fAdapter);
        tabHometitle.setupWithViewPager(vpHomepager);

        tabHometitle.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置ViewPager联动
                vpHomepager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        normalTop.setTitleText("待处理");
//                        EventBus.getDefault().post(new OrderDeatailRefreshEvent());
                        break;
                    case 1:
                        normalTop.setTitleText("进行中");
//                        EventBus.getDefault().post(new OrderDeatailRefreshEvent());
                        break;
                    case 2:
                        normalTop.setTitleText("已完成");
//                        EventBus.getDefault().post(new OrderDeatailRefreshEvent());
                        break;
                    case 3:
                        normalTop.setTitleText("已取消");
//                        EventBus.getDefault().post(new OrderDeatailRefreshEvent());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //默认选中第1个页面
        tabHometitle.getTabAt(0).select();
    }

    public static KFragment newIntence() {

        ShopOrderMangerFragment shopOrderMangerFragment = new ShopOrderMangerFragment();
        Bundle bundle = new Bundle();
        shopOrderMangerFragment.setArguments(bundle);
        return shopOrderMangerFragment;
    }

    @Override
    public void onLeftClick(View view) {

        removeFragment();
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onTitleClick(View view) {

    }
}
