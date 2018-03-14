package com.example.administrator.geeknewsdemo.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/3/14.
 */
@Module
public class ActivityModule {
    Activity mActivity;
    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }
    @Provides
    public Activity provideActivity(){
        return mActivity;
    }
}