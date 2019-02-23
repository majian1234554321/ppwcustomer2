package com.ppwc.restaurant.views

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.api.SectionEvluateService
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.SubmitUserCommentModel
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.PhoneUtils
import com.yjhh.common.view.RatingBar
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.commentsdetailsfragment.*
import java.io.File


class CommentsDetailsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.commentsdetailsfragment


    override fun initView() {



        ImmersionBar.setTitleBar(activity, tbv_title)

        mAdapter = CommentsDetailsAdapter(list)
        addHeadView()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter


        val id = arguments?.getString("id")

        loadNetData(id)

        tv_send.setOnClickListener {

            val model = SubmitUserCommentModel()

            if (!TextUtils.isEmpty(tv_replyContext.text.toString())) {
                model.commentId = replayId
                model.content = tv_replyContext.text.toString()


                model.orderId = ""
                model.productGrade = ""
                model.serviceGrade = ""


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
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }

                    })

            } else {
                Toast.makeText(context, "回复内容不能为空", Toast.LENGTH_SHORT).show()
            }


        }




    }



    var mAdapter :CommentsDetailsAdapter? = null
    var nineGridView: NineGridView? = null
    var tv_content: TextView? = null
    var tv_time: TextView? = null
    var tv_username: TextView? = null
    var avatar: ImageView? = null
    var id_ratingbar: RatingBar? = null
    var tv_score: TextView? = null
    var replayId: String? = null

    val list = ArrayList<ItemsBean>()


    private fun addHeadView() {
        val headView = View.inflate(mActivity, R.layout. commentsdetailshead, null)
        nineGridView = headView.findViewById(R.id.nineGrid)
        tv_time = headView.findViewById(R.id.tv_time)
        tv_content = headView.findViewById(R.id.tv_content)
        tv_username = headView.findViewById(R.id.tv_username)
        id_ratingbar = headView.findViewById(R.id.id_ratingbar)
        avatar = headView.findViewById(R.id.avatar)
        tv_score = headView.findViewById(R.id.tv_score)



        mAdapter?.addHeaderView(headView)
    }



    companion object {
        fun newInstance(id: String?): CommentsDetailsFragment {
            val fragment = CommentsDetailsFragment()
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
                    val bean = gson.fromJson<CommentsDetailsBean>(response, CommentsDetailsBean::class.java)

                    val url = ArrayList<String>()


                    if(bean.files.isNotEmpty()){
                        val list1212 = bean.files
                        list1212.forEach {
                            url.add(it.fileUrl)
                        }
                        nineGridView?.setAdapter(NineGridViewClickAdapter(context, url))

                    }
                    if (bean != null) {
                        if (bean.items.isNotEmpty()) {
                            replayId = bean.items[bean.items.lastIndex].id
                        } else {
                            replayId = bean.id
                        }
                    }




                    ImageLoaderUtils.loadCircle(mActivity,avatar,bean.avatarUrl,R.drawable.icon_place,R.drawable.icon_place)
                    id_ratingbar?.setStar(bean.shopGrade.toFloat())
                    tv_score?.text = bean.shopGrade.toString()




                    tv_username?.text = bean?.userName
                    tv_content?.text = bean?.content
                    tv_time?.text = bean.timeText

                      bean?.shopScore?.let { id_ratingbar?.setStar(it) }


                    mAdapter?.setNewData(bean.items)

                }

            })

    }


  data class CommentsDetailsBean(
      val avatarUrl: String,
      val content: String,
      val createdTime: Int,
      val files: List<MoreCommentsFragment.FileBean>,
      val id: String,
      val ifAllowDel: Boolean,
      val ifShop: Int,
      val items: List<ItemsBean>,
      val pv: Int,
      val pvText: String,
      val shopGrade: Int,
      val shopGradeText: String,
      val shopId: Int,
      val shopLogoUrl: String,
      val shopName: String,
      val shopScore: Float,
      val timeText: String,
      val userId: Int,
      val userName: String
)

data class ItemsBean(
    val avatarUrl: String,
    val content: String,
    val createdTime: Int,
    val files: List<Any>,
    val id: String,
    val ifAllowDel: Boolean,
    val ifShop: String,
    val items: List<Any>,
    val pv: Int,
    val pvText: String,
    val shopGrade: Int,
    val shopGradeText: String,
    val shopId: Int,
    val shopLogoUrl: String,
    val shopName: String,
    val shopScore: Int,
    val timeText: String,
    val userId: Int,
    val userName: String
)




    class CommentsDetailsAdapter(data: List<ItemsBean>) :
        BaseQuickAdapter<ItemsBean, BaseViewHolder>(R.layout.commentsdetailsadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: ItemsBean?) {
            helper?.setText(R.id.tv_reply, item?.userName)
            helper?.setText(R.id.tv_time, item?.timeText)
            helper?.setText(R.id.tv_content, item?.content)



            if (item?.ifShop == "1") {// 商家
                helper?.setBackgroundColor(R.id.tv_reply, Color.parseColor("#F7EEDD"))
            } else {
                helper?.setBackgroundColor(R.id.tv_reply, Color.parseColor("#E7E7E7"))
            }


        }
    }








}