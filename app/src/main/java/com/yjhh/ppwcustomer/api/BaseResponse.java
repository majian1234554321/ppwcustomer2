package com.yjhh.ppwcustomer.api;

public class BaseResponse<T> {
    public int status;
    public String message;
    public T data;

    public boolean isSuccess() {
        return status == 200;
    }
}


