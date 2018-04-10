package com.huafan.huafano2omanger.event;

/**
 * Created by caishen on 2017/12/9.
 * by--首页下部的选中
 */

public class MainEvent {
    private final String str;

    public MainEvent(String s) {
        this.str=s;
    }

    public String getStr() {
        return str;
    }
}
