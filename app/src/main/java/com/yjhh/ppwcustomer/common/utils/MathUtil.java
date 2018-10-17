package com.yjhh.ppwcustomer.common.utils;

import android.content.res.Resources;

/**
 * King on 2016/12/13.
 */

public class MathUtil {

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int intdp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  (int) (dp * scale + 0.5f);
    }
}
