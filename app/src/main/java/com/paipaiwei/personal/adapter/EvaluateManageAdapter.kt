package com.paipaiwei.personal.adapter

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.EvaluateManageItemBean
import com.paipaiwei.personal.bean.SubCommentsBean
import com.paipaiwei.personal.common.utils.GlideImageLoader
import com.paipaiwei.personal.common.utils.TimeUtil
import com.yjhh.common.utils.CornersTranform
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import com.yjhh.common.view.RatingBar
import com.yjhh.common.view.ninegridimage.NineGridImageView
import com.yjhh.common.view.ninegridimage.NineGridImageViewAdapter


class EvaluateManageAdapter(var context: Context, var fragmentManager: FragmentManager?, data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


    init {
        addItemType(TYPE_LEVEL_0, R.layout.evaluatemanageadapter)
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1)


    }


    override fun convert(helper: BaseViewHolder?, item22: MultiItemEntity?) {
        //


        when (helper?.itemViewType) {
            TYPE_LEVEL_0 -> {

                val item = item22 as EvaluateManageItemBean

                val list = ArrayList<String>()

                if (item.files != null) {
                    item.files.forEach {
                        list.add(it.fileUrl)
                    }

                    val view = helper.getView<NineGridImageView<String>>(R.id.nineGrid)

                  //  view.set

                    view?.setAdapter(object : NineGridImageViewAdapter<String>(){
                        override fun onDisplayImage(context: Context?, imageView: ImageView, t: String?) {
//                            ImageLoaderUtils.load(
//                                context,
//                                imageView,
//                                t,
//                                R.drawable.icon_place_square,
//                                R.drawable.icon_place_square,0
//                            )

//                            val options = RequestOptions()
//                                // 但不保证所有图片都按序加载
//                                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
//                                // 默认为Priority.NORMAL
//                                // 如果没设置fallback，model为空时将显示error的Drawable，
//                                // 如果error的Drawable也没设置，就显示placeholder的Drawable
//                                //.priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
//                                .placeholder(com.yjhh.common.R.drawable.icon_place_square)
//                                .error(com.yjhh.common.R.drawable.icon_place_square)
//                                // 缓存原始数据
//                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                                .centerCrop()


                            val options = RequestOptions()
                                // 但不保证所有图片都按序加载
                                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                                // 默认为Priority.NORMAL
                                // 如果没设置fallback，model为空时将显示error的Drawable，
                                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                                .placeholder(com.yjhh.common.R.drawable.icon_place_square)
                                .error(com.yjhh.common.R.drawable.icon_place_square)
                                // 缓存原始数据
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .centerCrop()
                               // .transform(CornersTranform(context, 10f))








                            Glide.with(mContext).load(t)
                                .apply(options)
                                .transition(DrawableTransitionOptions().crossFade())
                                .into(imageView)

                        }

                    })


                    view.setImagesData(list)

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