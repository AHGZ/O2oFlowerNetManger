package com.huafan.huafano2omanger.view.fragment.shop.shopunit;

import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by heguozhong on 2017/12/23.
 * 商品单位逻辑层
 */

public class IShopUnitPresenter extends IPresenter<IShopUnitView> {

    private final IShopUnitModel iShopUnitModel;

    public IShopUnitPresenter() {
        iShopUnitModel = new IShopUnitModel();
    }

    //门店商品单位查询商品单位方法
    public void selectShopUnit() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iShopUnitModel.selectShopUnit(new IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean>() {
            @Override
            protected void onSuccess(ShopUnitBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ShopUnitBean>> data, String message) {
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

    //门店商品单位增加商品单位方法
    public void addShopUnit(String unit) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iShopUnitModel.addShopUnit(unit, new IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean>() {
            @Override
            protected void onSuccess(ShopUnitBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ShopUnitBean>> data, String message) {
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

    //门店商品单位删除商品单位方法
    public void deleteShopUnit(int unit_id) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iShopUnitModel.deleteShopUnit(unit_id, new IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean>() {
            @Override
            protected void onSuccess(ShopUnitBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ShopUnitBean>> data, String message) {
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

    //门店商品单位修改商品单位方法
    public void updateShopUnit(int unit_id, String unit) {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        iShopUnitModel.updateShopUnit(unit_id, unit, new IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean>() {
            @Override
            protected void onSuccess(ShopUnitBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ShopUnitBean>> data, String message) {
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
        iShopUnitModel.cancel();
    }
}
