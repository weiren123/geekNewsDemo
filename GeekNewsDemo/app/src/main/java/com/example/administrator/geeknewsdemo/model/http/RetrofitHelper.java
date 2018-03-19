package com.example.administrator.geeknewsdemo.model.http;

import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;
import com.example.administrator.geeknewsdemo.model.http.api.ZhiHuApis;
import com.example.administrator.geeknewsdemo.model.bean.WelcomeBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/15.
 */

public class RetrofitHelper implements HttpHelper{

    private ZhiHuApis mZhihuApiService;
    @Inject
    public RetrofitHelper(ZhiHuApis zhihuApiService){
        this.mZhihuApiService = zhihuApiService;
    }
    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mZhihuApiService.getDailyList();
    }
}
