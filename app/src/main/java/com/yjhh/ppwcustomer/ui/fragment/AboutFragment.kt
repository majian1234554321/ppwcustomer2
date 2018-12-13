package com.yjhh.ppwcustomer.ui.fragment

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.ppwcustomer.apis.SectionUselessService
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.PhoneUtils
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.ppwcustomer.R

import com.yjhh.ppwcustomer.R.id.recyclerView
import com.yjhh.ppwcustomer.R.id.tv_introduce
import com.yjhh.ppwcustomer.adapter.AboutAdapter
import com.yjhh.ppwcustomer.bean.AboutBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.aboutfragment.*

class AboutFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.aboutfragment


    var list = ArrayList<AboutBean.FunctionsBean>()
    var mAdapter: AboutAdapter? = null

    override fun initView() {


        recyclerView.layoutManager = LinearLayoutManager(mActivity)




        mAdapter = AboutAdapter(list)


        recyclerView.adapter = mAdapter

        ApiServices.getInstance().create(SectionUselessService::class.java)
            .about()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                    Log.i("AboutFragment", response)

                    val model = Gson().fromJson<AboutBean>(response, AboutBean::class.java)
                    list.addAll(model.functions)
                    mAdapter?.setNewData(model.functions)


                    tv_introduce.text = "\t\t\t\t${model.content}"


                }

                override fun onFault(message: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.i("AboutFragment", message)
                }

            })

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            when (list[position].name) {
                "协议中心" -> {
                    start(BackViewFragment.newInstance(list[position].linkUrl))
                }
                "帮助中心" -> {
                    start(BackViewFragment.newInstance(list[position].linkUrl))
                }
                "意见反馈" -> {
                    start(A_FeedBackFragment())
                }
                "客服电话" -> {
                    val disposable = RxPermissions(this)
                        .request(Manifest.permission.CALL_PHONE)
                        .subscribe {
                            if (it) {

                                AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                                    "拨打服务热线",
                                    null,
                                    "确定", "取消",
                                    { dlg, v ->
                                        PhoneUtils.callPhone(mActivity, list.get(position).linkUrl.split("tels://")[1])
                                    }, { dlg, v ->
                                    })


                            } else {
                                Toast.makeText(mActivity, "请前往设置中心开启拨打电话", Toast.LENGTH_SHORT).show()
                            }
                        }

                    compositeDisposable.add(disposable)
                }
                "加盟合作" -> {
                    start(BackViewFragment.newInstance(list[position].linkUrl))
                }
                else -> {
                }
            }
        }


    }


}
