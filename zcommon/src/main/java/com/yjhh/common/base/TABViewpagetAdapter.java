package com.yjhh.common.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class TABViewpagetAdapter extends FragmentPagerAdapter {

    public ArrayList<BaseFragment> mFragments;

    public String[] mTitles;



    public TABViewpagetAdapter(FragmentManager fm, ArrayList<BaseFragment> mFragments, String[] mTitles) {
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
