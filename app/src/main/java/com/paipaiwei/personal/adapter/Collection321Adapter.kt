package com.paipaiwei.personal.adapter

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.*
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils


class Collection321Adapter(data: List<RecentlyBrowseBean.ItemsBean>) :
    BaseQuickAdapter<RecentlyBrowseBean.ItemsBean, BaseViewHolder>(R.layout.recentlybrowseadapter2, data) {
    override fun convert(helper: BaseViewHolder?, item: RecentlyBrowseBean.ItemsBean?) {
        //


        helper?.setText(R.id.tv_storeName, item?.title)
        helper?.setText(R.id.tv_price, mContext.getString(R.string.rmb_price_double2, item?.price))
        helper?.setText(R.id.tv_info, item?.remark)
        // helper?.setText(R.id.tv_KM,item?.remark)


        ImageLoaderUtils.load(
            BaseApplication.getIns(),
            helper?.getView(R.id.iv_image),
            item?.imageUrl,
            R.drawable.icon_place_square,
            R.drawable.icon_place_square,
            0
        )


    }


}