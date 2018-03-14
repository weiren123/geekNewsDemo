package com.example.administrator.geeknewsdemo.di.module;

import android.app.Activity;

import com.example.administrator.geeknewsdemo.di.scope.ActivityScope;

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
    @ActivityScope
    public Activity provideActivity(){
        return mActivity;
    }
}