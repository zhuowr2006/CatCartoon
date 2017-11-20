package com.wzgiceman.rxretrofitlibrary.retrofit_rx.http;

import android.content.Context;
import android.util.Log;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.cookie.CacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Homa on 2017/11/20.
 */

public class OkHttpUtils {
    private static OkHttpClient client;

    public static OkHttpClient getClient(final BaseApi basePar, Context context) {
        if (client == null) {
            synchronized (OkHttpUtils.class) {
                if (client == null) {
                    File okcache = new File(context.getExternalCacheDir(), "okhttpcache");
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(basePar.getConnectionTime(), TimeUnit.SECONDS)
                             .addNetworkInterceptor(new CacheInterceptor())//增加请求缓存
                            //设置缓存 10M
                            .cache(new Cache(okcache, 10 * 1024 * 1024));
                    if (RxRetrofitApp.isDebug()) {//log日志
                        //日志显示级别
                        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
                        //新建log拦截器
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Log.d("RxRetrofit", "Retrofit====Message:" + message);
                            }
                        });
                        loggingInterceptor.setLevel(level);
                        builder.addInterceptor(loggingInterceptor);
                    }
                    client=builder.build();
                }
            }
        }
        return client;
    }
}
