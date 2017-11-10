package com.homa.catcartoon.utils;

/**
 * Created by Homa on 2017/11/8.
 */

public class UrlUtils {
     // /comic/21847/
    //获取漫画url后半段,也就是 /comic 以后的这个 “/21847/”
    public static String getComicUrl(String url){
        String newUrl= url.substring(url.indexOf("comic") + 5, url.length());//去掉前面那部分只留后面那部分，就ok了
        return newUrl;
    }
    //获取漫画url后半段,也就是 /comic 以后的这个 “/21847/”
    public static String getName(String name){
        String newUrl= name.substring(name.lastIndexOf("-")+1, name.length());//去掉前面那部分只留后面那部分，就ok了
        return newUrl;
    }
}
