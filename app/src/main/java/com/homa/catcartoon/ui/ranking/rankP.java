package com.homa.catcartoon.ui.ranking;

import com.homa.catcartoon.ui.base.PMlistener;
import com.homa.catcartoon.ui.base.PxListener;
import com.homa.catcartoon.ui.base.Vlistener;
import com.homa.catcartoon.ui.ranking.bean.RankingBean;
import com.homa.catcartoon.ui.recom.recomM;
import com.litesuits.android.log.Log;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Homa on 2017/11/6.
 */

public class rankP extends PxListener implements PMlistener {

    private Vlistener vlistener;
    private recomM mlistener;

    private List<RankingBean> data;


    public rankP(Vlistener viewListener) {
        this.vlistener = viewListener;
        mlistener=new recomM(this);
        data=new ArrayList<>();
    }


    @Override
    public void startPostForFragment(RxFragment rxFragment) {
        mlistener.startPostForFragment(rxFragment);
    }



    @Override
    public void onNext(String resulte, String mothead) {
        getData(resulte);
        vlistener.onNext(mothead);
    }

    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
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
                Log.i("排行数据",e.select("span").text()+e.select("a").attr("href")+e.select("a").attr("title"));
                if (num!=100){
                    data.add(new RankingBean(e.select("span").text(),e.select("a").attr("title"),e.select("a").attr("href")));
                }
                num++;
            }
        }catch(Exception e) {
            Log.i("mytag", e.toString());
        }

    }



    public List<RankingBean> getData() {
        return data;
    }

    public void cleran(){
        data.clear();
    }
}
