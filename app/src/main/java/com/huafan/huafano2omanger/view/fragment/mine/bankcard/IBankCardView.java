package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import com.huafan.huafano2omanger.entity.BankCardListBean;

/**
 * Created by caishen on 2018/1/30.
 * by--
 */

public interface IBankCardView {

    void showDialog();

    void onError(String s);

    void hideDiaglog();

    void onSuccesscheck(String data);

    String getPwd();

    void onSuccessData(BankCardListBean data);

    void onSuccess(String message);

    String id();
}
