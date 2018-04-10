package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heguozhong on 2017/12/27/027.
 * 营业设置Bean类
 */

public class DobusinessBean implements Serializable{

    /**
     * lists : [{"id":"2","starttime":"37800","endtime":"63000","distrib_money":"6.00"}]
     * state : 0
     */

    private int state;
    private List<ListsBean> lists;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean {
        /**
         * id : 2
         * starttime : 37800
         * endtime : 63000
         * distrib_money : 6.00
         */

        private int id;
        private int starttime;
        private int endtime;
        private float distrib_money;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStarttime() {
            return starttime;
        }

        public void setStarttime(int starttime) {
            this.starttime = starttime;
        }

        public int getEndtime() {
            return endtime;
        }

        public void setEndtime(int endtime) {
            this.endtime = endtime;
        }

        public float getDistrib_money() {
            return distrib_money;
        }

        public void setDistrib_money(float distrib_money) {
            this.distrib_money = distrib_money;
        }
    }
}
