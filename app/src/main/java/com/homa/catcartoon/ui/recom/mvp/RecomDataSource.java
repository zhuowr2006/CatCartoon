package com.homa.catcartoon.ui.recom.mvp;

import android.support.annotation.NonNull;

import com.homa.catcartoon.ui.recom.bean.Banner;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.List;

/**
 * Created by Homa on 2017/11/17.
 */

public interface RecomDataSource {

    interface GetDataCallback {

        void onGetData(List<Banner> banners, List<String> url, List<MySection> data);
        void onDataErro(ApiException e, String method);
    }

        void GetDatas(RxFragment rxFragment,@NonNull GetDataCallback callback);
}
