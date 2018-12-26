package com.paipaiwei.personal.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.common.BaseApplication.context
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.*
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils


class Main2Adapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.recentlybrowseadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.



        helper?.setText(R.id.tv_storeName,item)
        helper?.setText(R.id.tv_avgPrice,item)
        helper?.setText(R.id.tv_info,item)
        helper?.setText(R.id.tv_KM,item)



        ImageLoaderUtils.load(BaseApplication.getIns(),helper?.getView(R.id.iv_image),item,R.mipmap.ic_launcher,R.mipmap.ic_launcher,5)





    }


}