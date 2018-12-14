package com.paipaiwei.takeout_personal.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.R.id.*
import com.paipaiwei.takeout_personal.bean.CouponBean
import com.paipaiwei.takeout_personal.bean.RecentlyBrowseBean


class Collection123Adapter( data: List<RecentlyBrowseBean.ItemsBean>) :
    BaseQuickAdapter<RecentlyBrowseBean.ItemsBean, BaseViewHolder>(R.layout.recentlybrowseadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: RecentlyBrowseBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



        helper?.setText(R.id.tv_storeName,item?.title)
        helper?.setText(R.id.tv_avgPrice,item?.consumption)
        helper?.setText(R.id.tv_info,item?.remark)
        helper?.setText(R.id.tv_KM,item?.remark)



    }


}