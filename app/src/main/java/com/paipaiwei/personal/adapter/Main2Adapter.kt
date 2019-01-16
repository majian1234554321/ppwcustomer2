package com.paipaiwei.personal.adapter

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.*
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.NearByDataBean
import com.paipaiwei.personal.bean.NearbyBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.view.RatingBar
import java.lang.StringBuilder


class Main2Adapter(data: List<NearByDataBean.ItemsBean>) :
    BaseQuickAdapter<NearByDataBean.ItemsBean, BaseViewHolder>(R.layout.recentlybrowseadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: NearByDataBean.ItemsBean?) {
        //


        helper?.setText(com.ppwc.restaurant.R.id.tv_storeName, item?.name)
        helper?.setText(com.ppwc.restaurant.R.id.tv_avgPrice, "${item?.perCapita}/元")

        val sb = StringBuilder()
        item?.labels?.forEach {
            sb.append(it).append("\t")
        }
        helper?.setText(com.ppwc.restaurant.R.id.tv_info, sb.toString())

        helper?.setText(com.ppwc.restaurant.R.id.tv_KM, item?.distance)

        ImageLoaderUtils.load(
            BaseApplication.getIns(),
            helper?.getView(com.ppwc.restaurant.R.id.iv_image),
            item?.logoUrl,
            com.ppwc.restaurant.R.drawable.icon_place_pai,
            com.ppwc.restaurant.R.drawable.icon_place_pai,
            5
        )



        item?.grade?.toFloat()?.let { helper?.getView<RatingBar>(com.ppwc.restaurant.R.id.id_ratingbar)?.setStar(it) }

        helper?.setText(com.ppwc.restaurant.R.id.tv_score, item?.grade.toString())


        if (item?.ifNews!!) {
            helper?.setVisible(com.ppwc.restaurant.R.id.iv_zsj, true)
            helper?.setText(com.ppwc.restaurant.R.id.iv_zsj, "新店")
        } else if (item?.ifRec) {
            helper?.setVisible(com.ppwc.restaurant.R.id.iv_zsj, true)
            helper?.setText(com.ppwc.restaurant.R.id.iv_zsj, "推荐")
        } else {
            helper?.setVisible(com.ppwc.restaurant.R.id.iv_zsj, false)
        }





        if (item?.coupons != null) {
            if (item?.coupons.size >= 1) {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.text = item?.coupons[0].valueText
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.visibility = View.VISIBLE
            } else {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.visibility = View.GONE
            }

            if (item?.coupons.size >= 2) {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.text = item?.coupons[1].valueText
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.visibility = View.VISIBLE
            } else {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.visibility = View.GONE
            }

            if (item?.coupons.size >= 3) {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.text = item?.coupons[2].valueText
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.visibility = View.VISIBLE
                helper?.setVisible(com.ppwc.restaurant.R.id.tv_more, true)

            } else {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.visibility = View.GONE

                helper?.setVisible(com.ppwc.restaurant.R.id.tv_more, false)
            }


        } else {
            helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.visibility = View.GONE
            helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.visibility = View.GONE
            helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.visibility = View.GONE
        }






    }


}