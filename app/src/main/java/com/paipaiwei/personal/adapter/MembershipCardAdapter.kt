package com.paipaiwei.personal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.expandable_layout
import com.paipaiwei.personal.R.id.tv_status
import com.paipaiwei.personal.bean.AboutBean

import kotlinx.android.synthetic.main.membershipcardadapter.view.*
import net.cachapa.expandablelayout.ExpandableLayout


class MembershipCardAdapter(data: List<Boolean>?) :
    BaseQuickAdapter<Boolean, BaseViewHolder>(R.layout.membershipcardadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: Boolean?) {

        val expandable_layout = helper?.getView<ExpandableLayout>(R.id.expandable_layout)

        if (item!!) {


            expandable_layout?.setExpanded(true, false)
        } else {
            expandable_layout?.setExpanded(false, false)
        }


        helper?.setText(tv_status, "立即\n使用")


    }

}


