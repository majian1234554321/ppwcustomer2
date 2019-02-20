package com.ppwc.restaurant.views.reserve

import android.graphics.Color
import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.mrbean.RecommendProductBean

class ReserveListAdapter(data: List<ReserveListBean.ItemsBean>) :
    BaseQuickAdapter<ReserveListBean.ItemsBean, BaseViewHolder>(R.layout.reservelistadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: ReserveListBean.ItemsBean?) {


        helper?.addOnClickListener(R.id.tv_text)

        helper?.setText(R.id.tv_name, item?.shopName)
            ?.setText(R.id.tv_userCount, "人数 ${item?.userCountText}")
            ?.setText(
                R.id.timeText, if ((!TextUtils.isEmpty(item?.timeText))&&(item?.timeText?.contains("到店")!!)) {
                    item?.timeText?.split("到店")?.get(0)
                } else {
                    item?.timeText
                }
            )
            ?.setText(R.id.tv_status, item?.statusText)




        when (item?.status) { //0申请中 1接受 2用户已取消 3店家取消 4超时取消（已过时） 5已完成

            0 -> {
                helper?.setText(R.id.tv_text, "取消预约")
                    ?.setTextColor(R.id.tv_text, Color.WHITE)
                    ?.setBackgroundColor(R.id.tv_text, Color.parseColor("#F9572D"))
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#F9572D"))
                    ?.setVisible(R.id.tv_text, true)

            }

            1 -> {
                helper?.setText(R.id.tv_text, "若无法赴约请联系商家取消")
                    ?.setVisible(R.id.tv_text, true)
                    ?.setTextColor(R.id.tv_text, Color.parseColor("#333333"))
                    ?.setBackgroundColor(R.id.tv_text, Color.TRANSPARENT)
                    ?.setText(R.id.tv_status, item.statusText)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#333333"))
            }

            4 -> {
                helper?.setText(R.id.tv_text, item?.statusDisplayText)
                    ?.setVisible(R.id.tv_text, false)
                    ?.setBackgroundColor(R.id.tv_text, Color.TRANSPARENT)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FF999999"))
                    ?.setBackgroundColor(R.id.tv_text, Color.TRANSPARENT)

            }


            else -> {

                helper?.setTextColor(R.id.tv_status, Color.parseColor("#FF999999"))
                    ?.setVisible(R.id.tv_text, false)
            }
        }

    }

}