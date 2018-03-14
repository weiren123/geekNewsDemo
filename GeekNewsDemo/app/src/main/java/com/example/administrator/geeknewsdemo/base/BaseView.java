package com.example.administrator.geeknewsdemo.base;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface BaseView {
    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    void stateErroe();

    void stateLoading();

    void stateMain();
}
