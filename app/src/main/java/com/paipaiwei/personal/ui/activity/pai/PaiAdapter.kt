package com.paipaiwei.personal.ui.activity.pai

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.personal.R
import com.ppwc.restaurant.mrbean.RecommendProductBean

class PaiAdapter (data: List<PaiBean>) :
    BaseQuickAdapter<PaiBean, BaseViewHolder>(R.layout.recommendproductadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: PaiBean?) {

    }
}