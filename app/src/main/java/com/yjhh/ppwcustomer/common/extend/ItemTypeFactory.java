package com.yjhh.ppwcustomer.common.extend;

import android.view.View;

import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.bean.Main1BannerBean;
import com.yjhh.ppwcustomer.bean.Main1ContentBean;
import com.yjhh.ppwcustomer.bean.Main1HeadBean;

public class ItemTypeFactory implements TypeFactory {
    //  将id作为type传入adapter
    public static final int BANNER_ITEM_LAYOUT = R.layout.item_banner_mulittype;
    public static final int CONTENT_ITEM_LAYOUT = R.layout.item_content_mulittype;
    @Override public int type(Main1BannerBean bannerBean) {
        return BANNER_ITEM_LAYOUT;
    }

    @Override public int type(Main1ContentBean contentBean) {
        return CONTENT_ITEM_LAYOUT;
    }

    @Override public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case BANNER_ITEM_LAYOUT:
               // return new BannerViewHolder(itemView);
            case CONTENT_ITEM_LAYOUT:
                return new ContentViewHolder(itemView);
            default:
                return null;
        }
    }
}
