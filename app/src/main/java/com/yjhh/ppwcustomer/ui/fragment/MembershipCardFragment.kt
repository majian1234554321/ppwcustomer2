package com.yjhh.ppwcustomer.ui.fragment

import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.yjhh.ppwcustomer.common.ScaleTransformer
import android.support.v7.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
import com.google.gson.Gson
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionMembershipService
import com.yjhh.ppwcustomer.bean.MemCanBuyBean
import com.yjhh.ppwcustomer.adapter.MembershipCardAdapter
import com.yjhh.ppwcustomer.adapter.MyVpAdater
import com.yjhh.ppwcustomer.bean.MembCardBean
import com.yjhh.ppwcustomer.bean.MyBuyCardInfoBean
import com.yjhh.ppwcustomer.common.SpacesItemDecoration
import com.yjhh.ppwcustomer.common.utils.Util
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

        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.addItemDecoration( SpacesItemDecoration(Util.dip2px(mActivity,16f)))

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
            Function3<ResponseBody, ResponseBody, ResponseBody, List<String>> { t1, t2, t3 ->
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                val list = ArrayList<String>()

                val response1 = t1.string()
                val response2 = t2.string()
                val response3 = t3.string()
                list.add(response1)
                list.add(response2)
                list.add(response3)

                list
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    val value1 = it[0]
                    Log.i("MembershipCardFragment", value1)
                    val value2 = it[1]
                    Log.i("MembershipCardFragment", value2)
                    val value3 = it[2]
                    Log.i("MembershipCardFragment", value3)


                    val jsonobject1 = JSONObject(value1)
                    val jsonobject2 = JSONObject(value2)
                    val jsonobject3 = JSONObject(value3)


                    if (jsonobject1.getBoolean("success")) {
                        val jsonArray = jsonobject1.getString("data")
                        val a = JSONArray(jsonArray)
                        a.length()
                        for (i in 0 until a.length()) {
                            val job = a.getJSONObject(i) // 遍历 jsonarray 数组，把每一个对象转成 json 对象
//                            Log.i("MembershipCardFragment", job.get("name").toString())
//                            Log.i("MembershipCardFragment", job.get("type").toString())
                        }


                    } else {
                    }


                    if (jsonobject2.getBoolean("success")) {
                        val jsonString = jsonobject2.getString("data")


                        val sb = StringBuilder()

                        sb.append(
                            "{\n" +
                                    "    \"items\":"
                        ).append(jsonString).append("}")




                        val gson = Gson()


                        val bean = gson.fromJson<MemCanBuyBean>(sb.toString(), MemCanBuyBean::class.java)

                       /* if (bean?.items != null) {

                            bean?.items.forEach { aValue ->
                                var model = MembCardBean()
                                model.name = aValue.name
                                model.remark = aValue.remark
                                model.type = aValue.type
                                model.typeName = aValue.typeName
                                aValue?.items?.forEach { bValue ->
                                    model.id = bValue.id
                                    model.index = bValue.index
                                    model.price = bValue.price
                                    model.time = bValue.time
                                    model.timeText = bValue.timeText
                                    lists.add(model)
                                }

                            }
                            mRecyclerView.adapter = MembershipCardAdapter(context, lists)

                        }*/

                        mRecyclerView.adapter = MembershipCardAdapter(context, bean.items)



                    } else {

                    }


                    Log.i("MembershipCardFragment", "-----------------------------------------")

                    if (jsonobject3.getBoolean("success")) {
                        val list2 = ArrayList<MyBuyCardInfoBean>()
                        val jsonArray = jsonobject3.getString("data")
                        val a = JSONArray(jsonArray)

                        for (i in 0 until a.length()) {

                            val job = a.getJSONObject(i) // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            var model2 = MyBuyCardInfoBean()


                            model2.buyTime = job.optString("buyTime")?.toString()
                            model2.cardNo = job.optString("cardNo")?.toString()
                            model2.expiredTime = job.optString("expiredTime")?.toString()
                            model2.expiredTimeText = job.optString("expiredTimeText")?.toString()
                            model2.id = job.optString("id")?.toString()
                            model2.imageUrl = job.optString("imageUrl")?.toString()
                            model2.name = job.optString("name")?.toString()
                            model2.status = job.optString("status")?.toString()

                            model2.type = job.optString("type")?.toString()
                            model2.typeName = job.optString("typeName")?.toString()

                            list2.add(model2)

                        }

                        val adater = MyVpAdater(context, list2)

                        mViewPager.offscreenPageLimit = list2.size

                        mViewPager.adapter = adater


                        mViewPager.setPageTransformer(true, ScaleTransformer())


                    } else {

                    }


                }, {

                }
            )


    }
}