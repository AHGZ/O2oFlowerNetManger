package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.MD5Utils;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.view.fragment.mine.bankcard.BankCardModel;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 提现管理逻辑层
 */

public class ICashManagementPresenter extends IPresenter<ICashManagementView>{
    private final ICashManagementModel iCashManagementModel;
    private final BankCardModel bankcardModel;

    public ICashManagementPresenter() {
        iCashManagementModel = new ICashManagementModel();
        bankcardModel = new BankCardModel();

    }

    //门店提现管理查询提现方法
    public void selectCash() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iCashManagementModel.selectCash(new IModelImpl<ApiResponse<CashManagementBean>, CashManagementBean>() {
            @Override
            protected void onSuccess(CashManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<CashManagementBean>> data, String message) {
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

    //门店提现管理增加提现方法
    public void addCash(String balance) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iCashManagementModel.addCash(balance, new IModelImpl<ApiResponse<CashManagementBean>, CashManagementBean>() {
            @Override
            protected void onSuccess(CashManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<CashManagementBean>> data, String message) {
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
        bankcardModel.check_pwd(MD5Utils.MD5To32(pwd), new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDialog();
                getView().onSuccesscheck(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {

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


    //支付宝提现
    public void withdraw() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String money = getView().getMoney();
        if (TextUtils.isEmpty(money)) {
            getView().onError("请输入提现金额");
            return;
        }

        String alipayAccount = getView().getAlipayAccount();
        if (TextUtils.isEmpty(alipayAccount)) {
            getView().onError("请输入支付宝账号");
            return;
        }

        // 加载进度条
        getView().showDialog();
        iCashManagementModel.withdraw(money, alipayAccount, new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDialog();
                getView().onSuccessedWith(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {
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

    @Override
    protected void cancel() {
        iCashManagementModel.cancel();
        bankcardModel.cancel();

    }
}
