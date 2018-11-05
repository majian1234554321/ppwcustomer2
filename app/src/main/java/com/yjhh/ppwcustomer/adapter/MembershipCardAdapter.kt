package com.yjhh.ppwcustomer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yjhh.common.BaseApplication.context
import com.yjhh.ppwcustomer.R

class MembershipCardAdapter(var context: Context, val list: List<String>) :
    RecyclerView.Adapter<MembershipCardAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: MembershipCardAdapter.ViewHolder, p1: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MembershipCardAdapter.ViewHolder = ViewHolder(
        View.inflate(
            context,
            R.layout.membershipcardadapter, null
        )
    )
}