package com.paipaiwei.personal.ui.activity

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.google.gson.Gson
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.base.BaseActivity

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.bean.PhotoBean
import com.paipaiwei.personal.bean.UserinfoBean
import com.paipaiwei.personal.common.utils.TimeUtil
import com.yjhh.common.present.CommonPresent
import com.paipaiwei.personal.present.SectionUserPresent


import com.yjhh.common.iview.CommonView
import com.paipaiwei.personal.view.UserInfoView
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.utils.*
import com.yjhh.common.view.AbsSheetDialog
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.common.view.BottomVerSheetDialog
import com.zhihu.matisse.Matisse

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.userinfofragment.*

import top.zibin.luban.CompressionPredicate
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File

import java.text.SimpleDateFormat
import java.util.*

class UserInfoActivity : BaseActivity(), View.OnClickListener, UserInfoView, CommonView {
    override fun onSuccess(value: String?) {
        val gson = Gson()
        val model = gson.fromJson<PhotoBean>(value, PhotoBean::class.java)

        val map = ArrayMap<String, String>()
        map["avatarId"] = model.item[0].fileId

        ApiServices.getInstance().create(SectionUserService::class.java).setAvatar(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(this@UserInfoActivity) {
                override fun processValue(response: String?) {
                    ImageLoaderUtils.load(
                        this@UserInfoActivity,
                        iv_image,
                        model.item[0].fileUrl,
                        R.drawable.icon_login_touxiang,
                        R.drawable.icon_login_touxiang, 0
                    )

                    Log.i("setAvatar",response)
                }

                override fun onFault(message: String) {
                    Log.i("setAvatar",message)
                }

            })

    }

    override fun onSuccess(main1bean: UserinfoBean) {
        iev_nickName.setTextContent(main1bean.nickName)
        ImageLoaderUtils.load(
            this@UserInfoActivity,
            iv_image,
            main1bean.avatarUrl,
            R.drawable.icon_login_touxiang,
            R.drawable.icon_login_touxiang, 0
        )

        iev_birthday.setTextContent(TimeUtil.stampToDate2(main1bean.birthday))
    }

    override fun onFault(errorMsg: String?) {

    }


    protected override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userinfofragment)
        initView()
    }


    var present: SectionUserPresent? = null

    var present2: CommonPresent? = null

    fun initView() {

        val array = arrayOf(iev_nickName, iev_birthday, iev_address, rl_head)
        array.forEach {
            it.setOnClickListener(this)
        }

        initTimePicker()

        present = SectionUserPresent(context, this)
        present?.getUserinfo()


        present2 = CommonPresent(this@UserInfoActivity, this)


    }


    private fun getTimeValue(date: Date): String {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.time)
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }


    var pvTime: TimePickerView? = null

    private fun initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, v ->
            // Toast.makeText(this, getTime(date), Toast.LENGTH_SHORT).show()
            Log.i("pvTime", "onTimeSelect")
            iev_birthday.setTextContent(getTimeValue(date))

            present?.setBirthday(TimeUtil.dateToStamp(getTimeValue(date)))

        })
            .setTimeSelectChangeListener { Log.i("pvTime", "onTimeSelectChanged") }
            .setType(booleanArrayOf(true, true, true, false, false, false))
            .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
            .build()

        val mDialog = pvTime?.getDialog()
        if (mDialog != null) {

            val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
            )

            params.leftMargin = 0
            params.rightMargin = 0
            pvTime?.dialogContainerLayout?.layoutParams = params

            val dialogWindow = mDialog.window
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM)//改成Bottom,底部显示
            }


        }
    }


    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        when (v?.id) {
            R.id.iev_nickName -> {
                val intent =
                    Intent(this, SetNickNameActivity::class.java).putExtra("title", iev_nickName.getTextContent())
                startActivityForResult(intent, 10086)
            }
            R.id.iev_birthday -> {
                pvTime?.show(v)
            }
            R.id.iev_address -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MyAddressFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            else -> {

                photo()

            }
        }
    }


    private fun photo() {
        AlertDialogFactory.createFactory(this).getBottomVerDialog(null,
            Arrays.asList<BottomVerSheetDialog.Bean>(
                BottomVerSheetDialog.Bean(
                    "拍照",
                    R.color.lib_pub_color_text_main,
                    false
                ),
                BottomVerSheetDialog.Bean(
                    "从手机相册选择",
                    R.color.lib_pub_color_text_main,
                    false
                )
            ),
            object : AbsSheetDialog.OnItemClickListener<BottomVerSheetDialog.Bean> {
                override fun onClick(dlg: Dialog, position: Int, item: BottomVerSheetDialog.Bean) {

                    when (position) {
                        0 -> {
                            requestPermission("photo")
                        }
                        else -> {

                            requestPermission("select")
                        }
                    }

                }

                override fun onCancel(dlg: Dialog) {

                }
            })
    }


    var mPublicPhotoPath: String? = null


    private fun requestPermission(string: String) {


        var disposable: Disposable? = null

        if ("photo" == string) {

            disposable = RxPermissions(this)
                .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe {
                    if (it) {
                        mPublicPhotoPath = PhotoUtils.takePhote(this@UserInfoActivity, 10084)
                    } else {
                        Toast.makeText(this@UserInfoActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
                    }
                }

        } else {

            disposable = RxPermissions(this)
                .request(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe {
                    if (it) {
                        PhotoUtils.selectPhoto(this@UserInfoActivity, 1, 10085)
                    } else {
                        Toast.makeText(this@UserInfoActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
                    }
                }


        }


        compositeDisposable.add(disposable)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        val title = data?.getStringExtra("title")

        if (title != null) {
            iev_nickName.setTextContent(title)

            SharedPreferencesUtils.setParam(this, "nickName", title)

            val bean = LoginBean(title, true)

            RxBus.default.post(bean)
        }


        if (requestCode == 10085 && resultCode == BaseActivity.RESULT_OK) {

            val list = Matisse.obtainPathResult(data)

            val listFiles = ArrayList<File>()

            list.forEach {
                val file = File(it)
                listFiles.add(file)
            }


            val dis = Flowable.just(listFiles).map {
                Luban.with(this@UserInfoActivity).load(list).get()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.i("onActivityResult", "${it.size}1212");
                    present2?.UpLoadFiles(it)
                }

            compositeDisposable.add(dis)


        }
        //拍照
        if (requestCode == 10084 && resultCode == BaseActivity.RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return

            if (!TextUtils.isEmpty(mPublicPhotoPath)) {
                val uri = Uri.parse(mPublicPhotoPath)
                val path = uri.path
                val file = File(path)


                Luban.with(this@UserInfoActivity)
                    .load(file)
                    .ignoreBy(100)
                    .filter(object : CompressionPredicate {
                        override fun apply(path: String?): Boolean {
                            return !(TextUtils.isEmpty(path) || path?.toLowerCase()?.endsWith(".gif")!!)
                        }

                    }).setCompressListener(object : OnCompressListener {
                        override fun onSuccess(filevalue: File?) {
                            // Log.i("onActivityResult", filevalue?.length().toString())

                            val listfilevalue = ArrayList<File?>();
                            listfilevalue.add(filevalue)

                            present2?.UpLoadFiles(listfilevalue)
                        }

                        override fun onError(e: Throwable?) {
                            Log.i("onActivityResult", e.toString())
                        }

                        override fun onStart() {
                            Log.i("onActivityResult", "SSSSSS")
                        }

                    }).launch()


            } else {

            }


        }


    }


}