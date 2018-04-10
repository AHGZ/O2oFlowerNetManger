package com.rxy.netlib.http;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * author: zhangpeisen
 * created on: 2017/11/28 下午4:51
 * description:
 */
public class HttpResultGsonDeserializer implements JsonDeserializer<ApiResponse<?>> {

    @Override
    public ApiResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        Log.e("TAG", "jsonObject==" + jsonObject);
        JsonElement jsonData = jsonObject.has("data") ? jsonObject.get("data") : null;
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(jsonObject.has("r") ? jsonObject.get("r").getAsString() : "");
        apiResponse.setMessage(jsonObject.has("msg") ? jsonObject.get("msg").getAsString() : "");

        //处理Data("",{},[])空串情况
        if (jsonData != null && jsonData.isJsonObject()) {
            //指定泛型具体类型
            if (type instanceof ParameterizedType) {
                Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
                Object item = jsonDeserializationContext.deserialize(jsonData, itemType);
                apiResponse.setData(item);
            } else {
                //未指定泛型具体类型
                apiResponse.setData(jsonData);
            }
        } else if (jsonData != null && jsonData.isJsonArray()) {
            ArrayList<?> deserialize = jsonDeserializationContext.deserialize(jsonData, new
                    TypeToken<ArrayList<?>>() {
                    }.getType());
            apiResponse.setDatalist(deserialize);
        } else {
            apiResponse.setData(null);
        }

        return apiResponse;
    }
}