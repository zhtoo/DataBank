package com.hs.mydatabinding.retrofit.exception;

import android.util.Log;

import com.hs.mydatabinding.retrofit.auxiliary.HtmlRegexpUtil;


/**
 * Description: HTTP请求异常类
 * 异常码类型的处理可以根据实际需求更改
 */
public class ApiException extends RuntimeException {
    private int    code;
    private String msg;

    public ApiException(int resultCode, String msg) {
        this(msg);
        this.code = resultCode;
        this.msg = HtmlRegexpUtil.filterHtml(msg);//过滤HTML标记
        Log.d("msg", msg);
        Log.d("resultCode", resultCode + "");
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = HtmlRegexpUtil.filterHtml(msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void apiExceptionCode(int code) {
        switch (code) {
            case AppResultCode.TOKEN_MISS://丢失（缺失）的token
            case AppResultCode.TOKEN_LOSE_EFFICACY: //长期不在线,token失效
            case AppResultCode.ERR_USER_LOCKED_CAUSE_PAYPWD://用户被锁定，多次键入支付密码错误
            case AppResultCode.TOKEN_TIMEOUT://token过期
            case AppResultCode.TOKEN_INVALID://token不存在，无效的token
                //跳转到登录界面
                break;
            case AppResultCode.ERR_USER_LOCKED_CAUSE_PWD://用户被锁定，多次键入登录密码错误
                //处理不？

                break;
            case AppResultCode.ERR_USER_FREEZE://用户权限锁定
               //处理异常

                break;
            default:
        }
    }
}
