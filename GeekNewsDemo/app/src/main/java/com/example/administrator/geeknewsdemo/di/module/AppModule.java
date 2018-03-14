package com.example.administrator.geeknewsdemo.di.module;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.di.module.http.HttpHelper;
import com.example.administrator.geeknewsdemo.di.module.prefs.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/3/14.
 */

@Module
public class AppModule {
    App appliction;
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
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}