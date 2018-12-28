package com.ppwc.restaurant.adapter

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.R.id.parent
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.R.id.tv_pai
import com.ppwc.restaurant.mrbean.MultipleItem
import com.ppwc.restaurant.mrlistener.MuIListener
import com.yjhh.common.BaseApplication
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import net.cachapa.expandablelayout.ExpandableLayout

class RestaurantHomeAdapter(
    data: List<MultipleItem>,
    var mRecyclerView: RecyclerView,
    var fragmentManager: FragmentManager
) :
    BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {

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
                    listAExpandableLayout.add(expandable_layout)
                    val tv_useIf = multipleitema.findViewById<TextView>(R.id.tv_useIf)

                    listATextView.add(tv_useIf)

                    val tv_pai = multipleitema.findViewById<TextView>(R.id.tv_pai)

                    if (item.flag!!) {
                        expandable_layout?.setExpanded(true, false)
                        val drawableLeft = BaseApplication.context.resources.getDrawable(
                            R.drawable.icon_up_black
                        )

                        tv_useIf?.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null, drawableLeft, null
                        );
                        tv_useIf?.compoundDrawablePadding = 4

                    } else {
                        expandable_layout?.setExpanded(false, false)

                        val drawableLeft = BaseApplication.context.resources.getDrawable(
                            R.drawable.icon_down_black
                        )

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
                viewB.layoutManager = GridLayoutManager(helper.itemView.context,3)
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


    class MultipleitemAdapter(data: List<String>?) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.multipleitemadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: String?) {

        }

    }

}