package com.yjhh.ppwcustomer.common.utils;

/**
 * Created by TL20160309 on 2016/11/14.
 */

public class DateUtil {

    public static String getFormatDHMmDate( long seconds) {
        if (seconds < 0)
            return "还有0小时0分0秒";
        long one_day = 60 * 60 * 24;
        long one_hour = 60 * 60;
        long one_minute = 60;
        long day, hour, minute, second = 0L;

        day = seconds / one_day;
        hour = seconds % one_day / one_hour;
        minute = seconds % one_day % one_hour / one_minute;
        second = seconds % one_day % one_hour % one_minute;

        if (seconds < one_minute) {
            return seconds + "秒";
        } else if (seconds < one_hour) {
            return minute + "分" + second + "秒";
        } else if (seconds < one_day) {
            return hour + "时" + minute + "分" + second + "秒";
        } else {
            return day + "天" + hour + "时" + minute + "分"+ second + "秒";
        }
    }

}
