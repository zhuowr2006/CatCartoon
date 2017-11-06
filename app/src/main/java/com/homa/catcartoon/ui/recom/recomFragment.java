package com.homa.catcartoon.ui.recom;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseFragment;
import com.homa.catcartoon.ui.base.Vlistener;
import com.homa.catcartoon.ui.recom.adapter.SectionAdapter;
import com.homa.catcartoon.utils.BannerGlideImageLoader;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/11.
 */

public class recomFragment extends BaseFragment implements Vlistener ,SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "recomFragment";
    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private  Banner banner;
    private  View top;
    private recomP p;

    private SectionAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recom;
    }

    @Override
    public void onInit() {
        p = new recomP(this);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.app_color));

        fanjuRcview.setLayoutManager(new GridLayoutManager(mActivity, 2));
        adapter = new SectionAdapter(mActivity, R.layout.item_home_recom_body, R.layout.item_home_recom_head, p.getData());

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
//                if (!recomP.getData().get(position).isHeader) {
//                    VideoItemBean bean = recomP.getData().get(position).t;
//                    System.out.println(bean.getPic() + "=============");
//                    Intent i = new Intent();
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("videoItemdata", bean);
//                    i.setClass(getActivity(), VideoInfoActivity.class);
//                    i.putExtras(bundle);
//                    startActivity(i);
//                }
            }
        });
        fanjuRcview.setAdapter(adapter);
        swipeLayout.setRefreshing(true);


        p.startPostForFragment(this);


    }

    private boolean isFrist=true;
    @Override
    public void onNext(String name) {
        swipeLayout.setRefreshing(false);
        if (swipeLayout.isRefreshing()&&!isFrist){
            adapter.setNewData(p.getData());
            //设置图片集合
            banner.setImages(p.getBanners());
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            return;
        }
        adapter.openLoadAnimation();
        fanjuRcview.setAdapter(adapter);
        //设置图片集合
        banner.setImages(p.getUrl());
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        isFrist=false;
    }

    @Override
    public void onError(ApiException e) {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {

        p.startPostForFragment(this);
    }
}
