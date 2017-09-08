package com.hs.mydatabinding.retrofit;


import com.hs.mydatabinding.retrofit.exception.ApiException;
import com.hs.mydatabinding.retrofit.exception.AppResultCode;

import retrofit2.Call;

/**
 * Email: hbh@erongdu.com
 * Created by hebihe on 6/14/16.
 * 初始化操作调用
 */
public abstract class InitRequestCallBack<T> extends RequestCallBack<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ApiException) {
            ApiException apiException = (ApiException) t;
            apiException.apiExceptionCode(apiException.getCode());
            if (apiException.getCode() != AppResultCode.ERR_USER_INVALID) {
               //TOAST提示异常

            }
        }
        t.printStackTrace();
    }
}
