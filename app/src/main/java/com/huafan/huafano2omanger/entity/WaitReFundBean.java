package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/10.
 * by--待退款列表
 */

public class WaitReFundBean implements Serializable{
    private List<ListBean> list;
    private String count;

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

    public static class ListBean {
        /**
         * reason : 是打发第三方
         * amount : 30.00
         * explain :
         * refund_state : 0
         * created : 1515406063
         * refund_id : 1
         * id : 1
         * order_num : 279802
         * user_id : 30
         * merch_id : 1
         * customer_name : 打算
         * customer_tel : 15110144043
         * province_id : 100010
         * city_id : 100011
         * district_id : 100012
         * location : 北京北京市朝阳区
         * detail_address : 大萨达所付撒大群
         * distrib_mode : 1
         * distrib_id : 1
         * distance : 10387382
         * rider_name :
         * rider_phone :
         * booking_time :
         * goods_price : 119.20
         * box_cost : 328.00
         * distrib_cost : 0.00
         * order_amount : 447.20
         * pay_amount : 447.20
         * rebate_amount : 21.20
         * need_invoice : 0
         * goods_count : 6
         * dinner_set_count : 2
         * goods_list_num :
         * note : 做的好吃点
         * state : 1
         * eval_state : 2
         * refund_total : 0.00
         * pay_time : 0
         * deliv_time : 0
         * confirm_time : 0
         * channel : 0
         * pay_channel : 0
         * order_type : 0
         * is_del_user : 0
         * is_del_merch : 0
         * ended : 0
         * goods : [{"goods_name":"宫保鸡丁12","order_num":"279802","goods_num":"2","goods_price":"19.20","rebate":"1.10","file_path":"o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg"},{"goods_name":"宫保鸡丁12","order_num":"279802","goods_num":"2","goods_price":"21.20","rebate":"3.10","file_path":"o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg"},{"goods_name":"急死","order_num":"279802","goods_num":"2","goods_price":"19.20","rebate":"1.10","file_path":"o2o_good/2018/01-03/3b27acd6a7f856cad3835dfbc61eecae.jpg"}]
         * order_count : 1
         */

        private String reason;
        private String amount;
        private String explain;
        private String refund_state;
        private String created;
        private String refund_id;
        private String id;
        private String order_num;
        private String user_id;
        private String merch_id;
        private String customer_name;
        private String customer_tel;
        private String province_id;
        private String city_id;
        private String district_id;
        private String location;
        private String detail_address;
        private String distrib_mode;
        private String distrib_id;
        private String distance;
        private String rider_name;
        private String rider_phone;
        private String booking_time;
        private String goods_price;
        private String box_cost;
        private String distrib_cost;
        private String order_amount;
        private String pay_amount;
        private String rebate_amount;
        private String need_invoice;
        private String goods_count;
        private String dinner_set_count;
        private String goods_list_num;
        private String note;
        private String state;
        private String eval_state;
        private String refund_total;
        private String pay_time;
        private String deliv_time;
        private String confirm_time;
        private String channel;
        private String pay_channel;
        private String order_type;
        private String is_del_user;
        private String is_del_merch;
        private String ended;
        private String order_count;
        private boolean isshow;
        private String refund_created;
        private String re_state;//退款状态
        private String price;
        private List<GoodsBean> goods;



        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRefund_created() {
            return refund_created;
        }

        public void setRefund_created(String refund_created) {
            this.refund_created = refund_created;
        }

        public boolean getIsshow() {
            return isshow;
        }

        public void setIsshow(boolean isshow) {
            this.isshow = isshow;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getRefund_id() {
            return refund_id;
        }

        public void setRefund_id(String refund_id) {
            this.refund_id = refund_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMerch_id() {
            return merch_id;
        }

        public void setMerch_id(String merch_id) {
            this.merch_id = merch_id;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getDistrib_mode() {
            return distrib_mode;
        }

        public void setDistrib_mode(String distrib_mode) {
            this.distrib_mode = distrib_mode;
        }

        public String getDistrib_id() {
            return distrib_id;
        }

        public void setDistrib_id(String distrib_id) {
            this.distrib_id = distrib_id;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getRider_name() {
            return rider_name;
        }

        public void setRider_name(String rider_name) {
            this.rider_name = rider_name;
        }

        public String getRider_phone() {
            return rider_phone;
        }

        public void setRider_phone(String rider_phone) {
            this.rider_phone = rider_phone;
        }

        public String getBooking_time() {
            return booking_time;
        }

        public void setBooking_time(String booking_time) {
            this.booking_time = booking_time;
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

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getRebate_amount() {
            return rebate_amount;
        }

        public void setRebate_amount(String rebate_amount) {
            this.rebate_amount = rebate_amount;
        }

        public String getNeed_invoice() {
            return need_invoice;
        }

        public void setNeed_invoice(String need_invoice) {
            this.need_invoice = need_invoice;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public String getDinner_set_count() {
            return dinner_set_count;
        }

        public void setDinner_set_count(String dinner_set_count) {
            this.dinner_set_count = dinner_set_count;
        }

        public String getGoods_list_num() {
            return goods_list_num;
        }

        public void setGoods_list_num(String goods_list_num) {
            this.goods_list_num = goods_list_num;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getEval_state() {
            return eval_state;
        }

        public void setEval_state(String eval_state) {
            this.eval_state = eval_state;
        }

        public String getRefund_total() {
            return refund_total;
        }

        public void setRefund_total(String refund_total) {
            this.refund_total = refund_total;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getDeliv_time() {
            return deliv_time;
        }

        public void setDeliv_time(String deliv_time) {
            this.deliv_time = deliv_time;
        }

        public String getConfirm_time() {
            return confirm_time;
        }

        public void setConfirm_time(String confirm_time) {
            this.confirm_time = confirm_time;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getPay_channel() {
            return pay_channel;
        }

        public void setPay_channel(String pay_channel) {
            this.pay_channel = pay_channel;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getIs_del_user() {
            return is_del_user;
        }

        public void setIs_del_user(String is_del_user) {
            this.is_del_user = is_del_user;
        }

        public String getIs_del_merch() {
            return is_del_merch;
        }

        public void setIs_del_merch(String is_del_merch) {
            this.is_del_merch = is_del_merch;
        }

        public String getEnded() {
            return ended;
        }

        public void setEnded(String ended) {
            this.ended = ended;
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

        public static class GoodsBean {
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
