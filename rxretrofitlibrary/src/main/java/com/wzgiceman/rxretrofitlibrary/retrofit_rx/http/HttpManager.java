package com.wzgiceman.rxretrofitlibrary.retrofit_rx.http;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
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
    private HttpOnNextListener onNextListener;
    private SoftReference<HttpOnNextObserverListener> onNextSubListener;
    private SoftReference<RxAppCompatActivity> appCompatActivity;
    private SoftReference<RxFragment> appFragment;


    public HttpManager(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        this.onNextListener = onNextListener;
        this.appCompatActivity = new SoftReference(appCompatActivity);
    }

    public HttpManager(HttpOnNextListener onNextListener, RxFragment appFragment) {
        this.onNextListener =onNextListener;
        this.appFragment = new SoftReference(appFragment);
    }




    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(final BaseApi basePar) {
        httpDeal(basePar.getObservable(), basePar);
    }

    /**
     * 获取Retrofit对象
     *
     * @param basePar
     * @return
     */
    public Retrofit getReTrofit(final BaseApi basePar) {
        OkHttpClient client;
        if (appCompatActivity!=null){
            client=OkHttpUtils.getClient(basePar,appCompatActivity.get());
        }else {
            client=OkHttpUtils.getClient(basePar,appFragment.get().getContext());
        }

        /*创建retrofit对象*/
        final Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(basePar.getBaseUrl())
                .build();
        return retrofit;
    }

    /**
     * RxRetrofit处理
     *
     * @param observable
     * @param basePar
     */
    public void httpDeal(Observable observable, BaseApi basePar) {
         if (appCompatActivity!=null){
                /*失败后的retry配置*/
             observable = observable.retryWhen(new RetryWhenNetworkException(basePar.getRetryCount(),
                     basePar.getRetryDelay(), basePar.getRetryIncreaseDelay()))
                /*异常处理*/
                     .onErrorResumeNext(new ExceptionFunc())
                /*生命周期管理*/
                     //.compose(appCompatActivity.get().bindToLifecycle())
                     //Note:手动设置在activity onDestroy的时候取消订阅
                     .compose(appCompatActivity.get().bindUntilEvent(ActivityEvent.DESTROY))
                /*返回数据统一判断*/
                     .map(new ResulteFunc())
                /*http请求线程*/
                     .subscribeOn(Schedulers.io())
                     .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                     .observeOn(AndroidSchedulers.mainThread());
         }else {
                /*失败后的retry配置*/
             observable = observable.retryWhen(new RetryWhenNetworkException(basePar.getRetryCount(),
                     basePar.getRetryDelay(), basePar.getRetryIncreaseDelay()))
                /*异常处理*/
                     .onErrorResumeNext(new ExceptionFunc())
                /*生命周期管理*/
                     //.compose(appCompatActivity.get().bindToLifecycle())
                     //Note:手动设置在activity onDestroy的时候取消订阅
                     .compose(appFragment.get().bindUntilEvent(FragmentEvent.DESTROY))
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
            onNextSubListener.get().onNext(observable, basePar.getMethod());
        }
        /*数据String回调*/
        if (onNextListener != null) {
            if (appCompatActivity!=null){
                ProgressObserver subscriber = new ProgressObserver(basePar, onNextListener, appCompatActivity.get());
                observable.subscribe(subscriber);
            }else {
                ProgressObserver subscriber = new ProgressObserver(basePar, onNextListener, appFragment.get().getContext());
                observable.subscribe(subscriber);
            }
        }
    }

}
