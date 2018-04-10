package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.BankCardListBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.MD5Utils;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2018/1/30.
 * by--
 */

public class IBankCardPrenter extends IPresenter<IBankCardView> {

    private final BankCardModel bankCardModel;

    @Override
    protected void cancel() {

        bankCardModel.cancel();
    }

    public IBankCardPrenter() {

        bankCardModel = new BankCardModel();
    }

    //检查用户登录密码
    public void check_pwd() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String pwd = getView().getPwd();
        if (TextUtils.isEmpty(pwd)) {
            getView().onError("请输入登录密码");
            return;
        }

        getView().showDialog();
        bankCardModel.check_pwd(MD5Utils.MD5To32(pwd), new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDiaglog();
                getView().onSuccesscheck(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {

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

    //获取银行卡列表
    public void getcard_list() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        bankCardModel.getcard_list(new IModelImpl<ApiResponse<BankCardListBean>, BankCardListBean>() {
            @Override
            protected void onSuccess(BankCardListBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccessData(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<BankCardListBean>> data, String message) {

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

    //解绑银行卡
    public void un_bind() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }


        String id = getView().id();
        if(TextUtils.isEmpty(id)) {
            return;
        }

        getView().showDialog();

        bankCardModel.un_bind(id,new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDiaglog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {

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
