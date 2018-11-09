package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.yjhh.ppwcustomer.R.id.mViewPager
import com.yjhh.ppwcustomer.common.ScaleTransformer
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionMembershipService
import com.yjhh.ppwcustomer.adapter.MembershipCardAdapter
import com.yjhh.ppwcustomer.adapter.MyVpAdater
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.function.BiFunction
import java.util.function.Function


class MembershipCardFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.membershipcardfragment

    override fun initView() {


        val observable1 = ApiServices.getInstance().create(SectionMembershipService::class.java)
            .membershipcardtypes()


        val observable2 = ApiServices.getInstance().create(SectionMembershipService::class.java)
            .membershipcardcards()


        val map = ArrayMap<String, String>()


        val observable3 = ApiServices.getInstance().create(SectionMembershipService::class.java)
            .myMembershipcard(map)


        val dis = Observable.zip(
            observable1,
            observable2,
            observable3,
            object : Function3<ResponseBody, ResponseBody, ResponseBody, List<String>> {
                override fun apply(t1: ResponseBody, t2: ResponseBody, t3: ResponseBody): List<String> {
                    // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                    val list = ArrayList<String>()

                    val response1 = t1.string()
                    val response2 = t2.string()
                    val response3 = t3.string()
                    list.add(response1)
                    list.add(response2)
                    list.add(response3)

                    return list
                }

            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    val value1 = it[0]
                    Log.i("MembershipCardFragment",value1)
                    val value2 = it[1]
                    Log.i("MembershipCardFragment",value2)
                    val value3 = it[2]
                    Log.i("MembershipCardFragment",value3)


                    val jsonobject1 = JSONObject(value1)
                    val jsonobject2 = JSONObject(value2)
                    val jsonobject3 = JSONObject(value3)


                    if (jsonobject1.getBoolean("success")) {
                        val jsonArray = jsonobject1.getString("data")
                      val a  =   JSONArray(jsonArray)
                        a.length()

                        for (i in 0 until a.length()) {
                            val job = a.getJSONObject(i) // 遍历 jsonarray 数组，把每一个对象转成 json 对象

                            Log.i("MembershipCardFragment", job.get("name").toString())
                            Log.i("MembershipCardFragment", job.get("type").toString())

                        }


                    } else {

                    }

                    if (jsonobject2.getBoolean("success")) {
                        val jsonObject = jsonobject1.getString("data")




                    } else {

                    }

                    if (jsonobject3.getBoolean("success")) {

                    } else {

                    }


                }, {

                }
            )


        val list = ArrayList<Int>()
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        val adater = MyVpAdater(context, list)

        mViewPager.offscreenPageLimit = list.size

        mViewPager.adapter = adater


        mViewPager.setPageTransformer(true, ScaleTransformer())


        val list2 = ArrayList<String>()

        list2.add("1")
        list2.add("1")
        list2.add("1")
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mRecyclerView.adapter = MembershipCardAdapter(context, list2)

    }
}