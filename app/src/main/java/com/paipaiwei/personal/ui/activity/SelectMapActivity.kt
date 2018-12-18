package com.paipaiwei.personal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.SyncStateContract
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.model.*
import com.amap.api.maps2d.overlay.PoiOverlay
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.geocoder.*
import com.amap.api.services.help.Inputtips
import com.amap.api.services.help.InputtipsQuery
import com.amap.api.services.help.Tip
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.jakewharton.rxbinding2.widget.RxTextView

import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.KeywordMapAddressAdapter
import com.paipaiwei.personal.adapter.MapAddressAdapter
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseActivity.mListener

import kotlinx.android.synthetic.main.activity_select_map.*

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SelectMapActivity : BaseActivity(), AMapLocationListener, LocationSource, PoiSearch.OnPoiSearchListener,
    AMap.OnCameraChangeListener, GeocodeSearch.OnGeocodeSearchListener, Inputtips.InputtipsListener {
    override fun onGetInputtips(tipList: MutableList<Tip>?, rCode: Int) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
            rl2.visibility = View.GONE
            rl3.visibility = View.VISIBLE


            mAdapter2?.setNewData(tipList)

        } else {

        }
    }

    override fun onRegeocodeSearched(regeocodeResult: RegeocodeResult?, rCode: Int) {
        if (1000 == rCode) {
            val address = regeocodeResult?.regeocodeAddress
            val pois = address?.pois
        }
    }

    override fun onGeocodeSearched(p0: GeocodeResult?, p1: Int) {

    }

    override fun onCameraChangeFinish(cameraPosition: CameraPosition?) {


        // mCameraTextView.setText("onCameraChangeFinish:" + cameraPosition.toString())
        val visibleRegion = aMap?.projection?.visibleRegion // 获取可视区域、




        aMap?.clear()
        aMap?.addMarker(
            MarkerOptions().anchor(0.5f, 0.5f)
                .position(
                    cameraPosition?.target
                ).title("成都市").draggable(true)
        )

        if (cameraPosition?.target != null) {
            lp = cameraPosition?.target?.latitude?.let {
                cameraPosition?.target?.longitude?.let { it1 ->
                    LatLonPoint(
                        it,
                        it1
                    )
                }
            }
            doSearchQuery("", "商务写字楼", "")
        }


    }

    override fun onCameraChange(p0: CameraPosition?) {

    }

    override fun onPoiItemSearched(p0: PoiItem?, p1: Int) {

    }

    override fun onPoiSearched(result: PoiResult?, rCode: Int) {

        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result?.query != null) {// 搜索poi的结果


                poiResult = result
                val poiItems = poiResult?.pois// 取得第一页的poiitem数据，页数从数字0开始

                if (poiItems != null && poiItems.size > 0) {

                    mAdapter?.setNewData(poiItems)

                } else {
                    Log.i("SelectMapActivity", "没有数据")
                }

            }


        } else {
            Log.i("SelectMapActivity", "rCode$rCode")
        }

    }


    private var query: PoiSearch.Query? = null

    var lp: LatLonPoint? = null


    var poiResult: PoiResult? = null

    override fun deactivate() {
        mListener = null
    }

    override fun activate(listener: LocationSource.OnLocationChangedListener?) {
        mListener = listener
    }

    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.locationType;//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                amapLocation.latitude;//获取纬度
                amapLocation.longitude;//获取经度


                amapLocation.accuracy;//获取精度信息
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                val date = Date(amapLocation.time);
                df.format(date);//定位时间
                amapLocation.address;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.country;//国家信息
                amapLocation.province;//省信息
                amapLocation.city;//城市信息
                amapLocation.district;//城区信息
                amapLocation.street;//街道信息
                amapLocation.streetNum;//街道门牌号信息
                amapLocation.cityCode;//城市编码
                amapLocation.adCode;//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap?.moveCamera(CameraUpdateFactory.zoomTo(17f));
                    //将地图移动到定位点
                    aMap?.moveCamera(
                        CameraUpdateFactory.changeLatLng(
                            LatLng(
                                amapLocation.latitude,
                                amapLocation.longitude
                            )
                        )
                    )
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener?.onLocationChanged(amapLocation);
                    //添加图钉
                    //  aMap?.addMarker(getMarkerOptions(amapLocation));


                    aMap?.addMarker(
                        MarkerOptions().anchor(0.5f, 0.5f)
                            .position(
                                LatLng(
                                    amapLocation.latitude,
                                    amapLocation.longitude
                                )
                            ).title("成都市").draggable(true)
                    )


