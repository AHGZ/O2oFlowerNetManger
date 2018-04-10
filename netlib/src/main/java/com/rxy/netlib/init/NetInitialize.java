package com.rxy.netlib.init;

import android.content.Context;

import okhttp3.Interceptor;

/**
 * Created by rxy on 17/7/19.
 * 初始化refiter网络请求
 */

public class NetInitialize {

    private static Context mContext;
    private static NetInitialize instance = new NetInitialize();
    private static Interceptor mIntercept;

    private NetInitialize() {
    }

    public static NetInitialize getInstance() {
        return instance;
    }

    /**
     * 考虑到将http模块独立出去，需要在项目application中注入context
     * */
    public NetInitialize init(Context context) {
        mContext = context;
        return instance;
    }

    /**
     * 考虑到将http模块独立出去，需要在项目application中注入url
     * */
    public NetInitialize setRunUrl(String url) {
        NetConfig.setRunUrl(url);
        return instance;
    }

    public NetInitialize setIntercept(Interceptor intercept) {
        mIntercept = intercept;
        return instance;
    }

    public static Interceptor getIntercept() {
        return mIntercept;
    }

    public static Context getContext() {
        return mContext;
    }

}
