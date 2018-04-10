package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by caishen on 2017/12/25.
 * by--星期
 */

public class WeekBean implements Serializable{

    private boolean isChoose;
    private String name;
    private String id;

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
