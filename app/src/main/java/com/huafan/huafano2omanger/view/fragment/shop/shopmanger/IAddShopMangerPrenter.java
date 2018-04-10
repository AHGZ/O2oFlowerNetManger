package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.GoodTopSortBean;
import com.huafan.huafano2omanger.entity.GoodsDetailBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.entity.SpecificationBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.view.fragment.shop.shopunit.IShopUnitModel;
import com.huafan.huafano2omanger.view.fragment.shop.sortmanagement.ISortManagementModel;
import com.rxy.netlib.http.ApiResponse;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caishen on 2017/12/25.
 * by--
 */

public class IAddShopMangerPrenter extends IPresenter<IAddShopMangerView> {

    private final AddShopMangerModel addShopMangerModel;
    private final ISortManagementModel iSortManagementModel;
    private IShopUnitModel iShopUnitModel;

    @Override
    protected void cancel() {

    }

    public IAddShopMangerPrenter() {

        addShopMangerModel = new AddShopMangerModel();
        iSortManagementModel = new ISortManagementModel();
        iShopUnitModel = new IShopUnitModel();
    }

    //添加商品
    public void commitTask() throws JSONException {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String cataId = getView().getCataId();
        if (cataId.equals("") || TextUtils.isEmpty(cataId)) {
            getView().onError("请选择商品分类数据");
            return;
        }

        String name = getView().getName();
        if (name.equals("") || TextUtils.isEmpty(name)) {
            getView().onError("请填写商品的名称");
            return;
        }else if (name.length()>15){
            getView().onError("商品名称过长，最多可以输入15个字符");
            return;
        }

        List<String> imgPath = getView().getImgPath();
        if (imgPath.size() == 0) {
            getView().onError("上传一张商品图片");
            return;
        }

        String price = getView().getPrice();
        if (price.equals("") || TextUtils.isEmpty(price)||price.equals(".")) {
            getView().onError("请填写商品价格,且不能只输入符号");
            return;
        }

        String getoprice = getView().getoprice();
        if (getoprice.equals("") || TextUtils.isEmpty(getoprice)||getoprice.equals(".")) {
            getView().onError("请填写商品让利价格,且不能只输入符号");
            return;
        }
        if (Float.parseFloat(getoprice)>Float.parseFloat(price)){
            getView().onError("商品让利价格不能大于商品价格");
            return;
        }

        String getboxnum = getView().getboxnum();
        if (getboxnum.equals("") || TextUtils.isEmpty(getboxnum)) {
            getView().onError("请填写商品餐盒数量");
            return;
        }

        String box_price = getView().getbox_price();
        if (box_price.equals("") || TextUtils.isEmpty(box_price)||box_price.equals(".")) {
            getView().onError("请填写商品餐盒价格,且不能只输入符号");
            return;
        }

        String unitId = getView().getUnitId();
        if (unitId.equals("") || TextUtils.isEmpty(unitId)) {
            getView().onError("请选择商品单位");
            return;
        }

        String isNew = getView().getisNew();
        if (isNew.equals("") || TextUtils.isEmpty(isNew)) {
            getView().onError("请选择是否新品");
            return;
        }

        String sale = getView().isSale();//是否上架
        String getdescription = getView().getdescription();
        if (getdescription.equals("") || TextUtils.isEmpty(getdescription)) {
            getView().onError("请填写商品描述");
            return;
        }

        String getstock = getView().getstock();
        if (getstock.equals("") || TextUtils.isEmpty(getstock)) {
            getView().onError("请填写商品库存");
            return;
        }


        List<SpecificationBean> specificationBeen = getView().getgoods_spec();

        String sale0 = getView().is_sale0();
        String sale1 = getView().is_sale1();
        String sale2 = getView().is_sale2();
        String sale3 = getView().is_sale3();
        String sale4 = getView().is_sale4();
        String sale5 = getView().is_sale5();
        String sale6 = getView().is_sale6();


        getView().showDialog();
        addShopMangerModel.addGoods(cataId, name, imgPath, price, getoprice, getboxnum, box_price, unitId, isNew,
                sale0, sale1, sale2, sale3, sale4, sale5, sale6, sale, getdescription, getstock, specificationBeen,
                new IModelImpl<ApiResponse<GoodTopSortBean>, GoodTopSortBean>() {
                    @Override
                    protected void onSuccess(GoodTopSortBean data, String message) {
                        getView().hideDiaglog();
                        getView().onsuccessAddShop(message);

                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<GoodTopSortBean>> data, String message) {
                        getView().hideDiaglog();
                        getView().onsuccessAddShop(message);
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

    //获取商品分类
    public void getShopCategray() {


        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        iSortManagementModel.selectSortShop(new IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean>() {
            @Override
            protected void onSuccess(SortManagementBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccessSortShop(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<SortManagementBean>> data, String message) {

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

    //获取商品单元
    public void getShopUnit() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        getView().showDialog();

        iShopUnitModel.selectShopUnit(new IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean>() {
            @Override
            protected void onSuccess(ShopUnitBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccessUnitShop(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<ShopUnitBean>> data, String message) {
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

    //修改商品
    public void updata() throws JSONException {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String cataId = getView().getCataId();
        if (cataId.equals("") || TextUtils.isEmpty(cataId)) {
            getView().onError("请选择商品分类数据");
            return;
        }

        String name = getView().getName();
        if (name.equals("") || TextUtils.isEmpty(name)) {
            getView().onError("请填写商品的名称");
            return;
        }

        List<String> imgPath = getView().getImgPath();
        if (imgPath.size() == 0) {
            getView().onError("上传一张商品图片");
            return;
        }

        String price = getView().getPrice();
        if (price.equals("") || TextUtils.isEmpty(price)||price.equals(".")) {
            getView().onError("请填写商品价格,且不能只输入符号");
            return;
        }

        String getoprice = getView().getoprice();
        if (getoprice.equals("") || TextUtils.isEmpty(getoprice)||getoprice.equals(".")) {
            getView().onError("请填写商品让利价格,且不能只输入符号");
            return;
        }
        if (Float.parseFloat(getoprice)>Float.parseFloat(price)){
            getView().onError("商品让利价格不能大于商品价格");
            return;
        }

        String getboxnum = getView().getboxnum();
        if (getboxnum.equals("") || TextUtils.isEmpty(getboxnum)) {
            getView().onError("请填写商品餐盒数量");
            return;
        }

        String box_price = getView().getbox_price();
        if (box_price.equals("") || TextUtils.isEmpty(box_price)||box_price.equals(".")) {
            getView().onError("请填写商品餐盒价格,且不能只输入符号");
            return;
        }

        String unitId = getView().getUnitId();
        if (unitId.equals("") || TextUtils.isEmpty(unitId)) {
            getView().onError("请选择商品单元");
            return;
        }

        String isNew = getView().getisNew();
        if (isNew.equals("") || TextUtils.isEmpty(isNew)) {
            getView().onError("请选择是否新品");
            return;
        }

        String sale = getView().isSale();//是否上架
        String getdescription = getView().getdescription();
        if (getdescription.equals("") || TextUtils.isEmpty(getdescription)) {
            getView().onError("请填写商品描述");
            return;
        }

        String getstock = getView().getstock();
        if (getstock.equals("") || TextUtils.isEmpty(getstock)) {
            getView().onError("请填写商品库存");
            return;
        }

        List<SpecificationBean> specificationBeen = getView().getgoods_spec();

        String sale0 = getView().is_sale0();
        String sale1 = getView().is_sale1();
        String sale2 = getView().is_sale2();
        String sale3 = getView().is_sale3();
        String sale4 = getView().is_sale4();
        String sale5 = getView().is_sale5();
        String sale6 = getView().is_sale6();
        String goods_img = getView().getGoods_img();
        String goodId = getView().getGoodId();

        //加载进度条
        getView().showDialog();
        addShopMangerModel.updata(cataId, name, imgPath, price, getoprice, getboxnum, box_price, unitId, isNew,
                sale0, sale1, sale2, sale3, sale4, sale5, sale6, sale, getdescription, getstock, specificationBeen, goods_img,
                goodId, new IModelImpl<ApiResponse<ReplyBean>, ReplyBean>() {
                    @Override
                    protected void onSuccess(ReplyBean data, String message) {

                        getView().hideDiaglog();
                        getView().onsuccessUpdataShop(message);

                    }

                    @Override
                    protected void onSuccess(ArrayList<ApiResponse<ReplyBean>> data, String message) {
                        getView().hideDiaglog();
                        getView().onsuccessUpdataShop(message);
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

    //上传图片
    public void uploadImg() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        List<File> userImgList = getView().getUserImgList();
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

    //获取商品详情
    public void getShopDetail() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }

        String goodId = getView().getGoodId();
        //加载进度条
        getView().showDialog();

        addShopMangerModel.getShopDetail(goodId, new IModelImpl<ApiResponse<GoodsDetailBean>, GoodsDetailBean>() {
            @Override
            protected void onSuccess(GoodsDetailBean data, String message) {
                getView().hideDiaglog();
                getView().onSuccessShopDetail(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<GoodsDetailBean>> data, String message) {
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
