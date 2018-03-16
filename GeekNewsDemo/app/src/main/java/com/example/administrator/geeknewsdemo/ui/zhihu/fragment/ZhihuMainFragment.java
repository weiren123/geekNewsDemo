package com.example.administrator.geeknewsdemo.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.geeknewsdemo.R;
import com.example.administrator.geeknewsdemo.base.SimpleFragment;
import com.example.administrator.geeknewsdemo.ui.zhihu.adapter.ZhihuMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ZhihuMainFragment extends SimpleFragment {
    @BindView(R.id.tab_zhihu_main)
    TabLayout tabZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager vpZhihuMain;

    String[] tabTitle = new String[]{"日报","主题","专栏","热门"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventData() {
        fragments.add(new DailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        ZhihuMainAdapter zhihuadapter = new ZhihuMainAdapter(getChildFragmentManager(),fragments);
        vpZhihuMain.setAdapter(zhihuadapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[0]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[1]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[2]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[3]));
        tabZhihuMain.setupWithViewPager(vpZhihuMain);
        tabZhihuMain.getTabAt(0).setText(tabTitle[0]);
        tabZhihuMain.getTabAt(1).setText(tabTitle[1]);
        tabZhihuMain.getTabAt(2).setText(tabTitle[2]);
        tabZhihuMain.getTabAt(3).setText(tabTitle[3]);

    }
}
