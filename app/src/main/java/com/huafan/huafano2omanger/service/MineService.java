package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.MapEntity;
import com.huafan.huafano2omanger.entity.O2oMineBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.ShopDetailBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @描述: 我的
 * @创建人：zhangpeisen
 * @创建时间：2018/1/3 上午11:50
 * @修改人：zhangpeisen
 * @修改时间：2018/1/3 上午11:50
 * @修改备注：
 * @throws
 */
public interface MineService {


    /**
     * 个人中心
     */
    @FormUrlEncoded
    @POST(ApiUrlConstant.GET_MERCH_CORE)
    Observable<ApiResponse<O2oMineBean>> merchcore(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //获取地图设置
    @FormUrlEncoded
    @POST(ApiUrlConstant.SETTING_GET_MAP)
    Observable<ApiResponse<String>> settinggetmap(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //更改地图设置
    @FormUrlEncoded
    @POST(ApiUrlConstant.SETTING_UP_MAP)
    Observable<ApiResponse<MapEntity>> settingupmap(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //获取店铺信息
    @FormUrlEncoded
    @POST(ApiUrlConstant.GET_MERCHANT_INFO)
    Observable<ApiResponse<ShopDetailBean>> getShopDetail(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //修改店铺信息
    @FormUrlEncoded
    @POST(ApiUrlConstant.UP_MERCHANT_INFO)
    Observable<ApiResponse<ReplyBean>> up_merchant_info(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

}
