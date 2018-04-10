package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.SelfMotionBean;
import com.huafan.huafano2omanger.entity.WaitDisposeBean;
import com.huafan.huafano2omanger.entity.WaitReFundBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2018/1/9.
 * by--
 */

public interface WaitDisposeService {


    //获取待处理订单
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELORDER)
    Observable<ApiResponse<WaitDisposeBean>> getWaitDis(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);


    //获取退款列表数据
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELREFUND)
    Observable<ApiResponse<WaitReFundBean>> getWaitRebund(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);


    //确认接单
    @FormUrlEncoded
    @POST(ApiUrlConstant.CONFIRMORDER)
    Observable<ApiResponse<ReplyBean>> confirmOrder(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //拒绝接单
    @FormUrlEncoded
    @POST(ApiUrlConstant.REFUSINGORDER)
    Observable<ApiResponse<ReplyBean>> refuseOrder(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //同意退款
    @FormUrlEncoded
    @POST(ApiUrlConstant.TYREFUND)
    Observable<ApiResponse<ReplyBean>> addirmRefund(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //拒绝退款
    @FormUrlEncoded
    @POST(ApiUrlConstant.REFUSINGREFUND)
    Observable<ApiResponse<ReplyBean>> refuseRefund(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //拒绝退款
    @FormUrlEncoded
    @POST(ApiUrlConstant.CANCELORDER)
    Observable<ApiResponse<ReplyBean>> cancelOrder(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(ApiUrlConstant.CONFIRMCOM)
    Observable<ApiResponse<ReplyBean>> confirmCom(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);


    @FormUrlEncoded
    @POST(ApiUrlConstant.SELORDERINFO)
    Observable<ApiResponse<SelfMotionBean>> getselOrderInfo(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);
}
