package com.paipaiwei.personal.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jakewharton.rxbinding2.widget.RxTextView

import com.yjhh.common.base.BaseActivity
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.SearchAdapter
import com.paipaiwei.personal.adapter.SearchContentAdapter
import com.paipaiwei.personal.bean.Main1FootBean
import com.paipaiwei.personal.bean.SearchInfoBean

import com.paipaiwei.personal.db.AppDataBase
import com.paipaiwei.personal.db.entity.HistoricalModel
import com.paipaiwei.personal.present.SearchInfoPresent
import com.paipaiwei.personal.view.SearchInfoView
import com.yjhh.common.bean.QueryResultBean

import com.yjhh.common.iview.QueryResultView
import com.yjhh.common.present.QueryPresent
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter


import io.reactivex.Observable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

import kotlin.collections.ArrayList


@Route(path = "/SearchActivity/Search")
class SearchActivity : BaseActivity(), QueryResultView, SearchInfoView {
    override fun queryResultValue(model: QueryResultBean) {
        ll_history.visibility = View.GONE
        ll_hot.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        mAdapter?.setNewData(model.items)

        if (model.items.size==0) {
            val view = View.inflate(this, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "没有搜索到相关数据"
            mAdapter?.emptyView = view
        }else{
            mAdapter?.setNewData(model.items)
        }


    }

    override fun SearchInfoValue(modle: SearchInfoBean) {

        ll_hot.visibility = View.VISIBLE
        listHot.addAll(modle.labels)
        recyclerView.visibility = View.GONE

        val adapter = HotTagAdapter(this@SearchActivity, modle.labels)
        hot_flowlayout.adapter = adapter

        hot_flowlayout.setOnTagClickListener { view, position, parent ->

            Observable.just(modle.labels[position])
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
                .subscribe {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }






            true
        }


    }


    class HotTagAdapter(val activity: Activity, var list: List<String>) : TagAdapter<String>(list) {
        override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
            val tv = View.inflate(activity, R.layout.hottagadapter, null) as TextView
            tv.text = t
            return tv
        }

    }


    override fun onFault(errorMsg: String?) {

    }

    val pageSize = 15
    var pageIndex = 0

    val listHot = ArrayList<String>()

    var mAdapter: SearchContentAdapter? = null
    var historyadapter: SearchAdapter? = null

    var listsHistory = ArrayList<HistoricalModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val present = QueryPresent(this@SearchActivity, this)
        val list = ArrayList<QueryResultBean.ItemsBean>()
        val keyWord = intent.getStringExtra("keyWord");
        if (!TextUtils.isEmpty(keyWord)) {
            et_search.setText(keyWord)
        } else {

        }


        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        mAdapter = SearchContentAdapter(list)
        mAdapter = SearchContentAdapter(list)
        recyclerView.adapter = mAdapter

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
                    listsHistory.addAll(it)
                    historyadapter = SearchAdapter(this, it, history_flowlayout)
                    history_flowlayout.adapter = historyadapter

                    history_flowlayout.setOnTagClickListener { arg1, arg2, arg3 ->
                        if (listsHistory.size > 0) {
                            Toast.makeText(this, listsHistory[arg2].keyword, Toast.LENGTH_SHORT).show()
                            et_search.setText(listsHistory[arg2].keyword)
                        }
                        true
                    }
                    recyclerView.visibility = View.GONE
                    ll_history.visibility = View.VISIBLE

                } else {
                    ll_history.visibility = View.GONE
                }
            }, {
                Toast.makeText(this@SearchActivity, it.toString(), Toast.LENGTH_SHORT).show()
            })

        compositeDisposable.add(dis2)


        val dis = RxTextView.textChanges(et_search).throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ itValue ->
                if (!TextUtils.isEmpty(itValue)) {



                    present.queryValue(itValue.toString(), "", "", "", "", "", pageIndex, pageSize)


                } else {
                    recyclerView.visibility = View.GONE
                    if (listsHistory.size > 0) {
                        ll_history.visibility = View.VISIBLE
                    } else {
                        ll_hot.visibility = View.GONE
                    }

                    if (listHot.size > 0) {
                        ll_hot.visibility = View.VISIBLE
                    } else {
                        ll_hot.visibility = View.GONE
                    }

                    val searchinfopresent = SearchInfoPresent(this@SearchActivity, this)
                    searchinfopresent.searchHot()

                }

            }, {

            })

        compositeDisposable.add(dis)

        iv_back.setOnClickListener {
            finish()
        }


        iv_delete.setOnClickListener {
            Observable.just(true)
                .doOnNext {
                    AppDataBase
                        .getInstance(this@SearchActivity).historicalDao.deleteAll()
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listsHistory.clear()
                    ll_history.visibility = View.GONE
                }, {

                })
        }





        mAdapter?.setOnItemClickListener { adapter, view, position ->

            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantHomeFragment")
                .withString("id", (adapter.data[position] as QueryResultBean.ItemsBean).id)
                .navigation()


            /*  val disposable = Observable.just(list[position])
                  .doOnNext {
                      val all = AppDataBase
                          .getInstance(this)
                          .historicalDao.findByName(et_search.text.toString(), it)

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

                      val intent = Intent()
                      intent.putExtra("keyWord", it)
                      setResult(RESULT_OK, intent)

                      Toast.makeText(this, list[position], Toast.LENGTH_SHORT).show()
                  }, {
                      Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                  })
              compositeDisposable.add(disposable)*/
        }

    }
}


