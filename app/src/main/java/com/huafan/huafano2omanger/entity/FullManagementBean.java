package com.huafan.huafano2omanger.entity;

import java.util.List;

/**
 * Created by heguozhong on 2018/1/15/015.
 * 满返管理实体类
 */

public class FullManagementBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * rebate_id : 2
         * order_amount : 100.00
         * rebate : 20.00
         */

        private int rebate_id;
        private String order_amount;
        private String rebate;

        public int getRebate_id() {
            return rebate_id;
        }

        public void setRebate_id(int rebate_id) {
            this.rebate_id = rebate_id;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }
    }
}
