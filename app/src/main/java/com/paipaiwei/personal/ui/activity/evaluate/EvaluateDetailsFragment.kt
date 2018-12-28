package com.paipaiwei.personal.ui.activity.evaluate

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.PhoneUtils
import com.paipaiwei.personal.adapter.EvaluateDetailsAdapter
import com.paipaiwei.personal.R
import com.paipaiwei.personal.apis.SectionEvluateService
import com.paipaiwei.personal.bean.EvaluateDetailsBean
import com.paipaiwei.personal.bean.SubmitShopReplyCommentModel
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import com.yjhh.common.view.RatingBar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.evaluatedetailsfragment.*


class EvaluateDetailsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.evaluatedetailsfragment

    var replayId: String? = null
    var mAdapter: EvaluateDetailsAdapter? = null

    val list = ArrayList<EvaluateDetailsBean.ItemsBean>()
    override fun initView() {

        mAdapter = EvaluateDetailsAdapter(list)
        addHeadView()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter


        val id = arguments?.getString("id")

        loadNetData(id)





        tv_send.setOnClickListener {

            val model = SubmitShopReplyCommentModel()

            if (!TextUtils.isEmpty(tv_replyContext.text.toString())) {
                model.commentId = replayId
                model.content = tv_replyContext.text.toString()


                ApiServices.getInstance()
                    .create(SectionEvluateService::class.java)
                    .reply(model)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(mActivity) {
                        override fun processValue(response: String?) {
                            Log.i("EvaluateDetailsFragment", response)
                            recyclerView.scrollToPosition(list.lastIndex)
                            tv_replyContext.text.clear()
                            PhoneUtils.hideKeyboard(mActivity)
                            loadNetData(id)
                        }

                        override fun onFault(message: String) {
                            Log.i("EvaluateDetailsFragment", message)
                        }

                    })

            } else {
                Toast.makeText(context, "回复内容不能为空", Toast.LENGTH_SHORT).show()
            }


        }


    }

    var nineGridView: NineGridView? = null
    var tv_content: TextView? = null
    var tv_time: TextView? = null
    var tv_username: TextView? = null
    var id_ratingbar: RatingBar? = null

    private fun addHeadView() {
        val headView = View.inflate(mActivity, R.layout.evaluatedetailshead, null)
        nineGridView = headView.findViewById(R.id.nineGrid)
        tv_time = headView.findViewById(R.id.tv_time)
        tv_content = headView.findViewById(R.id.tv_content)
        tv_username = headView.findViewById(R.id.tv_username)
        id_ratingbar = headView.findViewById(R.id.id_ratingbar)

        mAdapter?.addHeaderView(headView)
    }

    companion object {
        fun newInstance(id: String): EvaluateDetailsFragment {
            val fragment = EvaluateDetailsFragment()
            val bundle = Bundle()
            bundle.putSerializable("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    fun loadNetData(id: String?) {


        val map = androidx.collection.ArrayMap<String, String>()
        map["id"] = id.toString()//类别，默认null（null/0全部 1好评 2中评 3差评）
        ApiServices.getInstance()
            .create(SectionEvluateService::class.java)
            .comment(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun onFault(message: String) {
                    Log.i("EvaluateDetailsFragment", message)
                }

                override fun processValue(response: String?) {
                    Log.i("EvaluateDetailsFragment", response)
                    val gson = Gson()
                    val bean = gson.fromJson<EvaluateDetailsBean>(response, EvaluateDetailsBean::class.java)

                    val url = ArrayList<String>()

                    val list1212 = bean.files


//                    list1212.forEach {
//                        url.add(it.fileUrl)
//                    }

                    if (bean != null) {
                        if (bean.items != null && bean.items.size > 0) {
                            replayId = bean.items[bean.items.lastIndex].id
                        } else {
                            replayId = bean.id
                        }
                    }


                    nineGridView?.setAdapter(NineGridViewClickAdapter(context, url))


                    tv_username?.text = bean?.userName
                    tv_content?.text = bean?.content
                    tv_time?.text = bean.timeText

                    bean?.shopScore?.let { id_ratingbar?.setStar(it) }


                    mAdapter?.setNewData(bean.items)

                }

            })

    }


}