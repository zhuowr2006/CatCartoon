package com.homa.catcartoon.ui.ranking.mvp;

import android.support.annotation.NonNull;

import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.ui.ranking.bean.RankingBean;
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

public class RankModel implements RankDataSource {

    private List<RankingBean> data;

    {
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
                        callback.onGetData(data);
                    }
                });
            }

            @Override
            public void onError(ApiException e, String method) {
                callback.onDataErro(e,method);
            }
        });
    }

    private void getData(String html){
        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.parse(html);
            //排行数据
//            System.out.println("tttttttttt");
//            Log.i("排行数据",doc.select("div.rankListBak").select("div.item"));
            int num=0;
            for (Element e:doc.select("div.rankListBak").select("div.item")){
//                System.out.println("=="+e.select("span").text());
//                System.out.println("=="+e.select("a").attr("href"));
//                System.out.println("=="+e.select("a").attr("title"));
//                Log.i("排行数据",e.select("span").text()+e.select("a").attr("href")+e.select("a").attr("title"));
                if (num!=100){
                    data.add(new RankingBean(e.select("span").text(),e.select("a").attr("title"),e.select("a").attr("href")));
                }
                num++;
            }
        }catch(Exception e) {
            Log.i("mytag", e.toString());
        }

    }
}
