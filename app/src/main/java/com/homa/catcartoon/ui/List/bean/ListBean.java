package com.homa.catcartoon.ui.List.bean;

/* * Created by Homa on 2017/11/6.
 */

public class ListBean {
    private String name;
    private String title;
    private String tip;
    private String summary;
    private String img;
    private String url;

    public ListBean(String name, String title, String tip, String summary, String img, String url) {
        this.name = name;
        this.title = title;
        this.tip = tip;
        this.summary = summary;
        this.img = img;
        this.url = url;
    }

    public ListBean() {
    }

    public String getName() {
        return name;
    }

    public ListBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ListBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public ListBean setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public ListBean setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getImg() {
        return img;
    }

    public ListBean setImg(String img) {
        this.img = img;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ListBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
