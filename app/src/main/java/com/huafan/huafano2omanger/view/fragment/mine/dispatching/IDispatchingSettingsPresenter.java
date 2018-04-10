package com.huafan.huafano2omanger.view.fragment.mine.dispatching;

import com.huafan.huafano2omanger.entity.DispatchingBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 配送设置逻辑层
 */

public class IDispatchingSettingsPresenter extends IPresenter<IDispatchingSettingsView>{

    private final IDispatchingSettingsModel iDispatchingSettingsModel;

    public IDispatchingSettingsPresenter() {
        iDispatchingSettingsModel = new IDispatchingSettingsModel();
    }

    //获取接单设置
    public void getDispatching() {

        getView().showDialog();
        iDispatchingSettingsModel.getDispatching(new IModelImpl<ApiResponse<DispatchingBean>, DispatchingBean>() {
            @Override
            protected void onSuccess(DispatchingBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DispatchingBean>> data, String message) {
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

    //更改接单设置
    public void changeDispatching(int distrib,float quota,int self_pick,int prepare_time) {

        getView().showDialog();
        iDispatchingSettingsModel.changeDispatching(distrib,quota,self_pick,prepare_time,new IModelImpl<ApiResponse<DispatchingBean>, DispatchingBean>() {
            @Override
            protected void onSuccess(DispatchingBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<DispatchingBean>> data, String message) {
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
        iDispatchingSettingsModel.cancel();
    }
}
