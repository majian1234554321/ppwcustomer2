package com.yjhh.ppwcustomer.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.MyMessageBean

import kotlinx.android.synthetic.main.mymessagefragmentadapter.view.*

class MyMessageFragmentAdapter(var data: ArrayList<MyMessageBean.ItemsBean>, var context: Context) :
    BaseQuickAdapter<MyMessageBean.ItemsBean, MyMessageFragmentAdapter.ViewHolder>(data) {


    fun onRefresh(data: ArrayList<MyMessageBean.ItemsBean>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun onLoad(data: ArrayList<MyMessageBean.ItemsBean>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(context, R.layout.mymessagefragmentadapter
            , null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.itemView) {
            tv1.text = data[position].title
            tv2.text = data[position].content
            //tv3.text = data[position].remark
        }
    }

    override fun convert(helper: MyMessageFragmentAdapter.ViewHolder, item: MyMessageBean.ItemsBean)=Unit

     class ViewHolder(view: View) : BaseViewHolder(view)
}
