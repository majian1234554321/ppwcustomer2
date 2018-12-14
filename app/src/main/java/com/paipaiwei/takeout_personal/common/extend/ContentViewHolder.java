package com.paipaiwei.takeout_personal.common.extend;

import android.view.View;
import android.widget.TextView;
import com.paipaiwei.takeout_personal.R;

public class ContentViewHolder extends BaseViewHolder {

    TextView textView;
    public ContentViewHolder(View itemView) {
        super(itemView);

    }

     public void bindViewData(Object data) {
        //imageview.setImageURI(((BannerBean)data).getUrl());
    }
}