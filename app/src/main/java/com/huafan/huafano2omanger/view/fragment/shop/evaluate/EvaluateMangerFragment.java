package com.huafan.huafano2omanger.view.fragment.shop.evaluate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.HomeFragmentAdapter;
import com.huafan.huafano2omanger.event.EvaluateEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.actionsheet.OptionPicker;
import com.huafan.huafano2omanger.view.fragment.shop.evaluate.good.GoodEvaluateFragment;
import com.huafan.huafano2omanger.view.fragment.shop.evaluate.medium.MediumEvaluateFragment;
import com.huafan.huafano2omanger.view.fragment.shop.evaluate.negative.NegativeEvaluateFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by caishen on 2017/12/26.
 * by--评价管理
 */

public class EvaluateMangerFragment extends KFragment<IEvaluateMangerView, IEvaluateMangerPrenter>
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
    public IEvaluateMangerPrenter createPresenter() {

        return new IEvaluateMangerPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_evluate_manger;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("评价列表");
        normalTop.setTitleTextColor(Color.WHITE);
        normalTop.setBackgroundResource(R.drawable.app_statusbar_bg);
        Utils.setStatusBar(getActivity(), 0, false);
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setRightText("筛选");
        normalTop.setRightTextColor(Color.WHITE);
        normalTop.setTopClickListener(this);

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(GoodEvaluateFragment.newIntence());//好评
        list_fragment.add(MediumEvaluateFragment.newIntence());//中评
        list_fragment.add(NegativeEvaluateFragment.newIntence());//差评

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("好评");
        list_title.add("中评");
        list_title.add("差评");

        //设置TabLayout的模式
        tabHometitle.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(0)));
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(1)));
        tabHometitle.addTab(tabHometitle.newTab().setText(list_title.get(2)));

        HomeFragmentAdapter fAdapter = new HomeFragmentAdapter(getActivity().getSupportFragmentManager(),
                list_fragment, list_title);

        //viewpager加载adapter
        vpHomepager.setAdapter(fAdapter);
        tabHometitle.setupWithViewPager(vpHomepager);

        //默认选中第1个页面
        tabHometitle.getTabAt(0).select();
    }

    public static KFragment newIntence() {

        EvaluateMangerFragment evaluateMangerFragment = new EvaluateMangerFragment();
        Bundle bundle = new Bundle();
        evaluateMangerFragment.setArguments(bundle);
        return evaluateMangerFragment;
    }

    @Override
    public void onLeftClick(View view) {

        removeFragment();
    }

    @Override
    public void onRightClick(View view) {

        String[] options = new String[3];
        options[0] = "订单";
        options[1] = "商家";
        options[2] = "团购";
        OptionPicker optionPicker = new OptionPicker(getActivity(), options);
        optionPicker.setTitleText("");
        optionPicker.setSubmitTextColor(Color.parseColor("#FF2E00"));
        optionPicker.show();
        optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {

            //0 订单 1商家 2团购
            private String type;

            @Override
            public void onOptionPicked(String option, int selectedIndex) {

                if (selectedIndex == 0) {
                    type = "0";
                } else if (selectedIndex == 1) {
                    type = "1";
                } else {
                    type = "2";
                }

                //发消息刷新页面
                EventBus.getDefault().post(new EvaluateEvent(type));
            }
        });
    }

    @Override
    public void onTitleClick(View view) {

    }
}
