package com.yjhh.ppwcustomer.base;

import android.app.Activity;
import android.content.pm.PackageManager;


import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yjhh.ppwcustomer.common.ActivityCollector;
import com.yjhh.ppwcustomer.common.utils.SystemBarUtil;
import com.yjhh.ppwcustomer.listener.PermissionListener;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.disposables.DisposableContainer;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public static PermissionListener mListener;
    public static final int REQUEST_CODE = 1;

    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SystemBarUtil.immersiveStatusBar(this, 0.0f);
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        // Disposable
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public static void requestRuntimePermission(String[] permissions,
                                                PermissionListener listener) {
        // 获取栈顶 Activity
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null)
            return;
        mListener = listener;
        // 需要请求的权限列表
        List<String> requestPermisssionList = new ArrayList<>();
        // 检查权限  是否已被授权
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(topActivity, permission)
                    != PackageManager.PERMISSION_GRANTED)
                // 未授权时添加该权限
                requestPermisssionList.add(permission);
        }

        if (requestPermisssionList.isEmpty())
            // 所有权限已经被授权过 回调 Listener onGranted 方法 已授权
            listener.onGranted();
        else
            // 进行请求权限操作
            ActivityCompat.requestPermissions(topActivity,
                    requestPermisssionList.toArray(new String[requestPermisssionList.size()]),
                    REQUEST_CODE);

    }

    // 请求权限的回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE: {

                List<String> deniedPermissionList = new ArrayList<>();
                // 检查返回授权结果不为空
                if (grantResults.length > 0) {
                    // 判断授权结果
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        if (result != PackageManager.PERMISSION_GRANTED)
                            // 保存被用户拒绝的权限
                            deniedPermissionList.add(permissions[i]);
                    }
                    if (deniedPermissionList.isEmpty())
                        // 都被授权  回调 Listener onGranted 方法 已授权
                        mListener.onGranted();
                    else
                        // 有权限被拒绝 回调 Listner onDeynied 方法
                        mListener.onDenied(deniedPermissionList);
                }
                break;
            }
            default:
                break;
        }
    }


    protected boolean isTrans;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar(boolean isTransparent) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (isTransparent) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);


    }


}
