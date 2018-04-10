package com.huafan.huafano2omanger.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caishen on 2018/1/16.
 * by--团购详情
 */

public class DetailGroupBean implements Serializable{


    /**
     * id : 13
     * merch_id : 1
     * province_id : 110000
     * city_id : 110100
     * district_id : 110101
     * title : 标题
     * short_title : 短标题
     * fit_type : 1
     * intro : info
     * total_num : 20
     * sold_num : 0
     * used_num : 0
     * refund_num : 0
     * appro_state : 1
     * market_price : 100.00
     * price : 90.00
     * supply_price : 10.00
     * stock : 20
     * imgs : img/o2o_good/2018/01-16/06ee64fc3dc337417752f984c95e8194.jpg
     * consume_avg : 98.00
     * description : img/o2o_good/2018/01-16/a8bb927b82d57916a13089e41fe8975f.jpg,img/o2o_good/2018/01-16/92ae6b57e4e0fc43c0c1622f57475b1e.jpg,img/o2o_good/2018/01-16/fbed11b5ea62bf32d5303059049b67e2.jpg
     * starttime : 2018-01-15
     * endtime : 2018-01-16
     * day_start : 01:17
     * day_end : 01:19
     * holiday_usable : 1
     * weekend_usable : 1
     * need_resv : 1
     * resv_tips : resc_tips
     * rules :
     * tips : tips
     * is_new : 1
     * is_sale : 1
     * is_takeaway : 1
     * keywords : keywords
     * eval_score_total : 0
     * eval_times : 0
     * eval_score : 0.0
     * created : 1516087278
     * img_id : 558
     * desc_id : 559,560,561
     * group_content : [{"id":"10","goods_name":"name","num":"1","price":"10.00"}]
     */

    private String id;
    private String merch_id;
    private String province_id;
    private String city_id;
    private String district_id;
    private String title;
    private String short_title;
    private String fit_type;
    private String intro;
    private String total_num;
    private String sold_num;
    private String used_num;
    private String refund_num;
    private String appro_state;
    private String market_price;
    private String price;
    private String supply_price;
    private String stock;
    private String imgs;
    private String consume_avg;
    private String description;
    private String starttime;
    private String endtime;
    private String day_start;
    private String day_end;
    private String holiday_usable;
    private String weekend_usable;
    private String need_resv;
    private String resv_tips;
    private String rules;
    private String tips;
    private String is_new;
    private String is_sale;
    private String is_takeaway;
    private String keywords;
    private String eval_score_total;
    private String eval_times;
    private String eval_score;
    private String created;
    private String img_id;
    private String desc_id;
    private List<GroupContentBean> group_content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerch_id() {
        return merch_id;
    }

    public void setMerch_id(String merch_id) {
        this.merch_id = merch_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getFit_type() {
        return fit_type;
    }

    public void setFit_type(String fit_type) {
        this.fit_type = fit_type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupply_price() {
        return supply_price;
    }

    public void setSupply_price(String supply_price) {
        this.supply_price = supply_price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getConsume_avg() {
        return consume_avg;
    }

    public void setConsume_avg(String consume_avg) {
        this.consume_avg = consume_avg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDay_start() {
        return day_start;
    }

    public void setDay_start(String day_start) {
        this.day_start = day_start;
    }

    public String getDay_end() {
        return day_end;
    }

    public void setDay_end(String day_end) {
        this.day_end = day_end;
    }

    public String getHoliday_usable() {
        return holiday_usable;
    }

    public void setHoliday_usable(String holiday_usable) {
        this.holiday_usable = holiday_usable;
    }

    public String getWeekend_usable() {
        return weekend_usable;
    }

    public void setWeekend_usable(String weekend_usable) {
        this.weekend_usable = weekend_usable;
    }

    public String getNeed_resv() {
        return need_resv;
    }

    public void setNeed_resv(String need_resv) {
        this.need_resv = need_resv;
    }

    public String getResv_tips() {
        return resv_tips;
    }

    public void setResv_tips(String resv_tips) {
        this.resv_tips = resv_tips;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_sale() {
        return is_sale;
    }

    public void setIs_sale(String is_sale) {
        this.is_sale = is_sale;
    }

    public String getIs_takeaway() {
        return is_takeaway;
    }

    public void setIs_takeaway(String is_takeaway) {
        this.is_takeaway = is_takeaway;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getEval_score_total() {
        return eval_score_total;
    }

    public void setEval_score_total(String eval_score_total) {
        this.eval_score_total = eval_score_total;
    }

    public String getEval_times() {
        return eval_times;
    }

    public void setEval_times(String eval_times) {
        this.eval_times = eval_times;
    }

    public String getEval_score() {
        return eval_score;
    }

    public void setEval_score(String eval_score) {
        this.eval_score = eval_score;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getDesc_id() {
        return desc_id;
    }

    public void setDesc_id(String desc_id) {
        this.desc_id = desc_id;
    }

    public List<GroupContentBean> getGroup_content() {
        return group_content;
    }

    public void setGroup_content(List<GroupContentBean> group_content) {
        this.group_content = group_content;
    }

    public static class GroupContentBean {
        /**
         * id : 10
         * goods_name : name
         * num : 1
         * price : 10.00
         */

        private String id;
        private String goods_name;
        private String num;
        private float price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
}
