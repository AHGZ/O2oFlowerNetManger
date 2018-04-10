package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.entity.WithDrawInfoBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by heguozhong on 2017/12/29/029.
 * 门店提现管理
 */

public interface CashManagementService {
    //查询提现
    @POST(ApiUrlConstant.CASH)
    Observable<ApiResponse<CashManagementBean>> selectCash(@Header("sign") String sign, @Header("token") String token);

    //添加提现
    @FormUrlEncoded
    @POST(ApiUrlConstant.ADD_CASH)
    Observable<ApiResponse<CashManagementBean>> addCash(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //提现到银行卡
    @FormUrlEncoded
    @POST(ApiUrlConstant.WITHDRAWALS_BANKCARD)
    Observable<ApiResponse<String>> withdrawalsToBankCard(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //查看提现
    @POST(ApiUrlConstant.HFW_SELWITHDRAWALS)
    Observable<ApiResponse<WithDrawInfoBean>> selwithdrawals(@Header("sign") String sign, @Header("token") String token);

}
