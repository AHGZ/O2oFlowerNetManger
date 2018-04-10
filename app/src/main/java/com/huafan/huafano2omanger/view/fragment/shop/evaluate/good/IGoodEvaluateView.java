package com.huafan.huafano2omanger.view.fragment.shop.evaluate.good;

import com.huafan.huafano2omanger.entity.EvaluateBean;

/**
 * Created by caishen on 2017/12/26.
 * by--
 */

public interface IGoodEvaluateView {
    void onError(String s);

    void showDialog();

    int getPage();

    String getState();

    void hideDialog();

    void OnsuccessDatas(EvaluateBean data);

    void onSuccess(String message);

}
