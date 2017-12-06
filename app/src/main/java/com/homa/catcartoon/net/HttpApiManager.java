package com.homa.catcartoon.net;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import io.reactivex.Observable;


/**
 * 请求Api管理类
 */
public  class HttpApiManager {

    public static String BaseUrl="http://www.tuku.cc/";


    /**
     * 首页推荐*/
    public static void getRecommend(RxFragment rxFragment, HttpOnNextListener onNextListener) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getBangumiAPI");//用来识别返回时是哪个数据
        HttpManager httpManager=new HttpManager(rxFragment,api);
        httpManager.httpDeal(httpManager.getReTrofit(HttpPostService.class).getRecommend(),onNextListener);
    }
    /**
     * 搜索*/
    public static void getSearch(RxAppCompatActivity rxAppCompatActivity,String url,HttpOnNextListener onNextListener) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getSearch");//用来识别返回时是哪个数据
        HttpManager httpManager=new HttpManager(rxAppCompatActivity,api);
        httpManager.httpDeal(httpManager.getReTrofit(HttpPostService.class).getSearch(url),onNextListener);
    }
    /**
     * 详情*/
    public static void getInfo(RxAppCompatActivity rxAppCompatActivity,String url,HttpOnNextListener onNextListener) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getInfo");//用来识别返回时是哪个数据
        HttpManager httpManager=new HttpManager(rxAppCompatActivity,api);
        httpManager.httpDeal(httpManager.getReTrofit(HttpPostService.class).getInfo(url),onNextListener);
    }

    /**
     * 分类*/
    public static void getCategory(RxAppCompatActivity rxAppCompatActivity,int type,int num,HttpOnNextListener onNextListener) {
        BaseApi api = new BaseApi();
        api.setBaseUrl(BaseUrl);
        api.setMethod("getCategory");//用来识别返回时是哪个数据
        HttpManager httpManager=new HttpManager(rxAppCompatActivity,api);
        HttpPostService httpService = httpManager.getReTrofit(HttpPostService.class);
        Observable sbt = null;
        switch (type){
            case 0://连载
//                api.setObservable(httpService.getLianzai(num+""));
                sbt=httpService.getLianzai(num+"");
                break;
            case 1://完结
//                api.setObservable(httpService.getWanjie(num+""));
                sbt=httpService.getWanjie(num+"");
                break;
            case 2://搞笑
//                api.setObservable(httpService.getGaoxiao(num+""));
                sbt=httpService.getGaoxiao(num+"");
                break;
            case 3://热血
//                api.setObservable(httpService.getRexue(num+""));
                sbt=httpService.getRexue(num+"");
                break;
            case 4://悬疑
//                api.setObservable(httpService.getxuanyi(num+""));
                sbt=httpService.getxuanyi(num+"");
                break;
            case 5://格斗
//                api.setObservable(httpService.getGedou(num+""));
                sbt=httpService.getGedou(num+"");
                break;
            case 6://恐怖
//                api.setObservable(httpService.getKongbu(num+""));
                sbt=httpService.getKongbu(num+"");
                break;
            case 7://科幻
//                api.setObservable(httpService.getKehuan(num+""));
                sbt=httpService.getKehuan(num+"");
                break;
            case 8://魔幻
//                api.setObservable(httpService.getMohuan(num+""));
                sbt=httpService.getMohuan(num+"");
                break;
            case 9://励志
                sbt=httpService.getLizhi(num+"");
//                api.setObservable(httpService.getLizhi(num+""));
                break;
            case 10://校园
//                api.setObservable(httpService.getXiaoyuan(num+""));
                sbt=httpService.getXiaoyuan(num+"");
                break;
            case 11://爱情
//                api.setObservable(httpService.getAiqing(num+""));
                sbt=httpService.getAiqing(num+"");
                break;
            case 12://武侠
//                api.setObservable(httpService.getWuxia(num+""));
                sbt=httpService.getWuxia(num+"");
                break;
            case 13://日漫
//                api.setObservable(httpService.getRiman(num+""));
                sbt=httpService.getRiman(num+"");
                break;
            case 14://港漫
//                api.setObservable(httpService.getGangman(num+""));
                sbt=httpService.getGangman(num+"");
                break;
            case 15://国漫
//                api.setObservable(httpService.getGuoman(num+""));
                sbt=httpService.getGuoman(num+"");
                break;
            case 16://美漫
//                api.setObservable(httpService.getMeiman(num+""));
                sbt=httpService.getMeiman(num+"");
                break;
        }

//        manager.doHttpDeal(api);
        httpManager.httpDeal(sbt,onNextListener);
    }
    

}
