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
    private String title;
    private String imgurl;
    private String author;
    private String infourl;//信息url
    private String seewhere;//看到哪
    private String seewhereurl;//看到哪集的具体url
    private String updatahere;//更新到哪集
    private long modifytime;//修改的时间
    @Generated(hash = 1747970072)
    public ManHua(Long id, String title, String imgurl, String author,
            String infourl, String seewhere, String seewhereurl, String updatahere,
            long modifytime) {
        this.id = id;
        this.title = title;
        this.imgurl = imgurl;
        this.author = author;
        this.infourl = infourl;
        this.seewhere = seewhere;
        this.seewhereurl = seewhereurl;
        this.updatahere = updatahere;
        this.modifytime = modifytime;
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
    public String getImgurl() {
        return this.imgurl;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getInfourl() {
        return this.infourl;
    }
    public void setInfourl(String infourl) {
        this.infourl = infourl;
    }
    public String getSeewhere() {
        return this.seewhere;
    }
    public void setSeewhere(String seewhere) {
        this.seewhere = seewhere;
    }
    public String getSeewhereurl() {
        return this.seewhereurl;
    }
    public void setSeewhereurl(String seewhereurl) {
        this.seewhereurl = seewhereurl;
    }
    public String getUpdatahere() {
        return this.updatahere;
    }
    public void setUpdatahere(String updatahere) {
        this.updatahere = updatahere;
    }
    public long getModifytime() {
        return this.modifytime;
    }
    public void setModifytime(long modifytime) {
        this.modifytime = modifytime;
    }

    @Override
    public String toString() {
        return "ManHua{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", author='" + author + '\'' +
                ", infourl='" + infourl + '\'' +
                ", seewhere='" + seewhere + '\'' +
                ", seewhereurl='" + seewhereurl + '\'' +
                ", updatahere='" + updatahere + '\'' +
                ", modifytime=" + modifytime +
                '}';
    }
}
