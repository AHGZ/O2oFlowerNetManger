package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by heguozhong on 2018/1/2/002.
 * 配送设置bean
 */

public class DispatchingBean implements Serializable {

    /**
     * distrib_setting : 2
     * distrib_quota : 0.00
     * self_pick_setting : 0
     * prepare_time : 60
     */

    private int distrib_setting;
    private float distrib_quota;
    private int self_pick_setting;
    private int prepare_time;

    public int getDistrib_setting() {
        return distrib_setting;
    }

    public void setDistrib_setting(int distrib_setting) {
        this.distrib_setting = distrib_setting;
    }

    public float getDistrib_quota() {
        return distrib_quota;
    }

    public void setDistrib_quota(float distrib_quota) {
        this.distrib_quota = distrib_quota;
    }

    public int getSelf_pick_setting() {
        return self_pick_setting;
    }

    public void setSelf_pick_setting(int self_pick_setting) {
        this.self_pick_setting = self_pick_setting;
    }

    public int getPrepare_time() {
        return prepare_time;
    }

    public void setPrepare_time(int prepare_time) {
        this.prepare_time = prepare_time;
    }
}
