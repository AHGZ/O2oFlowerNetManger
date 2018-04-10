package com.huafan.huafano2omanger.event;

import android.bluetooth.BluetoothDevice;

/**
 * author: zhangpeisen
 * created on: 2018/1/3 下午2:07
 * description:
 */
public class BlueToothEvent {
    BluetoothDevice bluetoothDevice;

    public BlueToothEvent(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }
}
