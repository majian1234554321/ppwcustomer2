package com.yjhh.ppwcustomer.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.LogUtils
import com.yjhh.ppwcustomer.CurrentApplication.provinceBean
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.SortAdapter
import com.yjhh.ppwcustomer.bean.PhoneBean
import com.yjhh.ppwcustomer.bean.ProvinceBean2
import com.yjhh.ppwcustomer.common.utils.GetJsonDataUtil
import com.yjhh.ppwcustomer.common.utils.PhoneUtil
import com.yjhh.ppwcustomer.common.utils.SortUtil
import com.yjhh.ppwcustomer.ui.customview.SideBar
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.activity_contact.*
import java.util.ArrayList
import java.util.concurrent.TimeUnit


class ActivityContact : BaseActivity() {
    lateinit var phoneDtos: ArrayList<PhoneBean>
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)


        val sortUtil = SortUtil()
        rvList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)





        Observable.just("1")
            .flatMap {
                val phoneUtil = PhoneUtil(this@ActivityContact)
                phoneDtos = phoneUtil.phone

                Observable.just(phoneDtos)

            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val letters = sortUtil.sortDatas(it)
                    var rvAdapter = SortAdapter(this, it, R.layout.adapter_sort)

                    rvList.adapter = rvAdapter
                    sideBar.reset(letters)
                }, {
                    Log.i("ActivityContact", it.message)
                }
            )





        rvList.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                sortUtil.onScrolled(recyclerView, llytTin, tvTinLetter)
            }
        })

        sideBar.setOnLetterChangedListener { index, c -> sortUtil.onChange(index, c, rvList) }




        RxTextView.textChanges(et_search)
            .debounce(600, TimeUnit.MILLISECONDS)
            .map {
                val list = ArrayList<PhoneBean>()

                phoneDtos.forEachIndexed { i, v ->
                    if (v.content.contains(it.toString())) {
                        list.add(v)
                    }
                }

                list
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //   Log.i("ActivityContact", it[0].content)
                if (it != null && it.size > 0) {
                    listView.visibility = View.VISIBLE

                } else {
                    listView.visibility = View.GONE
                }

            }, {
                //listView.visibility = View.GONE
            })


        listView.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent()
          //  intent.putExtra("phone", info.district + info.key)
            setResult(RESULT_OK, intent)
            finish()

        }

    }


}
