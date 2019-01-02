package com.ppwc.restaurant.adapter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
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
import com.ppwc.restaurant.mrlistener.MuIListener
import com.yjhh.common.BaseApplication
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import net.cachapa.expandablelayout.ExpandableLayout

class RestaurantHomeAdapter(
    data: List<MultipleItem>,
    var mRecyclerView: RecyclerView,
    var fragmentManager: FragmentManager
) : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

    var parentHeight = 0

    var itemHeight = 0


    public var listAExpandableLayout = ArrayList<ExpandableLayout>()
    public var listATextView = ArrayList<TextView>()

    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            MultipleItem.A -> {
                item.list?.forEachIndexed { index, s ->
                    val multipleitema = View.inflate(helper.itemView.context, R.layout.multipleitema, null)
                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    lp.bottomMargin = 12
                    helper.getView<LinearLayout>(R.id.lla).addView(multipleitema, lp)
                    val expandable_layout = multipleitema.findViewById<ExpandableLayout>(R.id.expandable_layout)


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
                viewB.layoutManager = GridLayoutManager(helper.itemView.context, 3)


                val viewBAdapter = MultipleitemAdapter(item.list)
                viewB?.adapter = viewBAdapter


            }

            MultipleItem.C -> {

                item.list?.forEach {
                    val multipleitemc = View.inflate(helper.itemView.context, R.layout.multipleitemc, null)
                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    val viewC = multipleitemc.findViewById<NineGridView>(R.id.rlNineGridView)
                    viewC.setAdapter(NineGridViewClickAdapter(context, item.list, fragmentManager))

                    lp.bottomMargin = 12
                    helper.getView<LinearLayout>(R.id.llc).addView(multipleitemc, lp)
                }


                val tagFlowLayout = helper.getView<TagFlowLayout>(R.id.tagFlowLayout)


                val tagAdapter = MultipleItemCTagAdapter(helper.itemView.context as Activity, tagFlowLayout, item.list)

                tagFlowLayout.adapter = tagAdapter


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

    class MultipleitemAdapter(data: List<String>?) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.multipleitemadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: String?) {
            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                helper?.getView<ImageView>(R.id.iv_image),
                item,
                R.drawable.icon_place_pai,
                R.drawable.icon_place_pai,
                5
            )
        }

    }

}