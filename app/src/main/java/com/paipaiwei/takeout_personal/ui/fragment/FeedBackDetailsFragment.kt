package com.paipaiwei.takeout_personal.ui.fragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.yjhh.common.base.BaseFragment

import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.PhotoAdapter
import com.paipaiwei.takeout_personal.bean.AllFeedBackBean
import com.paipaiwei.takeout_personal.present.AboutPresent
import com.paipaiwei.takeout_personal.view.AboutView
import kotlinx.android.synthetic.main.feedbackdetailsfragment.*

class FeedBackDetailsFragment : BaseFragment(), AboutView {

    override fun onSuccess(response: String?, flag: String?) {

        Log.i("FeedBackDetailsFragment", response)


        val modle = Gson().fromJson<AllFeedBackBean.ItemsBean>(response, AllFeedBackBean.ItemsBean::class.java)


        tv_title22.text = modle.title


        tv_status.text = modle.statusText
        tv_content.text = modle.cause

        val list = ArrayList<String>()
        if (modle.images != null && modle.images.isNotEmpty()) {
            list.clear()
            modle.images.forEach {
                list.add(it.fileUrl)
            }
            recyclerView.adapter = PhotoAdapter(list, false)
        }


    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(mActivity, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutRes(): Int = R.layout.feedbackdetailsfragment

    override fun initView() {

        val id = arguments?.getString("id")

        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)
        AboutPresent(mActivity, this).feedbackDetail(id)

    }

    companion object {
        fun newInstance(id: String): FeedBackDetailsFragment {
            val fragment = FeedBackDetailsFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }

}