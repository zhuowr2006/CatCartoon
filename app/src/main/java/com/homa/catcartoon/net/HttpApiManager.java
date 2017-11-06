package com.homa.catcartoon.net;


import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;


/**
 * 请求Api管理类
 */
public  class HttpApiManager {

    public static String BaseUrl="http://www.tuku.cc/";


    /**
     * 首页推荐*/
    public static void getRecommend(HttpManager manager) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getBangumiAPI");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getRecommend());
        manager.doHttpDeal(api);
    }

}
