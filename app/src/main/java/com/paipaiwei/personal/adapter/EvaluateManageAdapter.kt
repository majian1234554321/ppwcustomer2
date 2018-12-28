package com.paipaiwei.personal.adapter

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.EvaluateManageItemBean
import com.paipaiwei.personal.bean.SubCommentsBean
import com.paipaiwei.personal.common.utils.TimeUtil
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import com.yjhh.common.view.RatingBar


class EvaluateManageAdapter(var context: Context, data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


    init {
        addItemType(TYPE_LEVEL_0, R.layout.evaluatemanageadapter)
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1)


    }


    override fun convert(helper: BaseViewHolder?, item22: MultiItemEntity?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        when (helper?.itemViewType) {
            TYPE_LEVEL_0 -> {

                val item = item22 as EvaluateManageItemBean

                val list = ArrayList<String>()

                if (item.files != null) {
                    item.files.forEach {
                        list.add(it.fileUrl)
                    }

                    val view = helper.getView<NineGridView>(R.id.nineGrid)

                    view?.setAdapter(NineGridViewClickAdapter(context, list))
                    helper.setVisible(R.id.nineGrid, true)
                } else {
                    helper.setVisible(R.id.nineGrid, false)
                }





                helper.getView<RatingBar>(R.id.id_ratingbar).setStar(item.shopScore)


                helper.setText(R.id.tv_username, item?.userName)
                helper.setText(R.id.tv_content, item?.content)
                helper.setText(R.id.tv_time, TimeUtil.stampToDate2(item?.createdTime))


            }

            TYPE_LEVEL_1 -> {

                val item22TYPE_LEVEL_1 = item22 as SubCommentsBean

                helper.getView<LinearLayout>(R.id.ll_content).visibility =
                        if (!TextUtils.isEmpty(item22TYPE_LEVEL_1.content)) View.VISIBLE else View.GONE


                helper.getView<LinearLayout>(R.id.rl_more).visibility =
                        if (item22TYPE_LEVEL_1.last) View.VISIBLE else View.GONE //最后一个item 显示查看详情


                if (item22TYPE_LEVEL_1.ifShop == "1") {//// 商家
                    helper.setBackgroundColor(R.id.rll, Color.parseColor("#F7EEDD"))
                } else {
                    helper.setBackgroundColor(R.id.rll, Color.parseColor("#E7E7E7"))
                }


                helper.setText(R.id.tv_reply22, item22TYPE_LEVEL_1.userName)

                helper.setText(R.id.tv_content22, item22TYPE_LEVEL_1.content)

                helper.setText(R.id.tv_time22, TimeUtil.stampToDate2(item22TYPE_LEVEL_1.createdTime.toString()))


            }


        }


    }


    companion object {


        const val TYPE_LEVEL_0 = 0
        const val TYPE_LEVEL_1 = 1


    }
}