package com.homa.catcartoon.ui.info.bean;

/* * Created by Homa on 2017/11/6.
 */

public class OtherBean {
    private String title;
    private String tip;
    private String img;
    private String url;

    public OtherBean(String title, String tip, String img, String url) {
        this.title = title;
        this.tip = tip;
        this.img = img;
        this.url = url;
    }

    public OtherBean() {
    }

    public String getTitle() {
        return title;
    }

    public OtherBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public OtherBean setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public String getImg() {
        return img;
    }

    public OtherBean setImg(String img) {
        this.img = img;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public OtherBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
