package com.example.administrator.geeknewsdemo.di.component;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.di.module.AppModule;
import com.example.administrator.geeknewsdemo.di.module.http.RetrofitHelper;
import com.example.administrator.geeknewsdemo.di.module.prefs.DataManager;
import com.example.administrator.geeknewsdemo.model.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/14.
 */
@Singleton
@Component(modules = {AppModule.class,HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
