package com.rxy.netlib.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxy.netlib.init.NetConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rxy on 17/7/19.
 */

public class RetrofitUtils {

    private static OkHttpClient mClient;
    private static volatile Retrofit mRetrofit;
    private static Gson gson;

    private RetrofitUtils() {
    }

    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (mRetrofit == null) {
                    if (mClient == null) {
                        mClient = OkHttpUtils.getInstance();
                    }
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(NetConfig.API_SERVER)
                            .addConverterFactory(GsonConverterFactory.create(buildGson()))
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(mClient)
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    public static Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(ApiResponse.class, new HttpResultGsonDeserializer())
                    .serializeNulls().create();
        }
        return gson;
    }

}
