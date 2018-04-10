package com.huafan.huafano2omanger.view.fragment.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.UserInfo;
import com.huafan.huafano2omanger.entity.UserInfoEvent;
import com.huafan.huafano2omanger.event.GroupOnEvent;
import com.huafan.huafano2omanger.event.OutProgramEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.appstatus.Eyes;
import com.huafan.huafano2omanger.view.activity.UpdatePasswordActivity;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * author: zhangpeisen
 * created on: 2017/10/11 上午9:26
 * description: 登录主页面
 */
public class LoginFragment extends KFragment<ILoginView, ILoginPresenter> implements ILoginView,
        NormalTopBar.normalTopClickListener, CustomEditText.IMyRightDrawableClick, TextWatcher {
    @BindView(R.id.normal_top)
    //自定义通用标题
            NormalTopBar normalTopBar;
    //账户输入框
    @BindView(R.id.edittext_username)
    CustomEditText mEdittext_username;
    //密码输入框
    @BindView(R.id.edittext_pwd)
    CustomEditText mEdittext_pwd;
    // 登录提交按钮
    @BindView(R.id.loginin_btn)
    Button loginin_btn;
    private boolean isHidden = false;
    // 加载进度条
    private ShapeLoadingDialog shapeLoadingDialog;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ILoginPresenter createPresenter() {
        return new ILoginPresenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.login_fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        //初始化沉浸式
        Eyes.setStatusBarLightMode(getActivity(), Color.WHITE);
        // 初始化标题及相关事件监听
        normalTopBar.setTitleText("登录");
        normalTopBar.getRightImage().setVisibility(View.GONE);

        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTopBar.getLeftImage(), 50);
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        normalTopBar.setTopClickListener(this);
        mEdittext_username.setRightDrawable(getResources().getDrawable(R.drawable.edittext_clear_close));
        mEdittext_username.setDrawableClick(this);
        mEdittext_pwd.setRightDrawable(getResources().getDrawable(R.drawable.icon_close_eye));

        // 密码输入框设置右图标
        mEdittext_pwd.setDrawableClick(this);
        mEdittext_pwd.addTextChangedListener(this);
    }


    @Override
    public String getUser() {

        return mEdittext_username.getText().toString().trim();
    }

    @Override
    public String getPwd() {

        return mEdittext_pwd.getText().toString().trim();
    }

    @Override
    public void onError(String errorMsg) {

        showShortToast(errorMsg);
    }

    @Override
    public void onLoginSuccess(UserInfo userinfo) {

        // 登录成功
        if (userinfo == null) {
            return;
        }

        Constants.TOKEN = userinfo.getToken();
        SPUtils.put(getActivity(), "token", userinfo.getToken());
        SPUtils.put(getActivity(), Constants.ISLOGIN, "isLogin");
        SPUtils.put(getActivity(), "region", userinfo.getRegion());

        //发送消息刷新页面
        EventBus.getDefault().post(new UserInfoEvent(userinfo));
        EventBus.getDefault().post(new GroupOnEvent());//团购列表数据刷新
        removeFragment();
    }


    @Override
    public void showDialog() {
        shapeLoadingDialog.show();
    }

    @Override
    public void hideDialog() {
        if (shapeLoadingDialog.isShowing()) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void onLeftClick(View view) {

        showAlertMsgDialog("未登录将退出花返网商家版", "狠心退出花返网商家版", "狠心离去", "取消");
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onTitleClick(View view) {

    }

    @Override
    public void rightDrawableClick(View view) {
        switch (view.getId()) {
            case R.id.edittext_username:
                // 账户清除
                mEdittext_username.setText("");
                break;
            case R.id.edittext_pwd:
                // 密码切换
                if (isHidden) {
                    //设置EditText文本为可见的
                    mEdittext_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEdittext_pwd.setRightDrawable(getResources().getDrawable(R.mipmap.icon_open_eye));
                } else {
                    //设置EditText文本为隐藏的
                    mEdittext_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEdittext_pwd.setRightDrawable(getResources().getDrawable(R.drawable.icon_close_eye));
                }
                isHidden = !isHidden;
                mEdittext_pwd.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = mEdittext_pwd.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                break;
        }
    }

    @OnClick({R.id.loginin_btn, R.id.forget_password})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.loginin_btn:
                // 登录提交按钮
                mPresenter.login();
                break;
            case R.id.forget_password://忘记密码
                Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * @param
     * @return void    返回类型
     * @throws
     * @Title: setButtonBackground
     * @Description: TODO 设置登录按钮的背景颜色
     */
    private void setButtonBackground() {
        String username = mEdittext_username.getText().toString().trim();
        String userpwd = mEdittext_pwd.getText().toString().trim();
        if (username.length() > 0 && userpwd.length() > 0) {
            loginin_btn.setClickable(true);
            loginin_btn.setBackgroundResource(R.drawable.btn_pressed);
        } else {
            loginin_btn.setClickable(false);
            loginin_btn.setBackgroundResource(R.drawable.btn_norml);
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

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return_ticket void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        //退出程序
                        EventBus.getDefault().post(new OutProgramEvent("1"));
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.show();
    }
}
