package com.huafan.huafano2omanger.view.fragment.pending.waitdispose;

import com.huafan.huafano2omanger.entity.SelfMotionBean;
import com.huafan.huafano2omanger.entity.WaitDisposeBean;

/**
 * Created by caishen on 2018/1/9.
 * by--
 */

interface IWaitDisposeView {

    void showDialog();

    String getState();

    void hideDiaglog();


    void onSuccessData(WaitDisposeBean data);

    void onError(String s);

    int getPage();

    String getOrderId();

    void onsuccess(String message);

    void onsuccessSelf(SelfMotionBean data);
}
