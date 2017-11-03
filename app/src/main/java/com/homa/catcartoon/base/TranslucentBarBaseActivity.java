package com.homa.catcartoon.base;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/17.
 */

public abstract class TranslucentBarBaseActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());//把设置布局文件的操作交给继承的子类 //绑定activity
        ButterKnife.bind(this);//VIEW绑定
        initView();
        setListener();
    }

    protected abstract void initView();
    protected abstract void setListener();

    /**
     * 返回当前Activity布局文件的id
     *
     * @return
     */
    abstract protected int getLayoutResId();
}
