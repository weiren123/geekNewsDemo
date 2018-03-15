package com.example.administrator.geeknewsdemo.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.base.BaseActivity;
import com.example.administrator.geeknewsdemo.base.contract.MainContrct;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContrct.Presenter> implements MainContrct.View {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_search)
    MaterialSearchView viewSearch;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    public void showUpdateDialog(String versionContent) {

    }

    @Override
    public void startDownloadService() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
