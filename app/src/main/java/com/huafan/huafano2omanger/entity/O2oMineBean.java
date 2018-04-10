package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * author: zhangpeisen
 * created on: 2018/1/3 下午1:26
 * description: 我的实体类
 */
public class O2oMineBean implements Serializable {
    /**
     * id : 1
     * merch_name : 武汉一高校
     * starttime : 2017/12/29
     * endtime : 2018/12/29
     * apply_id : 1
     * logo_url : role_auth_img/20171229/036eb39cafa69fbd30f7ab6b02c802f3.jpg
     * business_state : 0
     * take_setting : 1
     * distrib_setting : 2
     * notice_setting : 0
     * map : 0.0000000-0.0000000
     * invoice_setting : 0
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable {
        private String id;
        private String merch_name;
        private String starttime;
        private String endtime;
        private String apply_id;
        private String logo_url;
        private String business_state;
        private String take_setting;
        private String distrib_setting;
        private String notice_setting;
        private String map;
        private String pay_qrcode;
        private String invoice_setting;

        public String getPay_qrcode() {
            return pay_qrcode;
        }

        public void setPay_qrcode(String pay_qrcode) {
            this.pay_qrcode = pay_qrcode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMerch_name() {
            return merch_name;
        }

        public void setMerch_name(String merch_name) {
            this.merch_name = merch_name;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
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

        public String getBusiness_state() {
            return business_state;
        }

        public void setBusiness_state(String business_state) {
            this.business_state = business_state;
        }

        public String getTake_setting() {
            return take_setting;
        }

        public void setTake_setting(String take_setting) {
            this.take_setting = take_setting;
        }

        public String getDistrib_setting() {
            return distrib_setting;
        }

        public void setDistrib_setting(String distrib_setting) {
            this.distrib_setting = distrib_setting;
        }

        public String getNotice_setting() {
            return notice_setting;
        }

        public void setNotice_setting(String notice_setting) {
            this.notice_setting = notice_setting;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public String getInvoice_setting() {
            return invoice_setting;
        }

        public void setInvoice_setting(String invoice_setting) {
            this.invoice_setting = invoice_setting;
        }

        @Override
        public String toString() {
            return "InfoBean{" +
                    "id='" + id + '\'' +
                    ", merch_name='" + merch_name + '\'' +
                    ", starttime='" + starttime + '\'' +
                    ", endtime='" + endtime + '\'' +
                    ", apply_id='" + apply_id + '\'' +
                    ", logo_url='" + logo_url + '\'' +
                    ", business_state='" + business_state + '\'' +
                    ", take_setting='" + take_setting + '\'' +
                    ", distrib_setting='" + distrib_setting + '\'' +
                    ", notice_setting='" + notice_setting + '\'' +
                    ", map='" + map + '\'' +
                    ", invoice_setting='" + invoice_setting + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "O2oMineBean{" +
                "info=" + info +
                '}';
    }
}
