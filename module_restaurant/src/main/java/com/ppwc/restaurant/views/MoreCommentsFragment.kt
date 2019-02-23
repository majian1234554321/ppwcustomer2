package com.ppwc.restaurant.views

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.ppwc.restaurant.views.reserve.ReserveCancelFragment
import com.ppwc.restaurant.views.reserve.ReserveDetailFragment
import com.ppwc.restaurant.views.reserve.ReserveListAdapter
import com.ppwc.restaurant.views.reserve.ReserveListFragment
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.TabEntity
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.TimeUtil
import com.yjhh.common.view.RatingBar
import com.yjhh.common.view.WrapContentLinearLayoutManager
import com.yjhh.common.view.ninegrid.NineGridView
import com.yjhh.common.view.ninegrid.NineGridViewClickAdapter
import com.yjhh.common.view.ninegridimage.NineGridImageView
import com.yjhh.common.view.ninegridimage.NineGridImageViewAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.morecommentsfragment.*

import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class MoreCommentsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.morecommentsfragment


    override fun initView() {


        ImmersionBar.setTitleBar(mActivity, tbv_title)


        val shopId = arguments?.getString("shopId")
        initNav(shopId)

        initAdapter(shopId)
        initRefreshLayout(shopId)


        checkbox.setOnClickListener {

            refresh(shopId)

        }


    }


    private fun initAdapter(shopId: String?) {

        mRecyclerView.layoutManager = WrapContentLinearLayoutManager(mActivity)
        mAdapter = MoreCommentsAdapter(listValue)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore(shopId)
        }, mRecyclerView)

