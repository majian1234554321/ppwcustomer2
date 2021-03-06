package com.ppwc.restaurant.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.bean.MeiShiHeadBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils


class RestaurantInTabAdapter(data: List<MeiShiHeadBean.TabsModuleModelsBean>) :
    BaseQuickAdapter<MeiShiHeadBean.TabsModuleModelsBean, BaseViewHolder>(R.layout.restaurantintabadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: MeiShiHeadBean.TabsModuleModelsBean?) {

        helper?.setText(R.id.tv_name, item?.text)

        helper?.getView<ImageView>(R.id.iv_image)?.let { Glide.with(mContext).load(item?.iconUrl).into(it) }

//        ImageLoaderUtils.load(
//            mContext,
//            helper?.getView(R.id.iv_image),
//            item?.iconUrl,
//            R.drawable.icon_place_pai,
//            R.drawable.icon_place_pai, 5
//        )

    }


}