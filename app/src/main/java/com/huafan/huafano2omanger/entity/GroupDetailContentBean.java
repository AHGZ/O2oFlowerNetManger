package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by caishen on 2018/1/15.
 * by--团单内容
 */

public class GroupDetailContentBean implements Serializable{

    private  String name;
    private  String price;
    private  String id;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
