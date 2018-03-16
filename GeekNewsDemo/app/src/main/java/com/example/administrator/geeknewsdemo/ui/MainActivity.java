package com.example.administrator.geeknewsdemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.app.Constants;
import com.example.administrator.geeknewsdemo.base.BaseActivity;
import com.example.administrator.geeknewsdemo.base.contract.MainContrct;
import com.example.administrator.geeknewsdemo.presenter.MainPresenter;
import com.example.administrator.geeknewsdemo.ui.zhihu.fragment.ZhihuMainFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContrct.View {


    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.view_search)
    MaterialSearchView viewSearch;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private ZhihuMainFragment zhihuMainFragment;
    private int showFragment;
    private MenuItem mMenuItem;
    private int hideFragment;

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
        zhihuMainFragment = new ZhihuMainFragment();
        setToolBar(toolBar,"知乎日报");
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawer.addDrawerListener(drawerToggle);
        mMenuItem = navigation.getMenu().findItem(R.id.drawer_zhihu);
        loadMultipleRootFragment(R.id.fl_main_content,0,zhihuMainFragment);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_zhihu:
                        showFragment = Constants.TYPE_ZHIHU;
                        break;
                    case R.id.drawer_wechat:
                        showFragment = Constants.TYPE_WECHAT;
                        break;
                    case R.id.drawer_gank:
                        showFragment = Constants.TYPE_GANK;
                        break;
                    case R.id.drawer_gold:
                        showFragment = Constants.TYPE_GOLD;
                        break;
                    case R.id.drawer_vtex:
                        showFragment = Constants.TYPE_VTEX;
                        break;
                    case R.id.drawer_like:
                        showFragment = Constants.TYPE_LIKE;
                        break;
                    case R.id.drawer_setting:
                        showFragment = Constants.TYPE_SETTING;
                        break;
                    case R.id.drawer_about:
                        showFragment = Constants.TYPE_SETTING;
                        break;
                    default:
                        break;
                }
                if(mMenuItem!=null){
                    mMenuItem.setChecked(false);
                }
                mMenuItem =item;
                mPresenter.setCurrentItem(showFragment);
                mMenuItem.setChecked(true);
                toolBar.setTitle(mMenuItem.getTitle());
                drawer.closeDrawers();
                showHideFragment(getTargetFragment(showFragment),getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });
    }
    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return zhihuMainFragment;
            case Constants.TYPE_GANK:
                return zhihuMainFragment;
            case Constants.TYPE_WECHAT:
                return zhihuMainFragment;
            case Constants.TYPE_GOLD:
                return zhihuMainFragment;
            case Constants.TYPE_VTEX:
                return zhihuMainFragment;
            case Constants.TYPE_LIKE:
                return zhihuMainFragment;
            case Constants.TYPE_SETTING:
                return zhihuMainFragment;
            case Constants.TYPE_ABOUT:
                return zhihuMainFragment;
        }
        return zhihuMainFragment;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
