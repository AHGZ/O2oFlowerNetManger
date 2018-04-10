package com.huafan.huafano2omanger.service;

import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.FinancingDetailBean;
import com.huafan.huafano2omanger.entity.FinancingSituationBean;
import com.huafan.huafano2omanger.entity.FinancingTakeDetailBean;
import com.rxy.netlib.http.ApiResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by heguozhong on 2017/12/29/029.
 * 门店财务概况
 */

public interface FinancingSituationService {
    //查询财务概况
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELECT_FINANCING_DETAIL)
    Observable<ApiResponse<FinancingSituationBean>> selectFinancingSituation(@Header("sign") String sign, @Header("token") String token,@FieldMap Map<String, String> options);

    //查询财务概况团购明细
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELECT_GROUP_DETAIL)
    Observable<ApiResponse<FinancingDetailBean>> selectGroupDetail(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

    //查询财务概况外卖明细
    @FormUrlEncoded
    @POST(ApiUrlConstant.SELECT_TAKEOUT_DETAIL)
    Observable<ApiResponse<FinancingTakeDetailBean>> selectTakeOutDetail(@Header("sign") String sign, @Header("token") String token, @FieldMap Map<String, String> options);

}
