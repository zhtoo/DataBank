package com.hs.mydatabinding.retrofit.interceptor;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.hs.mydatabinding.MyApplication;
import com.hs.mydatabinding.base.BaseParams;
import com.hs.mydatabinding.retrofit.RequestParams;
import com.hs.mydatabinding.retrofit.auxiliary.MD5Util;

import okhttp3.Interceptor;

/**
 * Description: 拦截器 - 用于添加签名参数
 *  根据实际项目更改
 */
public class BasicParamsInject {
    private static final String CHARSET_NAME = "UTF-8";
    private BasicParamsInterceptor interceptor;

    private BasicParamsInject() {
    }

    public static BasicParamsInject getInstance() {
        return new BasicParamsInject();
    }

    public Interceptor getInterceptor() {

        if (interceptor == null) {
            upDataInterceptor();
        }
        return interceptor;
    }

    public void upDataInterceptor() {
        // 时间戳
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        // 签名(加密算法)
        String signa = getSigna(ts);
        interceptor = new BasicParamsInterceptor.Builder()
                .addParam(RequestParams.APP_KEY, BaseParams.APP_KEY)
                .addParam(RequestParams.SIGNA, signa)
                .addParam(RequestParams.TS, ts)
                .addParam(RequestParams.MOBILE_TYPE, BaseParams.MOBILE_TYPE)
                .addParam(RequestParams.VERSION_NUMBER, getVersion())
               // .addParam(RequestParams.TOKEN, getToken())
                .addParam(RequestParams.USER_ID, getUserId())
                .build();
    }

    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param ts
     *         时间戳
     */
    private String getSigna(String ts) {
        // appsecrt拼接ts的字符串后进行MD5（32）
        String signa = MD5Util.MD5Encode(BaseParams.APP_SECRET + ts, CHARSET_NAME);
        // 得到的MD5串拼接appkey再次MD5，所得结果转大写
        signa = MD5Util.MD5Encode(signa + BaseParams.APP_KEY, CHARSET_NAME).toUpperCase();
        return signa;
    }

    /**
     * 获取Token (通过SharedPreferences获取)
     */
    private String getToken() {

        return "";
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = MyApplication.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "can not find version name";
        }
    }

    /**
     * 获取userId (通过SharedPreferences获取)
     */
    private String getUserId() {

        return "1";
    }
}
