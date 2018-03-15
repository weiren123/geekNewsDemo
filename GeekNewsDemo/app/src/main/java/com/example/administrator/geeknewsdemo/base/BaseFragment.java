package com.example.administrator.geeknewsdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.geeknewsdemo.app.App;
import com.example.administrator.geeknewsdemo.di.component.DaggerFragmentComponent;
import com.example.administrator.geeknewsdemo.di.component.FragmentComponent;
import com.example.administrator.geeknewsdemo.di.module.FragmentModule;
import com.example.administrator.geeknewsdemo.utils.SnackbarUtil;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/15.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {
    @Inject
    protected T mPresenter;
    protected FragmentComponent getFragmentComponent(){
       return DaggerFragmentComponent.builder()
               .appComponent(App.getAppComponent())
               .fragmentModule(getFragmentModule())
               .build();
   }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInject();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
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

    @Override
    protected void initEventData() {

    }

    protected abstract void initInject();
}
