package com.yjhh.ppwcustomer.adapter

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.R.id.tv_Number
import com.yjhh.ppwcustomer.bean.DishBean
import com.yjhh.ppwcustomer.bean.ModelDish
import kotlinx.android.synthetic.main.shopcartadapter.view.*

class ShopCartAdapter(var lists: List<DishBean>) :
    BaseQuickAdapter<DishBean, BaseViewHolder>(R.layout.shopcartadapter, lists) {
    override fun convert(helper: BaseViewHolder?, item: DishBean?) {
        helper?.setText(R.id.tv_Number, "x ${item?.count}")

        helper?.setText(R.id.tv_dishName, item?.dish?.dishName)
    }


}