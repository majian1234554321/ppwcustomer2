package com.paipaiwei.takeout_personal.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.paipaiwei.takeout_personal.CurrentApplication;


/**
 * King on 2016/12/6.
 */

public class SystemUtil {

    public static String getIMEI() {
        try {
            String imei = "";
            TelephonyManager tm = (TelephonyManager) CurrentApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
            if (imei == null || imei.length() == 0) {
                imei = Settings.Secure.getString(CurrentApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            if (imei == null || imei.length() == 0) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String getVersionName() {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = CurrentApplication.getInstance().getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(CurrentApplication.getInstance().getPackageName(), 0);
            return packInfo.versionName;
        } catch (Exception e) {
            return "";
        }
    }
}
