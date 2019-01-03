package com.ppwc.restaurant.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.mrbean.RestaurantAlbumBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils

class RestaurantAlbumAdapter(data: List<RestaurantAlbumBean.ItemsBean>) :
    BaseQuickAdapter<RestaurantAlbumBean.ItemsBean, BaseViewHolder>(R.layout.restaurantalbumadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: RestaurantAlbumBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        helper?.setText(R.id.tv_info, item?.name)

        ImageLoaderUtils.load(
            BaseApplication.getIns(),
            helper?.getView(R.id.iv_image),
            item?.imageUrl,
            R.drawable.icon_place_pai,
            R.drawable.icon_place_pai,
            5
        )


    }


}