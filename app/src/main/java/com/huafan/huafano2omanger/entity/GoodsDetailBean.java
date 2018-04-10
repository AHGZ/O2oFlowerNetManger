package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/2.
 * by--商品详情
 */

public class GoodsDetailBean implements Serializable{


    /**
     * list : [{"id":"39","goods_spec":"甜的","o_spec_price":"19.20","spec_price":"18.10","spec_stock":"11"},{"id":"40","goods_spec":"辣的","o_spec_price":"21.20","spec_price":"18.10","spec_stock":"11"},{"id":"50","goods_spec":"红烧猪手","o_spec_price":"1.00","spec_price":"1.00","spec_stock":"12"}]
     * id : 20
     * name : 红烧张培森
     * cate_id : 1
     * cate_name : 热菜
     * file_path : o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg
     * price : 10.00
     * o_price : 9.00
     * unit_id : 9
     * unit_name : 红烧张培森
     * stock : 88
     * box_num : 89
     * box_price : 80.00
     * is_new : 1
     * is_sale0 : 0
     * is_sale1 : 1
     * is_sale2 : 1
     * is_sale3 : 1
     * is_sale4 : 0
     * is_sale5 : 0
     * is_sale6 : 1
     * is_sale : 1
     * description : 还是女的下
     */

    private String id;
    private String name;
    private String cate_id;
    private String cate_name;
    private String file_path;
    private String price;
    private String o_price;
    private String unit_id;
    private String unit_name;
    private String stock;
    private String box_num;
    private String box_price;
    private String is_new;
    private String is_sale0;
    private String is_sale1;
    private String is_sale2;
    private String is_sale3;
    private String is_sale4;
    private String is_sale5;
    private String is_sale6;
    private String is_sale;
    private String description;
    private String goods_img;
    private List<SpecificationBean> list;

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getO_price() {
        return o_price;
    }

    public void setO_price(String o_price) {
        this.o_price = o_price;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getBox_num() {
        return box_num;
    }

    public void setBox_num(String box_num) {
        this.box_num = box_num;
    }

    public String getBox_price() {
        return box_price;
    }

    public void setBox_price(String box_price) {
        this.box_price = box_price;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_sale0() {
        return is_sale0;
    }

    public void setIs_sale0(String is_sale0) {
        this.is_sale0 = is_sale0;
    }

    public String getIs_sale1() {
        return is_sale1;
    }

    public void setIs_sale1(String is_sale1) {
        this.is_sale1 = is_sale1;
    }

    public String getIs_sale2() {
        return is_sale2;
    }

    public void setIs_sale2(String is_sale2) {
        this.is_sale2 = is_sale2;
    }

    public String getIs_sale3() {
        return is_sale3;
    }

    public void setIs_sale3(String is_sale3) {
        this.is_sale3 = is_sale3;
    }

    public String getIs_sale4() {
        return is_sale4;
    }

    public void setIs_sale4(String is_sale4) {
        this.is_sale4 = is_sale4;
    }

    public String getIs_sale5() {
        return is_sale5;
    }

    public void setIs_sale5(String is_sale5) {
        this.is_sale5 = is_sale5;
    }

    public String getIs_sale6() {
        return is_sale6;
    }

    public void setIs_sale6(String is_sale6) {
        this.is_sale6 = is_sale6;
    }

    public String getIs_sale() {
        return is_sale;
    }

    public void setIs_sale(String is_sale) {
        this.is_sale = is_sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SpecificationBean> getList() {
        return list;
    }

    public void setList(List<SpecificationBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 39
         * goods_spec : 甜的
         * o_spec_price : 19.20
         * spec_price : 18.10
         * spec_stock : 11
         */

        private String id;
        private String goods_spec;
        private String o_spec_price;
        private String spec_price;
        private String spec_stock;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_spec() {
            return goods_spec;
        }

        public void setGoods_spec(String goods_spec) {
            this.goods_spec = goods_spec;
        }

        public String getO_spec_price() {
            return o_spec_price;
        }

        public void setO_spec_price(String o_spec_price) {
            this.o_spec_price = o_spec_price;
        }

        public String getSpec_price() {
            return spec_price;
        }

        public void setSpec_price(String spec_price) {
            this.spec_price = spec_price;
        }

        public String getSpec_stock() {
            return spec_stock;
        }

        public void setSpec_stock(String spec_stock) {
            this.spec_stock = spec_stock;
        }
    }
}
