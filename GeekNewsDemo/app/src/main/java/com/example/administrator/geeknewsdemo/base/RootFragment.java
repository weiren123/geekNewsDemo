package com.example.administrator.geeknewsdemo.base;

/**
 * Created by Administrator on 2018/3/16.
 */

public abstract class RootFragment<T extends BasePresenter> extends BaseFragment<T> {
    @Override
    public void stateErroe() {
        super.stateErroe();
    }

    @Override
    public void stateLoading() {
        super.stateLoading();
    }

    @Override
    public void stateMain() {
        super.stateMain();
    }

    @Override
    protected void initEventData() {
        super.initEventData();

    }
}
