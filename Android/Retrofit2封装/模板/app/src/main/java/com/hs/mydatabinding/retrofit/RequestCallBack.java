package com.hs.mydatabinding.retrofit;


import com.hs.mydatabinding.retrofit.exception.ApiException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: 网络请求回调封装类
 * 对http请求失败处理可以在此处进行，注释已经写好
 */
public abstract class RequestCallBack<T> implements Callback<T> {
    public abstract void onSuccess(Call<T> call, Response<T> response);

    private boolean isShow = false;//是否提示 HTTP请求异常类 ApiException 以外的异常

    public RequestCallBack() {
    }

    public RequestCallBack(boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ApiException) {
            ApiException apiException = (ApiException) t;
            apiException.apiExceptionCode(apiException.getCode());
            //Toast提示

        } else if (isShow) {
            //Toast提示
        }
        t.printStackTrace();
    }
}
