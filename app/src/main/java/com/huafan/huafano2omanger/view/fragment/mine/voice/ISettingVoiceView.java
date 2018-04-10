package com.huafan.huafano2omanger.view.fragment.mine.voice;

import com.huafan.huafano2omanger.entity.OrderReceivingBean;

/**
 * Created by caishen on 2018/1/8.
 * by--
 */

public interface ISettingVoiceView{

    void onSuccess(String data);

    void onGetSuccess(OrderReceivingBean data);

    void onError(String message);

    void showDialog();

    void hideDialog();

    int getType();

    int getState();

}
