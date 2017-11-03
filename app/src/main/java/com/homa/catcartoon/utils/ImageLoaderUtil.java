package com.homa.catcartoon.utils;

import android.content.Context;
import android.widget.ImageView;

import com.homa.catcartoon.R;


/**
 * 图片加载管理类
 * Created by Homa on 2017/9/5.
 */
public class ImageLoaderUtil {


    /**
     * 图片加载方法
     *
     * @param context the context
     * @param url     the url
     * @param img     the img
     */
    public static void loader(Context context, String url, ImageView img) {
        int placeid = R.mipmap.ic_launcher;
        int errorid = R.mipmap.ic_launcher;
        GlideUtils.getInstance().LoadContextBitmap(context, url, img, placeid, errorid, null);
    }
    public static void loader(Context context, int id, ImageView img) {
        int placeid = R.mipmap.ic_launcher;
        int errorid = R.mipmap.ic_launcher;
        GlideUtils.getInstance().LoadContextBitmap(context, id, img, placeid, errorid, null);
    }


    public static void loaderCircle(Context context, int id, ImageView img) {
        int placeid = R.mipmap.ic_launcher;
        int errorid = R.mipmap.ic_launcher;
        GlideUtils.getInstance().LoadContextCircleBitmap(context, id, img);
    }

}
