package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.TeamExamineBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by heguozhong on 2018/1/22.
 * 团购核销
 */

public interface TeamExamineSercice {

    //提交团购码
    @FormUrlEncoded
    @POST(ApiUrlConstant.SUBMIT_CODE)
    Observable<ApiResponse<TeamExamineBean>> submitCode(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

}
