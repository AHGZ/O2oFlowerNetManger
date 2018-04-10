package com.huafan.huafano2omanger.view.fragment.shop.sortmanagement;

import com.huafan.huafano2omanger.entity.SortManagementBean;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 分类管理视图层
 */

interface ISortManagementView {

//    //得到门店分类管理商品单元名
////    String getSortShopUnit();
//
//    //得到门店分类管理商品单元id
//    int getSortShopUnitId();
//
//    //得到门店分类管理商品分类id
//    int getSortShopCateId();
//
//    //得到门店分类管理商品分类名
//    String getSortShopName();
//
//    //得到门店分类管理商品排序序号
//    int getSortShopSort();

    void onError(String errorMsg);

    void onSuccess(SortManagementBean sortManagementBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
