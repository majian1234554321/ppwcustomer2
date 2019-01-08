package com.yjhh.common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * King on 2016/12/6.
 */

public class TimeUtil {

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {

        if (TextUtils.isEmpty(s)) {
            return "";
        }

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String stampToDate2(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = Long.valueOf(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String stampToDate(String s, String stytle) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(stytle, Locale.CHINA);
        long lt = Long.valueOf(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime() / 1000;
        res = String.valueOf(ts);
        return res;
    }


    public static String secondToTime(long second) {

        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数

        if (days > 0) {
            return days + ":" + hours + ":" + minutes + ":" + second;
        } else if (hours > 0) {
            return hours + ":" + minutes + ":" + second;
        } else if (minutes > 0) {
            if (second >= 10) {
                return minutes + ":" + second;
            }
            return minutes + ":0" + second;
        } else {
            return "00:" + second;
        }


    }


    private boolean isTimeRange(String s, String e) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = df.parse(df.format(new Date()));
        Date begin = df.parse(s);
        Date end = df.parse(e);
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(now);
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(begin);
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);
        if (nowTime.before(endTime) && nowTime.after(beginTime)) {
            return true;
        } else {
            return false;
        }
    }


}
