package com.homa.catcartoon.ui.ranking.bean;

/* * Created by Homa on 2017/11/6.
 */

public class RankingBean {
    private String num;
    private String title;
    private String url;

    public RankingBean(String num, String title, String url) {
        this.num = num;
        this.title = title;
        this.url = url;
    }

    public String getNum() {
        return num;
    }

    public RankingBean setNum(String num) {
        this.num = num;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RankingBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RankingBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
