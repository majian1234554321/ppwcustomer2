package com.paipaiwei.personal.ui.fragment

import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.paipaiwei.personal.common.ScaleTransformer
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
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

        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)
        list.add(false)

        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(Util.dip2px(mActivity, 25f),"TOP,BOTTOM"))

      val mAdapter =   MembershipCardAdapter( list)

        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            if (list[position]) {
                list.set(position, false)
                view.findViewById<ExpandableLayout>(R.id.expandable_layout).collapse()
                //mAdapter.notifyItemChanged(position)
            } else {
                // expandButton.setSelected(true);
                view.findViewById<ExpandableLayout>(R.id.expandable_layout).expand()
                list.set(position, true)
               // mAdapter.notifyItemChanged(position)
            }

        }




    }
}