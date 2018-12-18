package com.paipaiwei.personal.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.MyMessageBean
import com.paipaiwei.personal.common.utils.TimeUtil

import kotlinx.android.synthetic.main.mymessagefragmentadapter.view.*

class MyMessageFragmentAdapter(var data: ArrayList<MyMessageBean.ItemsBean>) :
    BaseQuickAdapter<MyMessageBean.ItemsBean, BaseViewHolder>(R.layout.mymessagefragmentadapter,data) {
    override fun convert(helper: BaseViewHolder?, item: MyMessageBean.ItemsBean?) {


        helper?.setText(R.id.tv1,item?.title)
        helper?.setText(R.id.tv2,item?.content)

        helper?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.sendTime.toString()))

    }


}

