package com.huafan.huafano2omanger.view.fragment.mine.shopdetail;

import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ShopDetailBean;

import java.io.File;
import java.util.List;

/**
 * Created by caishen on 2018/1/20.
 * by--
 */

interface IShopDetailView {
    void onError(String s);

    void hideDialog();

    void onSuccessData(ShopDetailBean data);

    void showDialog();

    List<File> getImgList();

    String getType();

    void mofityPhoto(ImgDataBean data);

    String getfilePath();

    String getmanager_name();

    String getmanager_tel();

    void onsuccess(String message);
}
