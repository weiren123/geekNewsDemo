package com.example.administrator.geeknewsdemo.presenter;

import com.example.administrator.geeknewsdemo.base.RxPresenter;
import com.example.administrator.geeknewsdemo.base.contract.WelcomeContrct;
import com.example.administrator.geeknewsdemo.di.module.prefs.DataManager;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/14.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContrct.View> implements WelcomeContrct.Presenter {
    private DataManager mDataManager;
    @Inject
    public WelcomePresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }
    @Override
    public void getWelcomData() {

    }
}
