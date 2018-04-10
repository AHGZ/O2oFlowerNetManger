package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import com.huafan.huafano2omanger.entity.GoodsDetailBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.entity.SpecificationBean;

import java.io.File;
import java.util.List;

/**
 * Created by caishen on 2017/12/25.
 * by--
 */

public interface IAddShopMangerView {
    void hideDiaglog();

    void onSuccessSortShop(SortManagementBean data);

    void onError(String s);

    void showDialog();

    void onSuccessUnitShop(ShopUnitBean data);

    String getCataId();

    String getName();

    List<String> getImgPath();

    String getPrice();

    String getoprice();

    String getboxnum();

    String getbox_price();

    String getUnitId();

    String getisNew();

    String is_sale0();
    String is_sale1();
    String is_sale2();
    String is_sale3();
    String is_sale4();
    String is_sale5();
    String is_sale6();

    String isSale();

    String getdescription();

    String getstock();

    List<SpecificationBean> getgoods_spec();

    List<File> getUserImgList();

    String getType();

    void hideDialog();

    void mofityPhoto(ImgDataBean data);

    void onsuccessAddShop(String message);

    String getGoodId();

    void onSuccessShopDetail(GoodsDetailBean data);

    void onsuccess(String message);

    String getGoods_img();

    void onsuccessUpdataShop(String message);
}
