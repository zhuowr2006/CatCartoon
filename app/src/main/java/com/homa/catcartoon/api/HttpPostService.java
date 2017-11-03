package com.homa.catcartoon.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 请求接口类
 */

public interface HttpPostService {

    /**
     * 首页番剧
     *
     * @return
     */
    @GET("api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios")
    Observable<String> getBangumiAppIndex();
    /**
     * 首页番剧推荐
     *
     * @return
     */
    @GET("api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558")
    Observable<String> getBangumiRecommended();


    /**
     * 首页推荐数据
     *
     * @return
     */
    @GET("index/ding.json")
    Observable<String> getRecommendedInfo();

    /**
     * 首页推荐banner
     *
     * @return
     */
    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<String> getRecommendedBannerInfo();


    /**
     * 首页直播
     *
     * @return
     */
    @GET("AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Observable<String> getLiveAppIndex();

    /**
     * 获取直播源
     *
     * @param cid
     * @return
     */
    @GET("api/playurl?player=1&quality=0")
    Observable<String> getLiveUrl(@Query("cid") String cid, @Query("appkey") String appkey, @Query("ts") String ts, @Query("sign") String sign);

    /**
     * 获取具体分区列表
     *
     * @return
     */

    @GET("index/ding/{num}.json")
    Observable<String> getVideoList(@Path("num") String num);

    /**
     * 获取具体分区排行列表
     *
     * @return
     */
    @GET("index/rank/all-7-{num}.json")
    Observable<String> getVideoPHList(@Path("num") String num);


    /**
     * 视频详情数据
     *
     * @param aid
     * @return
     */
    @GET("x/view?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=1206255541e2648c1badb87812458046&ts=1478349831")
    Observable<String> getVideoDetails(@Query("aid") String aid);


    @GET("mobile/video/av{aid}.html")
    Observable<String> getVideoTag(@Path("aid") String aid);

    /**
     * 通过av号获取html
     * http://interface.bilibili.com/playurl?&cid=13022613&from=miniplay&player=1&sign=28b2be2b017493211727a28746ea6318
     * @return
     */
    @GET("video/av{av}")
    Observable<String> getBiliAVSearchHtml(@Path("av") String av);

    /**
     * 通过cid获取html
     * @return
     */
    @GET("playurl")
    Observable<String> getBiliAVVideoHtml(@QueryMap Map<String, String> map);
}
