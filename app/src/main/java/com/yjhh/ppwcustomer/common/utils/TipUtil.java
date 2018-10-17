package com.yjhh.ppwcustomer.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * King on 2016/12/6.
 */

public class TipUtil {
    public static void showLongToast(Context context, int strid) {
        Toast.makeText(context, context.getResources().getString(strid), Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, int strid) {
        Toast.makeText(context, context.getResources().getString(strid), Toast.LENGTH_SHORT).show();
    }
}
