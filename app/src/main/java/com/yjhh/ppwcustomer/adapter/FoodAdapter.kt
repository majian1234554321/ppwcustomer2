package com.yjhh.ppwcustomer.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwcustomer.R

class FoodAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.foodadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
