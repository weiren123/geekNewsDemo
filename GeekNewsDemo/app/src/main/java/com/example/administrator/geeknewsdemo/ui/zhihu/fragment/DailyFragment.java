package com.example.administrator.geeknewsdemo.ui.zhihu.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.base.RootFragment;
import com.example.administrator.geeknewsdemo.base.contract.zhihu.DailyContract;
import com.example.administrator.geeknewsdemo.model.bean.DailyBeforeListBean;
import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.presenter.zhihu.DaillyPresenter;
import com.example.administrator.geeknewsdemo.ui.zhihu.adapter.DailyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/16.
 */

public class DailyFragment extends RootFragment<DaillyPresenter> implements DailyContract.View {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private DailyAdapter mDailyAdapter;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();
    private String currentDate;
    private boolean isDataReady;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventData() {
        mDailyAdapter = new DailyAdapter(mContext,mList);
        viewMain.setLayoutManager(new LinearLayoutManager(mContext));
        viewMain.setAdapter(mDailyAdapter);
        mPresenter.getDailyData();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showContent(DailyListBean info) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()) + 1);
        mDailyAdapter.addDailyDate(info);
        isDataReady = true;
        mPresenter.startInterval();
    }

    @Override
    public void showMoreContent(String date, DailyBeforeListBean info) {

    }

    @Override
    public void doInterval(int currentCount) {

    }
}
