package com.huafan.huafano2omanger.view.fragment.shop.financingsituation.financingdetail;

import com.huafan.huafano2omanger.entity.FinancingDetailBean;
import com.huafan.huafano2omanger.entity.FinancingTakeDetailBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.view.fragment.shop.financingsituation.IFinancingSituationModel;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务明细逻辑层
 */

public class IFinancingDetailPresenter extends IPresenter<IFinancingDetailView>{
    private final IFinancingSituationModel iFinancingSituationModel;

    public IFinancingDetailPresenter() {
        iFinancingSituationModel = new IFinancingSituationModel();
    }

    //门店财务概况查询团购明细方法
    public void selectGroupDetail() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        int pages = getView().getPages();
        getView().showDialog();
        iFinancingSituationModel.selectGroupDetail(pages,new IModelImpl<ApiResponse<FinancingDetailBean>, FinancingDetailBean>() {
            @Override
            protected void onSuccess(FinancingDetailBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FinancingDetailBean>> data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDialog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {

            }
        });
    }

    //门店财务概况查询外卖明细方法
    public void selectTakeOutDetail() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        int pages = getView().getPages();
        getView().showDialog();
        iFinancingSituationModel.selectTakeOutDetail(pages,new IModelImpl<ApiResponse<FinancingTakeDetailBean>, FinancingTakeDetailBean>() {
            @Override
            protected void onSuccess(FinancingTakeDetailBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FinancingTakeDetailBean>> data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDialog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {

            }
        });
    }

    @Override
    protected void cancel() {
        iFinancingSituationModel.cancel();
    }
}
