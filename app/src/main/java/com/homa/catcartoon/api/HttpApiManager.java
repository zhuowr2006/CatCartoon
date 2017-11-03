package com.homa.catcartoon.api;


import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;

import java.util.Map;


/**
 * 请求Api管理类
 */
public  class HttpApiManager {

    public static String getBangumiAPI="getBangumiAPI";
    public static String getBangumiRecommended="getBangumiRecommended";

    public static void getBangumiAPI(HttpManager manager) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BANGUMI_BASE_URL);
        api.setMethod("getBangumiAPI");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getBangumiAppIndex());
        manager.doHttpDeal(api);
    }

    public static void getBangumiRecommended(HttpManager manager) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BANGUMI_BASE_URL);
        api.setMethod("getBangumiRecommended");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getBangumiRecommended());
        manager.doHttpDeal(api);
    }

    public static void getRecommendedInfo(HttpManager manager) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BASE_URL);
        api.setMethod("getRecommendedInfo");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getRecommendedInfo());
        manager.doHttpDeal(api);
    }
    public static void getRecommendedBannerInfo(HttpManager manager) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.APP_BASE_URL);
        api.setMethod("getRecommendedBannerInfo");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getRecommendedBannerInfo());
        manager.doHttpDeal(api);
    }

    public static void getLiveAppIndex(HttpManager manager) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.LIVE_BASE_URL);
        api.setMethod("getLiveAppIndex");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getLiveAppIndex());
        manager.doHttpDeal(api);
    }
    public static void getLiveUrl(HttpManager manager, String cid, String appkey, String ts, String sign) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.LIVE_BASE_URL);
        api.setMethod("getLiveUrl");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getLiveUrl(cid,appkey, ts, sign));
        manager.doHttpDeal(api);
    }

    public static void getVideoList(HttpManager manager, String num) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BASE_URL);
        api.setMethod("getVideoList");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getVideoList(num));
        manager.doHttpDeal(api);
    }
    public static void getVideoPHList(HttpManager manager, String num) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BASE_URL);
        api.setMethod("getVideoPHList");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getVideoPHList(num));
        manager.doHttpDeal(api);
    }
    public static void getVideoDetails(HttpManager manager, String aid) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.APP_BASE_URL);
        api.setMethod("getVideoDetails");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getVideoDetails(aid));
        manager.doHttpDeal(api);
    }

    public static void getVideoTag(HttpManager manager, String aid) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BASE_URL);
        api.setMethod("getVideoTag");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getVideoTag(aid));
        manager.doHttpDeal(api);
    }
    public static void getBiliAVVideoHtml(HttpManager manager, Map<String, String> map) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.VIDEO_BASE_URL);
        api.setMethod("getBiliAVVideoHtml");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getBiliAVVideoHtml(map));
        manager.doHttpDeal(api);
    }
    public static void getBiliAVSearchHtml(HttpManager manager, String aid) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl.BASE_URL);
        api.setMethod("getBiliAVSearchHtml");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getBiliAVSearchHtml(aid));
        manager.doHttpDeal(api);
    }

}
