package com.huafan.huafano2omanger.view.fragment.mine.orderreceiving;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.huafan.huafano2omanger.event.getMapEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 接单设置主界面
 */

public class OrderReceivingFragment extends KFragment<IOrderReceivingView, IOrderReceivingPresenter>
        implements IOrderReceivingView, NormalTopBar.normalTopClickListener {

    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //自动接单、手动接单的父radiogroup
    @BindView(R.id.orderReceiving_radiogroup)
    RadioGroup orderReceivingRadiogroup;
    //保存按钮
    @BindView(R.id.orderReceiving_sava_button)
    Button orderReceivingSavaButton;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static OrderReceivingFragment newInstance() {
        Bundle args = new Bundle();
        OrderReceivingFragment fragment = new OrderReceivingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IOrderReceivingPresenter createPresenter() {
        return new IOrderReceivingPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_order_receiving;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("接单设置");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();
        mPresenter.getOrderReceiving(2);
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

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(OrderReceivingBean orderReceivingBean) {
        int state = Integer.parseInt(orderReceivingBean.getState());
        if (state == 0) {
            //0-未设置
        } else if (state == 1) {
            //1-自动
            RadioButton radioButton = (RadioButton) orderReceivingRadiogroup.getChildAt(1);
            radioButton.setChecked(true);
        } else if (state == 2) {
            //2-手动
            RadioButton radioButton = (RadioButton) orderReceivingRadiogroup.getChildAt(0);
            radioButton.setChecked(true);
        }
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
        removeFragment();
    }

    @Override
    public void showDialog() {

        shapeLoadingDialog.show();
    }

    @Override
    public void hideDialog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }


    //保存按钮监听事件
    @OnClick(R.id.orderReceiving_sava_button)
    public void onViewClicked() {
        //手动接单按钮
        RadioButton shoudongRadioButton = (RadioButton) orderReceivingRadiogroup.getChildAt(0);
        //自动接单按钮
        RadioButton zidongRadioButton = (RadioButton) orderReceivingRadiogroup.getChildAt(1);
        if (shoudongRadioButton.isChecked()) {
            mPresenter.changeOrderMessageReceiving(2, 2);
        } else if (zidongRadioButton.isChecked()) {
            mPresenter.changeOrderMessageReceiving(2, 1);
        }
        EventBus.getDefault().post(new getMapEvent());

    }
}
