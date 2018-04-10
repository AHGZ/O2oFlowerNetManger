package com.huafan.huafano2omanger.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.caimuhao.rxpicker.RxPicker;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.utils.DeviceUtil;
import com.huafan.huafano2omanger.utils.LogUtils;
import com.huafan.huafano2omanger.utils.RxImageLoader;
import com.huafan.huafano2omanger.utils.URLEntity;
import com.rxy.netlib.http.HeaderIntercept;
import com.rxy.netlib.init.NetConfig;
import com.rxy.netlib.init.NetInitialize;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


/**
 * author: zhangpeisen
 * created on: 2017/10/9 下午12:48
 * description:app 应用配置类
 */
public class BaseApplication extends MultiDexApplication {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        readHttpsCer();
        // 初始网络请求sdk
        initNetLib();
        // 图片选择器
        RxPicker.init(new RxImageLoader());
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }

    // 获取证书流
    private void readHttpsCer() {
        try {
            InputStream is = getAssets().open("ca.crt");
            NetConfig.addCertificate(is); // 这里将证书读取出来，，放在配置中byte[]里
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 获取全局的context
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 初始化全局联网
     */
    private void initNetLib() {

        HashMap<String, String> headers = new HashMap<>();
        // 客户端操作类型
        headers.put("rp", Constants.RP);
        // 移动设备唯一标识符
        headers.put("device-id", URLEntity.getInstance().getImei());
        // 客户端版本号version
        headers.put("v", DeviceUtil.getVersionName(mContext));
        // 时间戳timestamp
        headers.put("ts", Constants.TIME);
        // 消息推送token
        headers.put("device-token",  Constants.DEVICETOKEN);
        // 客户端平台  1：b2c   2: o2o
        headers.put("client", Constants.CLIENT);
        LogUtils.e("headers == ", headers.toString());
        HeaderIntercept intercept = new HeaderIntercept(headers);
        NetInitialize.getInstance().init(this).setIntercept(intercept);
        NetInitialize.getInstance().init(mContext).setRunUrl(ApiUrlConstant.API_BASE_URL).setIntercept(intercept);
    }
}
