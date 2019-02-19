package com.paipaiwei.personal.ui.fragment

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2Adapter
import com.paipaiwei.personal.bean.NearByDataBean
import com.paipaiwei.personal.bean.NearbyBean
import com.paipaiwei.personal.present.NearbyPresent
import com.paipaiwei.personal.ui.activity.GPSActivity
import com.paipaiwei.personal.view.NearbyView
import com.yjhh.common.utils.GlideLoader

import kotlinx.android.synthetic.main.main2fragment.*

import com.amap.api.maps2d.model.LatLng
import com.d.lib.xrv.listener.AppBarStateChangeListener
import com.google.android.material.appbar.AppBarLayout
import com.paipaiwei.personal.bean.Main1FootBean
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.common.Constants
import com.yjhh.common.listener.LocationLatlng
import com.yjhh.common.utils.AmpLocationUtil
import com.yjhh.common.utils.permission.PermissionUtils
import com.yjhh.common.view.AlertDialogFactory
import java.lang.StringBuilder


class Main2Fragment : BaseMainFragment(), NearbyView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rl_search -> {
                val postcard = ARouter.getInstance().build("/SearchActivity/Search")
                LogisticsCenter.completion(postcard);
                val destination = postcard.destination
                val intent = Intent(getContext(), destination)
                startActivityForResult(intent, 10086)
            }
            else -> {
            }
        }
    }

    override fun onNearbyData(model: NearByDataBean, flag: String) {

        if ("refresh" == flag) {
            mAdapter?.setNewData(model.items)



            if (pageIndex == 0) {

                if (model.items.size == pageSize) {
                    mAdapter?.loadMoreComplete()
                } else {
                    mAdapter?.loadMoreEnd()

                    val view = View.inflate(mActivity, R.layout.emptyview, null)
                    view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                    mAdapter?.emptyView = view
                }

            } else {

            }


        } else {
            if (model.items != null && model.items.size == pageSize) {
                mAdapter?.loadMoreComplete()
            } else {
                mAdapter?.loadMoreEnd()
            }

            mAdapter?.addData(model.items)
        }


    }


    //返回搜索结果
    fun setLocationLatlng(locationLatlng: LocationLatlng) {
        this.locationLatlng = locationLatlng
    }

    private var locationLatlng: LocationLatlng? = null


    var modelTAb: List<NearbyBean.CategoryModelsBean>? = null

    override fun onNearby(model: NearbyBean) {
        rl0.visibility = View.GONE
        cl0.visibility = View.VISIBLE

        if (model.bannerModels != null) {
            val listImageBanner = ArrayList<String>()
            model.bannerModels.forEach {
                listImageBanner.add(it.imageUrl)
            }

            banner.setImages(listImageBanner)
                .setImageLoader(GlideLoader())
                .setDelayTime(10000)
                .start()
        }





        if (model.categoryModels != null) {
            for (i in 0 until model.categoryModels.size) {
                mTabLayout1.addTab(mTabLayout1.newTab().setText(model.categoryModels[i].title))
            }
            modelTAb = model.categoryModels

        }


    }


    override fun onFault(errorMsg: String?) {
        rl0.visibility = View.VISIBLE
        cl0.visibility = View.GONE
    }


    var present: NearbyPresent? = null
    val pageSize = 15
    var pageIndex = 0
    val list = ArrayList<NearByDataBean.ItemsBean>()

    var mAdapter: Main2Adapter? = null


    override fun getLayoutRes(): Int = R.layout.main2fragment

    override fun initView() {


        rl0.setOnClickListener {
            present?.nearby()
            present?.nearbyData(code, Constants.LONGITUDE, Constants.LATITUDE, pageIndex, pageSize, "refresh")
        }

        present = NearbyPresent(mActivity, this)
        present?.nearby()
        present?.nearbyData(code, Constants.LONGITUDE, Constants.LATITUDE, pageIndex, pageSize, "refresh")

        arrayOf(rl_search).forEach {
            it.setOnClickListener(this)
        }

        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = Main2Adapter(list)
        recyclerView.adapter = mAdapter
        mAdapter?.setOnLoadMoreListener({
            loadMore(code)
        }, recyclerView)





        mAdapter?.setOnItemClickListener { adapter, view, position ->

//            val intent = Intent(mActivity, GPSActivity::class.java)
//
//            intent.putExtra("address", "")
//            intent.putExtra("gotoLatitude", "")
//            intent.putExtra("gotoLongitude", "")
//
//            startActivity(intent)



            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantHomeFragment")
                .withString("id", (adapter.data[position] as NearByDataBean.ItemsBean).id)
                .navigation()


        }


        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.EXPANDED -> //展开状态
                        iv_search.visibility = View.GONE
                    State.COLLAPSED -> //折叠状态

                        iv_search.visibility = View.VISIBLE
                    else -> //中间状态
                        iv_search.visibility = View.GONE
                }
            }
        });


        if (RxPermissions(this).isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            AmpLocationUtil.init(mActivity)
            AmpLocationUtil.getCurrentLocation { am ->


                if (am.errorCode == 0) {
                    if (tv_title != null)
                        tv_title.text = am.aoiName //获取当前定位点的AOI信息

                    if (locationLatlng != null) {
                        locationLatlng?.locatinmLatlng(LatLng(am.latitude, am.longitude), am.address)
                    }
                    Log.i("LocationServer", "${am.poiName}定位成功定位信息${am.aoiName}")
                } else {
                    //定位失败
                    tv_title.text = "XXXXX"
                    Log.i("LocationServer", "定位失败")
                }


            }
        } else {
            AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                "定位服务未开启",
                "请在设置定位服务中开启定位服务，需要知道您的位置才能提供更好的服务~",
                "去开启", "暂不",
                { dlg, v ->
                    PermissionUtils.toPermissionSetting(mActivity);
                },
                { dlg, v ->

                })

        }







        mTabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.position != 0) {
                    appBarLayout.setExpanded(false)
                    ll.visibility = View.VISIBLE
                } else {
                    ll.visibility = View.GONE
                }
                val lisCheckBox = ArrayList<RadioButton>()
                lisCheckBox.clear()
                ll.removeAllViews()
                if (modelTAb != null && tab?.position != null && modelTAb!![tab?.position!!].nodes != null && modelTAb!![tab?.position!!].nodes.isNotEmpty()) {
                    refresh(modelTAb?.get(tab?.position!!)?.code)
                    ll.visibility = View.VISIBLE

                    for (i in 0 until modelTAb!![tab.position].nodes.size) {

                        val checkboxitem = View.inflate(mActivity, R.layout.checkboxitem2, null) as RadioButton
                        checkboxitem.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
                        checkboxitem.setBackgroundResource(R.drawable.uncheckbox)

                        checkboxitem.setTextColor(Color.parseColor("#666666"))

                        val listA = modelTAb!![tab.position]
                        val listB = listA.nodes[i]
                        checkboxitem.text = listB.title
                        checkboxitem.isChecked = false


                        val lp = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        lp.rightMargin = 12

                        ll.addView(checkboxitem, lp)
                        lisCheckBox.add(checkboxitem)

                    }


                    val intList = ArrayList<Int>()


                    lisCheckBox.forEachIndexed { index, checkBox ->

                        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                            val sb = StringBuilder()

                            if (checkBox.isChecked) {
                                buttonView.setBackgroundResource(R.drawable.checkbox)
                                buttonView.setTextColor(Color.WHITE)

                                intList.add(index)

                                intList.forEach {
                                    sb.append(modelTAb!![tab?.position].nodes[it].title).append("\t")
                                }


                                if(modelTAb!![tab.position]?.nodes[index].code!=null){
                                    code = modelTAb!![tab.position]?.nodes[index].code
                                }



                                refresh(code)


//                                Toast.makeText(
//                                    context,
//                                    "点击了${modelTAb!![tab?.position].title} 目录下的 ${sb.toString()}",
//                                    Toast.LENGTH_SHORT
//                                ).show()

                            } else {

                                intList.remove(index)
                                intList.forEach {
                                    sb.append(modelTAb!![tab?.position].nodes[it].title).append("\t")
                                }
                                buttonView.setBackgroundResource(R.drawable.uncheckbox)
                                buttonView.setTextColor(Color.parseColor("#666666"))

//                                Toast.makeText(
//                                    context,
//                                    "点击了${modelTAb!![tab?.position].title} 目录下的 $sb",
//                                    Toast.LENGTH_SHORT
//                                ).show()
                            }

                        }
                    }


                } else {
                    ll.visibility = View.GONE
                }


            }

        })



        rl_search.setOnClickListener {
            ARouter.getInstance()
                .build("/SearchActivity/Search")
                .navigation()
        }

        iv_search.setOnClickListener {
            ARouter.getInstance()
                .build("/SearchActivity/Search")
                .navigation()
        }
    }


    var code = ""


    private fun refresh(code: String?) {
        pageIndex = 0
        present?.nearbyData(code, Constants.LONGITUDE, Constants.LATITUDE, pageIndex, pageSize, "refresh")
    }

    private fun loadMore(code: String) {
        pageIndex++
        present?.nearbyData(code, Constants.LONGITUDE, Constants.LATITUDE, pageIndex, pageSize, "loadMore")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        when (requestCode) {
            10086 -> {
                val keyWord = data?.getStringExtra("keyWord")
                //  Toast.makeText(mActivity, keyWord, Toast.LENGTH_SHORT).show()
                Log.i("Main2Fragment", "232323")

            }
            else -> {
            }
        }

    }


    // locationLatlng.searchResult(new LatLng(address.getLatLonPoint().getLatitude(), address.getLatLonPoint().getLongitude()));


}