package com.hs.mydatabinding.retrofit.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.hs.mydatabinding.BuildConfig;
import com.hs.mydatabinding.retrofit.entity.HttpResult;
import com.hs.mydatabinding.retrofit.exception.ApiException;
import com.hs.mydatabinding.retrofit.exception.AppResultCode;
import com.hs.mydatabinding.retrofit.auxiliary.LogWrap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Gson响应体转换类
 * （请求回来的数据的解析）
 * @param <T>
 */

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {


    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.type = type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string().trim();

        if (BuildConfig.LOG_DEBUG)
            LogWrap.w("HSClient", "response >> Json数据 \n" + response);

        // 解析resCode和resMsg
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        if (httpResult.getResCode() != AppResultCode.SUCCESS && !httpResult.getRes_code().equals(AppResultCode.SUCCESS_9999)) {
            //抛异常
            throw new ApiException(httpResult.getResCode(), httpResult.getResMsg());
        }

        //如果请求返回类是HttpResult 那么直接返回
        if (type == HttpResult.class) {
            httpResult.setBody(response);
            return (T) httpResult;
        }
        //捕获异常，有异常返回为空
        try {
            String str = new JSONObject(response).getString("resData");

            // 判断需要解析成的T对象是否是Collection的实现类,是则截取成array类型的JSON字符串
            if (isInstanceOfCollection(adapter.getClass())) {
                str = str.substring(str.indexOf("["), str.length() - 1);
            }

            StringReader reader     = new StringReader(str);
            JsonReader jsonReader = gson.newJsonReader(reader);
            return adapter.read(jsonReader);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } finally {
            value.close();
        }
    }

    /**
     * 是否是Collection的实现类
     */
    public static boolean isInstanceOfCollection(Class clazz) {
        Type   genType = clazz.getGenericSuperclass();
        Type[] params  = ((ParameterizedType) genType).getActualTypeArguments();
        String result  = params[0].toString();
        return result.contains(Collection.class.getName());
    }
}
