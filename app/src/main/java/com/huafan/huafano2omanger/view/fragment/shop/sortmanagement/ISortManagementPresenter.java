package com.huafan.huafano2omanger.view.fragment.shop.sortmanagement;

import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 分类管理逻辑层
 */

public class ISortManagementPresenter extends IPresenter<ISortManagementView> {

    private ISortManagementModel iSortManagementModel;

    public ISortManagementPresenter() {
        iSortManagementModel = new ISortManagementModel();
    }

    //门店分类管理查询分类方法
    public void selectSortShop() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iSortManagementModel.selectSortShop(new IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean>() {
            @Override
            protected void onSuccess(SortManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SortManagementBean>> data, String message) {
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

    //门店分类管理增加分类方法
    public void addSortShop(String name,int sort) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iSortManagementModel.addSortShop(name,sort, new IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean>() {
            @Override
            protected void onSuccess(SortManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SortManagementBean>> data, String message) {
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

    //门店分类管理删除分类方法
    public void deleteSortShop(int cate_id) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iSortManagementModel.deleteSortShop(cate_id, new IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean>() {
            @Override
            protected void onSuccess(SortManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SortManagementBean>> data, String message) {
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

    //门店分类管理修改分类方法
    public void updateSortShop(int cateId,String name,int sort) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iSortManagementModel.updateSortShop(cateId, name, sort, new IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean>() {
            @Override
            protected void onSuccess(SortManagementBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SortManagementBean>> data, String message) {
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
        iSortManagementModel.cancel();
    }
}
