package com.huafan.huafano2omanger.view.fragment.updatepwd;


import com.huafan.huafano2omanger.entity.CheckMobileBean;
import com.huafan.huafano2omanger.entity.SendCodeBean;
import com.huafan.huafano2omanger.entity.UpdatePwd;
import com.huafan.huafano2omanger.mvp.IView;

/**
 * Created by caishen on 2017/10/18.
 * by--
 */

interface IUpdatePwdView extends IView<IUpdatePwdPresenter> {

    /**
     * 获取新密码
     */
    String getPwd();

    /**
     * 获取确认密码
     * @return
     */
    String getaffirmPwd();

    void onError(String errorMsg);

    void onLoginSuccess(UpdatePwd info);

    void onSendCodeSuccess(String message);

    void showDialog();

    void hideDialog();

    String getPhone();

    void sendCheckMobileSuccess(CheckMobileBean checkMobileBean);

    void sendCodeSuccess(SendCodeBean sendCodeBean);

    String getSendCode();

    int getFromCode();
}
