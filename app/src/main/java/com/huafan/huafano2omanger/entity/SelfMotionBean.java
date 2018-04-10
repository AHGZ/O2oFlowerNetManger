package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/2/8.
 * by--自动打印餐单的数据
 */

public class SelfMotionBean implements Serializable {


    /**
     * list : {"id":"182","order_num":"7998702","trade_no":"hfw7998702","user_id":"1","merch_id":"1","customer_name":"何国忠","customer_tel":"18911005030","location":"北京市-北京市-朝阳区","detail_address":"北京市朝阳区朝阳北路与管庄路口交界处向东200米(万象","distrib_mode":"2","distrib_id":"1","distance":"405","rider_name":"","rider_phone":"","booking_time":"10:21","goods_price":"0.00","box_cost":"0.00","distrib_cost":"0.00","order_amount":"0.00","pay_amount":"0.00","rebate_amount":"0.00","need_invoice":"0","goods_count":"0","dinner_set_count":"12","goods_list_num":"","note":"","state":"2","eval_state":"0","refund_state":"0","refund_total":"0.00","pay_time":"1518054739","deliv_time":"0","confirm_time":"0","channel":"1","pay_channel":"4","order_type":"0","is_del_user":"0","is_del_merch":"0","ended":"0","created":"1518054705","goods":[{"id":"331","user_id":"1","order_num":"7998702","goods_id":"48","spec_id":"0","goods_num":"0","goods_price":"12.00","rebate":"10.00","goods_name":"啧啧啧","goods_spec":"","spec_img":"707"}]}
     * merch_name : 武汉一高校
     */

    private List<WaitDisposeBean.ListBean> list;
    private String merch_name;
    private String today_count;

    public String getToday_count() {
        return today_count;
    }

    public void setToday_count(String today_count) {
        this.today_count = today_count;
    }

    public List<WaitDisposeBean.ListBean> getList() {
        return list;
    }

    public void setList(List<WaitDisposeBean.ListBean> list) {
        this.list = list;
    }

    public String getMerch_name() {
        return merch_name;
    }

    public void setMerch_name(String merch_name) {
        this.merch_name = merch_name;
    }

    public static class ListBean {
        /**
         * id : 182
         * order_num : 7998702
         * trade_no : hfw7998702
         * user_id : 1
         * merch_id : 1
         * customer_name : 何国忠
         * customer_tel : 18911005030
         * location : 北京市-北京市-朝阳区
         * detail_address : 北京市朝阳区朝阳北路与管庄路口交界处向东200米(万象
         * distrib_mode : 2
         * distrib_id : 1
         * distance : 405
         * rider_name :
         * rider_phone :
         * booking_time : 10:21
         * goods_price : 0.00
         * box_cost : 0.00
         * distrib_cost : 0.00
         * order_amount : 0.00
         * pay_amount : 0.00
         * rebate_amount : 0.00
         * need_invoice : 0
         * goods_count : 0
         * dinner_set_count : 12
         * goods_list_num :
         * note :
         * state : 2
         * eval_state : 0
         * refund_state : 0
         * refund_total : 0.00
         * pay_time : 1518054739
         * deliv_time : 0
         * confirm_time : 0
         * channel : 1
         * pay_channel : 4
         * order_type : 0
         * is_del_user : 0
         * is_del_merch : 0
         * ended : 0
         * created : 1518054705
         * goods : [{"id":"331","user_id":"1","order_num":"7998702","goods_id":"48","spec_id":"0","goods_num":"0","goods_price":"12.00","rebate":"10.00","goods_name":"啧啧啧","goods_spec":"","spec_img":"707"}]
         */

        private String id;
        private String order_num;
        private String trade_no;
        private String user_id;
        private String merch_id;
        private String customer_name;
        private String customer_tel;
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
        private String refund_state;
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
        private String created;
        private List<GoodsBean> goods;

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

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
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

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
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

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 331
             * user_id : 1
             * order_num : 7998702
             * goods_id : 48
             * spec_id : 0
             * goods_num : 0
             * goods_price : 12.00
             * rebate : 10.00
             * goods_name : 啧啧啧
             * goods_spec :
             * spec_img : 707
             */

            private String id;
            private String user_id;
            private String order_num;
            private String goods_id;
            private String spec_id;
            private String goods_num;
            private String goods_price;
            private String rebate;
            private String goods_name;
            private String goods_spec;
            private String spec_img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(String spec_id) {
                this.spec_id = spec_id;
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

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
            }

            public String getSpec_img() {
                return spec_img;
            }

            public void setSpec_img(String spec_img) {
                this.spec_img = spec_img;
            }
        }
    }
}
