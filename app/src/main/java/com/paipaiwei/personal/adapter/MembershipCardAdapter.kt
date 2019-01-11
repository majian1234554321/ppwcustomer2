package com.paipaiwei.personal.adapter

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.*
import com.paipaiwei.personal.bean.MembershipCardBean
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.TextStyleUtils

import net.cachapa.expandablelayout.ExpandableLayout
import java.lang.StringBuilder


class MembershipCardAdapter(data: List<MembershipCardBean.ItemsBean>) :
    BaseQuickAdapter<MembershipCardBean.ItemsBean, BaseViewHolder>(R.layout.membershipcardadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: MembershipCardBean.ItemsBean?) {

        val expandable_layout = helper?.getView<ExpandableLayout>(R.id.expandable_layout)
        val tv_useIf = helper?.getView<TextView>(R.id.tv_useIf)
        val tv_cardName = helper?.getView<TextView>(R.id.tv_cardName)
        val tv_value = helper?.getView<TextView>(R.id.tv_value)

        helper?.setText(R.id.tv_access, item?.useText)?.setText(R.id.tv_access,item?.useMark)

        val stringValue = "拍拍味 | ${item?.title}"
        tv_cardName?.text = TextStyleUtils.changeTextColor(stringValue, 0, 5, Color.parseColor("#66333333"))

        val valueUnitValue = "${item?.value}${item?.valueUnit}"
        tv_value?.text = TextStyleUtils.changeTextAa(valueUnitValue, valueUnitValue.length-3, valueUnitValue.length, 14)


        val  sb2 = StringBuilder()

        item?.useMarks?.forEach {
            sb2.append(it).append("\n")
        }

        helper?.setText(R.id.tv_usemark, sb2.toString())





            when (item?.status) {//0 可以使用 1已使用 2已过期 3未领取 4未生效
                0 -> {
                    when (item?.style) { //1美食 红 2医疗 金 3 美容美发 蓝 4 休闲娱乐 黑 5 酒店住宿 橙


                        1 -> {

                            if (item.ifNeedAudit) {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_red_qrcode)
                                helper?.setText(R.id.tv_status, "  ")

                            } else {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_red)
                                helper?.setText(R.id.tv_status, "立即\n使用")

                            }


                        }

                        2 -> {


                            if (item.ifNeedAudit) {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_thj_qrcode)
                                helper?.setText(R.id.tv_status, "\t")

                            } else {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_thj)
                                helper?.setText(R.id.tv_status, "立即\n使用")

                            }

                        }

                        3 -> {


                            if (item.ifNeedAudit) {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_blue_qrcode)
                                helper?.setText(R.id.tv_status, "\t")

                            } else {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_blue)
                                helper?.setText(R.id.tv_status, "立即\n使用")

                            }
                        }

                        4 -> {


                            if (item.ifNeedAudit) {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_black_qrcode)
                                helper?.setText(R.id.tv_status, "\t")

                            } else {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_black)
                                helper?.setText(R.id.tv_status, "立即\n使用")

                            }


                        }

                        5 -> {

                            if (item.ifNeedAudit) {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_orange_qrcode)
                                helper?.setText(R.id.tv_status, "\t")
                            } else {
                                helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_orange)
                                helper?.setText(R.id.tv_status, "立即\n使用")
                            }

                        }

                        else -> {

                        }
                    }
                }

                2 -> {
                    if (item.ifNeedAudit) {
                        helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_before_qrcode)
                        helper?.setText(R.id.tv_status, "  ")

                    } else {
                        helper?.setBackgroundRes(R.id.rl_head, R.drawable.card_before)
                        helper?.setText(R.id.tv_status, "已过期")?.setTextColor(R.id.tv_status,Color.parseColor("#FFEAEAEA"))

                    }
                }

                else -> {
                }
            }








        tv_useIf?.text = "使用条件"
        if (item?.expand!!) {
            expandable_layout?.setExpanded(true, false)

            val drawableLeft = BaseApplication.context.resources.getDrawable(
                R.drawable.icon_up
            )

            tv_useIf?.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null, drawableLeft, null
            );
            tv_useIf?.compoundDrawablePadding = 4

        } else {
            expandable_layout?.setExpanded(false, false)

            val drawableLeft = BaseApplication.context.resources.getDrawable(
                R.drawable.icon_down
            )

            tv_useIf?.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null, drawableLeft, null
            )
            tv_useIf?.compoundDrawablePadding = 4
        }





        helper?.addOnClickListener(R.id.tv_useIf)
            ?.addOnClickListener(R.id.tv_status)

    }

}


