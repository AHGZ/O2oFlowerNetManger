package com.huafan.huafano2omanger.view.fragment.shop.messagemanagement;

import com.huafan.huafano2omanger.entity.MessaTypeBean;

/**
 * Created by heguozhong on 2017/12/27/027.
 * 消息管理视图层
 */

public interface IMessageManagementView{
    void onError(String s);

    String getType();

    String getreceiver();

    int getPage();

    void showDialog();

    void hideDialog();

    void onsuccessType(MessaTypeBean data);
}
