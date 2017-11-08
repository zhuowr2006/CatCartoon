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
    /**
     * 搜索*/
    public static void getSearch(HttpManager manager,String url) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getSearch");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getSearch(url));
        manager.doHttpDeal(api);
    }
    /**
     * 详情*/
    public static void getInfo(HttpManager manager,String url) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getInfo");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        api.setObservable(httpService.getInfo(url));
        manager.doHttpDeal(api);
    }

    /**
     * 分类*/
    public static void getCategory(HttpManager manager,int type,int num) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getCategory");//用来识别返回时是哪个数据
        HttpPostService httpService = manager.getReTrofit(api).create(HttpPostService.class);
        switch (type){
            case 0://连载
                api.setObservable(httpService.getLianzai(num+""));
                break;
            case 1://完结
                api.setObservable(httpService.getWanjie(num+""));
                break;
            case 2://搞笑
                api.setObservable(httpService.getGaoxiao(num+""));
                break;
            case 3://热血
                api.setObservable(httpService.getRexue(num+""));
                break;
            case 4://悬疑
                api.setObservable(httpService.getxuanyi(num+""));
                break;
            case 5://格斗
                api.setObservable(httpService.getGedou(num+""));
                break;
            case 6://恐怖
                api.setObservable(httpService.getKongbu(num+""));
                break;
            case 7://科幻
                api.setObservable(httpService.getKehuan(num+""));
                break;
            case 8://魔幻
                api.setObservable(httpService.getMohuan(num+""));
                break;
            case 9://励志
                api.setObservable(httpService.getLizhi(num+""));
                break;
            case 10://校园
                api.setObservable(httpService.getXiaoyuan(num+""));
                break;
            case 11://爱情
                api.setObservable(httpService.getAiqing(num+""));
                break;
            case 12://武侠
                api.setObservable(httpService.getWuxia(num+""));
                break;
            case 13://日漫
                api.setObservable(httpService.getRiman(num+""));
                break;
            case 14://港漫
                api.setObservable(httpService.getGangman(num+""));
                break;
            case 15://国漫
                api.setObservable(httpService.getGuoman(num+""));
                break;
            case 16://美漫
                api.setObservable(httpService.getMeiman(num+""));
                break;
        }

        manager.doHttpDeal(api);
    }

}
