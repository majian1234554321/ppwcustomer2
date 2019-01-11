package com.paipaiwei.personal.adapter

import com.amap.api.services.core.PoiItem
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.Main3_1Bean

class Main3_1Adapter(data: List<Main3_1Bean.ItemsBean>) :
    BaseQuickAdapter<Main3_1Bean.ItemsBean, BaseViewHolder>(R.layout.main3_1adapter, data) {


    override fun convert(helper: BaseViewHolder?, item: Main3_1Bean.ItemsBean?) {
        helper?.setText(R.id.tv_name, item?.nickName)

    }
}
