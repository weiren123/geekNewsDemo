package com.example.administrator.geeknewsdemo.di.module.prefs;

import com.example.administrator.geeknewsdemo.di.module.http.HttpHelper;
import com.example.administrator.geeknewsdemo.model.WelcomeBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/14.
 */

public class DataManager implements HttpHelper {
    HttpHelper mHttpHelper;
    @Inject
    public DataManager(HttpHelper httpHelper){
        this.mHttpHelper = httpHelper;
    }
    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }
}
