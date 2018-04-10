package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/9.
 * by--待处理订单
 */

public class WaitDisposeBean implements Serializable {


    private List<ListBean> list;
    private String count;
    private String today_count;
    private String merch_name;

    public String getToday_count() {
        return today_count;
    }

    public void setToday_count(String today_count) {
        this.today_count = today_count;
    }

    public String getMerch_name() {
        return merch_name;
    }

    public void setMerch_name(String merch_name) {
        this.merch_name = merch_name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public static class ListBean implements Serializable {
        /**
         * order_num : 279802
         * user_id : 0
         * customer_name : 打算
         * customer_tel : 15110144043
         * detail_address : 大萨达所付撒大群
         * distance : 10387382
         * location : 北京北京市朝阳区
         * distrib_mode : 1
         * need_invoice : 0
         * goods_price : 119.20
         * box_cost : 328.00
         * distrib_cost : 0.00
         * note : 做的好吃点
         * created : 1515406063
         * pay_channel : 0
         * goods : [{"goods_name":"宫保鸡丁12","order_num":"279802","goods_num":"2","goods_price":"19.20","rebate":"1.10","file_path":"o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg"},{"goods_name":"宫保鸡丁12","order_num":"279802","goods_num":"2","goods_price":"21.20","rebate":"3.10","file_path":"o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg"},{"goods_name":"急死","order_num":"279802","goods_num":"2","goods_price":"19.20","rebate":"1.10","file_path":"o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg"}]
         * order_count : 1
         */

        private String order_num;
        private int user_id;
        private String customer_name;
        private String customer_tel;
        private String detail_address;
        private String distance;
        private String location;
        private String distrib_mode;
        private String need_invoice;
        private String goods_price;
        private String box_cost;
        private String distrib_cost;
        private String note;
        private String created;
        private String pay_channel;
        private String state;
        private String id;
        private String order_count;
        private List<GoodsBean> goods;
        private String price;
        private String today_count;
        private String merch_name;
        private String dinner_set_count;
        private boolean isshow;
        private String booking_time;

        public String getMerch_name() {
            return merch_name;
        }

        public void setMerch_name(String merch_name) {
            this.merch_name = merch_name;
        }

        public String getToday_count() {
            return today_count;
        }

        public void setToday_count(String today_count) {
            this.today_count = today_count;
        }

        public String getDinner_set_count() {
            return dinner_set_count;
        }

        public void setDinner_set_count(String dinner_set_count) {
            this.dinner_set_count = dinner_set_count;
        }

        public void setBooking_time(String booking_time) {
            this.booking_time = booking_time;
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isshow() {
            return isshow;
        }

        public void setIsshow(boolean isshow) {
            this.isshow = isshow;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getCustomer_tel() {
            return customer_tel;
        }

        public void setCustomer_tel(String customer_tel) {
            this.customer_tel = customer_tel;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDistrib_mode() {
            return distrib_mode;
        }

        public void setDistrib_mode(String distrib_mode) {
            this.distrib_mode = distrib_mode;
        }

        public String getNeed_invoice() {
            return need_invoice;
        }

        public void setNeed_invoice(String need_invoice) {
            this.need_invoice = need_invoice;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getBox_cost() {
            return box_cost;
        }

        public void setBox_cost(String box_cost) {
            this.box_cost = box_cost;
        }

        public String getDistrib_cost() {
            return distrib_cost;
        }

        public void setDistrib_cost(String distrib_cost) {
            this.distrib_cost = distrib_cost;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getPay_channel() {
            return pay_channel;
        }

        public void setPay_channel(String pay_channel) {
            this.pay_channel = pay_channel;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public boolean getIsshow() {
            return isshow;
        }

        public String getBooking_time() {
            return booking_time;
        }

        public static class GoodsBean implements Serializable{
            /**
             * goods_name : 宫保鸡丁12
             * order_num : 279802
             * goods_num : 2
             * goods_price : 19.20
             * rebate : 1.10
             * file_path : o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg
             */

            private String goods_name;
            private String order_num;
            private String goods_num;
            private String goods_price;
            private String rebate;
            private String file_path;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getRebate() {
                return rebate;
            }

            public void setRebate(String rebate) {
                this.rebate = rebate;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }
        }
    }
}
