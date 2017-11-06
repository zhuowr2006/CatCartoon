package com.homa.catcartoon.ui.recom.bean;

/**
 * Created by Homa on 2017/11/6.
 */

public class Banner {

    private String title;
    private String imgUrl;

    public Banner(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public Banner setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Banner setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
