package com.example.administrator.geeknewsdemo.di.component;

import android.app.Activity;

import com.example.administrator.geeknewsdemo.di.module.ActivityModule;
import com.example.administrator.geeknewsdemo.di.scope.ActivityScope;
import com.example.administrator.geeknewsdemo.ui.MainActivity;
import com.example.administrator.geeknewsdemo.ui.WelcomeActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/14.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);
}
