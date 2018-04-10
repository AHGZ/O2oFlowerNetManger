package com.huafan.huafano2omanger.view.fragment.mine.invoice;

import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.view.fragment.mine.orderreceiving.IOrderReceivingModel;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2018/1/8.
 * by--
 */

public class ISettingInviocePrenter extends IPresenter<ISettingInvioceView> {

    private final IOrderReceivingModel iOrderReceivingModel;

    @Override
    protected void cancel() {

    }

    public ISettingInviocePrenter() {

        iOrderReceivingModel = new IOrderReceivingModel();
    }

    //获取设置状态
    public void getOrderReceiving() {

        int type = getView().getType();
        getView().showDialog();

        iOrderReceivingModel.getOrderReceiving(type, new IModelImpl<ApiResponse<OrderReceivingBean>, OrderReceivingBean>() {
            @Override
            protected void onSuccess(OrderReceivingBean data, String message) {
                getView().hideDialog();
                getView().onGetSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<OrderReceivingBean>> data, String message) {
                getView().hideDialog();
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDialog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {

                getView().hideDialog();
            }
        });
    }

    //更改状态
    public void saveSetting() {

        int type = getView().getType();
        int state = getView().getState();
        String quota = getView().getQuota();
        if (state == 0) {
            quota = "";
        } else if (state == -1) {
            getView().onError("请选中状态");
            return;
        }


        getView().showDialog();

        iOrderReceivingModel.changeInvoiceReceiving(type, state, quota, new
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
}
