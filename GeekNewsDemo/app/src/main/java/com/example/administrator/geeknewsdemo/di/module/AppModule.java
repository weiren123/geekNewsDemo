package com.example.administrator.geeknewsdemo.di.module;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.model.http.HttpHelper;
import com.example.administrator.geeknewsdemo.model.http.RetrofitHelper;
import com.example.administrator.geeknewsdemo.model.DataManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/3/14.
 */

@Module
public class AppModule {
    App appliction;
    @Inject
    public AppModule(App app){
        this.appliction = app;
    }
    @Provides
    @Singleton
    public App providesApplicationContext(){
        return appliction;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}