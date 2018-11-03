package com.yjhh.ppwcustomer.adapter


import android.content.Context

import android.support.v7.widget.RecyclerView

import android.view.View
import android.view.ViewGroup

import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.main1adapter.view.*
import com.yjhh.ppwcustomer.bean.Main1FootBean

class Main1FragmentAdapter(var context: Context,var list: ArrayList<Main1FootBean.ItemsBean>) : RecyclerView.Adapter<Main1FragmentAdapter.ViewHolder>() {


    fun setLoadMoreData(data: ArrayList<Main1FootBean.ItemsBean>) {
        list.addAll(data)
        notifyDataSetChanged()
    }



    fun setRefreshData(data: ArrayList<Main1FootBean.ItemsBean>) {
        list = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Main1FragmentAdapter.ViewHolder {
        return ViewHolder(View.inflate(context, R.layout.main1adapter, null))
    }

    override fun onBindViewHolder(viewHolder: Main1FragmentAdapter.ViewHolder, i: Int) {

        with(viewHolder.itemView) {
            tv_distance.text = list[i].distance
        }


    }

    override fun getItemCount(): Int {
        return if (list.size > 0) list.size else 0
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
