package com.example.administrator.geeknewsdemo.di.module.http;

import com.example.administrator.geeknewsdemo.model.WelcomeBean;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface HttpHelper {
    Flowable<WelcomeBean> fetchWelcomeInfo(String res);
}
