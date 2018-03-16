package com.example.administrator.geeknewsdemo.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ZhihuMainAdapter extends FragmentPagerAdapter {
    private  List<Fragment> mFragment;

    public ZhihuMainAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragment = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
