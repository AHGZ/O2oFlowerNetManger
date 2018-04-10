package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement;

import com.huafan.huafano2omanger.entity.CashManagementBean;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 提现管理视图层
 */

public interface ICashManagementView {
    void onError(String errorMsg);

    void onSuccess(CashManagementBean cashManagementBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();

    void onSuccesscheck(String data);

    String getPwd();

    void onSuccessedWith(String message);

    String getAlipayAccount();

    String getMoney();
}
