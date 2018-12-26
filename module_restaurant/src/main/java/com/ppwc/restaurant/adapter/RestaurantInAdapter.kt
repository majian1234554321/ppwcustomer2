package com.ppwc.restaurant.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils

class RestaurantInAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.restaurantinadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



            helper?.setText(R.id.tv_storeName,item)
            helper?.setText(R.id.tv_avgPrice,item)
            helper?.setText(R.id.tv_info,item)
            helper?.setText(R.id.tv_KM,item)



            ImageLoaderUtils.load(BaseApplication.getIns(),helper?.getView(R.id.iv_image),item,R.drawable.icon_place_pai,R.drawable.icon_place_pai,5)





        }


    }