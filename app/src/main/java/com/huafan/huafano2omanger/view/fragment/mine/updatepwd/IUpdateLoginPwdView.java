package com.huafan.huafano2omanger.view.fragment.mine.updatepwd;


import com.huafan.huafano2omanger.entity.SendCodeBean;

/**
 * Created by caishen on 2017/11/18.
 * by--
 */

public interface IUpdateLoginPwdView {

    void onError(String s);

    String getAPwd();
    String getBPwd();
    String getCPwd();

    void hideDialog();

    void sendCodeSuccess(SendCodeBean sendCodeBean);

    void showDialog();

    String getSmsCode();

    String getPhone();

    void sendUpdateSuccess(String sendCodeBean);

    int getFromCode();
}
