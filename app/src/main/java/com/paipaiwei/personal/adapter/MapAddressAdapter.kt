package com.paipaiwei.personal.adapter

import com.amap.api.services.core.PoiItem
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.view.ItemEntryView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.AboutBean
import java.util.ArrayList


class MapAddressAdapter(data: List<PoiItem>) :
    BaseQuickAdapter<PoiItem, BaseViewHolder>(R.layout.mapaddressadapter, data) {


    override fun convert(helper: BaseViewHolder?, item: PoiItem?) {
        helper?.setText(R.id.tv_name, item?.title)
        helper?.setText(R.id.tv_address, "${item?.provinceName}${item?.cityName}${item?.snippet}")
    }
}