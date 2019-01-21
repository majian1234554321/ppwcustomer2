package com.paipaiwei.personal.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


import com.paipaiwei.personal.R

import com.paipaiwei.personal.bean.MoreSectionBean
import com.paipaiwei.personal.ui.activity.SearchActivity
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ActivityCollector
import com.yjhh.common.utils.ImageLoaderUtils
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

import com.zhy.view.flowlayout.TagFlowLayout
import org.w3c.dom.Node


class MoreSectionAdapter(var activity: Activity, data: List<MoreSectionBean.ItemsBean>) :
    BaseQuickAdapter<MoreSectionBean.ItemsBean, BaseViewHolder>(R.layout.moresectionadapter, data) {

    override fun convert(helper: BaseViewHolder, item: MoreSectionBean.ItemsBean) {

        ImageLoaderUtils.load(
            BaseApplication.context,
            helper?.getView<ImageView>(R.id.iv_image),
            item?.iconUrl,
            R.drawable.icon_place_square,
            R.drawable.icon_place_square,
            0
        )

        helper.setText(R.id.text, item.title)
        val flowlayout = (helper.getView<TagFlowLayout>(R.id.flowlayout))
        flowlayout.adapter = MoreSectionTagAdapter(activity, flowlayout, item.nodes)

        flowlayout.setOnTagClickListener { view, position, parent ->


            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantInFragment")
                .withString("type", "MORE")
                .withString("typeValue", item.nodes[position].title)
                .withString("id",item.nodes[position].code)
                .navigation()


            true
        }

    }


    class MoreSectionTagAdapter(
        var activity: Activity, var flowLayout: FlowLayout,
        data: List<MoreSectionBean.ItemsBean.NodesBean>?
    ) : TagAdapter<MoreSectionBean.ItemsBean.NodesBean>(data) {
        override fun getView(parent: FlowLayout?, position: Int, t: MoreSectionBean.ItemsBean.NodesBean?): View {

            val tv = activity.layoutInflater.inflate(
                R.layout.moresectiontagadapter,
                flowLayout, false
            ) as TextView
            tv.text = t?.title

            return tv;
        }


    }


}