package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.UpdatePwd;
import com.huafan.huafano2omanger.entity.UserInfo;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2017/12/21.
 * by--登录
 */

public interface LoginService {


    //忘记密码
    @FormUrlEncoded
    @POST(ApiUrlConstant.RESET_ACCOUNT_PWD)
    Observable<ApiResponse<UpdatePwd>> updatePwd(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //登录
    @FormUrlEncoded
    @POST(ApiUrlConstant.HFW_LOGIN)
    Observable<ApiResponse<UserInfo>> login(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //修改密码
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPDATE_USER_PWD)
    Observable<ApiResponse<String>> updateLoginPwd(@Header("sign") String sign,@Header("token") String token, @FieldMap HashMap<String, String> options);


}
