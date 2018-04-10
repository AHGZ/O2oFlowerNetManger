package com.huafan.huafano2omanger.event;

/**
 * Created by caishen on 2018/1/17.
 * by--
 */

public class WaitDisposeEvent {

    private final String count;
    private final String tag;

    public WaitDisposeEvent(String s, String count) {
        this.count = count;
        this.tag=s;
    }

    public String getCount() {
        return count;
    }

    public String getTag() {
        return tag;
    }
}
