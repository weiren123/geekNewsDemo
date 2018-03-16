package com.example.administrator.geeknewsdemo.presenter;

import com.example.administrator.geeknewsdemo.base.RxPresenter;
import com.example.administrator.geeknewsdemo.base.contract.WelcomeContrct;
import com.example.administrator.geeknewsdemo.model.DataManager;
import com.example.administrator.geeknewsdemo.model.WelcomeBean;
import com.example.administrator.geeknewsdemo.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/3/14.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContrct.View> implements WelcomeContrct.Presenter {
    private static final String RES = "image.png";
    private static final int COUNT_DOWN_TIME = 2200;
    private DataManager mDataManager;
    @Inject
    public WelcomePresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }
    @Override
    public void getWelcomData() {
            addSubscribe(mDataManager.fetchWelcomeInfo(RES)
            .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
            .subscribe(new Consumer<WelcomeBean>() {
                @Override
                public void accept(@NonNull WelcomeBean welcomeBean) throws Exception {
                    Logger.d("welcombean:" + welcomeBean.getImg());
                    mView.showContent(welcomeBean);
                    startCountDown();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable throwable) throws Exception {
                    mView.jumpToMain();
                }
            }));
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
        .compose(RxUtil.<Long>rxSchedulerHelper())
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                mView.jumpToMain();
            }
        }));
    }
}
