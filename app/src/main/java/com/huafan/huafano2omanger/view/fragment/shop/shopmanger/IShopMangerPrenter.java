package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import com.huafan.huafano2omanger.entity.GoodTopSortBean;
import com.huafan.huafano2omanger.entity.SelGoodsListBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * Created by caishen on 2017/12/25.
 * by--
 */

public class IShopMangerPrenter extends IPresenter<IShopMangerView> {

    private final ShopMangerModel shopMangerModel;

    @Override
    protected void cancel() {

    }

    public IShopMangerPrenter() {

        shopMangerModel = new ShopMangerModel();
    }

    //获取商品列表数据
    public void getShopList() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String cateId = getView().getCateId();
        int page = getView().getPage();

        getView().showDialog();
        shopMangerModel.getShopList(cateId, page, new IModelImpl<ApiResponse<SelGoodsListBean>, SelGoodsListBean>() {
            @Override
            protected void onSuccess(SelGoodsListBean data, String message) {
                getView().hideDialog();
                getView().SuccessSelGoodList(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SelGoodsListBean>> data, String message) {
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

    //删除商品
    public void delShop(int id) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String specId = getView().getSpecId();
        getView().showDialog();

        shopMangerModel.delShop(String.valueOf(id), new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDialog();
                getView().onDelsuccess(data,message);
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

    //商品重置排序
    public void sortShop(int id, int id1, int targetPosition, int srcPosition) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        shopMangerModel.sortShop(id, id1, targetPosition, srcPosition, new IModelImpl<ApiResponse<GoodTopSortBean>, GoodTopSortBean>() {
            @Override
            protected void onSuccess(GoodTopSortBean data, String message) {
                getView().hideDialog();
                getView().onSortsuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<GoodTopSortBean>> data, String message) {
                getView().hideDialog();
                getView().onsuccess(message);
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

    //商品置顶排序
    public void topSort(int id, int position) {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();
        shopMangerModel.topSortShop(id, position, new IModelImpl<ApiResponse<GoodTopSortBean>, GoodTopSortBean>() {
            @Override
            protected void onSuccess(GoodTopSortBean data, String message) {
                getView().hideDialog();
                getView().onTopSortsuccess(message);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<GoodTopSortBean>> data, String message) {
                getView().hideDialog();
                getView().onsuccess(message);
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
