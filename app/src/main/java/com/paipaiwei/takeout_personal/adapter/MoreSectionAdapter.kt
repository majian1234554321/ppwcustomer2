package com.paipaiwei.takeout_personal.adapter

import android.app.Activity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


import com.paipaiwei.takeout_personal.R

import com.zhy.view.flowlayout.TagFlowLayout


class MoreSectionAdapter(var activity: Activity, data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>( R.layout.moresectionadapter, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        helper.setText(R.id.text, item)


//      val  flowlayout =  (helper.getView<TagFlowLayout>(R.id.flowlayout))
//        flowlayout.adapter = SearchAdapter(activity,data,flowlayout)

    }


}