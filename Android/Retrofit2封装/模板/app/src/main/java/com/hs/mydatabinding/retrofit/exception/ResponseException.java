package com.hs.mydatabinding.retrofit.exception;

/**
 * Description: 网络Response异常
 */
public class ResponseException extends RuntimeException {
    public ResponseException(String detailMessage) {
        super(detailMessage);
    }

    public ResponseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ResponseException(Throwable throwable) {
        super(throwable);
    }
}
