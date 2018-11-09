package com.yjhh.ppwcustomer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.MembCardBean
import kotlinx.android.synthetic.main.membershipcardadapter.view.*

class MembershipCardAdapter(var context: Context, var list: List<MembCardBean>) :
    RecyclerView.Adapter<MembershipCardAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: MembershipCardAdapter.ViewHolder, p1: Int) {

        with(p0.itemView) {
            tv_memberName.text = list[p1].typeName
            tv_Price.text = "￥${list[p1].price}"
            tv_tips.text = list[p1].remark
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MembershipCardAdapter.ViewHolder = ViewHolder(
        View.inflate(
            context,
            R.layout.membershipcardadapter, null
        )
    )
}