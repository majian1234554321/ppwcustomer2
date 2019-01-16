package com.paipaiwei.personal.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.*
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean


class Collection123Adapter( data: List<RecentlyBrowseBean.ItemsBean>) :
    BaseQuickAdapter<RecentlyBrowseBean.ItemsBean, BaseViewHolder>(R.layout.recentlybrowseadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: RecentlyBrowseBean.ItemsBean?) {
        //



        helper?.setText(R.id.tv_storeName,item?.title)
        helper?.setText(R.id.tv_avgPrice,item?.consumption)
        helper?.setText(R.id.tv_info,item?.remark)
        helper?.setText(R.id.tv_KM,item?.remark)



    }


}