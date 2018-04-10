package com.huafan.huafano2omanger.entity;

import java.io.Serializable;

/**
 * Created by heguozhong on 2018/1/2/002.
 * 接单设置bean
 */

public class OrderReceivingBean implements Serializable {
    /**
     * r : 0
     * msg : 查询成功
     * data : {"state":"1","quota":0}
     */
    /**
     * state : 1
     * quota : 0
     */

    private String state;
    private String quota;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

}
