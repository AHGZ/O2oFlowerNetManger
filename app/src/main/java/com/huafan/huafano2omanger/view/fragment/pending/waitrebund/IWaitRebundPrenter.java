package com.huafan.huafano2omanger.view.fragment.pending.waitrebund;

import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.WaitReFundBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2018/1/10.
 * by--待退款订单列表
 */

public class IWaitRebundPrenter extends IPresenter<IWaitRebundView> {

    private final IWaitRebundModel iWaitRebundModel;

    @Override
    protected void cancel() {

        iWaitRebundModel.cancel();
    }

    public IWaitRebundPrenter() {

        iWaitRebundModel = new IWaitRebundModel();
    }

    //获取待处理退款订单
    public void getWaitRebund() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String state = getView().getState();
        int page = getView().getPage();
        getView().showDialog();

        iWaitRebundModel.getWaitRebund(state, String.valueOf(page),
                new IModelImpl<ApiResponse<WaitReFundBean>, WaitReFundBean>() {
                    @Override
                    protected void onSuccess(WaitReFundBean data, String message) {

                        getView().hideDiaglog();
                        getView().onSuccessData(data);

                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<WaitReFundBean>> data, String message) {
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


    //同意退款
    public void comfirmRefund() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String refundId = getView().getrefundId();
        getView().showDialog();
        iWaitRebundModel.addirmRefund(refundId, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {

                getView().hideDiaglog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                getView().hideDiaglog();
                getView().onSuccess(message);
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

    //拒绝退款
    public void refuseRefund(String refuse_reason) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String refundId = getView().getrefundId();
        getView().showDialog();
        iWaitRebundModel.refuseRefund(refundId, refuse_reason, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                getView().hideDiaglog();
                getView().onSuccess(message);
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
