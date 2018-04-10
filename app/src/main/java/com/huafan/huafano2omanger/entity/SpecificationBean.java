package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by caishen on 2017/12/25.
 * by--添加商品规格
 */

public class SpecificationBean implements Serializable{

    private  String name;
    private  String price;
    private  String id;
    private String rlPrice;
    public String goods_spec;
    public float o_spec_price;
    public float spec_price;
    public String spec_stock;

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public float getO_spec_price() {
        return o_spec_price;
    }

    public void setO_spec_price(float o_spec_price) {
        this.o_spec_price = o_spec_price;
    }

    public float getSpec_price() {
        return spec_price;
    }

    public void setSpec_price(float spec_price) {
        this.spec_price = spec_price;
    }

    public String getSpec_stock() {
        return spec_stock;
    }

    public void setSpec_stock(String spec_stock) {
        this.spec_stock = spec_stock;
    }

    public String getRlPrice() {
        return rlPrice;
    }

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

    public void setRlPrice(String rlPrice) {
        this.rlPrice = rlPrice;
    }
}
