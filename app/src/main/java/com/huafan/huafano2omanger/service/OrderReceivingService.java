package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by heguozhong on 2017/12/29/029.
 * 接单设置
 */

public interface OrderReceivingService {
    //获取接单
    @FormUrlEncoded
    @POST(ApiUrlConstant.GET_ORDER_RECEIVING_SETTING)
    Observable<ApiResponse<OrderReceivingBean>> getOrderReceiving(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //更改接单
    @FormUrlEncoded
    @POST(ApiUrlConstant.CHANGE_ORDER_RECEIVING_SETTING)
    Observable<ApiResponse<OrderReceivingBean>> changeOrderReceiving(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

}
