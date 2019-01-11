package com.paipaiwei.personal.adapter

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

class Main3_1Adapter(data: List<Main3_1Bean.ItemsBean>) :
    BaseQuickAdapter<Main3_1Bean.ItemsBean, BaseViewHolder>(R.layout.main3_1adapter, data) {


    override fun convert(helper: BaseViewHolder?, item: Main3_1Bean.ItemsBean?) {
        helper?.setText(R.id.tv_name, item?.nickName)?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.createdTime))
            ?.setText(R.id.tv_price11, mContext.getString(R.string.rmb_price_double2, item?.totalMoney))
            ?.setText(R.id.tv_status, item?.statusDisplayText)


        if (!TextUtils.isEmpty(item?.money)) {
            helper?.setText(R.id.tv_price22, mContext.getString(R.string.rmb_price_double2, item?.money?.toFloat()))
            helper?.setGone(R.id.tv_price22, true)?.setGone(R.id.tv_price2, true)
        } else {
            helper?.setGone(R.id.tv_price22, false)?.setGone(R.id.tv_price2, false)
        }


        ImageLoaderUtils.load(
            BaseApplication.context,
            helper?.getView<ImageView>(R.id.iv_image),
            item?.avatarUrl,
            R.drawable.icon_place_square,
            R.drawable.icon_place_square,
            0
        )
    }
}
