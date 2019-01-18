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

class QiangPaiListAdapter(data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {
    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when (helper?.itemViewType) {
            TYPE_LEVEL_0 -> {
                val headValue = item as QiangPaiListFragment.HeadValue
                helper?.setText(R.id.tv_left, headValue.leftValue).setText(R.id.tv_right, headValue.rightValue)

            }

            TYPE_LEVEL_1 -> {

                val content = item as QiangPaiListBean.ItemsBeanX.ItemsBean

                helper?.setText(R.id.tv_1, content.shopName).setText(R.id.tv_2, content.describe)
                    .setText(R.id.tv_3, content.countText)

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
