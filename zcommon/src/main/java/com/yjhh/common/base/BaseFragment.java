package com.yjhh.common.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yjhh.common.utils.LogUtils;
import com.yjhh.common.utils.SharedPreferencesUtils;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation.SupportFragment;

import java.util.List;

public abstract class BaseFragment extends SupportFragment {
    public void loginOut() {


        SharedPreferencesUtils.setParam(context, "mobile", "");
        SharedPreferencesUtils.setParam(context, "nickName", "");
        SharedPreferencesUtils.setParam(context, "sessionId", "");
        SharedPreferencesUtils.setParam(context, "type", "");


    }

    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据

    private View convertView;
    private SparseArray<View> mViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  LogUtil.m("   " + this.getClass().getSimpleName());
        convertView = inflater.inflate(getLayoutRes(), container, false);

        return convertView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  LogUtil.m("   " + this.getClass().getSimpleName());
        mViews = new SparseArray<>();
        initView(view);
        isInitView = true;
        lazyLoadData();
    }

    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //  LogUtil.m("context" + "   " + this.getClass().getSimpleName());
        this.context = context;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //  LogUtil.m("isVisibleToUser " + isVisibleToUser + "   " + this.getClass().getSimpleName());
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();

        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    private void lazyLoadData() {
        if (isFirstLoad) {
            //   LogUtil.m("第一次加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        } else {
            //  LogUtil.m("不是第一次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        }
        if (!isFirstLoad || !isVisible || !isInitView) {
            //  LogUtil.m("不加载" + "   " + this.getClass().getSimpleName());
            return;
        }

        //  LogUtil.m("完成数据第一次加载"+ "   " + this.getClass().getSimpleName());
        initData();
        isFirstLoad = false;
    }

    /**
     * 加载页面布局文件
     *
     * @return
     */
    protected abstract int getLayoutRes();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initView(View view);

    /**
     * 加载要显示的数据
     */
    protected  void initData(){

    }

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     *
     * @param viewId
     * @param <E>
     * @return
     */
    protected <E extends View> E findView(int viewId) {
        if (convertView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

}
