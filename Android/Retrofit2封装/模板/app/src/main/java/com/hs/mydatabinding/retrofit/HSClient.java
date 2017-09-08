package com.hs.mydatabinding.retrofit;

import com.hs.mydatabinding.base.BaseParams;
import com.hs.mydatabinding.retrofit.converter.GsonConverterFactory;
import com.hs.mydatabinding.retrofit.interceptor.BasicParamsInject;
import com.hs.mydatabinding.retrofit.interceptor.HttpLoggingInterceptor;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 作者：zhanghaitao on 2017/8/29 14:05
 * 邮箱：820159571@qq.com
 * Description: 网络请求client
 * 重要类。不要手残修改
 */

public class HSClient {

    // 网络请求超时时间值(s)
    private static final int TIMEOUT = 10;

    // retrofit实例
    private Retrofit retrofit;

    /**
     * 私有化构造方法
     */
    private HSClient() {
        updataRetrofit();
    }

    /**
     * 调用单例对象
     */
    private static HSClient instance;

    public static HSClient getInstance() {
        if (instance == null)
            instance = new HSClient();
        return instance;
    }


    private void updataRetrofit() {

        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        // 添加签名参数
        builder.addInterceptor(BasicParamsInject.getInstance().getInterceptor());
        // 打印参数
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        //创建Retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseParams.TEST_URL)//主机地址
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .build();
    }


///////////////////////////////////////////////////////////////////////////
// service
///////////////////////////////////////////////////////////////////////////

    private static TreeMap<String, Object> serviceMap;

    private static TreeMap<String, Object> getServiceMap() {
        if (serviceMap == null)
            serviceMap = new TreeMap<>();
        return serviceMap;
    }

    /**
     * @return 指定service实例
     */
    public static <T> T getService(Class<T> clazz) {
        if (getServiceMap().containsKey(clazz.getSimpleName())) {
            return (T) getServiceMap().get(clazz.getSimpleName());
        }
        T service = HSClient.getInstance().retrofit.create(clazz);
        getServiceMap().put(clazz.getSimpleName(), service);
        return service;
    }


}
