package com.huafan.huafano2omanger.view.fragment.shop.financingsituation;

import com.huafan.huafano2omanger.entity.FinancingSituationBean;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务概况视图层
 */

public interface IFinancingSituationView {
    void onError(String errorMsg);

    void onSuccess(FinancingSituationBean financingSituationBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
