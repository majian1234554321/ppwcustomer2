package com.yjhh.ppwcustomer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.couponfragmentadapter.view.*


class CouponFragmentAdapter(var context: Context ) : RecyclerView.Adapter<CouponFragmentAdapter.ViewHolder>() {

    lateinit var list:ArrayList<String>

    constructor(context: Context, list:ArrayList<String>) :this(context){
        this.list = list
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(View.inflate(context,R.layout.couponfragmentadapter,null))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            with(viewHolder.itemView){
                texttext.text = list[i]
            }
    }
    fun addData(data: ArrayList<String>){
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun setNewData(data: ArrayList<String>){
        list  = data
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
