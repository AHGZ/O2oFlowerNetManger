package com.huafan.huafano2omanger.event;

/**
 * Created by caishen on 2018/2/3.
 * by--退出程序的消息
 */

public class OutProgramEvent {
    private final String str;

    public OutProgramEvent(String s) {
        this.str=s;
    }

    public String getStr() {
        return str;
    }
}
