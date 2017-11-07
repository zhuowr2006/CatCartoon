package com.homa.catcartoon.ui.ranking;

import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.ui.base.MxListener;
import com.homa.catcartoon.ui.base.PMlistener;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by Homa on 2017/11/6.
 */

public class rankM extends MxListener implements  HttpOnNextListener{

    PMlistener pMlistener;


    public rankM(PMlistener pVlistener) {
        this.pMlistener = pVlistener;
    }

    @Override
    public void startPostForFragment(RxFragment rxFragment) {
        HttpManager httpManager=new HttpManager(this,rxFragment);
        HttpApiManager.getRecommend(httpManager);
    }

    @Override
    public void onNext(String resulte, String method) {
        pMlistener.onNext(resulte, method);
    }

    @Override
    public void onError(ApiException e, String method) {
        pMlistener.onError(e);
    }
}
