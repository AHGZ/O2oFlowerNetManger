package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.FullManagementBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by heguozhong on 2017/12/29/029.
 * 门店满返管理（增删改查）
 */

public interface FullManagementService {
    //查询商品单位
    @POST(ApiUrlConstant.SELECT_FULL_RETURN)
    Observable<ApiResponse<FullManagementBean>> selectFullReturn(@Header("sign") String sign, @Header("token") String token);

    //添加商品单位
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADD_FULL_RETURN)
    Observable<ApiResponse<FullManagementBean>> addFullReturn(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //删除商品单位
    @FormUrlEncoded
    @POST(ApiUrlConstant.DELETE_FULL_RETURN)
    Observable<ApiResponse<FullManagementBean>> deleteFullReturn(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //修改商品单位
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPDATE_FULL_RETURN)
    Observable<ApiResponse<FullManagementBean>> updateFullReturn(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);


}
