package com.homa.catcartoon.ui.recom;

import com.homa.catcartoon.R;
import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.ui.base.PMlistener;
import com.homa.catcartoon.ui.base.PxListener;
import com.homa.catcartoon.ui.base.Vlistener;
import com.homa.catcartoon.ui.recom.bean.Banner;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.homa.catcartoon.ui.recom.bean.RecomBean;
import com.litesuits.android.log.Log;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Homa on 2017/11/6.
 */

public class recomP extends PxListener implements PMlistener {

    private Vlistener vlistener;
    private recomM mlistener;

    private List<Banner> banners;
    private List<String> url;
    private List<MySection> data;


    public recomP(Vlistener viewListener) {
        this.vlistener = viewListener;
        mlistener = new recomM(this);
        banners = new ArrayList<>();
        data = new ArrayList<>();
        url = new ArrayList<>();
    }


    @Override
    public void startPostForFragment(RxFragment rxFragment) {
        mlistener.startPostForFragment(rxFragment);
    }


    @Override
    public void onNext(final String resulte, final String mothead) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                getData(resulte);
                e.onNext(mothead);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String str) throws Exception {
                vlistener.onNext(mothead);
            }
        });

    }

    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
    }

    private void getData(final String html) {
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
            System.out.println("数据分析完成");
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

    public List<MySection> getData() {
        return data;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public List<String> getUrl() {
        return url;
    }

    public void cleran() {
        data.clear();
        url.clear();
        banners.clear();
    }
}
