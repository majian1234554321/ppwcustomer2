package com.paipaiwei.takeout_personal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.R.id.expandable_layout

import kotlinx.android.synthetic.main.membershipcardadapter.view.*

class MembershipCardAdapter(var context: Context, var list: ArrayList<Boolean>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MembershipCardAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MembershipCardAdapter.ViewHolder, position: Int) {

        if (list[position]) {
            holder.itemView.expandable_layout.setExpanded(true, false)
        } else {
            holder.itemView.expandable_layout.setExpanded(false, false)
        }

        holder.itemView.setOnClickListener {
            if (holder != null) {
//                holder.expandButton.setSelected(false)
//                holder.expandableLayout.collapse()
            }


            if (list[position]) {
                list.set(position,false)
                holder.itemView.expandable_layout.collapse()
            } else {
                // expandButton.setSelected(true);
                holder.itemView.expandable_layout.expand()
                list.set(position, true)
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