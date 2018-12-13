package com.yjhh.ppwcustomer.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwcustomer.R

import com.yjhh.ppwcustomer.bean.AllFeedBackBean
import com.yjhh.ppwcustomer.common.utils.TimeUtil


class AllFeedBackAdapter(data: List<AllFeedBackBean.ItemsBean>) :
    BaseQuickAdapter<AllFeedBackBean.ItemsBean, BaseViewHolder>(R.layout.allfeedbackadapter, data) {


    override fun convert(helper: BaseViewHolder?, item: AllFeedBackBean.ItemsBean?) {


        helper?.setText(R.id.tv_title, item?.name)
        helper?.setText(R.id.tv_content, item?.cause)


        when (item?.status) {
            0 -> {
                helper?.setText(R.id.tv_status, "已提交") //状态（0已提交 1已查阅 2已反馈）
            }
            1 -> {
                helper?.setText(R.id.tv_status, "查阅") //状态（0已提交 1已查阅 2已反馈）
            }

            else -> {
                helper?.setText(R.id.tv_status, "已反馈") //状态（0已提交 1已查阅 2已反馈）
            }
        }

        helper?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.createdTime))

    }
}