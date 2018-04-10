package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.BindCardBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.fragment.shop.messagemanagement.MessageManagementActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caishen on 2018/1/30.
 * by--添加银行卡
 */

public class AddbankCardFragment extends KFragment<IAddbankCardView, IAddbankCardPrenter>
        implements IAddbankCardView, CustomEditText.IMyRightDrawableClick, TextWatcher {

    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bankcardnum_ed)
    CustomEditText bankcardnumEd;
    @BindView(R.id.realname_ed)
    CustomEditText realnameEd;
    @BindView(R.id.bakcardnum_ly)
    LinearLayout bakcardnumLy;
    @BindView(R.id.idcard_ed)
    CustomEditText idcardEd;
    @BindView(R.id.checkbox_im)
    CheckBox checkboxIm;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.submit_btn)
    Button submitBtn;
    @BindView(R.id.bankname_ed)
    CustomEditText banknameEd;
    @BindView(R.id.iv_message)
    ImageView iv_message;
    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    public IAddbankCardPrenter createPresenter() {

        return new IAddbankCardPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_addbankcard;
    }

    @Override
    public void initData() {

        mPresenter.go_bind_card();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {


        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        bankcardnumEd.setDrawableClick(this);
        idcardEd.setDrawableClick(this);
        realnameEd.setDrawableClick(this);

        idcardEd.addTextChangedListener(this);
        realnameEd.addTextChangedListener(this);
        bankcardnumEd.addTextChangedListener(this);
        banknameEd.addTextChangedListener(this);

        initData();
    }

    public static KFragment newIntence() {

        AddbankCardFragment addbankCardFragment = new AddbankCardFragment();
        Bundle bundle = new Bundle();
        addbankCardFragment.setArguments(bundle);
        return addbankCardFragment;
    }

    @OnClick({R.id.im_back, R.id.submit_btn, R.id.iv_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_back://返回

                removeFragment();

                break;
            case R.id.submit_btn://提交银行卡信息

                mPresenter.commit();

                break;
            case R.id.iv_message://消息中心

                Intent intent = new Intent(getActivity(), MessageManagementActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public void showDialog() {

        shapeLoadingDialog.show();
    }

    @Override
    public void hideDiaglog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSuccess(BindCardBean data) {

        if (data != null) {

            realnameEd.setText(data.getName() == null ? "" : data.getName());
            idcardEd.setText(data.getIdnum() == null ? "" : data.getIdnum());
        }
    }

    @Override
    public String getname() {
        return realnameEd.getText().toString().trim();
    }

    @Override
    public String getIdNum() {
        return idcardEd.getText().toString().trim();
    }

    @Override
    public String getcard_num() {
        return bankcardnumEd.getText().toString().trim();
    }

    @Override
    public String getbank_name() {
        return banknameEd.getText().toString().trim();
    }

    @Override
    public void onSuccessMessage(String message) {

        showShortToast(message);
        removeFragment();
    }

    @Override
    public void rightDrawableClick(View view) {
        switch (view.getId()) {
            case R.id.bankcardnum_ed:
                bankcardnumEd.setText("");
                break;
            case R.id.realname_ed:
                realnameEd.setText("");
                break;
            case R.id.idcard_ed:
                idcardEd.setText("");
                break;
            case R.id.bankcardname_tv:
                banknameEd.setText("");
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            setButtonBackground();
        }
    }

    private void setButtonBackground() {

        String bankcardnumStr = bankcardnumEd.getText().toString().trim();
        String realnameStr = realnameEd.getText().toString().trim();
        String idcardStr = idcardEd.getText().toString().trim();
        String trim = banknameEd.getText().toString().trim();
        if (bankcardnumStr.length() > 0 && realnameStr.length() > 0 && idcardStr.length() > 0 && trim.length() > 0) {
            submitBtn.setClickable(true);
            submitBtn.setBackgroundResource(R.drawable.pay_bg);
        } else {
            submitBtn.setClickable(false);
            submitBtn.setBackgroundResource(R.drawable.btn_norml);
        }
    }
}
