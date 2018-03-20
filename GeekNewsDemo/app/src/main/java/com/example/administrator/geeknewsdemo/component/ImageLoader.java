package com.example.administrator.geeknewsdemo.component;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.geeknewsdemo.app.App;

/**
 * Created by Administrator on 2018/3/15.
 */

public class ImageLoader {
    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }
    public static void load(Activity activity, String imageurl, ImageView iv){
        if(!activity.isDestroyed()){
            Glide.with(activity).load(imageurl).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(iv);
        }
    }
}
