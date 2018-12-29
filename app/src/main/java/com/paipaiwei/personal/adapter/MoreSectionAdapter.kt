package com.paipaiwei.personal.adapter

import android.app.Activity
import android.view.View
import android.widget.TextView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


import com.paipaiwei.personal.R
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

import com.zhy.view.flowlayout.TagFlowLayout


class MoreSectionAdapter(var activity: Activity, data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.moresectionadapter, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        helper.setText(R.id.text, item)

        val flowlayout = (helper.getView<TagFlowLayout>(R.id.flowlayout))
        flowlayout.adapter = MoreSectionTagAdapter(activity, flowlayout, data)

    }


    class MoreSectionTagAdapter(
        var activity: Activity, var flowLayout: FlowLayout,
        data: MutableList<String>?
    ) : TagAdapter<String>(data) {
        override fun getView(parent: FlowLayout?, position: Int, t: String?): View {

            val tv = activity.layoutInflater.inflate(
               R.layout.moresectiontagadapter,
                flowLayout, false
            ) as TextView
            tv.text = t

            return tv;
        }


    }


}