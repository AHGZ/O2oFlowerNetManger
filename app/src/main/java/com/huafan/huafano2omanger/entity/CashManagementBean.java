package com.huafan.huafano2omanger.entity;

import java.util.List;

/**
 * Created by heguozhong on 2018/1/15/015.
 * 提现管理实体类
 */

public class CashManagementBean {


    /**
     * withdraw_money : 0.50
     * withdraw_total : 99.20
     * list : [{"created":"1517882088","amount":"0.10","state":"2","withdraw_mode":"2"}]
     */

    private String withdraw_money;
    private String withdraw_total;
    private List<ListBean> list;

    public String getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(String withdraw_money) {
        this.withdraw_money = withdraw_money;
    }

    public String getWithdraw_total() {
        return withdraw_total;
    }

    public void setWithdraw_total(String withdraw_total) {
        this.withdraw_total = withdraw_total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * created : 1517882088
         * amount : 0.10
         * state : 2
         * withdraw_mode : 2
         */

        private String created;
        private String amount;
        private int state;
        private int withdraw_mode;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getWithdraw_mode() {
            return withdraw_mode;
        }

        public void setWithdraw_mode(int withdraw_mode) {
            this.withdraw_mode = withdraw_mode;
        }
    }
}
