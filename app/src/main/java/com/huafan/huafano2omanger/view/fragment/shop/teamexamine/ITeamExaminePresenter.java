package com.huafan.huafano2omanger.view.fragment.shop.teamexamine;

import com.huafan.huafano2omanger.entity.TeamExamineBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 团队核销逻辑层
 */

public class ITeamExaminePresenter extends IPresenter<ITeamExamineView>{
    private final ITeamExamineModel iTeamExamineModel;

    public ITeamExaminePresenter() {
        iTeamExamineModel = new ITeamExamineModel();
    }

    //门店满返管理查询满返方法
    public void submitCode(String group_code) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iTeamExamineModel.submitCode(group_code,new IModelImpl<ApiResponse<TeamExamineBean>, TeamExamineBean>() {
            @Override
            protected void onSuccess(TeamExamineBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<TeamExamineBean>> data, String message) {
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

    @Override
    protected void cancel() {
        iTeamExamineModel.cancel();
    }
}
