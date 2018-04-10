package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.DetailGroupBean;
import com.huafan.huafano2omanger.entity.GroupOnBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2018/1/15.
 * by--
 */

public interface GroupSercice {

    //获取团购列表
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELGROUP)
    Observable<ApiResponse<GroupOnBean>> getGroup(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //获取团购详情
    @FormUrlEncoded
    @POST(ApiUrlConstant.GROUPXQ)
    Observable<ApiResponse<DetailGroupBean>> getGroupDetail(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //修改团购详情
    @FormUrlEncoded
    @POST(ApiUrlConstant.GROUPUP)
    Observable<ApiResponse<ReplyBean>> updataGroup(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //修改团购详情
    @FormUrlEncoded
    @POST(ApiUrlConstant.GROUPSTATE)
    Observable<ApiResponse<ReplyBean>> changeState(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

}
