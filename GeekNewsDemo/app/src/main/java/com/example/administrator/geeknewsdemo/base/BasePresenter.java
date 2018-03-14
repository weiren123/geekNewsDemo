package com.example.administrator.geeknewsdemo.base;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
