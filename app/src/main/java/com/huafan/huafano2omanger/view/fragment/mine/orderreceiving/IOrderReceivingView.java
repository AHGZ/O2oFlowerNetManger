package com.huafan.huafano2omanger.view.fragment.mine.orderreceiving;

import com.huafan.huafano2omanger.entity.OrderReceivingBean;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 接单设置视图层
 */

public interface IOrderReceivingView {
    void onError(String errorMsg);

    void onSuccess(OrderReceivingBean orderReceivingBean);


    void onSuccess(String message);

    void showDialog();

    void hideDialog();

}
