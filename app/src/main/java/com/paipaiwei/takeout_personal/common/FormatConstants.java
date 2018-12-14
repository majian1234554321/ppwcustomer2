package com.paipaiwei.takeout_personal.common;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * King on 2016/12/14.
 */

public class FormatConstants {
    private static DecimalFormat decimalFormatSub;
    public static DecimalFormat getDecimalFormatSub() {
        if (decimalFormatSub == null) {
            decimalFormatSub = new DecimalFormat("0.##");
            decimalFormatSub.setRoundingMode(RoundingMode.FLOOR);
        }
        return decimalFormatSub;
    }
    private static DecimalFormat decimalFormatTwo;
    public static DecimalFormat getDecimalFormatTwo() {
        if (decimalFormatTwo == null) {
            decimalFormatTwo = new DecimalFormat("0.00");
            decimalFormatTwo.setRoundingMode(RoundingMode.FLOOR);
        }
        return decimalFormatTwo;
    }
}
