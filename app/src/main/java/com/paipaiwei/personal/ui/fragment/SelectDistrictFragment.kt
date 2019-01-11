package com.paipaiwei.personal.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.yjhh.common.utils.AmpLocationUtil
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.ConstellationAdapter
import com.paipaiwei.personal.db.AppDataBase
import com.paipaiwei.personal.db.entity.CityHistoryModel
import com.yjhh.common.Constants
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.selectdistrictfragment.*
import java.util.*

class SelectDistrictFragment : BaseFragment() {

    private val constellations =
        arrayOf("全城", "江汉区", "武昌区", "江岸区", "硚口区", "汉阳区", "洪山区", "蔡甸区", "黄陂区")

    override fun getLayoutRes(): Int = R.layout.selectdistrictfragment
    override fun initView() {

        val value = arguments?.getString("id")
        var constellationAdapter = ConstellationAdapter(context, Arrays.asList(*constellations))
        constellations.forEachIndexed { index, s ->
            if (s == value) {
                constellationAdapter.setCheckItem(index)
            }
        }

        getAllDate(mActivity)
        gridView.adapter = constellationAdapter
        tv_currentLocation.text = "当前地址:${com.yjhh.common.Constants.district}"

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->


            val intent = Intent()
            intent.putExtra("location", constellations[position])
            mActivity.setResult(RESULT_OK, intent)


            //


            val disposable = Observable.just(constellations[position])
                .doOnNext {
                    val all = AppDataBase
                        .getInstance(mActivity)
                        .cityHistoryDao.findByName(it, it)

                    if (all != null) {

                        AppDataBase
                            .getInstance(mActivity)
                            .cityHistoryDao
                            .delete(all)

                    }
                    val historicalModel = CityHistoryModel(it, it)

                    AppDataBase
                        .getInstance(mActivity)
                        .cityHistoryDao
                        .insertAll(historicalModel)


                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    //  val intent = Intent()
//                    intent.putExtra("keyWord", it)


                }, {

                })
            compositeDisposable.add(disposable)



























            mActivity.finish()
        }

        tv_currentLocation.text = "当前位置：${Constants.district}"


        iv_delete.setOnClickListener {
            Observable.just(true)
                .doOnNext {
                    AppDataBase
                        .getInstance(mActivity).cityHistoryDao.deleteAll()
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listsHistory.clear()
                    ll_history.visibility = View.GONE
                }, {

                })
        }


        tv_currentLocation.setOnClickListener { view ->
            AmpLocationUtil.getCurrentLocation {

                if (it.errorCode == 0) {

                    tv_currentLocation.text = "当前位置：${it.district}"

                } else {
                    //定位失败
                    tv_currentLocation.text = "定位失败请重试"

                }

            }
        }


    }


    fun saveData(activity: Activity, value: String) {

    }

    var listsHistory = ArrayList<CityHistoryModel>()
    fun getAllDate(activity: Activity) {
        val dis2 = Observable.create<List<CityHistoryModel>> {
            it.onNext(
                AppDataBase
                    .getInstance(activity)
                    .cityHistoryDao.all
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it.isNotEmpty()) {
                    listsHistory.addAll(it)
                    val historyadapter =
                        SearchTAGAdapter(activity, it as ArrayList<CityHistoryModel>, history_flowlayout)
                    history_flowlayout.adapter = historyadapter

                    history_flowlayout.setOnTagClickListener { arg1, arg2, arg3 ->
                        if (listsHistory.size > 0) {
                            Toast.makeText(activity, listsHistory[arg2].keyword, Toast.LENGTH_SHORT).show()
                            //et_search.setText(listsHistory[arg2].keyword)
                        }
                        true
                    }


                } else {
                    ll_history.visibility = View.GONE
                }
            }, {
                Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            })

        compositeDisposable.add(dis2)
    }


    class SearchTAGAdapter(
        var activity: Activity,
        var data: ArrayList<CityHistoryModel>,
        var mFlowLayout: TagFlowLayout
    ) :
        TagAdapter<CityHistoryModel>(data) {

        override fun getView(parent: FlowLayout, position: Int, s: CityHistoryModel): View {


            val tv = View.inflate(activity, R.layout.hottagadapter, null) as TextView

            tv.text = s.keyword
            return tv
        }

        fun clear() {
            data.clear()
            notifyDataChanged()
        }

    }


    companion object {
        fun newInstance(id: String?): SelectDistrictFragment {
            val fragment = SelectDistrictFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }

}
