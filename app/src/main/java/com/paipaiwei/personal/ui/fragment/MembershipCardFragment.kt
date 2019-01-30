package com.paipaiwei.personal.ui.fragment

import android.graphics.drawable.Drawable
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.paipaiwei.personal.common.ScaleTransformer
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionMembershipService
import com.paipaiwei.personal.bean.MemCanBuyBean
import com.paipaiwei.personal.adapter.MembershipCardAdapter
import com.paipaiwei.personal.adapter.MyVpAdater
import com.paipaiwei.personal.bean.MembCardBean
import com.paipaiwei.personal.bean.MembershipCardBean
import com.paipaiwei.personal.bean.MyBuyCardInfoBean
import com.paipaiwei.personal.common.SpacesItemDecoration
import com.paipaiwei.personal.common.utils.Util
import com.paipaiwei.personal.present.MembershipCardPresent
import com.paipaiwei.personal.view.MembershipCardView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import net.cachapa.expandablelayout.ExpandableLayout
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder


class MembershipCardFragment : BaseFragment(), MembershipCardView {
    override fun onFault(errorMsg: String?) {

    }

    override fun onMembershipCardValue(model: MembershipCardBean) {


        if (model.items.isNotEmpty()) {
            mAdapter?.setNewData(model.items)
            list.addAll(model.items)
        } else {
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.data?.clear()
            mAdapter?.notifyDataSetChanged()
            mAdapter?.emptyView = view
        }

    }


    val status = "1" //状态，默认1(1有效的 2已过期的/失效的)
    var pageIndex = 0
    var pageSize = 15

    var present: MembershipCardPresent? = null
    var mAdapter: MembershipCardAdapter? = null

    val list = ArrayList<MembershipCardBean.ItemsBean>()

    override fun getLayoutRes(): Int = R.layout.membershipcardfragment

    override fun initView() {

        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(Util.dip2px(mActivity, 25f), "TOP,BOTTOM"))
        mAdapter = MembershipCardAdapter(list)
        mRecyclerView.adapter = mAdapter

        present = MembershipCardPresent(mActivity, this)
        present?.coupon(status, pageIndex, pageSize)

        mAdapter?.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.tv_useIf -> {
                    if (list[position].expand) {
                        list[position].expand = false
                        (adapter.getViewByPosition(
                            mRecyclerView,
                            position,
                            R.id.expandable_layout
                        ) as ExpandableLayout).collapse()


                        val textDrawable =
                            (adapter.getViewByPosition(mRecyclerView, position, R.id.tv_useIf)) as TextView

                        val drawableLeft = resources.getDrawable(
                            R.drawable.icon_down
                        )

                        textDrawable.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null, drawableLeft, null
                        );
                        textDrawable.compoundDrawablePadding = 4


                        //mAdapter.notifyItemChanged(position)
                    } else {
                        list[position].expand = true
                        (adapter.getViewByPosition(
                            mRecyclerView,
                            position,
                            R.id.expandable_layout
                        ) as ExpandableLayout).expand()


                        val textDrawable =
                            (adapter.getViewByPosition(mRecyclerView, position, R.id.tv_useIf)) as TextView
                        val drawableLeft = resources.getDrawable(
                            R.drawable.icon_up
                        )
                        textDrawable.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null, drawableLeft, null
                        );
                        textDrawable.compoundDrawablePadding = 4
                    }

                }

                R.id.tv_status -> {

                    if (list.get(position).ifNeedAudit) {
                        QRCodeFragment(list[position].qrCodeData).show(childFragmentManager, "TAG")
                    } else {
                        ARouter.getInstance()
                            .build("/DisplayActivity/Display")
                            .withString("displayTab", "CheckPayFragment")
                            .withString("value", list[position].id)
                            .withString("value1", "")
                            .withString("value2", list[position].shopId)
                            .withString("value3", "2")
                            .navigation()
                    }


                }

                else -> {
                }
            }


        }


        // mAdapter?.addFooterView()
        tv_next.setOnClickListener {
            ARouter.getInstance()
                .build("/DisplayActivity/Display")
                .withString("displayTab", "MembershipCardFragment2")
                .navigation()
        }

    }
}