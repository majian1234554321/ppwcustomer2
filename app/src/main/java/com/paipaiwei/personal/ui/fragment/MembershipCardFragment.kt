package com.paipaiwei.personal.ui.fragment

import android.graphics.drawable.Drawable
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.paipaiwei.personal.common.ScaleTransformer
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionMembershipService
import com.paipaiwei.personal.bean.MemCanBuyBean
import com.paipaiwei.personal.adapter.MembershipCardAdapter
import com.paipaiwei.personal.adapter.MyVpAdater
import com.paipaiwei.personal.bean.MembCardBean
import com.paipaiwei.personal.bean.MyBuyCardInfoBean
import com.paipaiwei.personal.common.SpacesItemDecoration
import com.paipaiwei.personal.common.utils.Util
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import net.cachapa.expandablelayout.ExpandableLayout
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder


class MembershipCardFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.membershipcardfragment

    override fun initView() {

        val list = ArrayList<Boolean>()

        for (i in 0..18) {
            list.add(false)
        }




        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(Util.dip2px(mActivity, 25f), "TOP,BOTTOM"))

        val mAdapter = MembershipCardAdapter(list)

        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.tv_useIf -> {
                    if (list[position]) {
                        list.set(position, false)
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
                        list[position] = true
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
                    QRCodeFragment("aa").show(childFragmentManager, "TAG")
                }

                else -> {
                }
            }


        }


    }
}