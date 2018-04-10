package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by caishen on 2017/12/25.
 * by--商品管理
 */

public class ShopMangerBean implements Serializable{

    private int id;
    private String name;
    private String desc;
    private String inventory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