//         mAdapter?.setOnItemChildClickListener { adapter, view, position ->
//             (parentFragment as ReserveListFragment).startBrotherFragment(
//                 CommentsDetailsFragment.newInstance(
//                     mAdapter?.data?.get(
//                         position
//                     )?.id
//                 )
//             )
//         }
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            if (adapter.data[position] is TYPE0Item) {
                start(CommentsDetailsFragment.newInstance((adapter.data[position] as TYPE0Item).id.toString()))
            } else {
                start(CommentsDetailsFragment.newInstance((adapter.data[position] as TYPE1Item).id))
            }

        }

    }


    private fun initRefreshLayout(shopId: String?) {
        swipeLayout.setRefreshHeader(ClassicsHeader(mActivity))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh(shopId)
            swipeLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()

    }


    var mAdapter: MoreCommentsAdapter? = null
    val listValue = ArrayList<MultiItemEntity>()
    var type = "0"//类别，默认null（null/0全部 1好评 2中评 3差评）
    var pageIndex = 0
    val pageSize = 15
    var grade: String? = "" //类别 nav 中的value


    fun refresh(shopId: String?) {
        pageIndex = 0

        loadData(shopId, "refresh")

    }


    fun loadData(shopId: String?, flag: String?) {


        val mapX = ArrayMap<String, String>()
        mapX["shopId"] = shopId
        mapX["grade"] = ""
        mapX["hasFile"] = if (checkbox.isChecked) {  //是否包含附件，默认0（0全部 1含）
            "1"
        } else {
            "0"
        }
        mapX["pageIndex"] = pageIndex.toString()
        mapX["pageSize"] = pageSize.toString()


        ApiServices.getInstance().create(MoreCommentsService::class.java)
            .shopComment(mapX)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("shopComment", response)


                    if (!TextUtils.isEmpty(response)) {
                        val modelX = Gson().fromJson<MoreCommentsBean>(response, MoreCommentsBean::class.java)
                        val newList = ArrayList<MultiItemEntity>()
                        if (flag == "refresh") {

                            if (!modelX.items.isEmpty()) {


                                modelX.items.forEachIndexed { index, itemsBeanX ->
                                    newList.add(itemsBeanX)
                                    itemsBeanX.items.forEachIndexed { indeB, it ->
                                        it.last = indeB == itemsBeanX.items.size - 1
                                        newList.add(it)
                                    }
                                }
                                mAdapter?.setNewData(newList)
                            } else {
                                val view = View.inflate(mActivity, R.layout.emptyview, null)
                                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                                mAdapter?.data?.clear()
                                mAdapter?.notifyDataSetChanged()
                                mAdapter?.emptyView = view
                            }


                        } else {
                            if (modelX.items.size == pageSize) {
                                mAdapter?.loadMoreComplete()
                            } else {
                                mAdapter?.loadMoreEnd()
                            }
                            modelX.items.forEachIndexed { indeA, itemsBeanX ->
                                newList.add(itemsBeanX)
                                itemsBeanX.items.forEachIndexed { indeB, it ->
                                    it.last = indeB == itemsBeanX.items.size - 1
                                    newList.add(it)
                                }


                            }
                            mAdapter?.addData(newList)

                        }

                    }


                }

                override fun onFault(message: String) {
                    Log.i("shopComment", message)


                }
            })

    }


    private fun loadMore(shopId: String?) {
        pageIndex++
        loadData(shopId, "loadMore")

    }


    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()


    private fun initNav(shopId: String?) {

        val map = ArrayMap<String, String>()
        map.clear()
        map.put("shopId", shopId)

        ApiServices.getInstance()
            .create(MoreCommentsService::class.java)
            .nav(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("EvaluateManageFragment", response)


                    val model = Gson().fromJson<Array<NavDateBean>>(
                        response,
                        Array<NavDateBean>::class.java
                    )


                    for (i in model.indices) {
                        mTabEntities.add(TabEntity(model[i].title, R.drawable.icon_place, R.drawable.icon_place))
                    }

                    mTabLayout_7?.setTabData(mTabEntities)



                    mTabLayout_7.setOnTabSelectListener(object : OnTabSelectListener {
                        override fun onTabSelect(position: Int) {


                            grade = model[position].value
                            pageIndex = 0
                            swipeLayout.autoRefresh()

                        }

                        override fun onTabReselect(position: Int) {

                        }

                    })

                }

                override fun onFault(message: String) {
                    Log.i("EvaluateManageFragment", message)
                }

            })
    }


    companion object {
        fun newInstance(shopId: String?): MoreCommentsFragment {
            val fragment = MoreCommentsFragment()
            val bundle = Bundle()
            bundle.putString("shopId", shopId)
            fragment.arguments = bundle
            return fragment
        }
    }


    interface MoreCommentsService {
        @FormUrlEncoded
        @POST("shopComment/nav")
        fun nav(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


        @FormUrlEncoded
        @POST("shopComment")
        fun shopComment(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    }


    data class NavDateBean(
        val ifCurr: Boolean,
        val title: String,
        val total: Int,
        val type: Int,
        val value: String?
    )


    class MoreCommentsAdapter(data: List<MultiItemEntity>) :
        BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


        companion object {

            const val TYPE_LEVEL_0 = 0
            const val TYPE_LEVEL_1 = 1

        }


        init {
            addItemType(TYPE_LEVEL_0, R.layout.morecommentsadapter_a)
            addItemType(TYPE_LEVEL_1, R.layout.morecommentsadapter_b)
        }


        override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {


            when (helper?.itemViewType) {
                TYPE_LEVEL_0 -> {


                    val tYPE0Item = item as TYPE0Item

                    val list = ArrayList<String>()

                    ImageLoaderUtils.loadCircle(
                        mContext,
                        helper.getView(R.id.iv_image),
                        tYPE0Item.shopLogoUrl,
                        R.drawable.icon_place_pai,
                        R.drawable.icon_place_pai
                    )


                    if (tYPE0Item.files.isNotEmpty()) {
                        tYPE0Item.files.forEach {
                            list.add(it.fileUrl)
                        }
                        val view = helper.getView<NineGridImageView<String>>(R.id.nineGrid)

                        view?.setAdapter(object : NineGridImageViewAdapter<String>() {
                            override fun onDisplayImage(context: Context?, imageView: ImageView?, t: String?) {
                                ImageLoaderUtils.load(
                                    context,
                                    imageView,
                                    t,
                                    R.drawable.icon_place_pai,
                                    R.drawable.icon_place_pai, 0
                                )

                            }

                            override fun generateImageView(context: Context?): ImageView {
                                return super.generateImageView(context)
                            }

                            override fun onItemImageClick(
                                context: Context?,
                                imageView: ImageView?,
                                index: Int,
                                list: MutableList<String>?
                            ) {
                                super.onItemImageClick(context, imageView, index, list)
                            }


                        })
                        view.setImagesData(list)
                        helper.setGone(R.id.nineGrid, true)
                    } else {
                        helper.setGone(R.id.nineGrid, false)
                    }

                    helper.getView<RatingBar>(R.id.id_ratingbar).setStar(tYPE0Item.shopScore)
                    helper.setText(R.id.tv_username, tYPE0Item?.userName)
                    helper.setText(R.id.tv_content, tYPE0Item?.content)
                    helper.setText(R.id.tv_time, TimeUtil.stampToDate2(tYPE0Item?.createdTime))


                }


                TYPE_LEVEL_1 -> {

                    val tYPE1Item = item as TYPE1Item

                    helper.getView<LinearLayout>(R.id.ll_content).visibility =
                        if (!TextUtils.isEmpty(tYPE1Item.content)) View.VISIBLE else View.GONE


                    helper.getView<LinearLayout>(R.id.rl_more).visibility =
                        if (tYPE1Item.last) View.VISIBLE else View.GONE //最后一个item 显示查看详情


                    if (tYPE1Item.ifShop == "1") {//// 商家
                        helper.setBackgroundColor(R.id.rll, Color.parseColor("#F7EEDD"))
                    } else {
                        helper.setBackgroundColor(R.id.rll, Color.parseColor("#E7E7E7"))
                    }


                    helper.setText(R.id.tv_reply22, tYPE1Item.userName)

                    helper.setText(R.id.tv_content22, tYPE1Item.content)

                    helper.setText(R.id.tv_time22, TimeUtil.stampToDate2(tYPE1Item.createdTime.toString()))


                }
            }


        }

    }


    data class MoreCommentsBean(
        val items: List<TYPE0Item>,
        val pageCount: Int,
        val queryModel: QueryModel,
        val recordCount: Int
    )

    data class TYPE0Item(
        val avatarUrl: String,
        val content: String,
        val createdTime: String,
        val files: List<FileBean>,
        val id: Int,
        val ifAllowDel: Boolean,
        val ifShop: Int,
        val items: List<TYPE1Item>,
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
    ) : MultiItemEntity {
        override fun getItemType(): Int {
            return MoreCommentsAdapter.TYPE_LEVEL_0
        }
    }

    data class TYPE1Item(
        val avatarUrl: String,
        val content: String,
        val createdTime: String,
        val files: List<FileBean>,
        val id: String,
        val ifAllowDel: Boolean,
        val ifShop: String,

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
        val userName: String,
        var last: Boolean = false

    ) : MultiItemEntity {
        override fun getItemType(): Int {
            return MoreCommentsAdapter.TYPE_LEVEL_1
        }
    }


    data class FileBean(
        val fileUrl: String
    )

    data class QueryModel(
        val pageIndex: Int,
        val pageSize: Int
    )


}