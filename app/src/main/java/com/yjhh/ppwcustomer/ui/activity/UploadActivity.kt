package com.yjhh.ppwcustomer.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionCommonService
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.LogUtils
import com.yjhh.ppwcustomer.R
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import okhttp3.MultipartBody
import java.lang.Exception
import com.zhihu.matisse.listener.OnCheckedListener
import com.zhihu.matisse.listener.OnSelectedListener
import com.yjhh.common.utils.Glide4Engine
import com.yjhh.common.utils.SharedPreferencesUtils
import com.zhihu.matisse.internal.entity.CaptureStrategy
import org.json.JSONArray
import org.json.JSONObject


class UploadActivity : BaseActivity() {

    val REQUEST_CODE_CHOOSE = 10086

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)


        Matisse.from(this)
            .choose(MimeType.ofAll(), false)
            .countable(true)
            .capture(true)
            .captureStrategy(
                CaptureStrategy(true, "com.yjhh.ppwcustomer.fileProvider")
            )
            .maxSelectable(9)
            //.addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
            .gridExpectedSize(
                resources.getDimensionPixelSize(R.dimen.grid_expected_size)
            )
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            .thumbnailScale(0.85f)
            //                                            .imageEngine(new GlideEngine())  // for glide-V3
            .imageEngine(Glide4Engine())    // for glide-V4
            .setOnSelectedListener(OnSelectedListener { uriList, pathList ->
                // DO SOMETHING IMMEDIATELY HERE
                Log.e("onSelected", "onSelected: pathList=$pathList")
            })
            .originalEnable(true)
            .maxOriginalSize(10)
            //.autoHideToolbarOnSingleTap(true)
            .setOnCheckedListener(OnCheckedListener { isChecked ->
                // DO SOMETHING IMMEDIATELY HERE
                Log.e("isChecked", "onCheck: isChecked=$isChecked")
            })
            .forResult(REQUEST_CODE_CHOOSE)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            val mSelected = Matisse.obtainResult(data)
            Matisse.obtainResult(data)
            val list = Matisse.obtainPathResult(data)
            Log.i("OnActivityResult ", list[0])

            val file = File(list[0])

            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body = MultipartBody.Part.createFormData("multipartFile", file.name, requestFile)

            val sessionId = SharedPreferencesUtils.getParam(this, "sessionId", "String")

            val dis = ApiServices.getInstance().create(SectionCommonService::class.java)
                .uploadFile(sessionId as String, body)
                .flatMap {
                    val response = it.string()
                    val value = JSONObject(response)
                    val map = ArrayMap<String, String>()
                    if (value.getBoolean("success")) {

                        val jsonObject = value.getString("data")
                        val jsonObject1 = JSONArray(jsonObject)
                        val js = jsonObject1.get(0) as JSONObject
                        val id = js.getString("id")
                        map["avaterId"] = id
                    }
                    ApiServices.getInstance().create(SectionUserService::class.java)
                        .setAvater(map)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    LogUtils.i("UploadActivity", it.string())
                }, {
                    LogUtils.i("UploadActivity", it.toString())
                }
                )
            compositeDisposable.add(dis)
        }


    }


}