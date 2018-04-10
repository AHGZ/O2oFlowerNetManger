package com.huafan.huafano2omanger.event;

/**
 * Created by caishen on 2018/1/9.
 * by--
 */

public class EvaluateEvent {
    private final String type;

    public EvaluateEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
