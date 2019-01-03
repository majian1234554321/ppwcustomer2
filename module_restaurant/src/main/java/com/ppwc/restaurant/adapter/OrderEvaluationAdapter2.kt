package com.ppwc.restaurant.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter

import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.foodlvadapter2.view.*
import kotlinx.android.synthetic.main.orderevaluationadapter.view.*


class OrderEvaluationAdapter2(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.orderevaluationadapter, data) {


    private val maxValue = 9


    override fun getItemCount(): Int = when {
        data.size >= maxValue -> maxValue+1
        else -> 2 + data.size
    }







    override fun convert(helper: BaseViewHolder?, item: String?) {


        val imageView = helper?.getView<ImageView>(R.id.iv_image)



        if (helper?.adapterPosition == this.data.size) {
            ImageLoaderUtils.loadImgId(
                BaseApplication.context,
                imageView,
                R.drawable.ic_upload,
                R.drawable.ic_upload,
                R.drawable.ic_upload,
                0
            )


            helper?.setVisible(R.id.tv_delete, false)


//            helper?.itemView.setOnClickListener { v ->
//                mOnItemClickListener?.onRecycleViewItemClick(
//                    v,
//                    adapterPosition,
//                    false
//                )
//            }
        } else {
            ImageLoaderUtils.load(
                BaseApplication.context,
                imageView,
                item,
                R.drawable.icon_place,
                R.drawable.icon_place,
                0
            )
            helper?.setVisible(R.id.tv_delete, true)


            /* helper?.itemView?.tv_delete?.setOnClickListener { v ->
                 mOnItemChildClickListener?.onItemChildClick(v, adapterPosition)
             }
             helper?.itemView.setOnClickListener { v ->
                 mOnItemClickListener?.onRecycleViewItemClick(
                     v,
                     adapterPosition,
                     true
                 )
             }*/
        }


    }


}