package com.huafan.huafano2omanger.view.fragment.shop.financingsituation.financingdetail;

import com.huafan.huafano2omanger.entity.FinancingDetailBean;
import com.huafan.huafano2omanger.entity.FinancingTakeDetailBean;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务明细视图层
 */

public interface IFinancingDetailView {
    void onError(String errorMsg);

    void onSuccess(FinancingDetailBean financingDetailBean);
    void onSuccess(FinancingTakeDetailBean financingTakeDetailBean);

    void onSuccess(String message);

    int getPages();

    void showDialog();

    void hideDialog();
}
