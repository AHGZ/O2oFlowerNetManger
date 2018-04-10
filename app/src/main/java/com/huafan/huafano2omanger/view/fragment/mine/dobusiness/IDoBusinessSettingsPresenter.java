package com.huafan.huafano2omanger.view.fragment.mine.dobusiness;

import com.huafan.huafano2omanger.entity.DobusinessBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 营业设置逻辑层
 */

public class IDoBusinessSettingsPresenter extends IPresenter<IDoBusinessSettingsView> {
    private final IDoBusinessSettingsModel iDoBusinessSettingsModel;

    public IDoBusinessSettingsPresenter() {
        iDoBusinessSettingsModel = new IDoBusinessSettingsModel();
    }

    //获取营业设置
    public void getDobusiness() {

        getView().showDialog();
        iDoBusinessSettingsModel.getDobusiness(new IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean>() {
            @Override
            protected void onSuccess(DobusinessBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DobusinessBean>> data, String message) {
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

    //修改营业设置
    public void updateDobusiness(int state) {

        getView().showDialog();
        iDoBusinessSettingsModel.updateDobusiness(state, new IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean>() {
            @Override
            protected void onSuccess(DobusinessBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DobusinessBean>> data, String message) {
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

    //添加营业时间
    public void addDobusinessTime(String starttime, String endtime, float distrib_money) {

        getView().showDialog();
        iDoBusinessSettingsModel.addDobusinessTime(starttime, endtime, distrib_money,new IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean>() {
            @Override
            protected void onSuccess(DobusinessBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DobusinessBean>> data, String message) {
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

    //删除营业时间
    public void deleteDobusinessTime(int business_id) {

        getView().showDialog();
        iDoBusinessSettingsModel.deleteDobusinessTime(business_id,new IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean>() {
            @Override
            protected void onSuccess(DobusinessBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DobusinessBean>> data, String message) {
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

    //修改营业时间
    public void updateDobusinessTime(int business_id, String starttime, String endtime, float distrib_money) {

        getView().showDialog();
        iDoBusinessSettingsModel.updateDobusinessTime(business_id, starttime, endtime, distrib_money, new IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean>() {
            @Override
            protected void onSuccess(DobusinessBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DobusinessBean>> data, String message) {
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
        iDoBusinessSettingsModel.cancel();
    }
}
