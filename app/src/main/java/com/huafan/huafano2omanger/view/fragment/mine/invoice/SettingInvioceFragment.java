package com.huafan.huafano2omanger.view.fragment.mine.invoice;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caishen on 2018/1/8.
 * by--
 */

public class SettingInvioceFragment extends KFragment<ISettingInvioceView, ISettingInviocePrenter>
        implements NormalTopBar.normalTopClickListener, RadioGroup.OnCheckedChangeListener, ISettingInvioceView {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.rb_invoice)
    RadioButton rbInvoice;
    @BindView(R.id.et_name)
    CustomEditText etName;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.rb_no_invoice)
    RadioButton rbNoInvoice;
    @BindView(R.id.orderReceiving_radiogroup)
    RadioGroup orderReceivingRadiogroup;
    @BindView(R.id.orderReceiving_sava_button)
    Button orderReceivingSavaButton;
    private ShapeLoadingDialog shapeLoadingDialog;
    private int states = -1;
    private int type = 6;
    private String quota = "";


    @Override
    public ISettingInviocePrenter createPresenter() {

        return new ISettingInviocePrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_invoice_setting;
    }

    @Override
    public void initData() {

        mPresenter.getOrderReceiving();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("发票信息");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        orderReceivingRadiogroup.setOnCheckedChangeListener(this);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();
    }

    public static KFragment newItence() {

        SettingInvioceFragment settingInvioceFragment = new SettingInvioceFragment();
        Bundle bundle = new Bundle();
        settingInvioceFragment.setArguments(bundle);
        return settingInvioceFragment;
    }

    @OnClick(R.id.orderReceiving_sava_button)
    public void onClick() {//保存

        if (states == 0) {
            mPresenter.saveSetting();
        } else if (states == 1) {

            float quoInt = 0;
            quota = etName.getText().toString().trim();
            if (!quota.equals("")) {
                if (quota.equals(".")){
                    quoInt=0;
                }else{
                    quoInt = Float.parseFloat(etName.getText().toString().trim());
                }

            }
            if (quota.equals("") || quoInt <= 0) {
                showShortToast("发票金额必填，且不能为0,也不能为符号");
                return;
            }
            mPresenter.saveSetting();
        }


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
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        int checkedRadioButtonId = group.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.rb_invoice://支持开发票

                states = 1;
                llMoney.setVisibility(View.VISIBLE);

                break;
            case R.id.rb_no_invoice://不支持开发票

                states = 0;
                llMoney.setVisibility(View.GONE);

                break;
        }
    }

    @Override
    public int getType() {
        return type;
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

    @Override
    public void onGetSuccess(OrderReceivingBean data) {

        if (data != null) {
            float quota = Float.parseFloat(data.getQuota());
            if (quota <= 0) {
                etName.setText(1 + "");
            } else {
                etName.setText(data.getQuota());
            }

            String state = data.getState();
            if (state.equals("0")) {
                rbNoInvoice.setChecked(true);
                states = 0;
            } else {
                rbInvoice.setChecked(true);
                states = 1;
            }
        }
    }

    @Override
    public void onSuccess(String message) {

        showShortToast(message);
        removeFragment();

    }

    @Override
    public void onError(String message) {

        showShortToast(message);
    }

    @Override
    public int getState() {
        return states;
    }

    @Override
    public String getQuota() {
        return quota;
    }
}
