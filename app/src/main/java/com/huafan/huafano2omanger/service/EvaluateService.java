package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.EvaluateBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2018/1/9.
 * by--评价管理
 */

public interface EvaluateService {

    /**
     * 获取订单评价列表
     */
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELGOODSEVAL)
    Observable<ApiResponse<EvaluateBean>> getEvaluate(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    @FormUrlEncoded
    @POST(ApiUrlConstant.HFGOODSEVAL)
    Observable<ApiResponse<ReplyBean>> replyEvaluate(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //获取商家评价列表
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELEVAL)
    Observable<ApiResponse<EvaluateBean>> getMerticEvaluate(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //回复商家评价
    @FormUrlEncoded
    @POST(ApiUrlConstant.HFEVAL)
    Observable<ApiResponse<ReplyBean>> replyMerticEvaluate(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

}
