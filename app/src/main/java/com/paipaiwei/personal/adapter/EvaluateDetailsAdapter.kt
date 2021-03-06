package com.paipaiwei.personal.adapter

import android.graphics.Color
import androidx.core.app.ComponentActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.EvaluateDetailsBean

class EvaluateDetailsAdapter(data: List<EvaluateDetailsBean.ItemsBean>) :
    BaseQuickAdapter<EvaluateDetailsBean.ItemsBean, BaseViewHolder>(R.layout.evaluatedetailsadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: EvaluateDetailsBean.ItemsBean?) {
        helper?.setText(R.id.tv_reply, item?.userName)
        helper?.setText(R.id.tv_time, item?.timeText)
        helper?.setText(R.id.tv_content, item?.content)



        if (item?.ifShop == "1") {// 商家
            helper?.setBackgroundColor(R.id.tv_reply, Color.parseColor("#F7EEDD"))
        } else {
            helper?.setBackgroundColor(R.id.tv_reply, Color.parseColor("#E7E7E7"))
        }


    }
}