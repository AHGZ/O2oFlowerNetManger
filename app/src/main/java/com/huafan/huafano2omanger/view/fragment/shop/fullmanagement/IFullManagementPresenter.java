package com.huafan.huafano2omanger.view.fragment.shop.fullmanagement;

import com.huafan.huafano2omanger.entity.FullManagementBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 满返管理逻辑层
 */

public class IFullManagementPresenter extends IPresenter<IFullManagementView>{
    private final IFullManagementModel iFullManagementModel;

    public IFullManagementPresenter() {
        iFullManagementModel = new IFullManagementModel();
    }

    //门店满返管理查询满返方法
    public void selectFullReturn() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iFullManagementModel.selectFullReturn(new IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean>() {
            @Override
            protected void onSuccess(FullManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FullManagementBean>> data, String message) {
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

    //门店满返管理增加满返方法
    public void addFullReturn(String order_amount,String rebate) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iFullManagementModel.addFullReturn(order_amount,rebate, new IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean>() {
            @Override
            protected void onSuccess(FullManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FullManagementBean>> data, String message) {
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

    //门店满返管理删除满返方法
    public void deleteFullReturn(int rebate_id) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iFullManagementModel.deleteFullReturn(rebate_id, new IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean>() {
            @Override
            protected void onSuccess(FullManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FullManagementBean>> data, String message) {
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

    //门店满返管理修改满返方法
    public void updateFullReturn(int rebate_id,String order_amount, String rebate) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iFullManagementModel.updateFullReturn(rebate_id, order_amount,rebate, new IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean>() {
            @Override
            protected void onSuccess(FullManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FullManagementBean>> data, String message) {
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
        iFullManagementModel.cancel();
    }
}
