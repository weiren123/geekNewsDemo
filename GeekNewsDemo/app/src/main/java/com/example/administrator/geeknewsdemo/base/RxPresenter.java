package com.example.administrator.geeknewsdemo.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/14.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    private CompositeDisposable mCompositeDisposable;
    protected T mView;

    protected void addSubscribe(Disposable substription){
        if(mCompositeDisposable==null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(substription);
    }

    protected void unSubscribe(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
