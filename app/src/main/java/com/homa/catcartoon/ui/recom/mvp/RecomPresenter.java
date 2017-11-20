package com.homa.catcartoon.ui.recom.mvp;

import android.support.annotation.Nullable;

import com.homa.catcartoon.ui.recom.bean.Banner;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.List;

/**
 * Created by Homa on 2017/11/17.
 */

public class RecomPresenter implements RecomContract.Presenter{

    private final RecomModel recomModel;

    private final RecomContract.View recomView;
//    private RxFragment fragment;

    public RecomPresenter(@Nullable RecomModel recomModel, @Nullable RecomContract.View recomView) {
        this.recomModel = recomModel;
        this.recomView = recomView;
        this.recomView.setPresenter(this);
    }

    @Override
    public void start() {
        recomModel.GetDatas((RxFragment) recomView, new RecomDataSource.GetDataCallback() {
            @Override
            public void onGetData(List<Banner> banners, List<String> url, List<MySection> data) {
                recomView.getData(banners,url,data);
            }

            @Override
            public void onDataErro(ApiException e, String method) {
                recomView.getDataError(e,method);
            }
        });
    }
}
