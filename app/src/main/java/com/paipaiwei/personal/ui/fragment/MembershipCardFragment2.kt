package com.paipaiwei.personal.ui.fragment

import android.graphics.drawable.Drawable
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import kotlinx.android.synthetic.main.membershipcardfragment2.*

import android.widget.TextView
import android.widget.Toast

import com.paipaiwei.personal.adapter.MembershipCardAdapter

import com.paipaiwei.personal.bean.MembershipCardBean

import com.paipaiwei.personal.common.SpacesItemDecoration
import com.paipaiwei.personal.common.utils.Util
import com.paipaiwei.personal.present.MembershipCardPresent
import com.paipaiwei.personal.view.MembershipCardView

import net.cachapa.expandablelayout.ExpandableLayout


class MembershipCardFragment2 : BaseFragment(), MembershipCardView {
    override fun onFault(errorMsg: String?) {

    }

    override fun onMembershipCardValue(model: MembershipCardBean) {
        mAdapter?.setNewData(model.items)
        list.addAll(model.items)
    }


    val status = "2" //状态，默认1(1有效的 2已过期的/失效的)
    var pageIndex = 0
    var pageSize = 15

    var present: MembershipCardPresent? = null
    var mAdapter: MembershipCardAdapter? = null

    val list = ArrayList<MembershipCardBean.ItemsBean>()

    override fun getLayoutRes(): Int = R.layout.membershipcardfragment2

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
                        Toast.makeText(mActivity, "立即使用", Toast.LENGTH_SHORT).show()
                    }


                }

                else -> {
                }
            }


        }


    }
}