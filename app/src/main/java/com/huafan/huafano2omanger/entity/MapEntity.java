package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * author: zhangpeisen
 * created on: 2018/1/9 下午2:09
 * description:
 */
public class MapEntity implements Serializable {

    /**
     * longitude : 0
     * latitude : 0
     */

    private int longitude;
    private int latitude;

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
