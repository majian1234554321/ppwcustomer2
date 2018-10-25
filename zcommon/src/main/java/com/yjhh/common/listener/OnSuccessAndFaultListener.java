package com.yjhh.common.listener;

public interface OnSuccessAndFaultListener<T> {

    void onSuccess(T result);

    void onFault(String errorMsg);
}
