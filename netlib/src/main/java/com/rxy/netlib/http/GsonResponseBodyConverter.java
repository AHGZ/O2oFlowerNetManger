package com.rxy.netlib.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author: zhangpeisen
 * created on: 2017/11/15 下午5:41
 * description: 自定义工厂解析类
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.e("TAG", "response==" + response);
        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        ApiResponse apiResponse = new ApiResponse();
        JSONObject json = null;
        try {
            json = new JSONObject(response);
            Log.e("json", "convert: " + response);
            String code = json.optString("r");
            String msg = json.optString("msg");
            if (response.contains("data\":[")) {
                JSONArray jsonArray = json.optJSONArray("data");
                if (jsonArray.length() != 0) {
                    ArrayList<T> fromJsonList = gson.fromJson(jsonArray.toString(), new
                            TypeToken<ArrayList<T>>() {
                            }.getType());
                    apiResponse.setDatalist(fromJsonList);
                    return gson.fromJson(response, type);
                }
            } else if (response.contains("data\":{")) {
                JSONObject jsonObject = json.optJSONObject("data");
                if (jsonObject != null) {
                    return gson.fromJson(response, type);
                }
            }
            throw new Exception(msg);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}

