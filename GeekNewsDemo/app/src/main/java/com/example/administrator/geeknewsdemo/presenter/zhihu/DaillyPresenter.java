package com.example.administrator.geeknewsdemo.presenter.zhihu;

import com.example.administrator.geeknewsdemo.base.RxPresenter;
import com.example.administrator.geeknewsdemo.base.contract.zhihu.DailyContract;
import com.example.administrator.geeknewsdemo.model.DataManager;
import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.utils.RxUtil;
import com.example.administrator.geeknewsdemo.widget.CommonSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/3/19.
 */

public class DaillyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter{

    private  DataManager mDatamanager;
    private Disposable intervalSubscription;
    private static final int INTERVAL_INSTANCE = 6;

    private int topCount = 0;
    private int currentTopCount = 0;
    @Inject
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
        if (intervalSubscription != null && !intervalSubscription.isDisposed()) {
            return;
        }
        intervalSubscription = Flowable.interval(INTERVAL_INSTANCE, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        if(currentTopCount == topCount)
                            currentTopCount = 0;
                        mView.doInterval(currentTopCount++);
                    }
                });
        addSubscribe(intervalSubscription);
    }

    @Override
    public void stopInterval() {
        if (intervalSubscription != null && !intervalSubscription.isDisposed()) {
            intervalSubscription.dispose();
        }
    }

    @Override
    public void insertReadToDB(int id) {
        mDatamanager.insertNewsId(id);
    }
}
