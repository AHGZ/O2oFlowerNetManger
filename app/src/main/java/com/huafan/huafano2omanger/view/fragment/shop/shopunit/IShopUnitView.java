package com.huafan.huafano2omanger.view.fragment.shop.shopunit;

import com.huafan.huafano2omanger.entity.ShopUnitBean;

/**
 * Created by heguozhong on 2017/12/23.
 * 商品单位视图层
 */

public interface IShopUnitView{
    void onError(String errorMsg);

    void onSuccess(ShopUnitBean ShopUnitBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
