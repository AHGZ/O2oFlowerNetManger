package com.huafan.huafano2omanger.view.fragment.mine.updatepwd;


import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.SendCodeBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.utils.ParamMatchUtils;
import com.huafan.huafano2omanger.view.fragment.updatepwd.UpdatePwdModel;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2017/11/18.
 * by--修改登录密码的逻辑层
 */

public class IUpdateLoginPwdPrenter extends IPresenter<IUpdateLoginPwdView> {

    private final UpdateLoginPwdModel updateLoginPwdModel;
    private final UpdatePwdModel updatePwdModel;

    @Override
    protected void cancel() {

        updateLoginPwdModel.cancel();
    }

    public IUpdateLoginPwdPrenter() {

        updateLoginPwdModel = new UpdateLoginPwdModel();
        updatePwdModel = new UpdatePwdModel();

    }

    //确认修改密码
    public void updateUserPwd() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String aPwd = getView().getAPwd();
        String bPwd = getView().getBPwd();
        String cPwd = getView().getCPwd();
        int fromCode = getView().getFromCode();//获取到的验证码
        String smsCode = getView().getSmsCode();//验证码

        if (!String.valueOf(fromCode).equals(smsCode)) {
            getView().onError("请输入正确的验证码");
            return;
        }
        getView().showDialog();

        //修改登录用户密码
        updateLoginPwdModel.updateUserPwd(aPwd, bPwd, cPwd, new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String sendCodeBean, String message) {
                getView().hideDialog();
                getView().sendUpdateSuccess(sendCodeBean);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {

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

    //发送验证码
    public void sendCode() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        String phone = getView().getPhone();

        if (!checkPhone(phone)) {

            getView().onError("请填写正确的手机号");
            return;
        }

        getView().showDialog();

        updatePwdModel.sendCode(phone, new IModelImpl<ApiResponse<SendCodeBean>, SendCodeBean>() {
            @Override
            protected void onSuccess(SendCodeBean sendCodeBean, String message) {
                getView().hideDialog();
                getView().sendCodeSuccess(sendCodeBean);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SendCodeBean>> data, String message) {

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

    //检查手机号是否合规
    private boolean checkPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            getView().onError("请填写手机号");
            return false;
        }

        if (!ParamMatchUtils.isPhoneAvailable(phone)) {
            getView().onError("请填写正确的手机号");
            return false;
        }
        return true;
    }
}
