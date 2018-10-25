package com.yjhh.common.api;

import android.content.Context;
import android.util.Log;
import com.yjhh.common.base.WaitProgressDialog;
import com.yjhh.common.listener.OnSuccessAndFaultListener;
import com.yjhh.common.listener.ProgressCancelListener;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

import javax.net.ssl.SSLHandshakeException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class OnSuccessAndFaultSub<T> extends DisposableObserver<T> implements ProgressCancelListener {
    /**
     * 是否需要显示默认Loading
     */
    private boolean showProgress = true;
    private OnSuccessAndFaultListener mOnSuccessAndFaultListener;

    private Context context;
    private WaitProgressDialog progressDialog;

    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    }

    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     * @param context                    上下文
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener, Context context) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        this.context = context;
        //   progressDialog = new WaitProgressDialog(context, this);
    }

    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     * @param context                    上下文
     * @param showProgress               是否需要显示默认Loading
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener, Context context, boolean showProgress) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        this.context = context;
        // progressDialog = new WaitProgressDialog(context, this);
        this.showProgress = showProgress;
    }

    private void showProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.dismiss();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
        progressDialog = null;
    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        try {

            if (e instanceof SocketTimeoutException) {//请求超时
            } else if (e instanceof ConnectException) {//网络连接超时
                mOnSuccessAndFaultListener.onFault("网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                mOnSuccessAndFaultListener.onFault("安全证书异常");
            } else if (e instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    mOnSuccessAndFaultListener.onFault("网络异常，请检查您的网络状态");
                } else if (code == 404) {
                    mOnSuccessAndFaultListener.onFault("请求的地址不存在");
                } else {
                    mOnSuccessAndFaultListener.onFault("请求失败");
                }
            } else if (e instanceof UnknownHostException) {//域名解析失败
                mOnSuccessAndFaultListener.onFault("域名解析失败");
            } else {
                mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
            dismissProgressDialog();
            progressDialog = null;

        }

    }

    /**
     * 当result等于1回调给调用者，否则自动显示错误信息，若错误信息为401跳转登录页面。
     * ResponseBody  body = response.body();//获取响应体
     * InputStream inputStream = body.byteStream();//获取输入流
     * byte[] bytes = body.bytes();//获取字节数组
     * String str = body.string();//获取字符串数据
     */
    @Override
    public void onNext(T response) {
       /* try {

            final String result = body.string();

            // CompressUtils.decompress(body.byteStream());
            Log.e("body", result);
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("ErrorCode");
            if (resultCode == 1) {
                mOnSuccessAndFaultListener.onSuccess(result);
            } else {
                String errorMsg = jsonObject.getString("ErrorMessage");
                mOnSuccessAndFaultListener.onFault(errorMsg);
                Log.e("OnSuccessAndFaultSub", "errorMsg: " + errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        if (response != null && ((BaseResponse) response).status == 0) {

            mOnSuccessAndFaultListener.onSuccess(response);
        } else {
            mOnSuccessAndFaultListener.onFault("");
        }


    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }


}


