package com.example.administrator.geeknewsdemo.di.component;

import android.app.Activity;

import com.example.administrator.geeknewsdemo.di.module.FragmentModule;
import com.example.administrator.geeknewsdemo.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/15.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
