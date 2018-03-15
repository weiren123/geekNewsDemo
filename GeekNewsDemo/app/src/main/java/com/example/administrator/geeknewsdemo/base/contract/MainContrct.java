package com.example.administrator.geeknewsdemo.base.contract;

import com.example.administrator.geeknewsdemo.base.BasePresenter;
import com.example.administrator.geeknewsdemo.base.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface MainContrct {
    interface View extends BaseView{
        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }
    interface Presenter extends BasePresenter<View>{
        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);

        void setNightModeState(boolean b);

        void setCurrentItem(int index);

        int getCurrentItem();

        void setVersionPoint(boolean b);

        boolean getVersionPoint();
    }
}
