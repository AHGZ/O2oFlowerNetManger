package com.huafan.huafano2omanger.view.fragment.shop.evaluate.good;

import com.huafan.huafano2omanger.entity.EvaluateBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2017/12/26.
 * by--
 */

public class IGoodEvaluatePrenter extends IPresenter<IGoodEvaluateView> {

    private final EvaluateModel evaluateModel;

    @Override
    protected void cancel() {

        evaluateModel.cancel();
    }

    public IGoodEvaluatePrenter() {

        evaluateModel = new EvaluateModel();
    }

    //获取
    public void getEvaluate() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        int page = getView().getPage();
        String state = getView().getState();
        getView().showDialog();
        evaluateModel.getEvaluate(page, state, new IModelImpl<ApiResponse<EvaluateBean>, EvaluateBean>() {
            @Override
            protected void onSuccess(EvaluateBean data, String message) {

                getView().hideDialog();
                getView().OnsuccessDatas(data);

            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<EvaluateBean>> data, String message) {

                getView().hideDialog();

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

    //回复商家评价
    public void replyEva(String reply, String eval_id) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();
        evaluateModel.replyEva(reply, eval_id, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
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

                getView().hideDialog();
            }
        });
    }

    //获取商家评价列表数据
    public void getMerticEvaluate() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        int page = getView().getPage();
        String state = getView().getState();

        evaluateModel.getMerticEvaluate(page, state, new IModelImpl<ApiResponse<EvaluateBean>, EvaluateBean>() {
            @Override
            protected void onSuccess(EvaluateBean data, String message) {

                getView().hideDialog();
                getView().OnsuccessDatas(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<EvaluateBean>> data, String message) {

                getView().hideDialog();
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

    //回复商家评价
    public void replyMerticEva(String reply, String eval_id) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();
        evaluateModel.replyMerticEva(reply, eval_id, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
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

                getView().hideDialog();
            }
        });
    }
}
