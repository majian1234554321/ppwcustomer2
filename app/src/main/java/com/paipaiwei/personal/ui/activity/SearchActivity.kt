package com.paipaiwei.personal.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jakewharton.rxbinding2.widget.RxTextView

import com.yjhh.common.base.BaseActivity
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.SearchAdapter
import com.paipaiwei.personal.adapter.SearchContentAdapter
import com.paipaiwei.personal.db.AppDataBase
import com.paipaiwei.personal.db.entity.HistoricalModel

import com.yjhh.common.iview.QueryResultView
import com.yjhh.common.present.QueryPresent


import io.reactivex.Observable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*

import kotlin.collections.ArrayList


@Route(path = "/SearchActivity/Search")
class SearchActivity : BaseActivity(), QueryResultView {
    override fun onFault(errorMsg: String?) {

    }

    val pageSize = 15
    var pageIndex = 0

    lateinit var mAdapter: SearchContentAdapter
    var historyadapter: SearchAdapter? = null

    var lists = ArrayList<HistoricalModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val present = QueryPresent(this@SearchActivity, this)
        val list = ArrayList<String>()

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        mAdapter = SearchContentAdapter(list)


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
                Toast.makeText(this@SearchActivity, it.toString(), Toast.LENGTH_SHORT).show()
            })

        compositeDisposable.add(dis2)

        history_flowlayout.setOnTagClickListener { arg1, arg2, arg3 ->
            if (list.size > 0) {
                Toast.makeText(this, lists[arg2].keyword, Toast.LENGTH_SHORT).show()
                et_search.setText(lists[arg2].keyword)
            }
            return@setOnTagClickListener true
        }


        val dis = RxTextView.textChanges(et_search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!TextUtils.isEmpty(it)) {
                    ll0.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    mAdapter = SearchContentAdapter(list)
                    recyclerView.adapter = mAdapter



                    mAdapter.setOnItemClickListener { adapter, view, position ->
                        Toast.makeText(this, list.get(position), Toast.LENGTH_SHORT).show()
                        val disposable = Observable.just(list.get(position))
                            .doOnNext {
                                val all = AppDataBase
                                    .getInstance(this)
                                    .historicalDao.findByName(it, it)

                                if (all != null) {

                                    AppDataBase
                                        .getInstance(this)
                                        .historicalDao
                                        .delete(all)

                                }
                                val historicalModel = HistoricalModel(it, it)

                                AppDataBase
                                    .getInstance(this)
                                    .historicalDao
                                    .insertAll(historicalModel)


                            }.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                present.queryValue(it, "", "", "", "", "", pageIndex, pageSize)
                                val intent = Intent()
                                intent.putExtra("keyWord", it)
                                setResult(RESULT_OK, intent)

                                Toast.makeText(this, list[position], Toast.LENGTH_SHORT).show()
                            }, {

                            })
                        compositeDisposable.add(disposable)
                    }


                }


            }, {

            })

        compositeDisposable.add(dis)



        iv_back.setOnClickListener {
            finish()
        }


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


    }
}


