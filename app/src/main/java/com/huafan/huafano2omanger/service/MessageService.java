package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.MessaDetailBean;
import com.huafan.huafano2omanger.entity.MessaTypeBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2018/1/30.
 * by--消息
 */

public interface MessageService {

    // 取指定分类消息列表
    @FormUrlEncoded
    @POST(ApiUrlConstant.GET_NOTICE_LIST)
    Observable<ApiResponse<MessaTypeBean>> getNoticeList(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    // 获取消息详情并更改状态
    @FormUrlEncoded
    @POST(ApiUrlConstant.GET_NOTICE_INFO)
    Observable<ApiResponse<MessaDetailBean>> getNoticeInfo(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

}
