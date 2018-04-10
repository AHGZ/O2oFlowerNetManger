package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.DobusinessBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by heguozhong on 2017/12/29/029.
 * 营业设置
 */

public interface DobusinessService {
    //获取营业设置
    @POST(ApiUrlConstant.GET_DOBUSINESS_SETTING)
    Observable<ApiResponse<DobusinessBean>> getDobusiness(@Header("sign") String sign, @Header("token") String token);

    //更改营业设置
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPDATE_DOBUSINESS_SETTING)
    Observable<ApiResponse<DobusinessBean>> updateDobusiness(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //更改营业时间
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPDATE_DOBUSINESS_TIME)
    Observable<ApiResponse<DobusinessBean>> updateDobusinessTime(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //删除营业时间
    @FormUrlEncoded
    @POST(ApiUrlConstant.DELETE_DOBUSINESS_TIME)
    Observable<ApiResponse<DobusinessBean>> deleteDobusinessTime(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //添加营业时间
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADD_DOBUSINESS_TIME)
    Observable<ApiResponse<DobusinessBean>> addDobusinessTime(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);
}
