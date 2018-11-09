package com.yjhh.ppwcustomer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener2
import kotlinx.android.synthetic.main.searchcontentadapter.view.*

class SearchContentAdapter(var context: Context, var list: List<String>) :
    RecyclerView.Adapter<SearchContentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchContentAdapter.ViewHolder =
        ViewHolder(View.inflate(context, R.layout.searchcontentadapter, null))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: SearchContentAdapter.ViewHolder, p1: Int) {
        with(p0.itemView) {
            tv_title.text = list[p1]

            setOnClickListener {
                onItemClickListener2?.onItemClick( p0, list , p1)
            }
        }


    }


    fun setOnItemClick(onItemClickListener2: OnItemClickListener2<String>){
            this.onItemClickListener2 = onItemClickListener2
    }


     var  onItemClickListener2: OnItemClickListener2<String>? = null


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
