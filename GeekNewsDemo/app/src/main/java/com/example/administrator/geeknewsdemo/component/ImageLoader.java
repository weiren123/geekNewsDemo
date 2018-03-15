package com.example.administrator.geeknewsdemo.component;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2018/3/15.
 */

public class ImageLoader {
    public static void load(Activity activity, String imageurl, ImageView iv){
        if(!activity.isDestroyed()){
            Glide.with(activity).load(imageurl).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(iv);
        }
    }
}
