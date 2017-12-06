package com.homa.catcartoon.ui.recom.mvp;

import android.support.annotation.NonNull;

import com.homa.catcartoon.R;
import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.ui.recom.bean.Banner;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.homa.catcartoon.ui.recom.bean.RecomBean;
import com.litesuits.android.log.Log;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Homa on 2017/11/17.
 */

public class RecomModel implements RecomDataSource {

    private List<Banner> banners;
    private List<String> url;
    private List<MySection> data;

    {
        this.banners=new ArrayList<>();
        this.url=new ArrayList<>();
        this.data=new ArrayList<>();
    }

    @Override
    public void GetDatas(RxFragment rxFragment, @NonNull final GetDataCallback callback) {

        HttpApiManager.getRecommend(rxFragment,new HttpOnNextListener() {
            @Override
            public void onNext(final String resulte, final String method) {
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<String> e) throws Exception {
                        getData(resulte);
                        e.onNext(method);
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String str) throws Exception {
                        callback.onGetData(banners,url,data);
                    }
                });
            }

            @Override
            public void onError(ApiException e, String method) {
                callback.onDataErro(e,method);
            }
        });
    }

    private void getData(final String html) {
        if (html!=null&&!html.equals("")){
            banners.clear();
            url.clear();
            data.clear();
        }
        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.parse(html);
            //广告数据
            for (Element e : doc.select("div.bannerForm").select("ul").select("li")) {
//                Log.i("广告数据", e.select("a").select("img").attr("src") + e.select("a").select("img").attr("title"));
                url.add(e.select("a").select("img").attr("src"));
            }
            //热门推荐
            int num = 0;
            for (Element e : doc.select("div.original").select("div.bookList_1").select("div.item").select("div.book")) {
                ////前14个是热门推荐，后面的是漫友推荐
                if (num < 14) {
                    if (num == 0) {
                        data.add(getheaditem(0));
                    } else {
                        data.add(new MySection(new RecomBean(HttpApiManager.BaseUrl + e.select("a").attr("href"), e.select("img").attr("src"), e.select("img").attr("title"), e.select("span").text())));
                    }
                } else {
                    if (num == 14) {
                        data.add(getheaditem(1));
                    } else {
                        data.add(new MySection(new RecomBean(HttpApiManager.BaseUrl + e.select("a").attr("href"), e.select("img").attr("src"), e.select("img").attr("title"), e.select("span").text())));
                    }
                }
                num++;
//                Log.i("热门推荐",e.select("a").attr("href")+"=="+e.select("img").attr("src")+"=="+e.select("img").attr("title")+"=="+e.select("span").text());
            }
//            //最新上架
            num = 0;
            for (Element e : doc.select("div.topic_2").select("div.item").select("div.book")) {
                if (num == 0) {
                    data.add(getheaditem(2));
                } else {
                    data.add(new MySection(new RecomBean(HttpApiManager.BaseUrl + e.select("a").attr("href"), e.select("img").attr("src"), e.select("img").attr("title"), e.select("span").text())));
                }

                num++;
//                Log.i("最新上架",e.select("a").attr("href")+"=="+e.select("img").attr("src")+"=="+e.select("img").attr("title")+"=="+e.select("span").text());
            }
        } catch (Exception e) {
            Log.i("mytag", e.toString());
        }
    }

    private MySection getheaditem(int type) {
        MySection bean = null;
        switch (type) {
            case 0:
                bean = new MySection(true, "热门推荐", R.drawable.recom_hot);
                break;
            case 1:
                bean = new MySection(true, "漫友推荐", R.drawable.recom_fn);
                break;
            case 2:
                bean = new MySection(true, "最近更新", R.drawable.newest);
                break;

        }
        return bean;
    }
}
