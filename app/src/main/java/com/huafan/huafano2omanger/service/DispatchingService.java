package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.DispatchingBean;
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

public interface DispatchingService {
    //获取配送设置
    @POST(ApiUrlConstant.GET_DISPATCHING_SETTING)
    Observable<ApiResponse<DispatchingBean>> getDispatching(@Header("sign") String sign, @Header("token") String token);

    //更改配送设置
    @FormUrlEncoded
    @POST(ApiUrlConstant.CHANGE_DISPATCHING_SETTING)
    Observable<ApiResponse<DispatchingBean>> changeDispatching(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

}
