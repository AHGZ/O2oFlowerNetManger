package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.BankCardListBean;
import com.huafan.huafano2omanger.entity.BindCardBean;
import com.huafan.huafano2omanger.entity.GoodTopSortBean;
import com.huafan.huafano2omanger.entity.GoodsDetailBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.SelGoodsListBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by caishen on 2018/1/2.
 * by--
 */

public interface ShopService {


    //添加商品
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELGOODSLIST)
    Observable<ApiResponse<SelGoodsListBean>> getShopList(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //商品修改
    @FormUrlEncoded
    @POST(ApiUrlConstant.UPGOODS)
    Observable<ApiResponse<ReplyBean>> updataShop(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //添加商品
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADDGOODS)
    Observable<ApiResponse<GoodTopSortBean>> addShop(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //删除商品
    @FormUrlEncoded
    @POST(ApiUrlConstant.DELGOODS)
    Observable<ApiResponse<String>> delShop(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //商品详情
    @FormUrlEncoded
    @POST(ApiUrlConstant.GOODSXQ)
    Observable<ApiResponse<GoodsDetailBean>> getShopDetail(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //商品置顶排序
    @FormUrlEncoded
    @POST(ApiUrlConstant.GOODTOPSORT)
    Observable<ApiResponse<GoodTopSortBean>> topSortShop(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //商品对调排序
    @FormUrlEncoded
    @POST(ApiUrlConstant.GOODSWAPSORT)
    Observable<ApiResponse<GoodTopSortBean>> goodSwapSort(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //添加团购
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADDGROUP)
    Observable<ApiResponse<ReplyBean>> commitGroup(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //2.1检查用户登录密码
    @FormUrlEncoded
    @POST(ApiUrlConstant.CHECK_PWD)
    Observable<ApiResponse<String>> check_pwd(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //获取法人信息
    @FormUrlEncoded
    @POST(ApiUrlConstant.GO_BIND_CARD)
    Observable<ApiResponse<BindCardBean>> go_bind_card(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //添加银行卡
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADD_CARD)
    Observable<ApiResponse<ReplyBean>> add_card(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);

    //银行卡信息
    @FormUrlEncoded
    @POST(ApiUrlConstant.CARD_LIST)
    Observable<ApiResponse<BankCardListBean>> getcard_list(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //解绑银行卡
    @FormUrlEncoded
    @POST(ApiUrlConstant.UN_BIND)
    Observable<ApiResponse<String>> un_bind(@Header("sign") String sgign, @Header("token") String token, @FieldMap HashMap<String, String> options);


    //支付宝提现
    @FormUrlEncoded
    @POST(ApiUrlConstant.WITHDRAWALS)
    Observable<ApiResponse<String>> withdraw(@Header("sign") String sign, @Header("token") String token, @FieldMap HashMap<String, String> param);
}
