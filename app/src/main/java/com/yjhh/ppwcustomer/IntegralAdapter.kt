package com.yjhh.ppwcustomer

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.R.id.*
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.IntegralBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean


class IntegralAdapter :
    BaseQuickAdapter<IntegralBean.ItemsBean, BaseViewHolder>(R.layout.recentlybrowseadapter, null) {
    override fun convert(helper: BaseViewHolder?, item: IntegralBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


//
//        helper?.setText(R.id.tv_storeName,item?.title)
//        helper?.setText(R.id.tv_avgPrice,item?.consumption)
        helper?.setText(R.id.tv_info,item?.remark)
        helper?.setText(R.id.tv_KM,item?.remark)




    }

}