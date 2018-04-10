package com.huafan.huafano2omanger.view.fragment.pending.waitrebund;

import com.huafan.huafano2omanger.entity.WaitReFundBean;

/**
 * Created by caishen on 2018/1/10.
 * by--
 */

interface IWaitRebundView {

    void onError(String s);

    void showDialog();

    String getState();

    int getPage();

    void hideDiaglog();

    void onSuccessData(WaitReFundBean data);

    String getrefundId();

    void onSuccess(String message);
}
