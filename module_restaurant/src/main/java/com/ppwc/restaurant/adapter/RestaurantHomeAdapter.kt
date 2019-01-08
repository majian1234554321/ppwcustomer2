package com.ppwc.restaurant.adapter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.format.DateUtils
import android.text.style.RelativeSizeSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.R.id.parent
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.R.id.tv_pai
import com.ppwc.restaurant.bean.MeiShiHeadBean
import com.ppwc.restaurant.mrbean.MultipleItem
import com.ppwc.restaurant.mrbean.RestaurantHomeBean
import com.ppwc.restaurant.mrlistener.MuIListener
import com.ppwc.restaurant.views.ProductDetailsFragment
import com.ppwc.restaurant.views.RestaurantHomeFragment
import com.yjhh.common.BaseApplication
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.TimeUtil
import com.yjhh.common.view.RatingBar
import com.yjhh.common.view.fragments.PhotoFragment
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import net.cachapa.expandablelayout.ExpandableLayout
import java.lang.StringBuilder

class RestaurantHomeAdapter(
    data: List<MultipleItem>,
    var mRecyclerView: RecyclerView,
    var fragment: RestaurantHomeFragment,
    var fragmentManager: FragmentManager
) : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

    var parentHeight = 0

    var itemHeight = 0


    public var listAExpandableLayout = ArrayList<ExpandableLayout>()
    public var listATextView = ArrayList<TextView>()

    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            MultipleItem.A -> {
                item.listone?.forEachIndexed { index, s ->
                    val multipleitema = View.inflate(helper.itemView.context, R.layout.multipleitema, null)
                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    lp.bottomMargin = 12
                    helper.getView<LinearLayout>(R.id.lla).addView(multipleitema, lp)
                    val expandable_layout = multipleitema.findViewById<ExpandableLayout>(R.id.expandable_layout)


                    val tv_cardName = multipleitema.findViewById<TextView>(R.id.tv_cardName)
                    tv_cardName.text = item.listone!![index].title

                    val tv_mark = multipleitema.findViewById<TextView>(R.id.tv_mark)
                    val sbMark = StringBuilder()
                    item.listone!![index].useMarks.forEach {
                        sbMark.append(it).append("\n")
                    }
                    tv_mark.text = sbMark.toString()


                    val tv_price = multipleitema.findViewById<TextView>(R.id.tv_price)
                    val textintegral = "1000元代金券"
                    val spannableString = SpannableString(textintegral)
                    val sizeSpan01 = RelativeSizeSpan(0.5f)
                    spannableString.setSpan(
                        sizeSpan01,
                        textintegral.split("元")[0].length,
                        textintegral.length,
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                    )

                    tv_price?.text = spannableString





                    listAExpandableLayout.add(expandable_layout)
                    val tv_useIf = multipleitema.findViewById<TextView>(R.id.tv_useIf)

                    listATextView.add(tv_useIf)

                    val tv_pai = multipleitema.findViewById<TextView>(R.id.tv_pai)

                    if (item.flag!!) {
                        expandable_layout?.setExpanded(true, false)
                        val drawableLeft =
                            ContextCompat.getDrawable(BaseApplication.context, R.drawable.icon_up_black)


                        tv_useIf?.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null, drawableLeft, null
                        );
                        tv_useIf?.compoundDrawablePadding = 4

                    } else {
                        expandable_layout?.setExpanded(false, false)

                        val drawableLeft =
                            ContextCompat.getDrawable(BaseApplication.context, R.drawable.icon_down_black)


                        tv_useIf?.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null, drawableLeft, null
                        );
                        tv_useIf?.compoundDrawablePadding = 4
                    }


                    tv_useIf.setOnClickListener {
                        muilisteners?.tv_if(index)
                    }

                    tv_pai.setOnClickListener {
                        muilisteners?.tv_pai(index)
                    }

                }
            }

            MultipleItem.B -> {


                val viewB = helper.getView<RecyclerView>(R.id.recyclerView)


                val tv_more2 = helper.getView<TextView>(R.id.tv_more2)

                tv_more2.text = "更多推荐(${item.type})"

                tv_more2.setOnClickListener {
                    muilisteners?.tv_more2()
                }

                viewB.layoutManager = GridLayoutManager(helper.itemView.context, 3)


                val viewBAdapter = MultipleitemAdapter(item.listProducts)
                viewB?.adapter = viewBAdapter

                viewBAdapter.setOnItemClickListener { adapter, view, position ->


                    fragment.start(ProductDetailsFragment.newInstance((adapter.data[position] as RestaurantHomeBean.ProductsBean).itemId))


//                    val imageList = ArrayList<String>()
//
//                    item.listProducts?.forEach {
//                        imageList.add(it.logoUrl)
//                    }
//                    val dialog = PhotoFragment(imageList, position)
//                    dialog?.show(fragmentManager, "TAG")
                }


            }

            MultipleItem.C -> {


                item.listUserComment?.forEachIndexed { index, userCommentBean ->
                    val multipleitemc = View.inflate(helper.itemView.context, R.layout.multipleitemc, null)
                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )


                    val iv_image3 = multipleitemc.findViewById<ImageView>(R.id.iv_image3)

                    val tv_details = multipleitemc.findViewById<TextView>(R.id.tv_details)

                    tv_details.setOnClickListener {

                    }


                    ImageLoaderUtils.load(
                        BaseApplication.getIns(),
                        iv_image3,
                        userCommentBean?.shopLogoUrl,
                        R.drawable.icon_place_pai,
                        R.drawable.icon_place_pai,
                        5
                    )

                    val tv_content3 = multipleitemc.findViewById<TextView>(R.id.tv_content3)
                    tv_content3.text = userCommentBean.content

                    val tv_storeName3 = multipleitemc.findViewById<TextView>(R.id.tv_storeName3)
                    tv_storeName3.text = userCommentBean.shopName

                    val ratingbar = multipleitemc.findViewById<RatingBar>(R.id.ratingbar)
                    ratingbar.setStar(userCommentBean.shopScore)



                    multipleitemc.findViewById<TextView>(R.id.tv_replay).text = if ("0" == userCommentBean.ifShop) {
                        "用户回复"
                    } else {
                        "商家回复"
                    }//是否商家回复 0否 1是


                    val tv_pv = multipleitemc.findViewById<TextView>(R.id.tv_pv)
                    tv_pv.text = userCommentBean.pv


                    val tv_time3 = multipleitemc.findViewById<TextView>(R.id.tv_time3)
                    tv_time3.text = TimeUtil.stampToDate2(userCommentBean.createdTime)


                    val tv_time33 = multipleitemc.findViewById<TextView>(R.id.tv_time33)
                    tv_time33.text = userCommentBean.timeText


                    val viewC = multipleitemc.findViewById<NineGridView>(R.id.rlNineGridView)
                    if (userCommentBean.files != null && userCommentBean.files.size > 0) {

                        val listImage3 = ArrayList<String>()

                        userCommentBean.files.forEach {
                            listImage3.add(it.fileUrl)
                        }

                        viewC.setAdapter(NineGridViewClickAdapter(context, listImage3, fragmentManager))

                    }

                    //val tagFlowLayout = helper.getView<TagFlowLayout>(R.id.tagFlowLayout)
                    //val tagAdapter = MultipleItemCTagAdapter(helper.itemView.context as Activity, tagFlowLayout, item.list)
                    //tagFlowLayout.adapter = tagAdapter


                    lp.bottomMargin = 12
                    helper.getView<LinearLayout>(R.id.llc).addView(multipleitemc, lp)
                }
                val tv_more3 = helper.getView<TextView>(R.id.tv_more3)
                tv_more3.text = "更多评价(${item.size})"
                tv_more3.setOnClickListener {
                    muilisteners?.tv_more3()
                }


                val view = View(helper.itemView.context)
                view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parentHeight - itemHeight)
                helper.itemView.post {
                    parentHeight = mRecyclerView.height
                    itemHeight = helper.itemView.height
                }

            }

            MultipleItem.D -> {
                val view = View(helper.itemView.context)
                view.layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 48)
            }

        }
    }

    init {
        addItemType(MultipleItem.A, R.layout.multipleitemaadapter)
        addItemType(MultipleItem.B, R.layout.multipleitembadapter)
        addItemType(MultipleItem.C, R.layout.multipleitemcadapter)
        addItemType(MultipleItem.D, R.layout.pingjia)
    }

    public var muilisteners: MuIListener? = null

    fun setMUIOnClickListener(muilisteners: MuIListener) {
        this.muilisteners = muilisteners
    }

    class MultipleItemCTagAdapter(var activity: Activity, var tagFlowLayout: TagFlowLayout, var data: List<String>?) :
        TagAdapter<String>(data) {
        override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
            val tv = activity.layoutInflater.inflate(
                R.layout.multipleitemctagadapter,
                tagFlowLayout, false
            ) as TextView

            tv.text = t

            return tv
        }

    }

    class MultipleitemAdapter(data: List<RestaurantHomeBean.ProductsBean>?) :
        BaseQuickAdapter<RestaurantHomeBean.ProductsBean, BaseViewHolder>(R.layout.multipleitemadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: RestaurantHomeBean.ProductsBean?) {
            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                helper?.getView<ImageView>(R.id.iv_image),
                item?.logoUrl,
                R.drawable.icon_place_pai,
                R.drawable.icon_place_pai,
                5
            )
        }

    }

}