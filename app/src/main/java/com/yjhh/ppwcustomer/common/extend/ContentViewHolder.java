package com.yjhh.ppwcustomer.common.extend;

import android.view.View;
import android.widget.TextView;
import com.yjhh.ppwcustomer.R;

public class ContentViewHolder extends BaseViewHolder {

    TextView textView;
    public ContentViewHolder(View itemView) {
        super(itemView);

    }

     public void bindViewData(Object data) {
        //imageview.setImageURI(((BannerBean)data).getUrl());
    }
}