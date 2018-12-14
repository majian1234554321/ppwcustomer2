package com.paipaiwei.takeout_personal.common.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * King on 2016/12/6.
 */

public class ImageUtil {
    public static void loadImg(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }
}
