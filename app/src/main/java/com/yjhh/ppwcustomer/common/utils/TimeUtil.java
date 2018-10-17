package com.yjhh.ppwcustomer.common.utils;

/**
 * King on 2016/12/6.
 */

public class TimeUtil {
    /**
     * 获取10位的时间戳
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
