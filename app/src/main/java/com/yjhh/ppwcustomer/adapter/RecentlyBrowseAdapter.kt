package com.yjhh.ppwcustomer.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean
import kotlinx.android.synthetic.main.recentlybrowseadapter.view.*

class RecentlyBrowseAdapter(var data: ArrayList<RecentlyBrowseBean.ItemsBean>, var context: Context) :
    BaseQuickAdapter<RecentlyBrowseBean.ItemsBean, RecentlyBrowseAdapter.ViewHolder>(data) {


    fun onRefresh(data: ArrayList<RecentlyBrowseBean.ItemsBean>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun onLoad(data: ArrayList<RecentlyBrowseBean.ItemsBean>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(context, R.layout.recentlybrowseadapter, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.itemView) {
            tv_storeName.text = data[position].title
            id_ratingbar.setStar(data[position].grade.toFloat())

            tv_avgPrice.text = data[position].consumption

            tv_info.text = data[position].remark

            tv_KM.text = data[position].remark

            Glide.with(context).load(data[position].imageUrl).into(iv_image)
        }
    }

    override fun convert(helper: ViewHolder, item: RecentlyBrowseBean.ItemsBean) = Unit

    class ViewHolder(view: View) : BaseViewHolder(view)
}