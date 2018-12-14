package com.paipaiwei.takeout_personal.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.bean.CouponBean
import com.zhy.view.flowlayout.TagFlowLayout


class CouponAdapter(data: List<CouponBean.ItemsBean>) :
    BaseQuickAdapter<CouponBean.ItemsBean, BaseViewHolder>(R.layout.couponadapter, data) {

    override fun convert(helper: BaseViewHolder, item: CouponBean.ItemsBean) {


        helper.setText(R.id.tv_name, item.title)

        helper.setText(R.id.tv_Price, "￥${item.value}")

        helper.setText(R.id.tv_remark1, item.remark)

        helper.setText(R.id.tv_name, item.title)


    }


}