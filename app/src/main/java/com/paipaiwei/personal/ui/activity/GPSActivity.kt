package com.paipaiwei.personal.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Point
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.paipaiwei.personal.R
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.view.AbsSheetDialog
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.common.view.BottomVerSheetDialog
import kotlinx.android.synthetic.main.activity_gps.*
import java.util.*
import com.paipaiwei.personal.R.id.mapView
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdate
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.*
import com.paipaiwei.personal.AmpLocationUtil

import com.amap.api.maps2d.model.Marker
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.BitmapDescriptorFactory

import com.amap.api.maps2d.model.MarkerOptions




class GPSActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {


            R.id.tv_back->{

                tv_back.visibility= View.INVISIBLE

                aMap?.addMarker(markerOption)

                aMap?.moveCamera(
                    CameraUpdateFactory.newCameraPosition(
                        CameraPosition(
                            LatLng(30.556118, 114.344792),
                            18f,
                            30f,
                            0f
                        )
                    )
                )

            }

            R.id.iv_back -> {
                finish()
            }

            R.id.iv_current -> {

                tv_back.visibility= View.VISIBLE


                AmpLocationUtil.getCurrentLocation {

                    val latLng =  LatLng(it.latitude, it.longitude)
                     val marker = aMap?.addMarker(MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"))

                     markerOption2 = MarkerOptions().zIndex(18f)
                    markerOption2?.position(latLng)

                    markerOption2?.draggable(true)//设置Marker可拖动
                    markerOption2?.icon(
                        BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory
                                .decodeResource(resources, R.drawable.icon_dingwei_gray)
                        )
                    )
                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果



                    aMap?.addMarker(markerOption2)


                    aMap?.moveCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition(
                                LatLng(it.latitude, it.longitude),
                                18f,
                                30f,
                                0f
                            )
                        )
                    )


                }

            }


            R.id.tv_gps -> {


                arrayOf("com.autonavi.minimap", "com.baidu.BaiduMap", "com.tencent.map").forEach {

                }





                AlertDialogFactory.createFactory(this).getBottomVerDialog(null,
                    Arrays.asList<BottomVerSheetDialog.Bean>(
                        BottomVerSheetDialog.Bean(
                            "高德地图",
                            R.color.lib_pub_color_text_main,
                            false
                        ),
                        BottomVerSheetDialog.Bean(
                            "百度地图",
                            R.color.lib_pub_color_text_main,
                            false
                        ),

                        BottomVerSheetDialog.Bean(
                            "腾讯地图",
                            R.color.lib_pub_color_text_main,
                            false
                        )

                    ),
                    object : AbsSheetDialog.OnItemClickListener<BottomVerSheetDialog.Bean> {
                        override fun onClick(dlg: Dialog, position: Int, item: BottomVerSheetDialog.Bean) {

                            when (position) {
                                0 -> {


                                    try {
                                        val act = "android.intent.action.VIEW";
                                        val dat =
                                            "androidamap://keywordNavi?sourceApplication=softname&keyword=$address &style=2";
                                        val pkg = "com.autonavi.minimap";
                                        val intent = Intent(act, Uri.parse(dat));
                                        intent.setPackage(pkg);
                                        startActivity(intent);
                                    } catch (e: Exception) {
                                        e.printStackTrace();
                                    }


                                }

                                1 -> {

                                    try {
                                        val i1 = Intent()
                                        i1.data = Uri.parse("baidumap://map/geocoder?src=openApiDemo&address=$address");
                                        startActivity(i1);
                                    } catch (e: Exception) {
                                        e.printStackTrace();
                                    }


                                }

                                2 -> {

                                    try {
                                        val pathUrl =
                                            "qqmap://map/routeplan?type=drive&to=$address&tocoord=$gotoLatitude,$gotoLongitude&policy=2&referer=myapp";
                                        val intent = Intent()
                                        intent.data = Uri.parse(pathUrl);
                                        startActivity(intent);
                                    } catch (e: Exception) {
                                        e.printStackTrace();
                                    }


                                }

                                else -> {


                                }
                            }

                        }

                        override fun onCancel(dlg: Dialog) {

                        }
                    })


            }

            else -> {
            }
        }
    }

    var aMap: AMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)

        address = intent.getStringExtra("address")
        gotoLatitude = intent.getStringExtra("gotoLatitude")

        gotoLongitude = intent.getStringExtra("gotoLongitude")


        arrayOf(iv_back, tv_gps, iv_current,tv_back).forEach {
            it.setOnClickListener(this)
        }



        mapView.onCreate(savedInstanceState)
        aMap = mapView.map
        aMap?.uiSettings?.isZoomControlsEnabled = false




        val latLng = LatLng(30.556118, 114.344792)
        // val marker = aMap?.addMarker(MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"))


         markerOption = MarkerOptions().zIndex(18f)
        markerOption?.position(latLng)

        markerOption?.draggable(true)//设置Marker可拖动
        markerOption?.icon(
            BitmapDescriptorFactory.fromBitmap(
                BitmapFactory
                    .decodeResource(resources, R.drawable.iv_mr_dingwei)
            )
        )
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果

        aMap?.addMarker(markerOption)



        AmpLocationUtil.getCurrentLocation {

            val latLng2 =  LatLng(it.latitude, it.longitude)

            markerOption2 = MarkerOptions().zIndex(18f)
            markerOption2?.position(latLng2)

            markerOption2?.draggable(true)//设置Marker可拖动
            markerOption2?.icon(
                BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory
                        .decodeResource(resources, R.drawable.icon_dingwei_gray)
                )
            )
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果

            aMap?.addMarker(markerOption2)


            aMap?.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition(
                        LatLng(it.latitude, it.longitude),
                        18f,
                        30f,
                        0f
                    )
                )
            )


        }
























        //addMarkerInScreenCenter()
        aMap?.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(
                     LatLng(30.556118, 114.344792),
                    18f,
                    30f,
                    0f
                )
            )
        )

    }


    var markerOption:MarkerOptions? = null
    var markerOption2:MarkerOptions? = null

    var address: String? = null
    var gotoLatitude: String? = null
    var gotoLongitude: String? = null

    var screenMarker: Marker? = null


    fun addMarkerInScreenCenter() {
        if (screenMarker == null) {
            screenMarker = aMap?.addMarker(
                MarkerOptions().zIndex(2f)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.iv_mr_dingwei))
            )
        }
        screenMarker?.setAnchor(0.5f, 1f);
        val latLng = aMap?.cameraPosition?.target;
        val screenPosition = aMap?.projection?.toScreenLocation(latLng)
        screenMarker?.setPositionByPixels(screenPosition!!.x, screenPosition.y);

    }


    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy();
    }


    override fun onResume() {
        super.onResume()
        mapView?.onResume();
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause();
    }

}
