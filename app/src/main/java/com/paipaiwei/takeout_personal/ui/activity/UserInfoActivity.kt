package com.paipaiwei.takeout_personal.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
import com.bumptech.glide.Glide
import com.tencent.bugly.beta.Beta.storageDir
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionCommonService
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.Glide4Engine
import com.yjhh.common.utils.LogUtils
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils

import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.bean.LoginBean
import com.paipaiwei.takeout_personal.bean.UserinfoBean
import com.paipaiwei.takeout_personal.common.utils.TimeUtil
import com.paipaiwei.takeout_personal.present.SectionUserPresent

import com.paipaiwei.takeout_personal.ui.customview.RxDialogChooseImage
import com.paipaiwei.takeout_personal.view.UserInfoView
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.listener.OnCheckedListener
import com.zhihu.matisse.listener.OnSelectedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.userinfofragment.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class UserInfoActivity : BaseActivity(), View.OnClickListener, UserInfoView {
    override fun onSuccess(main1bean: UserinfoBean) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        iev_nickName.setTextContent(main1bean.nickName)
        Glide.with(this).load(main1bean.avatarUrl).into(iv_image)


        iev_birthday.setTextContent(TimeUtil.stampToDate2(main1bean.birthday))
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        if(){
//        ( context as Activity).startActivity(Intent(context,LoginActivity::class.java))}
    }


    var dialogChooseImage: RxDialogChooseImage? = null


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userinfofragment)
        initView()
    }


    var present: SectionUserPresent? = null
    fun initView() {

        val array = arrayOf(iev_nickName, iev_birthday, iev_address, rl_head)
        array.forEach {
            it.setOnClickListener(this)
        }

        initTimePicker()

        present = SectionUserPresent(context, this)
        present?.getUserinfo()



        dialogChooseImage = RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE)


        dialogChooseImage?.mTvCamera?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialogChooseImage?.dismiss()
                //获取拍照的权限
                showTakePicture()
            }

        })

        dialogChooseImage?.getFromFileView()?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                dialogChooseImage?.dismiss()
                Matisse.from(this@UserInfoActivity)
                    .choose(MimeType.ofAll(), false)
                    .countable(true)
                    .capture(true)
                    .captureStrategy(
                        CaptureStrategy(true, "com.paipaiwei.takeout_personal.fileProvider")
                    )
                    .maxSelectable(1)
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
                    .forResult(10085)
            }

        })
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val title = data?.getStringExtra("title")

        if (title != null) {
            iev_nickName.setTextContent(title)

            SharedPreferencesUtils.setParam(this, "nickName", title)

            val bean = LoginBean(title, true)

            RxBus.default.post(bean)
        }



        if (requestCode == 10085 && resultCode == RESULT_OK) {
            val mSelected = Matisse.obtainResult(data)
            Matisse.obtainResult(data)
            val list = Matisse.obtainPathResult(data)
            Log.i("OnActivityResult ", list[0])

            val file = File(list[0])

            val dis = present?.setAvatarUpLoadJoin(file)

            if (dis != null) {
                compositeDisposable.add(dis)
            }
        }


        //拍照
        if (requestCode == 10084 && resultCode == RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return
            val uri = Uri.parse(mPublicPhotoPath)
            val path = uri.getPath()
            val file = File(path)
            val dis = present?.setAvatarUpLoadJoin(file)
            if (dis != null) {
                compositeDisposable.add(dis)
            }
        }


    }


    //获取拍照的权限

    //获取拍照的权限
    private fun showTakePicture() {
        //        判断手机版本,如果低于6.0 则不用申请权限,直接拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//7.0及以上
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA
                    )
                ) {
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 1
                    )
                }
            } else {
                startTake()
            }
        } else {
            startTake()
        }

    }


    private fun startTake() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //判断是否有相机应用
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            //创建临时图片文件
            var photoFile: File? = null
            try {
                photoFile = createPublicImageFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            //设置Action为拍照
            if (photoFile != null) {
                takePictureIntent.action = MediaStore.ACTION_IMAGE_CAPTURE
                //这里加入flag
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val photoURI: Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上   com.paipaiwei.takeout_personal.fileProvider
                    photoURI = FileProvider.getUriForFile(this, "$packageName.camera.fileProvider", photoFile)
                } else {
                    photoURI = Uri.fromFile(photoFile)
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, 10084)

            }
        }
        //将照片添加到相册中
        if (mPublicPhotoPath != null) {
            galleryAddPic(mPublicPhotoPath!!, this)
        }

    }


    /**
     * 将照片添加到相册中
     */
    fun galleryAddPic(mPublicPhotoPath: String, context: Context) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(mPublicPhotoPath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        context.sendBroadcast(mediaScanIntent)
    }

    /**
     * 创建临时图片文件
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun createPublicImageFile(): File {
        var imageFile: File
        var storagePath: String
        var storageDir: File
        val timeStamp =
            getTime(Date(), "yyyyMMdd_HHmmss", Locale.CHINA)
        //文件路径是公共的DCIM目录下的/camerademo目录
        storagePath = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            .getAbsolutePath() + File.separator + "camerademo"

        storageDir = File(storagePath)
        storageDir.mkdirs()
        imageFile = File.createTempFile(timeStamp, ".jpg", storageDir);
        mPublicPhotoPath = imageFile.getAbsolutePath()

        return imageFile

    }


    private var mPublicPhotoPath: String? = null

    /**
     * 判断sdcard是否被挂载
     * @return
     */
    fun hasSdcard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }


    private fun getTime(date: Date, mode: String, locale: Locale): String {
        val format = SimpleDateFormat(mode, locale)
        return format.format(date)
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

                dialogChooseImage?.show()

            }
        }
    }


    //权限申请的回调
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    if (i == 0) {
                        startTake()
                    }
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}