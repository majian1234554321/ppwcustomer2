package com.paipaiwei.personal.ui.fragment;

import android.content.Context;
import android.widget.Toast;
import com.yjhh.common.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment extends BaseFragment {
    protected OnBackToFirstListener _mBackToFirstListener;
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnBackToFirstListener) {
//            _mBackToFirstListener = (OnBackToFirstListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnBackToFirstListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        _mBackToFirstListener = null;
//    }

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                _mActivity.finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(_mActivity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}



