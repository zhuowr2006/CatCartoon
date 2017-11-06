package com.homa.catcartoon.ui.recom.bean;

/**
 * Created by Homa on 2017/11/6.
 */

public class RecomBean {
    private String url;
    private String img;
    private String title;
    private String num;

    public RecomBean(String url, String img, String title, String num) {
        this.url = url;
        this.img = img;
        this.title = title;
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public RecomBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getImg() {
        return img;
    }

    public RecomBean setImg(String img) {
        this.img = img;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecomBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getNum() {
        return num;
    }

    public RecomBean setNum(String num) {
        this.num = num;
        return this;
    }
}
