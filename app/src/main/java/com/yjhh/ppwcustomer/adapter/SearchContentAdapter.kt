package com.yjhh.ppwcustomer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener2
import kotlinx.android.synthetic.main.searchcontentadapter.view.*

class SearchContentAdapter(var list: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.searchcontentadapter, list) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_title, item)


    }


}








