package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/20.
 * by--店铺信息
 */

public class ShopDetailBean implements Serializable{


    /**
     * info : {"id":"1","manager_name":"你在哪里上班","manager_tel":"18513081386","merch_name":"武汉一高校","first_cate_id":"10001","second_cate_id":"0","third_cate_id":"0","province_id":"110000","city_id":"110100","district_id":"110101","area_name":"北京北京市东城区","address":"你在哪里呢。我","apply_id":"1","logo_url":"img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg","license_url":"img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg","scenery_url":["img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg","img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg"]}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 1
         * manager_name : 你在哪里上班
         * manager_tel : 18513081386
         * merch_name : 武汉一高校
         * first_cate_id : 10001
         * second_cate_id : 0
         * third_cate_id : 0
         * province_id : 110000
         * city_id : 110100
         * district_id : 110101
         * area_name : 北京北京市东城区
         * address : 你在哪里呢。我
         * apply_id : 1
         * logo_url : img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg
         * license_url : img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg
         * scenery_url : ["img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg","img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg"]
         */

        private String id;
        private String manager_name;
        private String manager_tel;
        private String merch_name;
        private String first_cate_id;
        private String second_cate_id;
        private String third_cate_id;
        private String province_id;
        private String city_id;
        private String district_id;
        private String area_name;
        private String address;
        private String apply_id;
        private String logo_url;
        private String license_url;
        private List<String> scenery_url;
        private String merch_type;
        private String phone;

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setMerch_type(String merch_type) {
            this.merch_type = merch_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getManager_name() {
            return manager_name;
        }

        public void setManager_name(String manager_name) {
            this.manager_name = manager_name;
        }

        public String getManager_tel() {
            return manager_tel;
        }

        public void setManager_tel(String manager_tel) {
            this.manager_tel = manager_tel;
        }

        public String getMerch_name() {
            return merch_name;
        }

        public void setMerch_name(String merch_name) {
            this.merch_name = merch_name;
        }

        public String getFirst_cate_id() {
            return first_cate_id;
        }

        public void setFirst_cate_id(String first_cate_id) {
            this.first_cate_id = first_cate_id;
        }

        public String getSecond_cate_id() {
            return second_cate_id;
        }

        public void setSecond_cate_id(String second_cate_id) {
            this.second_cate_id = second_cate_id;
        }

        public String getThird_cate_id() {
            return third_cate_id;
        }

        public void setThird_cate_id(String third_cate_id) {
            this.third_cate_id = third_cate_id;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(String district_id) {
            this.district_id = district_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getApply_id() {
            return apply_id;
        }

        public void setApply_id(String apply_id) {
            this.apply_id = apply_id;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public String getLicense_url() {
            return license_url;
        }

        public void setLicense_url(String license_url) {
            this.license_url = license_url;
        }

        public List<String> getScenery_url() {
            return scenery_url;
        }

        public void setScenery_url(List<String> scenery_url) {
            this.scenery_url = scenery_url;
        }

        public String getMerch_type() {
            return merch_type;
        }

        public String getPhone() {
            return phone;
        }
    }
}
