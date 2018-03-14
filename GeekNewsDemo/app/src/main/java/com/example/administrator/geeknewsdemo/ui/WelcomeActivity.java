package com.example.administrator.geeknewsdemo.ui;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.base.BaseActivity;
import com.example.administrator.geeknewsdemo.base.contract.WelcomeContrct;
import com.example.administrator.geeknewsdemo.model.WelcomeBean;
import com.example.administrator.geeknewsdemo.presenter.WelcomePresenter;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContrct.View {


    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventData() {

    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {

    }

    @Override
    public void jumpToMain() {

    }
}
