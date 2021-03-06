package com.paipaiwei.personal.adapter

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.tv_Number
import com.paipaiwei.personal.bean.DishBean
import com.paipaiwei.personal.bean.ModelDish
import kotlinx.android.synthetic.main.shopcartadapter.view.*

class ShopCartAdapter(var lists: List<DishBean>) :
    BaseQuickAdapter<DishBean, BaseViewHolder>(R.layout.shopcartadapter, lists) {
    override fun convert(helper: BaseViewHolder?, item: DishBean?) {
        helper?.setText(R.id.tv_Number, "x ${item?.count}")

        helper?.setText(R.id.tv_dishName, item?.dish?.dishName)
    }


}