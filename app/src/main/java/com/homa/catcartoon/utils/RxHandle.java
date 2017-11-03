package com.homa.catcartoon.utils;

import android.os.Message;

import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * RxHandle
 * 用来替换Handle
 * 根据Activity生命周期自动关闭
 * 使用方法更简单
 * Created by Homa on 2017/9/4.
 */
public class RxHandle {

    /**
     * The Timer listener.
     */
    public HandleListener handleListener=null;

    /**
     * The interface Timer listener.
     */
    public interface HandleListener {

        void callBack(Message msg);
    };


    /**
     * 设置返回监听
     *
     * @param handleListener the timer listener
     */
    public RxHandle setHandleListener(HandleListener handleListener){
        this.handleListener=handleListener;
        return this;
    }

    /**
     * 发送请求
     *
     * @param activity the 带有Rx生命周期的activity
     * @param msg  Handle的Message消息类
     * @return the rx handle
     */
    public RxHandle sendHandle(final RxFragmentActivity activity, final Message msg){
        Observable.create(new ObservableOnSubscribe<Message>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Message> e) throws Exception {
                Message mg=msg;
                e.onNext(mg);
                e.onComplete();
            }
        }).compose(activity.<Message>bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Message>() {
            @Override
            public void accept(@NonNull Message message) throws Exception {
                handleListener.callBack(message);
            }
        });
        return this;
    }

    /**
     * 发送延时请求
     *
     * @param activity the 带有Rx生命周期的activity
     * @param msg  Handle的Message消息类
     * @param dealy    延时发送，毫秒
     * @return the rx handle
     */
    public RxHandle sendHandle(final RxFragmentActivity activity, final Message msg, final long dealy){
        Observable.create(new ObservableOnSubscribe<Message>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Message> e) throws Exception {
                Message mg=msg;
                Thread.sleep(dealy);
                e.onNext(mg);
                e.onComplete();
            }
        }).compose(activity.<Message>bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Message>() {
            @Override
            public void accept(@NonNull Message message) throws Exception {
                handleListener.callBack(message);
            }
        });

        return this;
    }
    /**
     * 发送延时请求
     *
     * @param activity the 带有Rx生命周期的activity
     * @param dealy    延时发送，毫秒
     * @return the rx handle
     */
    public RxHandle sendHandle(final RxFragmentActivity activity, final long dealy){

        Observable.create(new ObservableOnSubscribe<Message>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Message> e) throws Exception {
                Message mg=new Message();
                Thread.sleep(dealy);
                e.onNext(mg);
            }
        }).compose(activity.<Message>bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Message>() {
            @Override
            public void accept(@NonNull Message message) throws Exception {
                handleListener.callBack(message);
            }
        });

        return this;
    }

}
