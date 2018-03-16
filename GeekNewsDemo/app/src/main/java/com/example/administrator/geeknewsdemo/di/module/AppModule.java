package com.example.administrator.geeknewsdemo.di.module;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.model.DataManager;
import com.example.administrator.geeknewsdemo.model.http.HttpHelper;
import com.example.administrator.geeknewsdemo.model.http.RetrofitHelper;
import com.example.administrator.geeknewsdemo.model.prefs.ImplPreferencesHelper;
import com.example.administrator.geeknewsdemo.model.prefs.PreferencesHelper;

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
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }
    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper,preferencesHelper);
    }
}