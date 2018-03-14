package com.example.administrator.geeknewsdemo.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.administrator.geeknewsdemo.di.component.AppComponent;
import com.example.administrator.geeknewsdemo.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/14.
 */

public class App extends Application {
    private static App instance;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    private static AppComponent appComponent;
    private Set<Activity> allActivities;
//    public static AppComponent appComponent;
    /**
    *   AppCompatDelegate.setDefaultNightMode(xxx);
    *  这个方法可以设置四个值：
    *  MODE_NIGHT_NO 日间模式
    *  MODE_NIGHT_YES 夜间模式
    *  MODE_NIGHT_AUTO 根据时间自动切换日夜间模式
    *  MODE_NIGHT_FOLLOW_SYSTEM 默认模式，
    * */
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化屏幕宽高
        getScreenSize();
        //初始化数据库
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static synchronized App getInstance(){
        return instance;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if ( allActivities!= null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
}
