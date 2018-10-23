package com.yjhh.ppwcustomer.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.yjhh.common.base.BaseActivity;
import com.yjhh.ppwcustomer.R;

import com.yjhh.common.utils.ActivityCollector;
import com.yjhh.common.utils.LogUtils;
import com.yjhh.common.utils.RxCountDown;
import com.yjhh.ppwcustomer.common.utils.SharedPreferencesUtils;
import com.yjhh.common.listener.PermissionListener;
import io.reactivex.observers.DisposableObserver;

import java.util.List;

@Route(path = "/splashactivity/splash")
public class SplashActivity extends BaseActivity {

    public String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private TextView tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tips = findViewById(R.id.text);

        requestRuntimePermission(permissions, new PermissionListener() {
            @Override
            public void onGranted() {
                nextStep();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (int i = 0; i < deniedPermission.size(); i++) {
                    Log.i("TAG", deniedPermission.get(i));
                }
                nextStep();

            }
        });
    }

    public void nextStep() {
        RxCountDown.countdown(3)
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer t) {
                        LogUtils.i("TAG", "onGranted" + t + Thread.currentThread().getName());
                       // tips.setText(String.valueOf(t));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        if (!TextUtils.isEmpty((String) SharedPreferencesUtils.getParam(SplashActivity.this, "currentVersionName", ""))) {
                            ActivityCollector.JumpActivity(SplashActivity.this, MainActivity.class);
                        } else {
                            try {
                                SharedPreferencesUtils.setParam(SplashActivity.this, "currentVersionName", getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                                SharedPreferencesUtils.setParam(SplashActivity.this, "currentVersionName", "1.0.0");
                            }
                            ActivityCollector.JumpActivity(SplashActivity.this, GuideActivity.class);
                        }

                        finish();
                    }
                });

    }


    protected void setStatusBar() {

        View decorView = getWindow().getDecorView();
        decorView.setBackgroundResource(R.mipmap.timg);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


}
