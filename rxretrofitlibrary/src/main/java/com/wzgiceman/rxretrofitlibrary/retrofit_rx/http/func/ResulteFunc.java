package com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.func;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.HttpTimeException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 服务器返回数据判断
 * Created by WZG on 2017/3/23.
 */

public class ResulteFunc implements Function<Object,Object> {

    @Override
    public Object apply(@NonNull Object o) throws Exception {
        if (o == null || "".equals(o.toString())) {
            throw new HttpTimeException("数据错误");
        }
        return o;
    }
}
