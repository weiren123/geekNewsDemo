package com.example.administrator.geeknewsdemo.di.component;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/14.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
}
