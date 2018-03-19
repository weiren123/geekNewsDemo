package com.example.administrator.geeknewsdemo.ui.zhihu.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.base.SimpleFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/16.
 */

public class DailyFragment extends SimpleFragment {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventData() {

    }
}
