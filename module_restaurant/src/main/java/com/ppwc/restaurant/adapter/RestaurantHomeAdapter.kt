package com.ppwc.restaurant.adapter

import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.ppwc.restaurant.mrbean.MultipleItem
import com.yjhh.common.BaseApplication
import com.yjhh.common.BaseApplication.context
import net.cachapa.expandablelayout.ExpandableLayout

class RestaurantHomeAdapter(data: List<MultipleItem>) : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {


    private var mIsFirstLoad = true
    private val mHandler = Handler(Looper.getMainLooper())
    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            MultipleItem.A -> {


                val expandable_layout = helper.getView<ExpandableLayout>(R.id.expandable_layout)
                val tv_useIf = helper?.getView<TextView>(R.id.tv_useIf)





                if (item.spanSize % 2 == 0) {
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



                helper?.addOnClickListener(R.id.tv_useIf)
                    .addOnClickListener(R.id.tv_pai)


            }
            MultipleItem.B -> {
                // helper.setText(R.id.text, item.spanSize.toString())

                helper.addOnClickListener(R.id.tv_more2)






            }
        }
    }

    init {
        addItemType(MultipleItem.A, R.layout.pai)
        addItemType(MultipleItem.B, R.layout.tuijian)
        addItemType(MultipleItem.C, R.layout.pingjia)
    }

}