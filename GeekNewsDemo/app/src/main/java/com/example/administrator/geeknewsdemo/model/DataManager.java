package com.example.administrator.geeknewsdemo.model;

import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.model.bean.WelcomeBean;
import com.example.administrator.geeknewsdemo.model.http.HttpHelper;
import com.example.administrator.geeknewsdemo.model.prefs.PreferencesHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/14.
 */

public class DataManager implements HttpHelper,PreferencesHelper{
    PreferencesHelper mPreferenceshelper;
    HttpHelper mHttpHelper;
    @Inject
    public DataManager(HttpHelper httpHelper,PreferencesHelper preferencesHelper){
        this.mHttpHelper = httpHelper;
        this.mPreferenceshelper = preferencesHelper;
    }


    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListInfo(){
        return mHttpHelper.fetchDailyListInfo();
    }


    @Override
    public boolean getNightModeState() {
        return mPreferenceshelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferenceshelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferenceshelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferenceshelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferenceshelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferenceshelper.setAutoCacheState(state);
    }

    @Override
    public int getCurrentItem() {
        return mPreferenceshelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mPreferenceshelper.setCurrentItem(item);
    }

    @Override
    public boolean getLikePoint() {
        return mPreferenceshelper.getLikePoint();
    }

    @Override
    public void setLikePoint(boolean isFirst) {
        mPreferenceshelper.setLikePoint(isFirst);
    }

    @Override
    public boolean getVersionPoint() {
        return mPreferenceshelper.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean isFirst) {
        mPreferenceshelper.setVersionPoint(isFirst);
    }

    @Override
    public boolean getManagerPoint() {
        return mPreferenceshelper.getManagerPoint();
    }

    @Override
    public void setManagerPoint(boolean isFirst) {
        mPreferenceshelper.setManagerPoint(isFirst);
    }
}
