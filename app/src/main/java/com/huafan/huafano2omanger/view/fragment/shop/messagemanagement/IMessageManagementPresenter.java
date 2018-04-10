package com.huafan.huafano2omanger.view.fragment.shop.messagemanagement;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.MessaTypeBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/27/027.
 * 消息管理逻辑层
 */

public class IMessageManagementPresenter extends IPresenter<IMessageManagementView> {

    private final IMessageModel iMessageModel;

    @Override
    protected void cancel() {

    }

    public IMessageManagementPresenter() {

        iMessageModel = new IMessageModel();
    }

    //获取指定分类消息列表
    public void get_notice_list() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        String type = getView().getType();
        if (TextUtils.isEmpty(type)) {
            return;
        }

        String receiver = getView().getreceiver();

        int page = getView().getPage();
        getView().showDialog();

        iMessageModel.getNoticeList(type, page,receiver, new IModelImpl<ApiResponse<MessaTypeBean>, MessaTypeBean>() {
            @Override
            protected void onSuccess(MessaTypeBean data, String message) {
                getView().hideDialog();
                getView().onsuccessType(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<MessaTypeBean>> data, String message) {
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
}
