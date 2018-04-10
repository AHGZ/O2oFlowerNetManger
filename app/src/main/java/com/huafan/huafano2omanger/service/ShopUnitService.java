package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
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
 * 门店商品单位（增删改查）
 */

public interface ShopUnitService {
    //查询商品单位
    @POST(ApiUrlConstant.SELECT_SHOP_UNIT)
    Observable<ApiResponse<ShopUnitBean>> selectShopUnit(@Header("sign") String sign, @Header("token") String token);

    //添加商品单位
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADD_SHOP_UNIT)
    Observable<ApiResponse<ShopUnitBean>> addShopUnit(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //删除商品单位
    @FormUrlEncoded
    @POST(ApiUrlConstant.DELETE_SHOP_UNIT)
    Observable<ApiResponse<ShopUnitBean>> deleteShopUnit(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //修改商品单位
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPDATE_SHOP_UNIT)
    Observable<ApiResponse<ShopUnitBean>> updateShopUnit(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);


}
