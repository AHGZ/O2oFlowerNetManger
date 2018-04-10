package com.huafan.huafano2omanger.view.fragment.shop.financingsituation.financingdetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务明细主界面
 */

public class FinancingDetailFragment extends KFragment<IFinancingDetailView, IFinancingDetailPresenter>{
    //页签团购
    @BindView(R.id.financing_detail_group)
    TextView financingDetailGroup;
    //页签外卖
    @BindView(R.id.financing_detail_take_out)
    TextView financingDetailTakeOut;
    //返回
    @BindView(R.id.financing_detail_back)
    ImageView goBack;
    private GroupFragment groupFragment;
    private TakeOutFragment takeOutFragment;

    public static FinancingDetailFragment newInstance() {
        Bundle args = new Bundle();
        FinancingDetailFragment fragment = new FinancingDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IFinancingDetailPresenter createPresenter() {
        return new IFinancingDetailPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_financing_detail;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(goBack, 50);

        groupFragment = new GroupFragment();
        takeOutFragment = new TakeOutFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager. beginTransaction();
        transaction.add(R.id.financing_frame, groupFragment);
        transaction.add(R.id.financing_frame, takeOutFragment);
        transaction.show(groupFragment);
        transaction.hide(takeOutFragment);
        transaction.commit();

    }


    @OnClick({R.id.financing_detail_group, R.id.financing_detail_take_out,R.id.financing_detail_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.financing_detail_back:
                removeFragment();

                break;
            //页签团购
            case R.id.financing_detail_group:
                financingDetailGroup.setBackgroundColor(Color.parseColor("#FC422A"));
                financingDetailGroup.setTextColor(Color.parseColor("#FFFFFF"));
                financingDetailTakeOut.setBackgroundColor(Color.parseColor("#FFFFFF"));
                financingDetailTakeOut.setTextColor(Color.parseColor("#444444"));

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager. beginTransaction();
                transaction.show(groupFragment);
                transaction.hide(takeOutFragment);
                transaction.commit();

                break;
            //页签外卖
            case R.id.financing_detail_take_out:
                financingDetailTakeOut.setBackgroundColor(Color.parseColor("#FC422A"));
                financingDetailTakeOut.setTextColor(Color.parseColor("#FFFFFF"));
                financingDetailGroup.setBackgroundColor(Color.parseColor("#FFFFFF"));
                financingDetailGroup.setTextColor(Color.parseColor("#444444"));

                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1. beginTransaction();
                transaction1.show(takeOutFragment);
                transaction1.hide(groupFragment);
                transaction1.commit();

                break;
        }
    }
}
