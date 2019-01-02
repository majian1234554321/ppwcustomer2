package com.yjhh.common.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import androidx.core.content.ContextCompat;
import com.yjhh.common.R;


public class TextStyleUtils {

    public static SpannableString changeTextAa(String values, int start, int end, int size) {

        SpannableString spannableString = new SpannableString(values);
        spannableString.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }


    public static SpannableString changeTextColor(String values, int start, int end, int Color) {

        SpannableString spannableString = new SpannableString(values);
        spannableString.setSpan(new ForegroundColorSpan(Color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }


    public static SpannableString stytle(String stringvalue, Context context) {
        SpannableString textString = new  SpannableString(stringvalue);

        textString.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(context, R.color.all_3)),
                0,
                5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        if (stringvalue.contains("天")) {
            textString.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
                    stringvalue.indexOf("有") + 1,
                    stringvalue.indexOf("天"),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );


            textString.setSpan(
                    new  ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
                    stringvalue.indexOf("天") + 1,
                    stringvalue.indexOf("小"),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );


            textString.setSpan(
                    new   AbsoluteSizeSpan(25, true),
                    stringvalue.indexOf("有") + 1,
                    stringvalue.indexOf("天"),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );


            textString.setSpan(
                    new  AbsoluteSizeSpan(25, true),
                    stringvalue.indexOf("天") + 1,
                    stringvalue.indexOf("小"),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );


        } else {
            textString.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
                    stringvalue.indexOf("有") + 1,
                    stringvalue.indexOf("小"),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );


            textString.setSpan(
                    new   AbsoluteSizeSpan(25, true),
                    stringvalue.indexOf("有") + 1,
                    stringvalue.indexOf("小"),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }


        textString.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
                stringvalue.indexOf("时") + 1,
                stringvalue.indexOf("分"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        textString.setSpan(
                new  AbsoluteSizeSpan(25, true),
                stringvalue.indexOf("时") + 1,
                stringvalue.indexOf("分"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        return textString;


    }


}
