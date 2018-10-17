package com.yjhh.ppwcustomer.listener;

public interface OnSuccessAndFaultListener {

    void onSuccess(String result);

    void onFault(String errorMsg);
}
