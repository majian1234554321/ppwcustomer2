package com.paipaiwei.takeout_personal.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.bean.CouponBean
import com.paipaiwei.takeout_personal.bean.MyAddressBean
import com.paipaiwei.takeout_personal.bean.RecentlyBrowseBean
import com.paipaiwei.takeout_personal.bean.rxbusbean.RxAddressBean
import kotlinx.android.synthetic.main.myaddressadapter.view.*
import kotlinx.android.synthetic.main.myaddressadapter.view.*

class MyAddressAdapter(var data: ArrayList<MyAddressBean.ItemsBean>, var context: Context) :
    BaseQuickAdapter<MyAddressBean.ItemsBean, MyAddressAdapter.ViewHolder>(data) {


    fun onRefresh(data: ArrayList<MyAddressBean.ItemsBean>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun onLoad(data: ArrayList<MyAddressBean.ItemsBean>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(context, R.layout.myaddressadapter, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.itemView) {
            tv_name.text = data[position].userName

            tv_phone.text = data[position].userPhone

            tv_address.text = data[position].address

            tv_no.text = data[position].no

            if (!TextUtils.isEmpty(data[position].tags)) {
                tv_tag.text = data[position].tags
                tv_tag.visibility = View.VISIBLE
            }else{
                tv_tag.visibility = View.GONE
            }


            setOnClickListener {
                val bean = RxAddressBean()
                bean.flag = "UPDATE"
                bean.address = data[position].address
                bean.userName = data[position].userName
                bean.gender = data[position].gender
                bean.userPhone = data[position].userPhone
                bean.no = data[position].no
                bean.tags = data[position].tags
                bean.position = position
                bean.id = data[position].id

                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "AddressADUFragment")
                    .withSerializable("bean", bean)
                    .navigation()
            }

        }
    }

    override fun convert(helper: ViewHolder, item: MyAddressBean.ItemsBean) = Unit

    class ViewHolder(view: View) : BaseViewHolder(view)
}