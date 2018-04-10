package com.huafan.huafano2omanger.view.fragment.shop.evaluate.medium;

import com.huafan.huafano2omanger.entity.EvaluateBean;

/**
 * Created by caishen on 2017/12/26.
 * by--
 */

interface IMediumEvaluateView {
    void onError(String s);

    int getPage();

    String getState();

    void showDialog();

    void hideDialog();

    void OnsuccessDatas(EvaluateBean data);

    void onSuccess(String message);

}
