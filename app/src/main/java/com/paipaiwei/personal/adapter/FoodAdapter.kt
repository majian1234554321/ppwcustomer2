package com.paipaiwei.personal.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.personal.R

class FoodAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.foodadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        //
    }

}
