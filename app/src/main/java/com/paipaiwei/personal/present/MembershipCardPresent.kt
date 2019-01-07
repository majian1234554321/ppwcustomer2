package com.paipaiwei.personal.present

import android.content.Context
import androidx.collection.ArrayMap
import com.paipaiwei.personal.apis.MembershipCardService
import com.paipaiwei.personal.bean.MembershipCardBean
import com.paipaiwei.personal.view.MembershipCardView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class MembershipCardPresent(var context: Context,var view :MembershipCardView) : BasePresent() {



    fun coupon(status: String, pageIndex: Int, pageSize: Int) {
        map.clear()
        map["status"] = status
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()

        toSubscribe2(ApiServices.getInstance().create(MembershipCardService::class.java).coupon(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    val model = gson.fromJson<MembershipCardBean>(response, MembershipCardBean::class.java)
                    view.onMembershipCardValue(model)

                }

                override fun onFault(message: String) {

                }

            }
        )

    }

}