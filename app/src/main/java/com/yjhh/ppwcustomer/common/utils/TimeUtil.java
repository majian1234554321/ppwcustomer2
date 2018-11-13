package com.yjhh.ppwcustomer.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * King on 2016/12/6.
 */

public class TimeUtil {

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }



}
