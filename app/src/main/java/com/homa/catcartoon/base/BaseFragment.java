package com.homa.catcartoon.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Homa on 2017/9/1.
 * 基础Fragment类
 */

public abstract class BaseFragment extends RxFragment implements BaseInit {
    protected Unbinder unbinder;
    protected Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mActivity= getActivity();
        onInit();
        setListener();
        return view;
    }
    /*获取LayoutId*/
    public abstract
    @LayoutRes
    int getLayoutResId();

    /*界面操作*/
    @Override
    public void onInit() {
    }
    @Override
    public void setListener() {

    }

}
