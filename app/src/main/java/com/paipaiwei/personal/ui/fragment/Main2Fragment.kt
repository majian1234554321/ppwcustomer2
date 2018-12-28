package com.paipaiwei.personal.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.yjhh.common.listener.LocationLatlng
import com.paipaiwei.personal.AmpLocationUtil


class Main2Fragment : BaseMainFragment(), NearbyView {
    override fun onNearbyData(model: NearByDataBean) {

        mAdapter?.setNewData(model.items)
    }


    //返回搜索结果
    fun setLocationLatlng(locationLatlng: LocationLatlng) {
        this.locationLatlng = locationLatlng
    }

    private var locationLatlng: LocationLatlng? = null


    var model1212: List<NearbyBean.CategoryModelsBean>? = null

    override fun onNearby(model: NearbyBean) {


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
            model1212 = model.categoryModels

        }


    }


    override fun onFault(errorMsg: String?) {

    }


    var present: NearbyPresent? = null
    val pageSize = 15
    var pageIndex = 0
    val list = ArrayList<NearByDataBean.ItemsBean>()

    var mAdapter: Main2Adapter? = null


    override fun getLayoutRes(): Int = R.layout.main2fragment

    override fun initView() {

        present = NearbyPresent(mActivity, this)
        present?.nearby()
        present?.nearbyData("", "", "", pageIndex, pageSize)

        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = Main2Adapter(list)
        recyclerView.adapter = mAdapter
        mAdapter?.setOnItemClickListener { adapter, view, position ->


            val intent = Intent(mActivity, GPSActivity::class.java)

            intent.putExtra("address", "")
            intent.putExtra("gotoLatitude", "")
            intent.putExtra("gotoLongitude", "")

            startActivity(intent)
        }





        AmpLocationUtil.getCurrentLocation {
            //针对location进行相关操作，如location.getCity()，无需验证location是否为null;
//            it.locationType// 定位类型
//            it.longitude //经度
//            it.latitude //纬度
//            location.getProvince()//省
//            location.getCity()//市
//            getDistrict()//区
//            getAddress()//地址

            val sb = StringBuffer();
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (it.errorCode == 0) {

                tv_title.text = it.aoiName //获取当前定位点的AOI信息


                Log.e("LocationServer", "获取当前定位结果来源:::" + it.getLocationType());
                Log.e("LocationServer", "获取纬度:::" + it.getLatitude());
                Log.e("LocationServer", "获取经度:::" + it.getLongitude());
                Log.e("LocationServer", "获取精度信息:::" + it.getAccuracy());
                Log.e("LocationServer", "获取地址:::" + it.getAddress());
                Log.e("LocationServer", "获取国家信息:::" + it.getCountry());
                Log.e("LocationServer", "获取省信息:::" + it.getProvince());
                Log.e("LocationServer", "获取城市信息:::" + it.getCity());
                Log.e("LocationServer", "获取城区信息:::" + it.getDistrict());
                Log.e("LocationServer", "获取街道信息:::" + it.getStreet());
                Log.e("LocationServer", "获取街道门牌号信息:::" + it.getStreetNum());
                Log.e("LocationServer", "获取城市编码:::" + it.getCityCode());
                Log.e("LocationServer", "获取地区编码:::" + it.getAdCode());
                Log.e("LocationServer", "获取当前定位点的AOI信息:::" + it.getAoiName());
                Log.e("LocationServer", "获取当前室内定位的建筑物Id:::" + it.getBuildingId());
                Log.e("LocationServer", "获取当前室内定位的楼层:::" + it.getFloor());
                Log.e("LocationServer", "获取GPS的当前状态:::" + it.getGpsAccuracyStatus());
                Log.e("LocationServer", "获取定位信息描述:::" + it.getLocationDetail());
                Log.e("LocationServer", "获取方向角信息:::" + it.getBearing());
                Log.e("LocationServer", "获取速度信息:::" + it.getSpeed() + "m/s");
                Log.e("LocationServer", "获取海拔高度信息:::" + it.getAltitude());
                Log.e("LocationServer", "获取当前位置的POI名称:::" + it.getPoiName());


                if (locationLatlng != null) {
                    locationLatlng?.locatinmLatlng(LatLng(it.latitude, it.longitude), it.address);
                }
            } else {
                //定位失败
                sb.append("定位失败" + "\n");
                sb.append("错误码:" + it.errorCode + "\n");
                sb.append("错误信息:" + it.errorInfo + "\n");
                sb.append("错误描述:" + it.locationDetail + "\n");
            }
            sb.append("***定位质量报告***").append("\n");
            // sb.append("* WIFI开关：").append(it.locationQualityReport.isWifiAble ? "开启" : "关闭").append("\n");
            // sb.append("* GPS状态：").append(getGPSStatusString(it.locationQualityReport.gpsStatus)).append("\n");
            sb.append("* GPS星数：").append(it.locationQualityReport.gpsSatellites).append("\n");
            sb.append("****************").append("\n");
            //定位之后的回调时间


            Log.i("LocationServer", sb.toString());


        }



        mTabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

                val lisCheckBox = ArrayList<CheckBox>()
                lisCheckBox.clear()
                ll.removeAllViews()
                if (model1212 != null && tab?.position != null && model1212!![tab?.position!!].nodes != null && model1212!![tab?.position!!].nodes.isNotEmpty()) {

                    ll.visibility = View.VISIBLE

                    for (i in 0 until model1212!![tab.position].nodes.size) {


                        val checkboxitem = View.inflate(mActivity, R.layout.checkboxitem, null) as CheckBox
                        checkboxitem.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
                        checkboxitem.setBackgroundResource(R.drawable.uncheckbox)
                        checkboxitem.setTextColor(Color.parseColor("#666666"))

                        val listA = model1212!![tab.position]
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
                            if (checkBox.isChecked) {
                                buttonView.setBackgroundResource(R.drawable.checkbox)
                                buttonView.setTextColor(Color.WHITE)
                                intList.add(index)
                                Toast.makeText(context, intList.size.toString(), Toast.LENGTH_SHORT).show()
                            } else {
                                intList.remove(index)
                                Toast.makeText(context, intList.size.toString(), Toast.LENGTH_SHORT).show()
                                buttonView.setBackgroundResource(R.drawable.uncheckbox)
                                buttonView.setTextColor(Color.parseColor("#666666"))
                            }

                        }
                    }


                } else {
                    ll.visibility = View.INVISIBLE
                }


            }

        })

    }


    // locationLatlng.searchResult(new LatLng(address.getLatLonPoint().getLatitude(), address.getLatLonPoint().getLongitude()));


}