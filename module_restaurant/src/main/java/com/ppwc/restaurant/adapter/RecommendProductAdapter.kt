package com.ppwc.restaurant.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.mrbean.RecommendProductBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils

class RecommendProductAdapter(data: List<RecommendProductBean.ItemsBean>) : BaseQuickAdapter<RecommendProductBean.ItemsBean, BaseViewHolder>(R.layout.recommendproductadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: RecommendProductBean.ItemsBean?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



            helper?.setText(R.id.tv_storeName,item?.shopName)
            helper?.setText(R.id.tv_price,item?.price)
            helper?.setText(R.id.tv_info,item?.describe)
           // helper?.setText(R.id.tv_like,item.)



            ImageLoaderUtils.load(BaseApplication.getIns(),helper?.getView(R.id.iv_image),item?.logoUrl,R.drawable.icon_place_pai,R.drawable.icon_place_pai,5)





        }


    }