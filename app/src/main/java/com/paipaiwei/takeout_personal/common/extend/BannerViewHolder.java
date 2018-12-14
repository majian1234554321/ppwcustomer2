/*
package com.paipaiwei.takeout_personal.common.extend;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.paipaiwei.takeout_personal.R;
import com.paipaiwei.takeout_personal.bean.Main1BannerBean;
import com.paipaiwei.takeout_personal.bean.Main1HeadBean;
import com.paipaiwei.takeout_personal.common.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannerViewHolder extends BaseViewHolder {

    Banner banner;

    public BannerViewHolder(View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.banner);
    }

    @Override
    public void bindViewData(Object data) {
        List<String> mImages = new ArrayList<String>();

        for (int i = 0; i < ((Main1BannerBean) data).list.size();i++) {
            mImages.add(((Main1BannerBean) data).list.get(i).getImageUrl());
        }

        banner.setImages(mImages)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(5000)
                .start();


//        banner.setOnBannerListener {
//            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
//            when (it) {
//                0 -> {
//                }
//                1 -> {
//                }
//                2 -> {
//                }
//                3 -> {
//                }
//                4 -> {
//                }
//                else -> {
//                }
//            }
//        }

    }
}
*/
