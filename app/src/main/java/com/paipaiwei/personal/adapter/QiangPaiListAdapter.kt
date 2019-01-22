package com.paipaiwei.personal.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.QiangPaiListBean
import com.paipaiwei.personal.ui.fragment.QiangPaiFragment
import com.paipaiwei.personal.ui.fragment.QiangPaiListFragment
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.TimeUtil

class QiangPaiListAdapter(data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {
    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when (helper?.itemViewType) {
            TYPE_LEVEL_0 -> {
                val headValue = item as QiangPaiListBean.ItemsBeanX


                when (headValue.status) { //0即将开始 1进行中 2已结束/已拍完/已过期
                    "0" -> {
                        helper?.setText(R.id.tv_right, "开始时间${TimeUtil.stampToDate(headValue.begin, "HH:mm")}")
                        helper?.setText(R.id.tv_left, "即将开始")
                    }
                    "1" -> {
                        helper?.setText(R.id.tv_right, "距离结束${TimeUtil.stampToDate(headValue.end, "HH:mm")}")
                        helper?.setText(R.id.tv_left, headValue.name)
                    }
                    else -> {
                        helper?.setText(R.id.tv_right, headValue.status)
                            .setText(R.id.tv_4, "进行中")
                            .setVisible(R.id.tv_4, true)
                        helper?.setText(R.id.tv_left, "已结束")

                    }
                }


            }

            TYPE_LEVEL_1 -> {

                val content = item as QiangPaiListBean.ItemsBeanX.ItemsBean

                helper?.setText(R.id.tv_1, content.shopName).setText(R.id.tv_2, content.describe)
                    .setText(R.id.tv_3, content.countText)



                when (content.status) {  // //0即将开始 1进行中 2已结束/已拍完/已过期
                    "0" -> {

                        helper.addOnClickListener(R.id.mb_pai)
                        helper?.setText(R.id.tv_4, "即将开始")
                            .setVisible(R.id.tv_4, true)
                            .setVisible(R.id.mb_pai, true)

                    }
                    "1" -> {

                        helper.addOnClickListener(R.id.mb_pai)
                        helper?.setText(R.id.tv_4, "进行中")
                            .setVisible(R.id.tv_4, true)
                            .setVisible(R.id.mb_pai, true)

                    }

                    else -> {
                        helper?.setVisible(R.id.tv_4, false)
                            .setVisible(R.id.mb_pai, false)
                    }
                }




                ImageLoaderUtils.load(
                    BaseApplication.getIns(),
                    helper?.getView(R.id.iv_image),
                    item?.imageUrl,
                    R.drawable.icon_place_square,
                    R.drawable.icon_place_square,
                    5
                )

            }


        }
    }


    init {
        addItemType(QiangPaiListAdapter.TYPE_LEVEL_0, R.layout.qiangpailistadapter_head)
        addItemType(QiangPaiListAdapter.TYPE_LEVEL_1, R.layout.qiangpailistadapter)
    }

    companion object {
        const val TYPE_LEVEL_0 = 0
        const val TYPE_LEVEL_1 = 1

    }


}
