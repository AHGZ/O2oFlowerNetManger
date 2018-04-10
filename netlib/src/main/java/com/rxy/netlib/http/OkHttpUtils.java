package com.rxy.netlib.http;

import android.util.Log;

import com.rxy.netlib.init.NetInitialize;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by rxy on 17/7/19.
 */

public class  OkHttpUtils {

    private static OkHttpClient mClient;
    private static final int TIME_OUT = 10;
    //设置缓存目录
    private static File cacheDirectory = new File(NetInitialize.getContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    public static OkHttpClient getInstance() {

        if (mClient == null) {
            HttpLoggingInterceptor.Level mLevel = HttpLoggingInterceptor.Level.BODY;
            HttpLoggingInterceptor mInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("mylog", message);
                }
            });
            mInterceptor.setLevel(mLevel);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.hostnameVerifier(new UnSafeHostnameVerifier());//添加hostName验证器
//            // 添加证书
//            List<InputStream> certificates = new ArrayList<>();
//            List<byte[]> certs_data = NetConfig.getCertificatesData();
//
//            // 将字节数组转为数组输入流
//            if (certs_data != null && !certs_data.isEmpty()) {
//                for (byte[] bytes : certs_data) {
//                    certificates.add(new ByteArrayInputStream(bytes));
//                }
//            }
//            javax.net.ssl.SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory(certificates);
//            if (sslSocketFactory != null) {
//                builder.sslSocketFactory(sslSocketFactory);
//            }
            Interceptor interceptor = NetInitialize.getIntercept();
            if (interceptor != null)
                builder.addInterceptor(interceptor);
            builder.addInterceptor(mInterceptor);
            builder.retryOnConnectionFailure(true)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .cache(cache)
            ;
            mClient = builder.build();
        }
        return mClient;
    }
    private static class UnSafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;//自行添加判断逻辑，true->Safe，false->unsafe
        }
    }
}
