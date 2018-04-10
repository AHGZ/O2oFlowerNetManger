package com.rxy.netlib.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rxy on 17/7/19.
 */

public class HeaderIntercept implements Interceptor {

    private HashMap<String, String> header;

    public HeaderIntercept(HashMap<String, String> headers) {
        header = new HashMap<>();
        header.putAll(headers);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        for (Map.Entry<String, String> entry : header.entrySet()) {

            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Response response = chain.proceed(builder.build());

        return response;
    }

}
