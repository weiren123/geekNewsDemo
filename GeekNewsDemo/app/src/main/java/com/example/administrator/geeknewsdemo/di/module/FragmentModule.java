package com.example.administrator.geeknewsdemo.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.administrator.geeknewsdemo.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/3/15.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;
    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }
    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return mFragment.getActivity();
    }
}
