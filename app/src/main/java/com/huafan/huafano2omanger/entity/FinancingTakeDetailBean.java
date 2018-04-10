package com.huafan.huafano2omanger.entity;

import java.util.List;

/**
 * Created by heguozhong on 2018/1/20/020.
 * 外卖明细
 */

public class FinancingTakeDetailBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * order_num : 22942502
         * order_amount : 166.00
         * merch_income : 6.00
         * completed : 0
         */

        private String order_num;
        private String order_amount;
        private String merch_income;
        private String completed;

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getMerch_income() {
            return merch_income;
        }

        public void setMerch_income(String merch_income) {
            this.merch_income = merch_income;
        }

        public String getCompleted() {
            return completed;
        }

        public void setCompleted(String completed) {
            this.completed = completed;
        }
    }
}
