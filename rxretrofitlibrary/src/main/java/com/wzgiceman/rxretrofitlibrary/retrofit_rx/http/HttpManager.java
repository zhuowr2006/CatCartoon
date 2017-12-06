package com.wzgiceman.rxretrofitlibrary.retrofit_rx.http;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Observers.ProgressObserver;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.RetryWhenNetworkException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.func.ExceptionFunc;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.func.ResulteFunc;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextObserverListener;

import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * http交互处理类
 * Created by WZG on 2016/7/16.
 */
public class HttpManager {
    /*软引用對象*/
//    private SoftReference<HttpOnNextListener> onNextListener;
//    private HttpOnNextListener onNextListener;
    private SoftReference<HttpOnNextObserverListener> onNextSubListener;
    private SoftReference<RxAppCompatActivity> appCompatActivity;
    private SoftReference<RxFragment> appFragment;
    private BaseApi api;

    public HttpManager(RxAppCompatActivity appCompatActivity,BaseApi api) {
        this.appCompatActivity = new SoftReference(appCompatActivity);
        this.api = api;
    }

    public HttpManager(RxFragment rxFragment,BaseApi api) {
        this.appFragment = new SoftReference(rxFragment);
        this.api = api;
    }



//
//    /**
//     * 处理http请求
//     *
//     * @param basePar 封装的请求数据
//     */
//    public void doHttpDeal(final BaseApi basePar,) {
//        httpDeal(basePar.getObservable(), basePar);
//    }

    /**
     * 获取Retrofit对象
     *
     * @param basePar
     * @return
     */
    public <T> T getReTrofit(final Class<T> s) {
        OkHttpClient client;
        if (appCompatActivity!=null){
            client=OkHttpUtils.getClient(api,appCompatActivity.get());
        }else {
            client=OkHttpUtils.getClient(api,appFragment.get().getContext());
        }

        /*创建retrofit对象*/
        final Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(api.getBaseUrl())
                .build();
        return retrofit.create(s);
    }

    /**
     * RxRetrofit处理
     *
     * @param observable
     * @param api
     */
    public void httpDeal(Observable observable,HttpOnNextListener onNextListener) {
         if (appCompatActivity!=null){
                /*失败后的retry配置*/
             observable = observable.retryWhen(new RetryWhenNetworkException(api.getRetryCount(),
                     api.getRetryDelay(), api.getRetryIncreaseDelay()))
                /*异常处理*/
                     .onErrorResumeNext(new ExceptionFunc())
                /*生命周期管理*/
                     //.compose(appCompatActivity.get().bindToLifecycle())
                     //Note:手动设置在activity onDestroy的时候取消订阅
                     .compose(appCompatActivity.get().bindToLifecycle())
                /*返回数据统一判断*/
                     .map(new ResulteFunc())
                /*http请求线程*/
                     .subscribeOn(Schedulers.io())
                     .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                     .observeOn(AndroidSchedulers.mainThread());
         }else {
                /*失败后的retry配置*/
             observable = observable.retryWhen(new RetryWhenNetworkException(api.getRetryCount(),
                     api.getRetryDelay(), api.getRetryIncreaseDelay()))
                /*异常处理*/
                     .onErrorResumeNext(new ExceptionFunc())
                /*生命周期管理*/
                     //.compose(appCompatActivity.get().bindToLifecycle())
                     //Note:手动设置在activity onDestroy的时候取消订阅
                     .compose(appFragment.get().bindToLifecycle())
                /*返回数据统一判断*/
                     .map(new ResulteFunc())
                /*http请求线程*/
                     .subscribeOn(Schedulers.io())
                     .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                     .observeOn(AndroidSchedulers.mainThread());
         }

        /*ober回调，链接式返回*/
        if (onNextSubListener != null && null != onNextSubListener.get()) {
            onNextSubListener.get().onNext(observable, api.getMethod());
        }
        /*数据String回调*/
        if (onNextListener != null) {
            ProgressObserver subscriber;
            if (appCompatActivity!=null){
                subscriber= new ProgressObserver(api, onNextListener);
            }else {
                subscriber = new ProgressObserver(api, onNextListener);
            }
            observable.subscribe(subscriber);
        }
    }
}
