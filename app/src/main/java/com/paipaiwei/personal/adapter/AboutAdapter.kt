package com.paipaiwei.personal.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.view.ItemEntryView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.AboutBean


class AboutAdapter(data: List<AboutBean.FunctionsBean>) :
    BaseQuickAdapter<AboutBean.FunctionsBean, BaseViewHolder>(R.layout.aboutadapter, data) {


    override fun convert(helper: BaseViewHolder?, item: AboutBean.FunctionsBean?) {





        when (item?.name) {

            "客服电话" -> {
                helper?.getView<ItemEntryView>(R.id.iev_1)?.setLeftTitle(item?.name)?.setTextContent(item?.linkUrl.split("tels://")[1])?.setArrow()
            }
            else -> {
                helper?.getView<ItemEntryView>(R.id.iev_1)?.setLeftTitle(item?.name)?.setTextContent("")
            }
        }


    }
}