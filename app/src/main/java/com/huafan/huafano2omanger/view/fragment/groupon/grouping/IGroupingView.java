package com.huafan.huafano2omanger.view.fragment.groupon.grouping;

import com.huafan.huafano2omanger.entity.GroupOnBean;

/**
 * Created by caishen on 2018/1/13.
 * by--
 */

interface IGroupingView {
    String getState();

    int getPage();

    void hideDialog();

    void successData(GroupOnBean data);

    void onError(String s);

    void onSuccess(String message);
}
