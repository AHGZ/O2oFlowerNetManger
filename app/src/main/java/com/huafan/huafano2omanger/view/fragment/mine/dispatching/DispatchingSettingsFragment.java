package com.huafan.huafano2omanger.view.fragment.mine.dispatching;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.DispatchingBean;
import com.huafan.huafano2omanger.event.getMapEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 配送设置主界面
 */

public class DispatchingSettingsFragment extends KFragment<IDispatchingSettingsView, IDispatchingSettingsPresenter> implements IDispatchingSettingsView, NormalTopBar.normalTopClickListener {
    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //到店自提
    @BindView(R.id.dispatching_to_shop)
    CheckBox dispatchingToShop;
    //蜂鸟配送、商家配送的父radiogroup
    @BindView(R.id.dispatching_radiogroup)
    RadioGroup dispatchingRadiogroup;
    //起送金额
    @BindView(R.id.ed_qisong)
    CustomEditText edQisong;
    //备餐时间
    @BindView(R.id.ed_beican)
    CustomEditText edBeican;
    //保存按钮
    @BindView(R.id.dispatching_sava_button)
    Button dispatchingSavaButton;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static DispatchingSettingsFragment newInstance() {
        Bundle args = new Bundle();
        DispatchingSettingsFragment fragment = new DispatchingSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IDispatchingSettingsPresenter createPresenter() {
        return new IDispatchingSettingsPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dispatching_settings;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {


        normalTop.setTitleText("配送设置");
        normalTop.setTopClickListener(this);

        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        //设置默认配送的方式
        RadioButton radioButton = (RadioButton) dispatchingRadiogroup.getChildAt(0);
        radioButton.setChecked(true);
        mPresenter.getDispatching();
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
    public void onSuccess(DispatchingBean dispatchingBean) {
        //配送方式:1-平台配送 2-商家配送
        int distrib_setting = dispatchingBean.getDistrib_setting();
        //是否支持自提:0-不允许 1-允许
        int self_pick_setting = dispatchingBean.getSelf_pick_setting();
        //起送金额
        float distrib_quota = dispatchingBean.getDistrib_quota();
        //商家备货时间
        int prepare_time = dispatchingBean.getPrepare_time();

        //设置是否支持到店自提
        if (self_pick_setting == 0) {
            dispatchingToShop.setChecked(false);
        } else if (self_pick_setting == 1) {
            dispatchingToShop.setChecked(true);
        }
        //设置配送方式
        if (distrib_setting == 1) {
            //设置为平台配送
            RadioButton radioButton = (RadioButton) dispatchingRadiogroup.getChildAt(0);
            radioButton.setChecked(true);
        } else if (distrib_setting == 2) {
            //设置为商家配送
            RadioButton radioButton = (RadioButton) dispatchingRadiogroup.getChildAt(1);
            radioButton.setChecked(true);
        }
        //设置起送金额
        edQisong.setText(distrib_quota + "");
        //设置备餐时间
        edBeican.setText(prepare_time + "");
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
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
    @OnClick(R.id.dispatching_sava_button)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dispatching_sava_button:

                //获取起送金额
                String qiSong = edQisong.getText().toString().trim();
                //获取备餐金额
                String beiCan = edBeican.getText().toString().trim();


                //蜂鸟配送按钮
                RadioButton fengniaoRadioButton = (RadioButton) dispatchingRadiogroup.getChildAt(0);
                //商家配送按钮
                RadioButton shangjiaRadioButton = (RadioButton) dispatchingRadiogroup.getChildAt(1);

                if (qiSong.equals("")){
                    qiSong=0+"";
                }else if (qiSong.equals(".")){
                    showShortToast("请输入正确的格式，不能只输入字符");
                    return;
                }

                if (beiCan.equals("0") || beiCan.equals("")) {
                    showShortToast("备餐时间不能为0或为空，请检查一下");
                    return;
                }else if (Integer.parseInt(beiCan)>60){
                    showShortToast("备餐时间不能大于一小时");
                    return;
                }
                //设置为蜂鸟配送方式
                if (fengniaoRadioButton.isChecked()) {
                    if (dispatchingToShop.isChecked()) {
                        mPresenter.changeDispatching(1, Float.parseFloat(qiSong), 1, Integer.parseInt(beiCan));
                    } else if (!dispatchingToShop.isChecked()) {
                        mPresenter.changeDispatching(1, Float.parseFloat(qiSong), 0, Integer.parseInt(beiCan));
                    }
                } else if (shangjiaRadioButton.isChecked()) {
                    //设置为商家配送方式
                    if (dispatchingToShop.isChecked()) {
                        mPresenter.changeDispatching(2, Float.parseFloat(qiSong), 1, Integer.parseInt(beiCan));
                    } else if (!dispatchingToShop.isChecked()) {
                        mPresenter.changeDispatching(2, Float.parseFloat(qiSong), 0, Integer.parseInt(beiCan));
                    }
                }

                EventBus.getDefault().post(new getMapEvent());
                removeFragment();
                break;
        }

    }
}
