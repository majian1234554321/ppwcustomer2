package com.ppwc.restaurant.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.R.id.tv_like
import com.ppwc.restaurant.mrbean.RecommendProductBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.TextStyleUtils


class RecommendProductAdapter(data: List<RecommendProductBean.ItemsBean>) :
    BaseQuickAdapter<RecommendProductBean.ItemsBean, BaseViewHolder>(R.layout.recommendproductadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: RecommendProductBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        helper?.setText(R.id.tv_storeName, item?.shopName)

        helper?.setText(R.id.tv_info, item?.describe)


        val textPrice = "¥ ${BaseApplication.context.getString(R.string.rmb_price_double, item?.price)}"

        helper?.setText(R.id.tv_price, TextStyleUtils.changeTextAa(textPrice, 0, 1, 12))

        val tv_like = helper?.getView<TextView>(R.id.tv_like)

        if (item?.ifZan!!) {
            //iv_zan.
            helper?.setBackgroundRes(R.id.iv_zan, R.drawable.zan042x)
            helper?.setTextColor(R.id.tv_like, Color.parseColor("#FFF9572D"))
        } else {
            helper?.setBackgroundRes(R.id.iv_zan, R.drawable.zan032x)
            helper?.setTextColor(R.id.tv_like, Color.parseColor("#333333"))
        }

        helper?.setText(R.id.tv_like, item?.zan)


        helper?.addOnClickListener(R.id.tv_like)
            ?.addOnClickListener(R.id.iv_zan)


        ImageLoaderUtils.load(
            BaseApplication.getIns(),
            helper?.getView(R.id.iv_image),
            item?.logoUrl,
            R.drawable.icon_place_pai,
            R.drawable.icon_place_pai,
            5
        )


    }


}