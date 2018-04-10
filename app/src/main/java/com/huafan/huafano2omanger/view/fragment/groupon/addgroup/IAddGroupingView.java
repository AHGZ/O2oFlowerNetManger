package com.huafan.huafano2omanger.view.fragment.groupon.addgroup;

import com.huafan.huafano2omanger.entity.DetailGroupBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;

import java.io.File;
import java.util.List;

/**
 * Created by caishen on 2018/1/13.
 * by--
 */

interface IAddGroupingView {
    void onError(String s);

    List<File> getImgList();

    String getType();

    void showDialog();

    void hideDialog();

    void mofityPhoto(ImgDataBean data);

    String gettitle();

    String getshort_title();

    List<String> getimags();

    String getintro();

    String getmarket_price();

    String getprice();

    String getsupply_price();

    String getstock();

    List<DetailGroupBean.GroupContentBean> group_content();

    List<String> getdescription();

    String getstarttime();

    String getendtime();

    String getday_start();

    String getday_end();

    String getholiday_usable();

    String getweekend_usable();

    String getneed_resv();

    String getresv_tips();

    String getrules();

    String gettips();

    String getkeywords();

    String isNew();

    String getis_sale();

    String getis_takeaway();

    String getconsume_avg();

    String getfitType();

    void onSuccess(String message);

    String getGroupId();

    void SuccessData(DetailGroupBean data);

    void onUpdataSuccess(String message);

    String getimg_id();

    String getdesc_id();
}
