package com.paipaiwei.personal.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.MoreSectionAdapter
import com.paipaiwei.personal.bean.MoreSectionBean
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseView
import com.yjhh.common.utils.SpaceItemDecoration2
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_more_section.*
import okhttp3.ResponseBody
import retrofit2.http.POST
import java.lang.StringBuilder

class MoreSectionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_section)

        ApiServices.getInstance().create(MoreSectionService::class.java)
            .category()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(this@MoreSectionActivity) {
                override fun processValue(response: String?) {
                    Log.i("MoreSectionActivity", response)
                    val sb = StringBuilder()
                    sb.append("{\n" + "\"items\":").append(response).append("}")
                    val model = Gson().fromJson<MoreSectionBean>(sb.toString(), MoreSectionBean::class.java)
                    recyclerView.layoutManager = LinearLayoutManager(this@MoreSectionActivity)
                    recyclerView.addItemDecoration(SpaceItemDecoration2(20, "top"))
                    recyclerView.adapter = MoreSectionAdapter(this@MoreSectionActivity, model.items)

                }

                override fun onFault(message: String) {
                    Log.i("MoreSectionActivity", message)
                }

            })


    }


    interface MoreSectionService {
        @POST("common/category")
        fun category(): Observable<ResponseBody>

        interface MoreSectionView : BaseView {
            fun onMoreSectionValue(response: String)
        }
    }



}




