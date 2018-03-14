package com.example.administrator.geeknewsdemo.base;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.di.component.ActivityComponent;
import com.example.administrator.geeknewsdemo.di.component.DaggerActivityComponent;
import com.example.administrator.geeknewsdemo.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/14.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;
    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }
//
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if(mPresenter!=null){
            mPresenter.attachView(this);
        }
    }

    protected abstract void initInject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateErroe() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }
}
