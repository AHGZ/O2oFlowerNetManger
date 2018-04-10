package com.rxy.netlib.http;

import java.util.ArrayList;

/**
 * Created by rxy on 17/7/19.
 */

public class ApiResponse<T> {
    private T data;
    private String r;
    private String msg;


    private ArrayList<T> datalist;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return r;
    }

    public void setCode(String r) {
        this.r = r;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }


    public ArrayList<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(ArrayList<T> datalist) {
        this.datalist = datalist;
    }

}
