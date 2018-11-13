package com.yjhh.ppwcustomer

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.yjhh.ppwcustomer.bean.IntegralBean

import com.yjhh.ppwcustomer.common.utils.TimeUtil


class IntegralAdapter :
    BaseQuickAdapter<IntegralBean.ItemsBean, BaseViewHolder>(R.layout.integraladapter, null) {
    override fun convert(helper: BaseViewHolder?, item: IntegralBean.ItemsBean?) {

        helper?.setText(R.id.tv_count, "+${item?.money} 个")
        helper?.setText(R.id.tv_remark, item?.remark)
        helper?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.createdTime.toString()))


    }

}