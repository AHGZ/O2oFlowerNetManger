package com.huafan.huafano2omanger.view.fragment.pending.waitdispose;

import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.SelfMotionBean;
import com.huafan.huafano2omanger.entity.WaitDisposeBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2018/1/9.
 * by--
 */

public class IWaitDisposePrenter extends IPresenter<IWaitDisposeView> {

    private final WaitDisposeModel waitDisposeModel;

    @Override
    protected void cancel() {

        waitDisposeModel.cancel();
    }

    public IWaitDisposePrenter() {

        waitDisposeModel = new WaitDisposeModel();

    }

    //获取待处理订单
    public void getWaitDis() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String state = getView().getState();
        int page = getView().getPage();

        getView().showDialog();

        waitDisposeModel.getWaitDis(state, String.valueOf(page), new IModelImpl<ApiResponse<WaitDisposeBean>, WaitDisposeBean>() {
            @Override
            protected void onSuccess(WaitDisposeBean data, String message) {

                getView().hideDiaglog();
                getView().onSuccessData(data);

            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<WaitDisposeBean>> data, String message) {
                getView().hideDiaglog();
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDiaglog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {
                getView().hideDiaglog();
            }
        });
    }

    //确认接单
    public void confirmOrder() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String orderId = getView().getOrderId();
        getView().showDialog();

        waitDisposeModel.confirmOrder(orderId, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDiaglog();
                getView().onsuccess(message);

            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDiaglog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {
                getView().hideDiaglog();
            }
        });
    }


    //拒绝接单
    public void refuseOrder() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String orderId = getView().getOrderId();
        getView().showDialog();

        waitDisposeModel.refuseOrder(orderId, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDiaglog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {
                getView().hideDiaglog();
            }
        });
    }

    //取消订单
    public void cancelOrder(String s) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String orderId = getView().getOrderId();
        getView().showDialog();
        waitDisposeModel.cancelOrder(orderId, s, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDiaglog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {
                getView().hideDiaglog();
            }
        });
    }

    //确认收货
    public void confirmCom() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String orderId = getView().getOrderId();
        getView().showDialog();
        waitDisposeModel.confirmCom(orderId, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {

                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {

                getView().hideDiaglog();
                getView().onsuccess(message);
            }

            @Override
            protected void onFailure(String code, String message) {

                getView().hideDiaglog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {

                getView().hideDiaglog();
            }
        });
    }

    //获取自动接单的数据
    public void getselOrderInfo() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String orderId = getView().getOrderId();
        getView().showDialog();
        waitDisposeModel.getselOrderInfo(orderId, new IModelImpl<ApiResponse<SelfMotionBean>, SelfMotionBean>() {
            @Override
            protected void onSuccess(SelfMotionBean data, String message) {

                getView().hideDiaglog();
                getView().onsuccessSelf(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SelfMotionBean>> data, String message) {

                getView().hideDiaglog();
            }

            @Override
            protected void onFailure(String code, String message) {

                getView().hideDiaglog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {

                getView().hideDiaglog();
            }
        });
    }
}
