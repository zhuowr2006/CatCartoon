package com.homa.catcartoon.ui.ranking.mvp;

import android.support.annotation.NonNull;

import com.homa.catcartoon.ui.ranking.bean.RankingBean;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.List;

/**
 * Created by Homa on 2017/11/17.
 */

public interface RankDataSource {

    interface GetDataCallback {

        void onGetData(List<RankingBean> data);
        void onDataErro(ApiException e, String method);
    }

    void GetDatas(RxFragment rxFragment, @NonNull GetDataCallback callback);
}
