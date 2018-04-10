package com.huafan.huafano2omanger.view.fragment.groupon.addgroup;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.DetailGroupBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.view.fragment.shop.shopmanger.AddShopMangerModel;
import com.rxy.netlib.http.ApiResponse;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caishen on 2018/1/13.
 * by--
 */

public class IAddGroupingPrenter extends IPresenter<IAddGroupingView> {

    private final AddGroupingModel addGroupingModel;
    private AddShopMangerModel addShopMangerModel;
    private String getdescription = "";
    private String imgBanner = "";

    @Override
    protected void cancel() {

        addGroupingModel.cancel();
        addShopMangerModel.cancel();
    }

    public IAddGroupingPrenter() {

        addGroupingModel = new AddGroupingModel();
        addShopMangerModel = new AddShopMangerModel();
    }

    //上传图片
    public void uploadImg() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        List<File> userImgList = getView().getImgList();
        String type = getView().getType();
        //加载进度条
        getView().showDialog();

        addShopMangerModel.upload(type, userImgList, new IModelImpl<ApiResponse<ImgDataBean>, ImgDataBean>() {
            @Override
            protected void onSuccess(ImgDataBean data, String message) {
                getView().hideDialog();
                getView().mofityPhoto(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ImgDataBean>> data, String message) {
                getView().hideDialog();
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDialog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {
                getView().hideDialog();
            }
        });

    }

    //添加审核
    public void commit() throws JSONException {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String gettitle = getView().gettitle();
        if (TextUtils.isEmpty(gettitle)) {
            getView().onError("请输入团购标题");
            return;
        }

        String short_title = getView().getshort_title();
        if (TextUtils.isEmpty(short_title)) {
            getView().onError("请输入短标题");
            return;
        }

        List<String> imags = getView().getimags();
        if (imags != null && imags.size() <= 0) {
            getView().onError("请上传轮播图图片");
            return;
        }

        if (imags != null && imags.size() > 0) {

            imgBanner = "";
            for (int i = 0; i < imags.size(); i++) {
                imgBanner += imags.get(i) + ",";
            }
            imgBanner = imgBanner.toString()
                    .substring(0, imgBanner.toString().lastIndexOf(","));
        }

        String intro = getView().getintro();//团购简介
        if (TextUtils.isEmpty(intro)) {
            getView().onError("请填写团购简介");
            return;
        }

        String market_price = getView().getmarket_price();
        if (TextUtils.isEmpty(market_price)) {
            getView().onError("请填写门市价");
            return;
        }else if (market_price.equals(".")){
            getView().onError("请填写正确的门市价，不能只输入字符");
            return;
        }

        String price = getView().getprice();
        if (TextUtils.isEmpty(price)) {
            getView().onError("请填写参团价");
            return;
        }else if (price.equals(".")){
            getView().onError("请填写正确的参团价，不能只输入字符");
            return;
        }

        String supply_price = getView().getsupply_price();
        if (TextUtils.isEmpty(supply_price)) {
            getView().onError("请填写让利价");
            return;
        }else if (supply_price.equals(".")){
            getView().onError("请填写正确的让利价，不能只输入字符");
            return;
        }

        if (Float.parseFloat(supply_price)>Float.parseFloat(price)){
            getView().onError("让利价不能大于参团价");
            return;
        }
        String getstock = getView().getstock();
        if (TextUtils.isEmpty(getstock)||getstock.equals("0")) {
            getView().onError("请填写参团人数,并且参团人数不能为0");
            return;
        }


        List<DetailGroupBean.GroupContentBean> group_content = getView().group_content();
        if (group_content == null || group_content.size() <= 0) {
            getView().onError("请填写团单内容");
            return;
        }

        List<String> description = getView().getdescription();//团购详情描述图片
        if (description != null && description.size() > 0) {

            getdescription = "";
            for (int i = 0; i < description.size(); i++) {
                getdescription += description.get(i) + ",";
            }
            getdescription = getdescription.toString()
                    .substring(0, getdescription.toString().lastIndexOf(","));
        }


        String getstarttime = getView().getstarttime();
        if (TextUtils.isEmpty(getstarttime)) {
            getView().onError("请选择有效期开始时间");
            return;
        }

        String getendtime = getView().getendtime();
        if (TextUtils.isEmpty(getendtime)) {
            getView().onError("请选择有效期结束时间");
            return;
        }

        String day_start = getView().getday_start();
        if (TextUtils.isEmpty(day_start)) {
            getView().onError("请选择可用开始时间");
            return;
        }

        String day_end = getView().getday_end();
        if (TextUtils.isEmpty(day_end)) {
            getView().onError("请选择可用结束时间");
            return;
        }

        String holiday_usable = getView().getholiday_usable();
        if (TextUtils.isEmpty(holiday_usable)) {
            getView().onError("请选择节假日是否可用");
            return;
        }

        String weekend_usable = getView().getweekend_usable();
        if (TextUtils.isEmpty(weekend_usable)) {
            getView().onError("请选择周末是否可用");
            return;
        }

        String need_resv = getView().getneed_resv();
        if (TextUtils.isEmpty(need_resv)) {
            getView().onError("请选择是否需要预约");
            return;
        }
        String resv_tips = getView().getresv_tips();
        if (need_resv.equals("1")) {
            if (TextUtils.isEmpty(resv_tips)) {
                getView().onError("请填写预约提示");
                return;
            }
        } else {
            if (TextUtils.isEmpty(resv_tips)) {
                resv_tips = "";
            }
        }

        String getrules = getView().getrules();
        if (TextUtils.isEmpty(getrules)) {
            getView().onError("请填写使用规则");
            return;
        }

        String gettips = getView().gettips();//温馨提示
        if (TextUtils.isEmpty(gettips)) {
            gettips = "";
        }

        String getkeywords = getView().getkeywords();//团购关键词
        if (TextUtils.isEmpty(getkeywords)) {
            getkeywords = "";
        }

        String isnew = getView().isNew();
        if (TextUtils.isEmpty(isnew)) {
            getView().onError("请选择是否新品");
            return;
        }

        String is_sale = getView().getis_sale();
        if (TextUtils.isEmpty(is_sale)) {
            getView().onError("请选择是否上架");
            return;
        }

        String is_takeaway = getView().getis_takeaway();
        if (TextUtils.isEmpty(is_takeaway)) {
            getView().onError("请选择是否支持打包");
            return;
        }

        String consume_avg = getView().getconsume_avg();
        if (TextUtils.isEmpty(consume_avg)) {
            getView().onError("请填写人均消费");
            return;
        }else if (consume_avg.equals(".")){
            getView().onError("请填写正确的人均消费，不能只输入字符");
            return;
        }

        String fitType = getView().getfitType();
        if (TextUtils.isEmpty(fitType)) {
            getView().onError("请选择适用类型");
            return;
        }

        //加载进度条
        getView().showDialog();
        addShopMangerModel.commit(gettitle, short_title, imgBanner, fitType, intro, market_price,
                price, supply_price, getstock, group_content, getdescription, getstarttime, getendtime, day_start, day_end,
                holiday_usable, weekend_usable, need_resv, resv_tips, getrules, gettips, getkeywords, isnew, is_sale, is_takeaway,
                consume_avg, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
                    @Override
                    protected void onSuccess(ReplyBean data, String message) {

                        getView().hideDialog();
                        getView().onSuccess(message);
                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                        getView().hideDialog();
                        getView().onSuccess(message);
                    }

                    @Override
                    protected void onFailure(String code, String message) {
                        getView().hideDialog();
                        getView().onError(message);
                    }

                    @Override
                    protected void onSuccess() {
                        getView().hideDialog();
                    }
                });
    }

