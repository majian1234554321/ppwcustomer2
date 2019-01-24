package com.paipaiwei.personal.adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import com.amap.api.services.core.PoiItem
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.imageView
import com.paipaiwei.personal.bean.Main3_1Bean
import com.paipaiwei.personal.common.utils.ImageUtil
import com.paipaiwei.personal.common.utils.TimeUtil
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.RxCountDown

class Main3_1Adapter(data: List<Main3_1Bean.ItemsBean>) :
    BaseQuickAdapter<Main3_1Bean.ItemsBean, BaseViewHolder>(R.layout.main3_1adapter, data) {


    override fun convert(helper: BaseViewHolder?, item: Main3_1Bean.ItemsBean?) {
        helper?.setText(R.id.tv_name, item?.shopName)
            ?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.time))
            ?.setText(R.id.tv_price11, mContext.getString(R.string.rmb_price_double2, item?.totalMoney))?.addOnClickListener(R.id.mb_pay)





        if (!TextUtils.isEmpty(item?.money)) {
            helper?.setText(R.id.tv_price22, mContext.getString(R.string.rmb_price_double2, item?.money?.toFloat()))
            helper?.setGone(R.id.tv_price22, true)?.setGone(R.id.tv_price2, true)
        } else {
            helper?.setGone(R.id.tv_price22, false)?.setGone(R.id.tv_price2, false)
        }


        when (item?.status) {// 1待付款 2已取消 3已付款 4已完成 5配送中 6退款申请中 7已关闭 // 8待评价 9待使用
            1 -> {
                helper?.setText(R.id.tv_status, "待付款")
                    ?.setVisible(R.id.mb_pay, true)?.setText(R.id.tv_status, "待付款")
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FFC536"))
                RxCountDown.countdown(item?.times).subscribe {
                    if (it != 0) {
                        helper?.setText(R.id.tv_status, "待付款")
                    } else {
                        helper?.setText(R.id.tv_status, "已关闭")
                    }
                }
            }


            2 -> {
                helper?.setText(R.id.tv_status, "已取消")
                    ?.setGone(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#333333"))
            }

            3 -> {
                helper?.setText(R.id.tv_status, "已付款")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FFC536"))
            }

            4 -> {
                helper?.setText(R.id.tv_status, "已完成")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#333333"))
            }

            5 -> {
                helper?.setText(R.id.tv_status, "配送中")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FFC536"))
            }

            6 -> {
                helper?.setText(R.id.tv_status, "退款申请中")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FFC536"))
            }

            7 -> {
                helper?.setText(R.id.tv_status, "已关闭")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FFC536"))
            }

            8 -> {
                helper?.setText(R.id.tv_status, "待评价")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#FFC536"))
            }

            9 -> {
                helper?.setText(R.id.tv_status, "待使用")
                    ?.setVisible(R.id.mb_pay, false)
                    ?.setTextColor(R.id.tv_status, Color.parseColor("#2C85FF"))
            }

            else -> {
            }
        }




        ImageLoaderUtils.load(
            BaseApplication.context,
            helper?.getView<ImageView>(R.id.iv_image),
            item?.shopLogoUrl,
            R.drawable.icon_place_square,
            R.drawable.icon_place_square,
            0
        )
    }
}
