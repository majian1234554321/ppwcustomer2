package com.yjhh.ppwcustomer.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.MemCanBuyBean
import com.yjhh.ppwcustomer.bean.MembCardBean
import kotlinx.android.synthetic.main.membershipcardadapter.view.*

class MembershipCardAdapter(var context: Context, var list: List<MemCanBuyBean.ItemsBeanX>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MembershipCardAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: MembershipCardAdapter.ViewHolder, p1: Int) {
        var index = 0
        with(p0.itemView) {
            tv_memberName.text = list[p1].typeName

            tv_tips.text = list[p1].remark

            if (list != null && list[p1] != null && list[p1].items != null && list[p1].items.size > 0) {
                tv_Price.text = "￥${list[p1].items[0].price}"

                tv_count.text = "${list[p1].items[0].timeText}"

                tv_endTime.text = "有效期至：${list[p1].items[0].date}"
            }

            tv_add.setOnClickListener {
                index++
                if (index < list[p1].items.size) {

                    tv_Price.text = "￥${list[p1].items[index].price}"

                    tv_count.text = "${list[p1].items[index].timeText}"

                    tv_endTime.text = "有效期至：${list[p1].items[index].date}"
                } else {
                    index--
                }

            }

            tv_reduce.setOnClickListener {
                index--
                if (index >= 0) {

                    tv_Price.text = "￥${list[p1].items[index].price}"

                    tv_count.text = "${list[p1].items[index].timeText}"

                    tv_endTime.text = "有效期至：${list[p1].items[index].date}"
                } else {
                    index = 0
                }
            }


            mb_pay.setOnClickListener {
                Toast.makeText(context,list[p1].items[index].id.toString(),Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MembershipCardAdapter.ViewHolder = ViewHolder(
        View.inflate(
            context,
            R.layout.membershipcardadapter, null
        )
    )
}