package com.homa.catcartoon.ui.base;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * m层提供给p层的接口
 * Created by WZG on 2016/12/26.
 */

public interface Mlistener {

    /**
     * 开始任务
     * @param rxAppCompatActivity
     */
    void startPostForActivity(RxAppCompatActivity rxAppCompatActivity);
    /**
     * 开始任务
     * @param rxFragment
     */
    void startPostForFragment(RxFragment rxFragment);

}
