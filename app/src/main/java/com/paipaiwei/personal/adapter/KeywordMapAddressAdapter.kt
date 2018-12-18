package com.paipaiwei.personal.adapter

import com.amap.api.services.core.PoiItem
import com.amap.api.services.help.Tip
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.view.ItemEntryView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.AboutBean
import java.util.ArrayList


class KeywordMapAddressAdapter(data: List<Tip>) :
    BaseQuickAdapter<Tip, BaseViewHolder>(R.layout.keywordmapaddressadapter, data) {


    override fun convert(helper: BaseViewHolder?, item: Tip?) {
        helper?.setText(R.id.tv_name, item?.name)
        helper?.setText(R.id.tv_address, "${item?.address}")
    }
}