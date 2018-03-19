package com.example.administrator.geeknewsdemo.model.http;

import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.model.bean.WelcomeBean;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface HttpHelper {
    Flowable<WelcomeBean> fetchWelcomeInfo(String res);

    Flowable<DailyListBean> fetchDailyListInfo();
}
