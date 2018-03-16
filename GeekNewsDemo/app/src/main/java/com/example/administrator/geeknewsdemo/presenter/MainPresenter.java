package com.example.administrator.geeknewsdemo.presenter;

import com.example.administrator.geeknewsdemo.base.RxPresenter;
import com.example.administrator.geeknewsdemo.base.contract.MainContrct;
import com.example.administrator.geeknewsdemo.model.DataManager;
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
        mDataManager.setNightModeState(b);
    }

    @Override
    public void setCurrentItem(int index) {
        mDataManager.setCurrentItem(index);
    }

    @Override
    public int getCurrentItem() {
        return  mDataManager.getCurrentItem();
    }

    @Override
    public void setVersionPoint(boolean b) {
        mDataManager.setVersionPoint(b);
    }

    @Override
    public boolean getVersionPoint() {
        return mDataManager.getVersionPoint();
    }
}
