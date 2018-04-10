package com.huafan.huafano2omanger.view.fragment.pending;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.HomeFragmentAdapter;
import com.huafan.huafano2omanger.event.OrderWaitBackEvent;
import com.huafan.huafano2omanger.event.WaitDisposeEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.fragment.pending.waitdispose.WaitDisposeFragment;
import com.huafan.huafano2omanger.view.fragment.pending.waitrebund.WaitRebundFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by caishen on 2017/12/19.
 * by--待处理
 */

public class PendingFragment extends KFragment<IPendingView, IPendingPrenter>
        implements NormalTopBar.normalTopClickListener {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.tab_hometitle)
    TabLayout tabHometitle;
    @BindView(R.id.vp_homepager)
    ViewPager vpHomepager;
    private ArrayList<Fragment> list_fragment;
    private ArrayList<String> list_title;
    private HomeFragmentAdapter fAdapter;

    @Override
    public IPendingPrenter createPresenter() {

        return new IPendingPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_pending;
    }

    @Override
    public void initData() {


    }

    /**
     * 刷新数量
     *
     * @param waitDisposeEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WaitDisposeEvent waitDisposeEvent) {

        String count = waitDisposeEvent.getCount();
        String tag = waitDisposeEvent.getTag();

        if (tag.equals("0")) {
            list_title.set(0, "待处理订单" + "   (" + count + ")");
        } else {
            list_title.set(1, "待处理退款" + "   (" + count + ")");
        }

        if (fAdapter != null) {
            fAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("待处理订单");
        normalTop.setTitleTextColor(Color.WHITE);
        normalTop.setBackgroundResource(R.drawable.app_statusbar_bg);
        Utils.setStatusBar(getActivity(), 0, false);
        normalTop.getLeftImage().setVisibility(View.GONE);
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setTopClickListener(this);

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(WaitDisposeFragment.newIntence("1"));//待处理订单
        list_fragment.add(WaitRebundFragment.newIntence("0"));//待处理退款

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("待处理订单");
        list_title.add("待处理退款");

        //设置TabLayout的模式
        tabHometitle.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(0)));
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(1)));

        fAdapter = new HomeFragmentAdapter(getChildFragmentManager(),
                list_fragment, list_title);

        //viewpager加载adapter
        vpHomepager.setAdapter(fAdapter);
        tabHometitle.setupWithViewPager(vpHomepager);

        //默认选中第1个页面
        tabHometitle.getTabAt(0).select();
    }

    public static KFragment newInstance(String str) {
        PendingFragment pendingFragment = new PendingFragment();
        Bundle bundle = new Bundle();
        pendingFragment.setArguments(bundle);
        return pendingFragment;
    }

    @Override
    public void onLeftClick(View view) {

        EventBus.getDefault().post(new OrderWaitBackEvent());
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onTitleClick(View view) {

    }
}
