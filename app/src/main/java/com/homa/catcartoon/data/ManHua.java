package com.homa.catcartoon.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Homa on 2017/11/10.
 */
@Entity
public class ManHua implements Serializable{
    static final long serialVersionUID = 42L;
    //不能用int
    @Id(autoincrement = true)
    private Long id;
    private String title;//名字
    private String author;//作者
    private String newstr;//最新更新
    private String nowstr;//现在看到第几话
    private String url;//具体图片地址
    @Generated(hash = 1497561074)
    public ManHua(Long id, String title, String author, String newstr,
            String nowstr, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.newstr = newstr;
        this.nowstr = nowstr;
        this.url = url;
    }
    @Generated(hash = 1308148084)
    public ManHua() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getNewstr() {
        return this.newstr;
    }
    public void setNewstr(String newstr) {
        this.newstr = newstr;
    }
    public String getNowstr() {
        return this.nowstr;
    }
    public void setNowstr(String nowstr) {
        this.nowstr = nowstr;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }


}
