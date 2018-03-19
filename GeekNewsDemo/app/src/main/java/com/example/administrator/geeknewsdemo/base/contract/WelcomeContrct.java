package com.example.administrator.geeknewsdemo.base.contract;

import com.example.administrator.geeknewsdemo.base.BasePresenter;
import com.example.administrator.geeknewsdemo.base.BaseView;
import com.example.administrator.geeknewsdemo.model.bean.WelcomeBean;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface WelcomeContrct {
    interface View extends BaseView{
        void showContent(WelcomeBean welcomeBean);

        void jumpToMain();
    }
    interface Presenter extends BasePresenter<View>{
        void getWelcomData();
    }
}
