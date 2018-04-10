package com.huafan.huafano2omanger.entity;

/**
 * author: zhangpeisen
 * created on: 2017/11/14 下午5:22
 * description:
 */
public class UserInfoEvent {
    UserInfo userInfo;

    public UserInfoEvent(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

}
