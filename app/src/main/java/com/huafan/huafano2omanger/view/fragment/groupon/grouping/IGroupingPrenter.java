package com.huafan.huafano2omanger.view.fragment.groupon.grouping;

import com.huafan.huafano2omanger.entity.GroupOnBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2018/1/13.
 * by--
 */

public class IGroupingPrenter extends IPresenter<IGroupingView> {

    private final GroupingModel groupingModel;


    @Override
    protected void cancel() {

        groupingModel.cancel();
    }

    public IGroupingPrenter() {

        groupingModel = new GroupingModel();
    }

    //获取团购列表数据
    public void getGroup() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String state = getView().getState();
        int page = getView().getPage();

        groupingModel.getGroup(state, page, new IModelImpl<ApiResponse<GroupOnBean>, GroupOnBean>() {
            @Override
            protected void onSuccess(GroupOnBean data, String message) {
                getView().hideDialog();
                getView().successData(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<GroupOnBean>> data, String message) {

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

    //修改团购状态（是否上架）
    public void changeState(String group_id, boolean ischeck) {

        String is_sale = "";
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        if (ischeck) {
            is_sale = "1";
        } else {
            is_sale = "0";
        }

        groupingModel.changeState(group_id, is_sale, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
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
