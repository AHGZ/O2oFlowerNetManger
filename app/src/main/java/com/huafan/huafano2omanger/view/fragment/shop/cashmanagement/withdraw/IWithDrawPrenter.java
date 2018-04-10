package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.withdraw;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.BankCardListBean;
import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.MD5Utils;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.view.fragment.mine.bankcard.BankCardModel;
import com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.ICashManagementModel;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * 提现模块逻辑层
 */
public class IWithDrawPrenter extends IPresenter<IWithDrawView> {
    private final ICashManagementModel iCashManagementModel;
    private IWithDrawModel iWithDrawModel;
    private final BankCardModel bankcardModel;

    public IWithDrawPrenter() {
        iWithDrawModel = new IWithDrawModel();
        bankcardModel = new BankCardModel();
        iCashManagementModel = new ICashManagementModel();
    }

    /**
     * @描述: 提现到银行卡
     */
    public void withdrawalsToBankCard() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        String balance = getView().getBalance();

        if (TextUtils.isEmpty(balance) && balance.equals("")) {
            return;
        }

        getView().showDialog();
        iWithDrawModel.withdrawalsToBankCard(balance, new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDialog();
                getView().onSucces(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {
                getView().hideDialog();
                getView().onSucces(message);
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

//    /**
//     * @描述: 查看提现
//     */
//    public void selwithdrawals() {
//
//        if (!NetWorkUtils.isNetworkAvailable()) {
//            getView().onError("网络信号弱,请稍后重试");
//            return;
//        }
//        getView().showDialog();
//        iWithDrawModel.selwithdrawals(new IModelImpl<ApiResponse<WithDrawInfoBean>, WithDrawInfoBean>() {
//            @Override
//            protected void onSuccess(WithDrawInfoBean data, String message) {
//                getView().hideDialog();
//                getView().onSuccessWithDraw(data);
//            }
//
//            @Override
//            protected void onSuccess(ArrayList<ApiResponse<WithDrawInfoBean>> data, String message) {
//                getView().hideDialog();
//            }
//
//            @Override
//            protected void onFailure(String code, String message) {
//                getView().hideDialog();
//            }
//
//            @Override
//            protected void onSuccess() {
//                getView().hideDialog();
//
//            }
//        });
//    }

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
                getView().onSucces(message);
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
                getView().onSucces(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<CashManagementBean>> data, String message) {
                getView().hideDialog();
                getView().onSucces(message);
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

    //获取银行卡列表
    public void getcard_list() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        bankcardModel.getcard_list(new IModelImpl<ApiResponse<BankCardListBean>, BankCardListBean>() {
            @Override
            protected void onSuccess(BankCardListBean data, String message) {
                getView().hideDialog();
                getView().onSuccessData(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<BankCardListBean>> data, String message) {

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
        iWithDrawModel.cancel();
        bankcardModel.cancel();
    }
}
