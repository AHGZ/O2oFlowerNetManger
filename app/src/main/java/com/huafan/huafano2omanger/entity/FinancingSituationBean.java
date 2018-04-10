package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by heguozhong on 2018/1/19/019.
 * 财务概况
 */

public class FinancingSituationBean implements Serializable{

    /**
     * order_amount : 0
     * income : 0
     * valid_order_count : 0
     * invalid_order_count : 0
     */

    private int order_amount;
    private int income;
    private int valid_order_count;
    private int invalid_order_count;

    public int getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(int order_amount) {
        this.order_amount = order_amount;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getValid_order_count() {
        return valid_order_count;
    }

    public void setValid_order_count(int valid_order_count) {
        this.valid_order_count = valid_order_count;
    }

    public int getInvalid_order_count() {
        return invalid_order_count;
    }

    public void setInvalid_order_count(int invalid_order_count) {
        this.invalid_order_count = invalid_order_count;
    }
}
