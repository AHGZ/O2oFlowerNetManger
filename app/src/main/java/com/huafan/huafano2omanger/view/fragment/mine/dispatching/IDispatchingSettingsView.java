package com.huafan.huafano2omanger.view.fragment.mine.dispatching;

import com.huafan.huafano2omanger.entity.DispatchingBean;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 配送设置视图层
 */

public interface IDispatchingSettingsView{
    void onError(String errorMsg);

    void onSuccess(DispatchingBean dispatchingBean);

    void onSuccess(String message);

    void showDialog();

    void hideDialog();
}
