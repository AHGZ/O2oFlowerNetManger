package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/30.
 * by--银行卡列表数据
 */

public class BankCardListBean implements Serializable{


    private List<ListsBean> lists;

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean {
        /**
         * id : 1
         * card_num : 130127199112210930
         * bank_name : dsadasd
         */

        private String id;
        private String card_num;
        private String bank_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
}
