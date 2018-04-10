package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.withdraw;

import com.huafan.huafano2omanger.entity.BankCardListBean;
import com.huafan.huafano2omanger.entity.CashManagementBean;

/**
 *提现视图模块
 */
public interface IWithDrawView {
    // 支付密码
    String getPwd();

    // 余额
    String getBalance();

    void showDialog();

    void hideDialog();

    void onError(String errorMsg);

    void onSucces(String message);

    void onSuccess(CashManagementBean cashManagementBean);

    void onSuccesscheck(String data);

//    void onSuccessWithDraw(WithDrawInfoBean withDrawInfoBean);

    void onSuccessData(BankCardListBean data);
}
