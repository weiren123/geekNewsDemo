package com.example.administrator.geeknewsdemo.presenter;

import com.example.administrator.geeknewsdemo.base.RxPresenter;
import com.example.administrator.geeknewsdemo.base.contract.MainContrct;
import com.example.administrator.geeknewsdemo.di.module.prefs.DataManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/14.
 */

public class MainPresenter extends RxPresenter<MainContrct.View> implements MainContrct.Presenter {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }


    @Override
    public void checkVersion(String currentVersion) {

    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {

    }

    @Override
    public void setNightModeState(boolean b) {

    }

    @Override
    public void setCurrentItem(int index) {

    }

    @Override
    public int getCurrentItem() {
        return 0;
    }

    @Override
    public void setVersionPoint(boolean b) {

    }

    @Override
    public boolean getVersionPoint() {
        return false;
    }
}
