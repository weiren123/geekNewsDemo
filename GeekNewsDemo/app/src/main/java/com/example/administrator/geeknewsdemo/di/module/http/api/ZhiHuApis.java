package com.example.administrator.geeknewsdemo.di.module.http.api;

import com.example.administrator.geeknewsdemo.model.WelcomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface ZhiHuApis {
//    String HOST = "http://news-at.zhihu.com/api/4/";
    String HOST = "http://10.63.205.74:5000/";
    //http://127.0.0.1:5000/startimage
    /**
     * 启动界面图片
     */
//    @GET("start-image/{res}")
//    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    @GET("startimage/{res}")
    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报
     */
//    @GET("news/latest")
//    Flowable<DailyListBean> getDailyList();

    /**
     * 往期日报
     */
//    @GET("news/before/{date}")
//    Flowable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 主题日报
     */
//    @GET("themes")
//    Flowable<ThemeListBean> getThemeList();

    /**
     * 主题日报详情
     */
//    @GET("theme/{id}")
//    Flowable<ThemeChildListBean> getThemeChildList(@Path("id") int id);
//
//    /**
//     * 专栏日报
//     */
//    @GET("sections")
//    Flowable<SectionListBean> getSectionList();
//
//    /**
//     * 专栏日报详情
//     */
//    @GET("section/{id}")
//    Flowable<SectionChildListBean> getSectionChildList(@Path("id") int id);
//
//    /**
//     * 热门日报
//     */
//    @GET("news/hot")
//    Flowable<HotListBean> getHotList();
//
//    /**
//     * 日报详情
//     */
//    @GET("news/{id}")
//    Flowable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);
//
//    /**
//     * 日报的额外信息
//     */
//    @GET("story-extra/{id}")
//    Flowable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);
//
//    /**
//     * 日报的长评论
//     */
//    @GET("story/{id}/long-comments")
//    Flowable<CommentBean> getLongCommentInfo(@Path("id") int id);
//
//    /**
//     * 日报的短评论
//     */
//    @GET("story/{id}/short-comments")
//    Flowable<CommentBean> getShortCommentInfo(@Path("id") int id);
}
