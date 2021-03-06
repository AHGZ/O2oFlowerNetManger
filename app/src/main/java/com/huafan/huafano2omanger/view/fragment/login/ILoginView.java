package com.huafan.huafano2omanger.view.fragment.login;


import com.huafan.huafano2omanger.entity.UserInfo;

/**
 * author: zhangpeisen
 * created on: 2017/7/25 上午10:21
 * description: 登录视图层
 */
public interface ILoginView {

    String getUser();


    String getPwd();

    void onError(String errorMsg);

    void onLoginSuccess(UserInfo info);


    void showDialog();

    void hideDialog();

}