    //修改数据
    public void update() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String gettitle = getView().gettitle();
        if (TextUtils.isEmpty(gettitle)) {
            getView().onError("请输入团购标题");
            return;
        }

        String short_title = getView().getshort_title();
        if (TextUtils.isEmpty(short_title)) {
            getView().onError("请输入短标题");
            return;
        }

        List<String> imags = getView().getimags();
        if (imags != null && imags.size() <= 0) {
            getView().onError("请上传轮播图图片");
            return;
        }

        if (imags != null && imags.size() > 0) {

            imgBanner = "";
            for (int i = 0; i < imags.size(); i++) {
                imgBanner += imags.get(i) + ",";
            }
            imgBanner = imgBanner.toString()
                    .substring(0, imgBanner.toString().lastIndexOf(","));
        }

        String intro = getView().getintro();//团购简介
        if (TextUtils.isEmpty(intro)) {
            getView().onError("请填写团购简介");
            return;
        }

        String market_price = getView().getmarket_price();
        if (TextUtils.isEmpty(market_price)) {
            getView().onError("请填写门市价");
            return;
        }else if (market_price.equals(".")){
            getView().onError("请填写正确的门市价，不能只输入字符");
            return;
        }

        String price = getView().getprice();
        if (TextUtils.isEmpty(price)) {
            getView().onError("请填写参团价");
            return;
        }else if (price.equals(".")){
            getView().onError("请填写正确的参团价，不能只输入字符");
            return;
        }

        String supply_price = getView().getsupply_price();
        if (TextUtils.isEmpty(supply_price)) {
            getView().onError("请填写让利价");
            return;
        }else if (supply_price.equals(".")){
            getView().onError("请填写正确的让利价，不能只输入字符");
            return;
        }

        if (Float.parseFloat(supply_price)>Float.parseFloat(price)){
            getView().onError("让利价不能大于参团价");
            return;
        }

        String getstock = getView().getstock();
        if (TextUtils.isEmpty(getstock)||getstock.equals("0")) {
            getView().onError("请填写参团人数,并且参团人数不能为0");
            return;
        }

