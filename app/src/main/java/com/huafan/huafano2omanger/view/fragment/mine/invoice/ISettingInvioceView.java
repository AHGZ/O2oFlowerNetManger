package com.huafan.huafano2omanger.view.fragment.mine.invoice;

import com.huafan.huafano2omanger.entity.OrderReceivingBean;

/**
 * Created by caishen on 2018/1/8.
 * by--
 */

interface ISettingInvioceView {
    int getType();

    void showDialog();

    void hideDialog();

    void onGetSuccess(OrderReceivingBean data);

    void onSuccess(String message);

    void onError(String message);

    int getState();

    String getQuota();
}
