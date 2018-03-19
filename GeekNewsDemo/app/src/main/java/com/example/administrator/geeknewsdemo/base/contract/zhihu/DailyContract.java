package com.example.administrator.geeknewsdemo.base.contract.zhihu;

import com.example.administrator.geeknewsdemo.base.BasePresenter;
import com.example.administrator.geeknewsdemo.base.BaseView;
import com.example.administrator.geeknewsdemo.model.bean.DailyBeforeListBean;
import com.example.administrator.geeknewsdemo.model.bean.DailyListBean;

/**
 * Created by Administrator on 2018/3/19.
 */

public interface DailyContract {
    interface View extends BaseView {

        void showContent(DailyListBean info);

        void showMoreContent(String date,DailyBeforeListBean info);

        void doInterval(int currentCount);
    }

    interface Presenter extends BasePresenter<View> {

        void getDailyData();

        void getBeforeData(String date);

        void startInterval();

        void stopInterval();

        void insertReadToDB(int id);
    }
}
