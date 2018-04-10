package com.huafan.huafano2omanger.view.fragment.groupon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.HomeFragmentAdapter;
import com.huafan.huafano2omanger.event.GroupEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.fragment.groupon.grouping.GroupingFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by caishen on 2017/12/19.
 * by--团购
 */

public class GrouponFragment extends KFragment<IGrouponView, IGrouponPrenter>
        implements NormalTopBar.normalTopClickListener {

    @BindView(R.id.tab_hometitle)
    TabLayout tabHometitle;
    @BindView(R.id.vp_homepager)
    ViewPager vpHomepager;
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    private ArrayList<Fragment> list_fragment;
    private ArrayList<String> list_title;
    private String tag = "";//0-需要返回键 1-不需要返回键
    private HomeFragmentAdapter fAdapter;

    @Override
    public IGrouponPrenter createPresenter() {
        return new IGrouponPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_groupon;
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
    public void onEvent(GroupEvent waitDisposeEvent) {

        int count = waitDisposeEvent.getCount();
        String tag = waitDisposeEvent.getTag();

        if (tag.equals("0")) {
            list_title.set(0, "进行中" + "   (" + count + ")");
        } else {
            list_title.set(1, "已结束" + "   (" + count + ")");
        }

        if (fAdapter != null) {
            fAdapter.notifyDataSetChanged();
        }
    }

    public static KFragment newInstance(String tag) {
        GrouponFragment grouponFragment = new GrouponFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        grouponFragment.setArguments(bundle);
        return grouponFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        tag = arguments.getString("tag");
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("团购");
        normalTop.setTitleTextColor(Color.WHITE);
        normalTop.setBackgroundResource(R.drawable.app_statusbar_bg);
        Utils.setStatusBar(getActivity(), 0, false);

        //0-需要返回键 1-不需要返回键
        if (tag.equals("0")) {
            normalTop.getLeftImage().setVisibility(View.VISIBLE);
        } else {
            normalTop.getLeftImage().setVisibility(View.GONE);
        }


        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setTopClickListener(this);

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(GroupingFragment.newIntence("1"));//进行中
        list_fragment.add(GroupingFragment.newIntence("2"));//已结束

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("进行中");
        list_title.add("已结束");

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
