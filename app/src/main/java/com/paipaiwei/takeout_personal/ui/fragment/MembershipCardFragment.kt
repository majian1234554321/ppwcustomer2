package com.paipaiwei.takeout_personal.ui.fragment

import com.yjhh.common.base.BaseFragment
import com.paipaiwei.takeout_personal.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.paipaiwei.takeout_personal.common.ScaleTransformer
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
import com.google.gson.Gson
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionMembershipService
import com.paipaiwei.takeout_personal.bean.MemCanBuyBean
import com.paipaiwei.takeout_personal.adapter.MembershipCardAdapter
import com.paipaiwei.takeout_personal.adapter.MyVpAdater
import com.paipaiwei.takeout_personal.bean.MembCardBean
import com.paipaiwei.takeout_personal.bean.MyBuyCardInfoBean
import com.paipaiwei.takeout_personal.common.SpacesItemDecoration
import com.paipaiwei.takeout_personal.common.utils.Util
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder


class MembershipCardFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.membershipcardfragment

    override fun initView() {

        val list = ArrayList<Boolean>()

        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)

        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(Util.dip2px(mActivity, 16f)))

        mRecyclerView.adapter = MembershipCardAdapter(context, list)


    }
}