//                    val marker = aMap?.addMarker(
//                        MarkerOptions()
//                            .position(LatLng(amapLocation.latitude, amapLocation.longitude))
//                            .title("好好学习")
//                            .icon(
//                                BitmapDescriptorFactory.fromResource(R.drawable.bg_address)
//                            )
//                            .draggable(true)
//                    )
//                    marker?.showInfoWindow()// 设置默认显示一个infowinfow


                    //获取定位信息
                    val buffer = StringBuffer()
                    buffer.append(amapLocation.country + "" + amapLocation.province + "" + amapLocation.city + "" + amapLocation.province + "" + amapLocation.district + "" + amapLocation.street + "" + amapLocation.streetNum);
                    //Toast.makeText(applicationContext, buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e(
                    "AmapError", "location Error, ErrCode:"
                            + amapLocation.errorCode + ", errInfo:"
                            + amapLocation.errorInfo
                )

                Toast.makeText(applicationContext, "定位失败", Toast.LENGTH_LONG).show();
            }
        }

    }

    private var mLocationClient: AMapLocationClient? = null;//定位发起端
    private var mLocationOption: AMapLocationClientOption? = null;//定位参数
    private var mListener: LocationSource.OnLocationChangedListener? = null;//定位监听器

    private var aMap: AMap? = null
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private var isFirstLoc = true

    private var mAdapter: MapAddressAdapter? = null
    private var mAdapter2: KeywordMapAddressAdapter? = null

    val list = ArrayList<PoiItem>()

    val list2 = ArrayList<Tip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_map)

        mapView.onCreate(savedInstanceState)
        aMap = mapView.map

        val settings = aMap?.uiSettings

        aMap?.setLocationSource(this);

        settings?.isMyLocationButtonEnabled = true
        settings?.isZoomControlsEnabled = false

        aMap?.isMyLocationEnabled = true;
        aMap?.mapType = AMap.MAP_TYPE_NORMAL

        val myLocationStyle = MyLocationStyle()
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
        myLocationStyle.radiusFillColor(android.R.color.transparent)
        myLocationStyle.strokeColor(android.R.color.transparent)
        aMap?.setMyLocationStyle(myLocationStyle)

        aMap?.setOnCameraChangeListener(this)



        mLocationClient = AMapLocationClient(applicationContext)
        //设置定位回调监听
        mLocationClient?.setLocationListener(this);
        //初始化定位参数
        mLocationOption = AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption?.isNeedAddress = true
        //设置是否只定位一次,默认为false
        mLocationOption?.isOnceLocation = false
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption?.isWifiActiveScan = true
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption?.isMockEnable = false;
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption?.interval = 2000
        //给定位客户端对象设置定位参数
        mLocationClient?.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient?.startLocation()


        //  doSearchQuery()


        val dis = RxTextView.textChanges(et_search).subscribe {
            if (!TextUtils.isEmpty(et_search.text.toString())) {
                keyword(et_search.text.toString(), tv_location.text.toString())
            }
        }


        rv_search.layoutManager = LinearLayoutManager(this@SelectMapActivity)
        mAdapter2 = KeywordMapAddressAdapter(list2)
        rv_search.adapter = mAdapter2



        recyclerView.layoutManager = LinearLayoutManager(this@SelectMapActivity)
        mAdapter = MapAddressAdapter(list)
        recyclerView.adapter = mAdapter

    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }



    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }


    fun doSearchQuery2() {
        val geocodeSearch = GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        val regeocodeQuery = RegeocodeQuery(lp, 5000f, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(regeocodeQuery)
    }


    fun keyword(value1: String, value3: String) {
        val inputquery = InputtipsQuery(value1, value3)
        val inputTips = Inputtips(this@SelectMapActivity, inputquery)
        inputTips.setInputtipsListener(this)
        inputTips.requestInputtipsAsyn()
    }


    fun doSearchQuery(value1: String, value2: String, value3: String) {
        val currentPage = 0;
        val query = PoiSearch.Query(value1, value2, value3) //// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.pageSize = 20;// 设置每页最多返回多少条poiitem
        query.pageNum = currentPage;// 设置查第一页

        val poiSearch = PoiSearch(this@SelectMapActivity, query)
        poiSearch.setOnPoiSearchListener(this)
        poiSearch.bound = PoiSearch.SearchBound(lp, 5000, true)//// 设置搜索区域为以lp点为圆心，其周围5000米范围
        poiSearch.searchPOIAsyn();// 异步搜索

    }
}
