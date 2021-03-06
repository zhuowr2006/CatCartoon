package com.homa.catcartoon.ui.recom;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseFragment;
import com.homa.catcartoon.ui.info.InfoActivity;
import com.homa.catcartoon.ui.recom.adapter.SectionAdapter;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.homa.catcartoon.ui.recom.bean.RecomBean;
import com.homa.catcartoon.ui.recom.mvp.RecomContract;
import com.homa.catcartoon.utils.BannerGlideImageLoader;
import com.homa.catcartoon.utils.UrlUtils;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/11.
 */

public class recomFragment extends BaseFragment implements RecomContract.View ,SwipeRefreshLayout.OnRefreshListener{
    //implements Vlistener
    private static final String TAG = "recomFragment";
    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private  Banner banner;
    private  View top;
//    private recomP p;
    private RecomContract.Presenter mPresenter;

    private SectionAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recom;
    }

    @Override
    public void onInit() {
//        p = new recomP(this);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.app_color));

        fanjuRcview.setLayoutManager(new GridLayoutManager(mActivity, 2));
        adapter = new SectionAdapter(mActivity, R.layout.item_home_recom_body, R.layout.item_home_recom_head, new ArrayList());

        top = mActivity.getLayoutInflater().inflate(R.layout.item_home_bangumi_banner, (ViewGroup) fanjuRcview.getParent(), false);
        adapter.addHeaderView(top);
        banner = (Banner) top.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideImageLoader());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(new OnBannerListener() {//监听单击
            @Override
            public void OnBannerClick(int position) {
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection section= (MySection)adapter.getData().get(position);
                if (!section.isHeader) {
                    RecomBean bean=section.t;
//                    System.out.println("=="+UrlUtils.getComicUrl(bean.getUrl()));
                    Intent i = new Intent(getActivity(), InfoActivity.class);
                    i.putExtra("url", UrlUtils.getComicUrl(bean.getUrl()));
                    i.putExtra("title", bean.getTitle());
                    startActivity(i);
                }
            }
        });
        fanjuRcview.setAdapter(adapter);
        swipeLayout.setRefreshing(true);

//        p.startPostForFragment(this);

        if (mPresenter!=null){
            mPresenter.start();
        }

    }

    private boolean isFrist=true;
//    @Override
//    public void onNext(String name) {
//        swipeLayout.setRefreshing(false);
//        if (swipeLayout.isRefreshing()&&!isFrist){
//            System.out.println(p.getData().size()+"");
//            adapter.setNewData(p.getData());
//            //设置图片集合
//            banner.setImages(p.getBanners());
//            //banner设置方法全部调用完毕时最后调用
//            banner.start();
//            return;
//        }
//        adapter.setNewData(p.getData());
//        fanjuRcview.setAdapter(adapter);
//        //设置图片集合
//        banner.setImages(p.getUrl());
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();
//        isFrist=false;
//    }

//    @Override
//    public void onError(ApiException e) {
//        swipeLayout.setRefreshing(false);
//    }

    @Override
    public void onRefresh() {
//        p.cleran();
//        p.startPostForFragment(this);
        mPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.start();
        System.out.println("tttttttttt");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner!=null){
            banner.startAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner!=null){
            banner.startAutoPlay();
        }
        System.out.println("ccccccc");
    }

    @Override
    public void getData(List<com.homa.catcartoon.ui.recom.bean.Banner> banners, List<String> url, List<MySection> data) {
        swipeLayout.setRefreshing(false);
        if (swipeLayout.isRefreshing()&&!isFrist){
            adapter.setNewData(data);
            //设置图片集合
            banner.update(banners);
            //banner设置方法全部调用完毕时最后调用
            banner.startAutoPlay();
            return;
        }
        adapter.setNewData(data);
        fanjuRcview.setAdapter(adapter);
        //设置图片集合
        banner.setImages(url);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        isFrist=false;
    }

    @Override
    public void getDataError(ApiException e, String method) {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(RecomContract.Presenter presenter) {
        mPresenter=presenter;
    }
}
