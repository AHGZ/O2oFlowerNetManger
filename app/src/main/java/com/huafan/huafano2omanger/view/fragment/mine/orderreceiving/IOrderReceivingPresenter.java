package com.huafan.huafano2omanger.view.fragment.mine.orderreceiving;

import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 接单设置逻辑层
 */

public class IOrderReceivingPresenter extends IPresenter<IOrderReceivingView> {

    private final IOrderReceivingModel iOrderReceivingModel;

    public IOrderReceivingPresenter() {
        iOrderReceivingModel = new IOrderReceivingModel();
    }

    //获取接单设置
    public void getOrderReceiving(int type) {

        getView().showDialog();
        iOrderReceivingModel.getOrderReceiving(type, new IModelImpl<ApiResponse<OrderReceivingBean>, OrderReceivingBean>() {
            @Override
            protected void onSuccess(OrderReceivingBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<OrderReceivingBean>> data, String message) {
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
    public void changeOrderMessageReceiving(int type, int state) {

        getView().showDialog();
        iOrderReceivingModel.changeOrderMessageReceiving(type, state, new
                IModelImpl<ApiResponse<OrderReceivingBean>, OrderReceivingBean>() {
                    @Override
                    protected void onSuccess(OrderReceivingBean responseBody, String message) {
                        getView().hideDialog();
                        getView().onSuccess(message);

                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<OrderReceivingBean>> data, String message) {
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
        iOrderReceivingModel.cancel();
    }
}