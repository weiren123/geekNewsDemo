package com.example.administrator.geeknewsdemo.presenter.zhihu;

import com.example.administrator.geeknewsdemo.base.RxPresenter;
import com.example.administrator.geeknewsdemo.base.contract.zhihu.DailyContract;
import com.example.administrator.geeknewsdemo.model.DataManager;
import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.utils.RxUtil;
import com.example.administrator.geeknewsdemo.widget.CommonSubscriber;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/3/19.
 */

public class DaillyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{

    private  DataManager mDatamanager;
    private int topCount;

    public DaillyPresenter(DataManager dataManager){
        this.mDatamanager = dataManager;
    }
    @Override
    public void getDailyData() {
        addSubscribe(mDatamanager.fetchDailyListInfo()
        .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
        .map(new Function<DailyListBean, DailyListBean>() {
            @Override
            public DailyListBean apply(@NonNull DailyListBean dailyListBean) throws Exception {
                List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
                for(DailyListBean.StoriesBean item : stories) {
                  item.setReadState(mDatamanager.queryNewsId(item.getId()));
                }
                return dailyListBean;
            }
        })
        .subscribeWith(new CommonSubscriber<DailyListBean>(mView) {
            @Override
            public void onNext(DailyListBean dailyListBean) {
                topCount = dailyListBean.getTop_stories().size();
                mView.showContent(dailyListBean);
            }
        })
        );
    }

    @Override
    public void getBeforeData(String date) {

    }

    @Override
    public void startInterval() {

    }

    @Override
    public void stopInterval() {

    }

    @Override
    public void insertReadToDB(int id) {

    }
}
