package com.paipaiwei.takeout_personal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.interfaces.OnItemClickListener
import com.paipaiwei.takeout_personal.interfaces.OnItemClickListener2
import kotlinx.android.synthetic.main.searchcontentadapter.view.*

class SearchContentAdapter(var list: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.searchcontentadapter, list) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_title, item)


    }


}








