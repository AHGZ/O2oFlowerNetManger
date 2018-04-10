package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import com.huafan.huafano2omanger.entity.BindCardBean;

/**
 * Created by caishen on 2018/1/30.
 * by--
 */

interface IAddbankCardView {
    void onError(String s);

    void showDialog();

    void hideDiaglog();


    void onSuccess(BindCardBean data);

    String getname();

    String getIdNum();

    String getcard_num();

    String getbank_name();

    void onSuccessMessage(String message);
}
