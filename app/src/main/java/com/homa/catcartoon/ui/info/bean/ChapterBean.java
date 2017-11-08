package com.homa.catcartoon.ui.info.bean;

/* * Created by Homa on 2017/11/6.
 */

public class ChapterBean {
    private String name;
    private String url;

    public ChapterBean(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public ChapterBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ChapterBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
