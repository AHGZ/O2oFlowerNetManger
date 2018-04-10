package com.huafan.huafano2omanger.event;

import com.huafan.huafano2omanger.entity.WaitDisposeBean;

/**
 * Created by caishen on 2018/1/18.
 * by--蓝牙打印
 */

public class BluetoothsEvent {

    private final WaitDisposeBean.ListBean data;

    public BluetoothsEvent(WaitDisposeBean.ListBean datas) {
        this.data=datas;
    }

    public WaitDisposeBean.ListBean getData() {
        return data;
    }
}
