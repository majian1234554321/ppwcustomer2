package com.yjhh.ppwcustomer.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.R.id.*
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean


class Collection123Adapter( data: List<RecentlyBrowseBean.ItemsBean>) :
    BaseQuickAdapter<RecentlyBrowseBean.ItemsBean, BaseViewHolder>(R.layout.recentlybrowseadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: RecentlyBrowseBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



        helper?.setText(R.id.tv_storeName,item?.title)
        helper?.setText(R.id.tv_avgPrice,item?.consumption)
        helper?.setText(R.id.tv_info,item?.remark)
        helper?.setText(R.id.tv_KM,item?.remark)

//        tv_storeName.text = data[position].title
//        id_ratingbar.setStar(data[position].grade.toFloat())
//
//        tv_avgPrice.text = data[position].consumption
//
//        tv_info.text = data[position].remark
//
//        tv_KM.text = data[position].remark
//
//        Glide.with(context).load(data[position].imageUrl).into(iv_image)


    }

//    override fun setNewData(data: MutableList<RecentlyBrowseBean.ItemsBean>?) {
//        data = data
//    }
}