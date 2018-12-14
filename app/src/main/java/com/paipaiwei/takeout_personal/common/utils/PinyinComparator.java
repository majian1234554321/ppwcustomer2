package com.paipaiwei.takeout_personal.common.utils;

import android.text.TextUtils;
import com.paipaiwei.takeout_personal.bean.PhoneBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<PhoneBean> {
    @Override
    public int compare(PhoneBean o1, PhoneBean o2) {
        if (TextUtils.equals(o1.letter, "#")) {
            return 1;
        } else if (TextUtils.equals(o2.letter, "#")) {
            return -1;
        } else {
            return o1.pinyin.compareTo(o2.pinyin);
        }
    }
}
