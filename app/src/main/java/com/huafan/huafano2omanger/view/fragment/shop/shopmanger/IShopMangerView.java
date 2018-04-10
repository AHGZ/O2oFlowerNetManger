package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import com.huafan.huafano2omanger.entity.SelGoodsListBean;

/**
 * Created by caishen on 2017/12/25.
 * by--
 */

public interface IShopMangerView {
    String getCateId();

    int getPage();

    void onError(String s);

    void showDialog();

    void hideDialog();

    void SuccessSelGoodList(SelGoodsListBean data);

    String getSpecId();

    void onsuccess(String message);

    void onDelsuccess(String data, String message);

    void onTopSortsuccess(String message);

    void onSortsuccess(String message);
}
