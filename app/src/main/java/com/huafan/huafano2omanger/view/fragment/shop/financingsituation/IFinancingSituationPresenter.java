package com.huafan.huafano2omanger.view.fragment.shop.financingsituation;

import com.huafan.huafano2omanger.entity.FinancingSituationBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务概况逻辑层
 */

public class IFinancingSituationPresenter extends IPresenter<IFinancingSituationView>{
    private final IFinancingSituationModel iFinancingSituationModel;

    public IFinancingSituationPresenter() {
        iFinancingSituationModel = new IFinancingSituationModel();
    }

    //门店查询财务概况方法
    public void selectFinancingSituation(int state,String starttime,String endtime) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iFinancingSituationModel.selectFinancingSituation(state,starttime,endtime,new IModelImpl<ApiResponse<FinancingSituationBean>, FinancingSituationBean>() {
            @Override
            protected void onSuccess(FinancingSituationBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<FinancingSituationBean>> data, String message) {
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
        iFinancingSituationModel.cancel();
    }
}
