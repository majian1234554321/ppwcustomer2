package com.yjhh.common.api;

public class BaseResponse<T> {
    public int status;
    public String message;
    public T data;

    public boolean isSuccess() {
        return status == 200;
    }
}


