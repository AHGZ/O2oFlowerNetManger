package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/15.
 * by--团购列表
 */

public class GroupOnBean implements Serializable{


    /**
     * list : [{"group_id":"5","title":"dsf","total_num":"11","sold_num":"0","used_num":"0","refund_num":"0","appro_state":"1","imgs":"433,434","starttime":1514736000,"endtime":1516550400,"is_sale":"1","created":"1515987753","price":"111.00","Unconsumed":0,"file_path":"img/o2o_good/2018/01-15/0de93b9f38189d1392ce965b02cd500e.jpg"}]
     * count : 1
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * group_id : 5
         * title : dsf
         * total_num : 11
         * sold_num : 0
         * used_num : 0
         * refund_num : 0
         * appro_state : 1
         * imgs : 433,434
         * starttime : 1514736000
         * endtime : 1516550400
         * is_sale : 1
         * created : 1515987753
         * price : 111.00
         * Unconsumed : 0
         * file_path : img/o2o_good/2018/01-15/0de93b9f38189d1392ce965b02cd500e.jpg
         */

        private String group_id;
        private String title;
        private String total_num;
        private String sold_num;
        private String used_num;
        private String refund_num;
        private String appro_state;
        private String imgs;
        private String starttime;
        private String endtime;
        private String is_sale;
        private String created;
        private String price;
        private int Unconsumed;
        private String file_path;
        private boolean isshow;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTotal_num() {
            return total_num;
        }

        public void setTotal_num(String total_num) {
            this.total_num = total_num;
        }

        public String getSold_num() {
            return sold_num;
        }

        public void setSold_num(String sold_num) {
            this.sold_num = sold_num;
        }

        public String getUsed_num() {
            return used_num;
        }

        public void setUsed_num(String used_num) {
            this.used_num = used_num;
        }

        public String getRefund_num() {
            return refund_num;
        }

        public void setRefund_num(String refund_num) {
            this.refund_num = refund_num;
        }

        public String getAppro_state() {
            return appro_state;
        }

        public void setAppro_state(String appro_state) {
            this.appro_state = appro_state;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
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

        public String getIs_sale() {
            return is_sale;
        }

        public void setIs_sale(String is_sale) {
            this.is_sale = is_sale;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getUnconsumed() {
            return Unconsumed;
        }

        public void setUnconsumed(int Unconsumed) {
            this.Unconsumed = Unconsumed;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public boolean getIsshow() {
            return isshow;
        }

        public void setIsshow(boolean isshow) {
            this.isshow = isshow;
        }
    }
}
