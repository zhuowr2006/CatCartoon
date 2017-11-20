package com.homa.catcartoon.ui.ranking.mvp;

import android.support.annotation.Nullable;

import com.homa.catcartoon.ui.ranking.bean.RankingBean;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Homa on 2017/11/17.
 */

public class RankPresenter implements RankContract.Presenter{

    private final RankModel rankModel;

    private final RankContract.View rankView;
//    private RxFragment fragment;

    private List<RankingBean> data;

    public RankPresenter(@Nullable RankModel rankModel, @Nullable RankContract.View rankView) {
        this.rankModel = rankModel;
        this.rankView = rankView;
        this.rankView.setPresenter(this);
        data=new ArrayList<>();
    }

    @Override
    public void start() {
        rankModel.GetDatas((RxFragment) rankView, new RankDataSource.GetDataCallback() {
            @Override
            public void onGetData(List<RankingBean> data) {
                setData(data);
                rankView.getData(data);
            }

            @Override
            public void onDataErro(ApiException e, String method) {
                rankView.getDataError(e,method);
            }
        });
    }

    public List<RankingBean> getData() {
        return data;
    }

    public RankPresenter setData(List<RankingBean> data) {
        this.data = data;
        return this;
    }
}