        List<DetailGroupBean.GroupContentBean> group_content = getView().group_content();
        if (group_content == null || group_content.size() <= 0) {
            getView().onError("请填写团单内容");
            return;
        }

        List<String> description = getView().getdescription();//团购详情描述图片
        if (description != null && description.size() > 0) {

            getdescription = "";
            for (int i = 0; i < description.size(); i++) {
                getdescription += description.get(i) + ",";
            }
            getdescription = getdescription.toString()
                    .substring(0, getdescription.toString().lastIndexOf(","));
        }


        String getstarttime = getView().getstarttime();
        if (TextUtils.isEmpty(getstarttime)) {
            getView().onError("请选择有效期开始时间");
            return;
        }

        String getendtime = getView().getendtime();
        if (TextUtils.isEmpty(getendtime)) {
            getView().onError("请选择有效期结束时间");
            return;
        }

        String day_start = getView().getday_start();
        if (TextUtils.isEmpty(day_start)) {
            getView().onError("请选择可用开始时间");
            return;
        }

        String day_end = getView().getday_end();
        if (TextUtils.isEmpty(day_end)) {
            getView().onError("请选择可用结束时间");
            return;
        }

        String holiday_usable = getView().getholiday_usable();
        if (TextUtils.isEmpty(holiday_usable)) {
            getView().onError("请选择节假日是否可用");
            return;
        }

        String weekend_usable = getView().getweekend_usable();
        if (TextUtils.isEmpty(weekend_usable)) {
            getView().onError("请选择周末是否可用");
            return;
        }

        String need_resv = getView().getneed_resv();
        if (TextUtils.isEmpty(need_resv)) {
            getView().onError("请选择是否需要预约");
            return;
        }
        String resv_tips = getView().getresv_tips();
        if (need_resv.equals("1")) {
            if (TextUtils.isEmpty(resv_tips)) {
                getView().onError("请填写预约提示");
                return;
            }
        } else {
            if (TextUtils.isEmpty(resv_tips)) {
                resv_tips = "";
            }
        }


        String getrules = getView().getrules();
        if (TextUtils.isEmpty(getrules)) {
            getView().onError("请填写使用规则");
            return;
        }

        String gettips = getView().gettips();//温馨提示
        if (TextUtils.isEmpty(gettips)) {
            gettips = "";
        }

        String getkeywords = getView().getkeywords();//团购关键词
        if (TextUtils.isEmpty(getkeywords)) {
            getkeywords = "";
        }

        String isnew = getView().isNew();
        if (TextUtils.isEmpty(isnew)) {
            getView().onError("请选择是否新品");
            return;
        }

        String is_sale = getView().getis_sale();
        if (TextUtils.isEmpty(is_sale)) {
            getView().onError("请选择是否上架");
            return;
        }

        String is_takeaway = getView().getis_takeaway();
        if (TextUtils.isEmpty(is_takeaway)) {
            getView().onError("请选择是否支持打包");
            return;
        }

        String consume_avg = getView().getconsume_avg();
        if (TextUtils.isEmpty(consume_avg)) {
            getView().onError("请填写人均消费");
            return;
        }else if (consume_avg.equals(".")){
            getView().onError("请填写正确的人均消费，不能只输入字符");
            return;
        }

        String fitType = getView().getfitType();
        if (TextUtils.isEmpty(fitType)) {
            getView().onError("请选择适用类型");
            return;
        }

        String img_id = getView().getimg_id();
        String desc_id = getView().getdesc_id();
        String groupId = getView().getGroupId();


        //加载进度条
        getView().showDialog();
        addShopMangerModel.updataGroup(gettitle, short_title, imgBanner, fitType, intro, market_price,
                price, supply_price, getstock, group_content, getdescription, getstarttime, getendtime, day_start, day_end,
                holiday_usable, weekend_usable, need_resv, resv_tips, getrules, gettips, getkeywords, isnew, is_sale, is_takeaway,
                consume_avg, img_id, desc_id, groupId, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
                    @Override
                    protected void onSuccess(ReplyBean data, String message) {

                        getView().hideDialog();
                        getView().onUpdataSuccess(message);
                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                        getView().hideDialog();
                        getView().onUpdataSuccess(message);
                    }

                    @Override
                    protected void onFailure(String code, String message) {
                        getView().hideDialog();
                        getView().onError(message);
                    }

                    @Override
                    protected void onSuccess() {
                        getView().hideDialog();
                    }
                });
    }

    //获取团购详情
    public void getGroupDetail() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String groupId = getView().getGroupId();

        //加载进度条
        getView().showDialog();
        addShopMangerModel.getGroupDetail(groupId, new IModelImpl<ApiResponse<DetailGroupBean>, DetailGroupBean>() {
            @Override
            protected void onSuccess(DetailGroupBean data, String message) {
                getView().hideDialog();
                getView().SuccessData(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DetailGroupBean>> data, String message) {
                getView().hideDialog();

            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDialog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {
                getView().hideDialog();
            }
        });
    }
}
