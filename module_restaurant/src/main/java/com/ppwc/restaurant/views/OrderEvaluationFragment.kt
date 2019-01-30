package com.ppwc.restaurant.views

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.azhon.appupdate.utils.PermissionUtil.requestPermission
import com.google.gson.Gson
import com.ppwc.restaurant.R
import com.ppwc.restaurant.R.id.recyclerView
import com.ppwc.restaurant.adapter.OrderEvaluationAdapter
import com.ppwc.restaurant.adapter.OrderEvaluationAdapter2
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.iview.CommonView
import com.yjhh.common.model.PhotoBean
import com.yjhh.common.present.CommonPresent
import com.yjhh.common.utils.PhotoUtils
import com.yjhh.common.view.AbsSheetDialog
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.common.view.BottomVerSheetDialog
import com.yjhh.common.view.RatingBar
import com.yjhh.common.view.fragments.PhotoFragment
import com.zhihu.matisse.Matisse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.orderevaluationfragment.*
import top.zibin.luban.CompressionPredicate
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.util.*

class OrderEvaluationFragment : BaseFragment(), CommonView {


    override fun onSuccess(value: String?, flag: String?) {
        val gson = Gson()
        val model = gson.fromJson<PhotoBean>(value, PhotoBean::class.java)


        model.item.forEach {
            listsId.add(it.fileId)
        }
    }

    override fun onFault(errorMsg: String?, flag: String?) {

    }


    override fun getLayoutRes(): Int = R.layout.orderevaluationfragment


    val lists = ArrayList<String>()
    val listsId = java.util.ArrayList<String>()

    var mAdapter: OrderEvaluationAdapter? = null

    var present: CommonPresent? = null

    override fun initView() {


        present = CommonPresent(mActivity, this)

        val headView = View.inflate(mActivity, R.layout.orderheadview, null)


        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)
        lists.clear()


        mAdapter = OrderEvaluationAdapter(mActivity, lists, 9)
        recyclerView.adapter = mAdapter
        //  mAdapter?.addHeaderView(headView)


        mAdapter?.setOnItemClickListener(object : OrderEvaluationAdapter.OnRecycleViewItemClickListener {
            override fun onRecycleViewItemClick(view: View, position: Int, flag: Boolean) {
                if (flag) {
                    // start(PhotoFragment(lists[position]))

                    val dialog = PhotoFragment(lists, position)
                    dialog?.show(childFragmentManager, "TAG")
                } else {
                    photo()
                }

            }

        })

        mAdapter?.setOnItemChildClickListener(object : OrderEvaluationAdapter.OnRecycleViewItemChildClickListener {
            override fun onItemChildClick(view: View, position: Int) {

                lists.removeAt(position)
                listsId.removeAt(position)
                mAdapter?.notifyItemRemoved(position)
                mAdapter?.notifyDataSetChanged()
            }

        })

        tv_submit.setOnClickListener {

        }

    }


    private fun photo() {
        AlertDialogFactory.createFactory(mActivity).getBottomVerDialog(null,
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
                        mPublicPhotoPath = PhotoUtils.takePhote(this@OrderEvaluationFragment, mActivity, 10084)
                    } else {
                        Toast.makeText(mActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
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
                        PhotoUtils.selectPhoto(this@OrderEvaluationFragment, 9, 10085)
                    } else {
                        Toast.makeText(mActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
                    }
                }


        }


        compositeDisposable.add(disposable)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10085 && resultCode == BaseActivity.RESULT_OK) {

            val list = Matisse.obtainPathResult(data)


            val listFiles = ArrayList<File>()

            lists.addAll(list)
            list.forEach {
                val file = File(it)
                listFiles.add(file)
            }


            val dis = Flowable.just(listFiles).map {
                Luban.with(mActivity).load(list).get()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.i("onActivityResult", "${it.size}1212");
                    present?.UpLoadFiles(it)
                }

            compositeDisposable.add(dis)

            mAdapter?.notifyDataSetChanged()


        }
        //拍照
        if (requestCode == 10084 && resultCode == BaseActivity.RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return

            if (!TextUtils.isEmpty(mPublicPhotoPath)) {
                val uri = Uri.parse(mPublicPhotoPath)
                val path = uri.path
                val file = File(path)
                lists.add(file.path)
                val listFiles = ArrayList<File>()

                listFiles.add(file)
                while (lists.size > 3) {
                    lists.removeAt(lists.lastIndex)
                }

                mAdapter?.notifyDataSetChanged()




                Luban.with(mActivity)
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

                            present?.UpLoadFiles(listfilevalue)
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

    companion object {
        fun newInstance(ids: String?, payType: String?): OrderEvaluationFragment {
            val fragment = OrderEvaluationFragment()
            val bundle = Bundle()
            bundle.putString("ids", ids)
            bundle.putString("payType", payType)
            fragment.arguments = bundle
            return fragment
        }
    }


}