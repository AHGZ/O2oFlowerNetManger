package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.SortManagementBean;
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
 * 门店分类管理（增删改查）
 */

public interface SortManagementService {
    //查询商品分类
    @POST(ApiUrlConstant.SELECT_SHOP_CLASSIFY)
    Observable<ApiResponse<SortManagementBean>> selectSortShop(@Header("sign") String sign, @Header("token") String token);

    //添加商品分类
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADD_SHOP_CLASSIFY)
    Observable<ApiResponse<SortManagementBean>> addSortShop(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //删除商品分类
    @FormUrlEncoded
    @POST(ApiUrlConstant.DELETE_SHOP_CLASSIFY)
    Observable<ApiResponse<SortManagementBean>> deleteSortShop(@Header("sign") String sign,@Header("token") String token, @FieldMap HashMap<String, String> options);

    //修改商品分类
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPDATE_SHOP_CLASSIFY)
    Observable<ApiResponse<SortManagementBean>> updateSortShop(@Header("sign") String sign,@Header("token") String token, @FieldMap HashMap<String, String> options);


}
