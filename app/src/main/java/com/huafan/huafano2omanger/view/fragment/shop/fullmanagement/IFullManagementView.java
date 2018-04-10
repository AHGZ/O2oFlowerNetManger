package com.huafan.huafano2omanger.view.fragment.shop.fullmanagement;

import com.huafan.huafano2omanger.entity.FullManagementBean;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 满返管理视图层
 */

public interface IFullManagementView {
    void onError(String errorMsg);

    void onSuccess(FullManagementBean fullManagementBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
