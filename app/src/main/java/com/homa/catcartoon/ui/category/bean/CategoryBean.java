package com.homa.catcartoon.ui.category.bean;

/* * Created by Homa on 2017/11/6.
 */

public class CategoryBean {
    private String img;
    private String title;

    public CategoryBean(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public CategoryBean setImg(String img) {
        this.img = img;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CategoryBean setTitle(String title) {
        this.title = title;
        return this;
    }
}
