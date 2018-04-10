package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.BindCardBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.IDCardValidate;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2018/1/30.
 * by--
 */

public class IAddbankCardPrenter extends IPresenter<IAddbankCardView> {

    private final IAddbankCardModel iAddbankCardModel;

    @Override
    protected void cancel() {

        iAddbankCardModel.cancel();
    }


    public IAddbankCardPrenter() {

        iAddbankCardModel = new IAddbankCardModel();
    }

    //获取绑卡人信息
    public void go_bind_card() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        iAddbankCardModel.go_bind_card(new IModelImpl<ApiResponse<BindCardBean>, BindCardBean>() {
            @Override
            protected void onSuccess(BindCardBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<BindCardBean>> data, String message) {
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

    //绑定银行卡
    public void commit() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String name = getView().getname();
        if (TextUtils.isEmpty(name)) {
            getView().onError("请填写姓名");
            return;
        }

        String idNum = getView().getIdNum();
        if (TextUtils.isEmpty(idNum)) {
            getView().onError("请填写身份证号");
            return;
        }
        // 判断是否符合身份证号码的规范
        boolean checkIDCard = IDCardValidate.checkIDCard(idNum);
        if (!checkIDCard) {//有效返回""，无效返回错误信息
            getView().onError("请填写正确的身份证号");
            return;
        }

        String card_num = getView().getcard_num();
        if (TextUtils.isEmpty(card_num)) {
            getView().onError("请填写银行卡号");
            return;
        }

        String bank_name = getView().getbank_name();
        if (TextUtils.isEmpty(bank_name)) {
            getView().onError("请填写开户行");
            return;
        }

        getView().showDialog();

        iAddbankCardModel.add_card(name,idNum,card_num,bank_name,new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
            @Override
            protected void onSuccess(ReplyBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccessMessage(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
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
