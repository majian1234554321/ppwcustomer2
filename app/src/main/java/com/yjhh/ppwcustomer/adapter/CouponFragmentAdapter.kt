package com.yjhh.ppwcustomer.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.CouponBean
import kotlinx.android.synthetic.main.couponfragmentadapter.view.*

class CouponFragmentAdapter(var data: ArrayList<CouponBean.ItemsBean>, var context: Context) :
    BaseQuickAdapter<CouponBean.ItemsBean, CouponFragmentAdapter.ViewHolder>(data) {


    fun onRefresh(data: ArrayList<CouponBean.ItemsBean>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun onLoad(data: ArrayList<CouponBean.ItemsBean>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(context, R.layout.couponfragmentadapter, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.itemView) {
            tv_status.text = data[position].title
            tv_info.text = data[position].remark
            tv_info.text = data[position].remark
        }
    }

    override fun convert(helper: CouponFragmentAdapter.ViewHolder, item: CouponBean.ItemsBean)=Unit

     class ViewHolder(view: View) : BaseViewHolder(view)
}
