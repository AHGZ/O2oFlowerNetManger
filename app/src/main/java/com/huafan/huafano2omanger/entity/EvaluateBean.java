package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/9.
 * by--订单评价
 */

public class EvaluateBean implements Serializable{


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * user_id : 30
         * imgs : 279,278
         * reply :
         * reply_time : 0
         * order_num : 14234424
         * score : 5
         * distrib_service_score : 5
         * content : 阿打发斯蒂芬
         * is_anon : 1
         * created : 1514943631
         * nickname : 匿名用户
         * imgurl : ["img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg","img/o2o_good/2018/01-03/d74038ae515a50e5a3a88d179f9041da.jpg"]
         * file_path :
         * name :
         */

        private String eval_id;
        private String user_id;
        private String imgs;
        private String reply;
        private String reply_time;
        private String order_num;
        private String score;
        private String distrib_service_score;
        private String content;
        private String is_anon;
        private String created;
        private String nickname;
        private String file_path;
        private String name;
        private List<String> imgurl;
        private boolean isshow;

        public boolean getIsshow() {
            return isshow;
        }

        public void setIsshow(boolean isshow) {
            this.isshow = isshow;
        }


        public String getId() {
            return eval_id;
        }

        public void setId(String id) {
            this.eval_id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getDistrib_service_score() {
            return distrib_service_score;
        }

        public void setDistrib_service_score(String distrib_service_score) {
            this.distrib_service_score = distrib_service_score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_anon() {
            return is_anon;
        }

        public void setIs_anon(String is_anon) {
            this.is_anon = is_anon;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getImgurl() {
            return imgurl;
        }

        public void setImgurl(List<String> imgurl) {
            this.imgurl = imgurl;
        }


    }
}
