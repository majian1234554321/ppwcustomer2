package com.ppwc.restaurant.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.R.id.id_ratingbar
import com.ppwc.restaurant.bean.MeiShiFootBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.view.RatingBar
import java.lang.StringBuilder

class RestaurantInAdapter(data: List<MeiShiFootBean.ItemsBean>) :
    BaseQuickAdapter<MeiShiFootBean.ItemsBean, BaseViewHolder>(R.layout.restaurantinadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: MeiShiFootBean.ItemsBean?) {


        helper?.setText(R.id.tv_storeName, item?.name)
        helper?.setText(R.id.tv_avgPrice, "${item?.perCapita}/元")

        val sb = StringBuilder()
        item?.labels?.forEach {
            sb.append(it).append("\t")
        }
        helper?.setText(R.id.tv_info, sb.toString())

        helper?.setText(R.id.tv_KM, item?.distance)



        ImageLoaderUtils.load(
            BaseApplication.getIns(),
            helper?.getView(R.id.iv_image),
            item?.logoUrl,
            R.drawable.icon_place_pai,
            R.drawable.icon_place_pai,
            5
        )



        item?.grade?.toFloat()?.let { helper?.getView<RatingBar>(R.id.id_ratingbar)?.setStar(it) }

        helper?.setText(R.id.tv_score, item?.grade.toString())


        if (item?.ifNews!!) {
            helper?.setVisible(R.id.iv_zsj, true)
            helper?.setText(R.id.iv_zsj, "新店")
        } else if (item?.ifRec) {
            helper?.setVisible(R.id.iv_zsj, true)
            helper?.setText(R.id.iv_zsj, "推荐")
        } else {
            helper?.setVisible(R.id.iv_zsj, false)
        }





        if (item?.coupons != null) {
            if (item?.coupons.size >= 1) {
                helper?.getView<TextView>(R.id.tv_1)?.text = item?.coupons[0].valueText
                helper?.getView<TextView>(R.id.tv_1)?.visibility = View.VISIBLE
            } else {
                helper?.getView<TextView>(R.id.tv_1)?.visibility = View.GONE
            }

            if (item?.coupons.size >= 2) {
                helper?.getView<TextView>(R.id.tv_2)?.text = item?.coupons[1].valueText
                helper?.getView<TextView>(R.id.tv_2)?.visibility = View.VISIBLE
            } else {
                helper?.getView<TextView>(R.id.tv_2)?.visibility = View.GONE
            }

            if (item?.coupons.size >= 3) {
                helper?.getView<TextView>(R.id.tv_3)?.text = item?.coupons[2].valueText
                helper?.getView<TextView>(R.id.tv_3)?.visibility = View.VISIBLE
                helper?.setVisible(R.id.tv_more, true)

            } else {
                helper?.getView<TextView>(R.id.tv_3)?.visibility = View.GONE

                helper?.setVisible(R.id.tv_more, false)
            }


        } else {
            helper?.getView<TextView>(R.id.tv_1)?.visibility = View.GONE
            helper?.getView<TextView>(R.id.tv_2)?.visibility = View.GONE
            helper?.getView<TextView>(R.id.tv_3)?.visibility = View.GONE
        }


    }


}