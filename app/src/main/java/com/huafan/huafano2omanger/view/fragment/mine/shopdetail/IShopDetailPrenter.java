package com.huafan.huafano2omanger.view.fragment.mine.shopdetail;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.ShopDetailBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.utils.ParamMatchUtils;
import com.huafan.huafano2omanger.view.fragment.shop.shopmanger.AddShopMangerModel;
import com.rxy.netlib.http.ApiResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caishen on 2018/1/20.
 * by--
 */

class IShopDetailPrenter extends IPresenter<IShopDetailView> {

    private final ShopDetailModel shopDetailModel;
    private final AddShopMangerModel addShopMangerModel;

    @Override
    protected void cancel() {

        shopDetailModel.cancel();
        addShopMangerModel.cancel();
    }

    public IShopDetailPrenter() {

        shopDetailModel = new ShopDetailModel();
        addShopMangerModel = new AddShopMangerModel();

    }

    //获取店铺信息
    public void getShopDetail() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        shopDetailModel.getShopDetail(new IModelImpl<ApiResponse<ShopDetailBean>, ShopDetailBean>() {
            @Override
            protected void onSuccess(ShopDetailBean data, String message) {
                getView().hideDialog();
                getView().onSuccessData(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ShopDetailBean>> data, String message) {
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

    //上传图片
    public void uploadImg() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        List<File> userImgList = getView().getImgList();
        String type = getView().getType();
        //加载进度条
        getView().showDialog();

        addShopMangerModel.upload(type, userImgList, new IModelImpl<ApiResponse<ImgDataBean>, ImgDataBean>() {
            @Override
            protected void onSuccess(ImgDataBean data, String message) {
                getView().hideDialog();
                getView().mofityPhoto(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ImgDataBean>> data, String message) {
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

    //修改商铺信息
    public void up_merchant_info() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String getfilePath = getView().getfilePath();
        String manager_name = getView().getmanager_name();
        if (TextUtils.isEmpty(manager_name)) {
            getView().onError("负责人姓名不能为空");
            return;
        }

        String manager_tel = getView().getmanager_tel();
        if (!checkPhone(manager_tel)) {
            getView().onError("请填写正确的负责人手机号");
            return;
        }

        //加载进度条
        getView().showDialog();
        shopDetailModel.up_merchant_info(getfilePath, manager_name, manager_tel,
                new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
                    @Override
                    protected void onSuccess(ReplyBean data, String message) {
                        getView().hideDialog();
                        getView().onsuccess(message);
                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
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
