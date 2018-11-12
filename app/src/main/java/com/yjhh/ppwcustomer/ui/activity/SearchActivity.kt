package com.yjhh.ppwcustomer.ui.activity

import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.base.BaseActivity
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.SearchAdapter
import com.yjhh.ppwcustomer.adapter.SearchContentAdapter
import com.yjhh.ppwcustomer.db.AppDataBase
import com.yjhh.ppwcustomer.db.entity.HistoricalModel
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener
import com.yjhh.ppwcustomer.interfaces.OnItemClickListener2
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : BaseActivity() {


    var mAdapter: SearchContentAdapter? = null


    var historyadapter: SearchAdapter? = null

    var lists = ArrayList<HistoricalModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val list = ArrayList<String>()
        list.add("A")
        list.add("BB")
        list.add("CCC")
        list.add("DDDD")
        list.add("EEEEE")
        list.add("AFFFF")


        recyclerView.layoutManager = LinearLayoutManager(this)

        val dis2 = Observable.create<List<HistoricalModel>> {

            it.onNext(
                AppDataBase
                    .getInstance(this@SearchActivity)
                    .historicalDao.all
            )

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it.isNotEmpty()) {
                    lists.addAll(it)
                    historyadapter = SearchAdapter(this, it, history_flowlayout)
                    history_flowlayout.adapter = historyadapter
                } else {
                    ll0.visibility = View.GONE
                }


            }, {
                Toast.makeText(this@SearchActivity, it.toString(), Toast.LENGTH_SHORT).show();
            })

        compositeDisposable.add(dis2)

        history_flowlayout.setOnTagClickListener { arg1, arg2, arg3 ->
            if (list.size > 0) {
                Toast.makeText(this, lists[arg2].keyword, Toast.LENGTH_SHORT).show()
                et_search.setText(lists[arg2].keyword)
            }
            return@setOnTagClickListener true
        }

        val dis = RxTextView.textChanges(et_search).subscribe({
            if (!TextUtils.isEmpty(it)) {
                ll0.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                mAdapter = SearchContentAdapter(this, list)
                recyclerView.adapter = mAdapter
            }


        }, {

        })

        compositeDisposable.add(dis)


        tv_history.setOnClickListener {
            Observable.just(true)
                .doOnNext {
                    AppDataBase
                        .getInstance(this@SearchActivity).historicalDao.deleteAll()
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ll0.visibility = View.GONE
                }, {
                    ll0.visibility = View.GONE
                })
        }




        mAdapter?.setOnItemClick(object : OnItemClickListener2<String>() {
            override fun onItemClick(vh: RecyclerView.ViewHolder?, data: MutableList<String>?, position: Int) {
                Toast.makeText(this@SearchActivity, data?.get(position), Toast.LENGTH_SHORT).show()
                val disposable = Observable.just(data?.get(position))
                    .doOnNext {
                        val all = AppDataBase
                            .getInstance(this@SearchActivity)
                            .historicalDao.all
                        val historicalModel = HistoricalModel(it, it)
                        if (all != null && !all.contains(historicalModel)) {
                            AppDataBase
                                .getInstance(this@SearchActivity)
                                .historicalDao
                                .insertAll(historicalModel)
                        }
                    }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(this@SearchActivity, data?.get(position), Toast.LENGTH_SHORT).show()
                    }, {

                    })
                compositeDisposable.add(disposable)

            }


        })

    }
}
