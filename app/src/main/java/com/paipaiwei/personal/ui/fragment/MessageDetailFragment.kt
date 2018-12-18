package com.paipaiwei.personal.ui.fragment

import android.os.Bundle
import com.yjhh.common.base.BaseFragment

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.MyMessageBean
import com.paipaiwei.personal.common.utils.TimeUtil
import kotlinx.android.synthetic.main.messagedetailfragment.*


class MessageDetailFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.messagedetailfragment

    override fun initView() {


        val objectValue = arguments?.getSerializable("objectValue") as MyMessageBean.ItemsBean


        tv1?.text = objectValue?.title
        tv2?.text = objectValue?.content

        tv_time?.text = TimeUtil.stampToDate(objectValue?.sendTime.toString())

    }


    companion object {

        fun newInstance(objectValue: MyMessageBean.ItemsBean): MessageDetailFragment {
            val fragment = MessageDetailFragment()
            val bundle = Bundle()

            bundle.putSerializable("objectValue", objectValue)
            fragment.arguments = bundle
            return fragment
        }


    }


}