package com.paipaiwei.personal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.*
import com.yjhh.common.BaseApplication

import net.cachapa.expandablelayout.ExpandableLayout


class MembershipCardAdapter(data: List<Boolean>?) :
    BaseQuickAdapter<Boolean, BaseViewHolder>(R.layout.membershipcardadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: Boolean?) {

        val expandable_layout = helper?.getView<ExpandableLayout>(R.id.expandable_layout)

        val tv_useIf = helper?.getView<TextView>(R.id.tv_useIf)



        when (helper?.adapterPosition) {
            0 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_red)
                helper.setText(R.id.tv_status, "立即\n使用")
            }

            1 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_red_qrcode)
                helper.setText(R.id.tv_status, "\t")

            }

            2 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_thj)
                helper.setText(R.id.tv_status, "立即\n使用")
            }

            3 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_thj_qrcode)
                helper.setText(R.id.tv_status, "\t")
            }

            4 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_orange)
                helper?.setVisible(R.id.tv_status, true)
            }

            5 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_orange_qrcode)
                helper.setVisible(R.id.tv_status, false)
            }

            6 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_black)
                helper?.setVisible(R.id.tv_status, true)
            }

            7 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_black_qrcode)
                helper.setVisible(R.id.tv_status, false)
            }

            8 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_blue)
                helper?.setVisible(R.id.tv_status, true)
            }

            9 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_blue_qrcode)
                helper.setVisible(R.id.tv_status, false)
            }

            10 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_before)
                helper?.setVisible(R.id.tv_status, true)
            }

            11 -> {
                helper.setBackgroundRes(R.id.rl_head, R.drawable.card_before_qrcode)
                helper.setVisible(R.id.tv_status, false)
            }

            else -> {
                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_red)
                helper?.setVisible(R.id.tv_status, true)
            }
        }




        tv_useIf?.text = "使用条件"
        if (item!!) {
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


        helper?.setText(tv_status, "立即\n使用")


        helper?.addOnClickListener(R.id.tv_useIf)
            ?.addOnClickListener(R.id.tv_status)

    }

}


