package com.homa.catcartoon.ui.base;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * mvp 中p层提供给m层使用的(回调)接口
 * Created by WZG on 2016/12/26.
 */

public interface PMlistener {

    /**
     * 成功后回调方法
     *
     * @param resulte
     * @param mothead
     */
    void onNext(String resulte, String mothead);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param e
     */
    void onError(ApiException e);

}
