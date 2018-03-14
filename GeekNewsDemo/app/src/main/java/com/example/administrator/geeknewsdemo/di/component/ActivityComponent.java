package com.example.administrator.geeknewsdemo.di.component;

import com.example.administrator.geeknewsdemo.di.module.ActivityModule;
import com.example.administrator.geeknewsdemo.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/14.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
}
