package com.yjhh.ppwcustomer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.yjhh.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class Main2ViewPagerAdapter extends FragmentPagerAdapter {

    public ArrayList<BaseFragment> mFragments;

    public String[] mTitles;



    public Main2ViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
