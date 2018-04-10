package com.huafan.huafano2omanger.event;

/**
 * Created by caishen on 2018/1/17.
 * by--
 */

public class GroupEvent {
    private final String tag;
    private final int count;

    public GroupEvent(String s, int count) {
        this.tag = s;
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public int getCount() {
        return count;
    }
}
