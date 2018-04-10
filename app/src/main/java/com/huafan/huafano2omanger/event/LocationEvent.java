package com.huafan.huafano2omanger.event;

/**
 * author: zhangpeisen
 * created on: 2017/10/10 上午11:22
 * description:
 */
public class LocationEvent {
    String location;

    public LocationEvent(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
