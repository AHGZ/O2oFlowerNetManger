package com.huafan.huafano2omanger.view.fragment.mine.dobusiness;

import com.huafan.huafano2omanger.entity.DobusinessBean;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 营业设置视图层
 */

public interface IDoBusinessSettingsView{
    void onError(String errorMsg);

    void onSuccess(DobusinessBean dobusinessBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
