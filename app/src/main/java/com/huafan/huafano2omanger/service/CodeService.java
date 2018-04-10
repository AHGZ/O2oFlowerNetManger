package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.CheckMobileBean;
import com.huafan.huafano2omanger.entity.SendCodeBean;
import com.rxy.netlib.http.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2017/12/21.
 * by--
 */

public interface CodeService {

    @FormUrlEncoded
    @POST(ApiUrlConstant.HFW_CHECK_MOBILE)
    Observable<ApiResponse<CheckMobileBean>> checkMobile(@Header("sign") String sign, @Header("token") String token, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST(ApiUrlConstant.HFW_SENDMOBILECODE)
    Observable<ApiResponse<SendCodeBean>> sendCode(@Header("sign") String sign, @Header("token") String token, @Field("mobile") String mobile);
}
