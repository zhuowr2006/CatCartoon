package com.homa.catcartoon.ui.ranking;


import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseFragment;
import com.homa.catcartoon.ui.info.InfoActivity;
import com.homa.catcartoon.ui.ranking.bean.RankingBean;
import com.homa.catcartoon.ui.ranking.mvp.RankContract;
import com.homa.catcartoon.ui.ranking.mvp.RankPresenter;
import com.homa.catcartoon.utils.UrlUtils;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/11.
 */

public class RankingFragment extends BaseFragment implements RankContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String TAG = "RankingFragment";

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    private BaseQuickAdapter adapter;
    private List<RankingBean> list;
    //    private rankP p;
    private RankPresenter presenter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recom;
    }

    @Override
    public void onInit() {
        list = new ArrayList<>();
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.app_color));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        fanjuRcview.setLayoutManager(layoutManager);
        adapter = new SubareaAdapter(R.layout.item_home_ranking, list);
        adapter.openLoadAnimation();
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        adapter.setOnLoadMoreListener(this);
//       // 默认第一次加载会进入回调，如果不需要可以配置：
//        adapter.disableLoadMoreIfNotFullPage();
        fanjuRcview.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RankingBean bean = list.get(position);
//                    System.out.println("=="+UrlUtils.getComicUrl(bean.getUrl()));
                Intent i = new Intent(getActivity(), InfoActivity.class);
                i.putExtra("url", UrlUtils.getComicUrl(bean.getUrl()));
                i.putExtra("title", bean.getTitle());
                startActivity(i);
            }
        });
//        p.startPostForFragment(this);
        presenter.start();
    }

    @Override
    public void getData(List<RankingBean> data) {
        swipeLayout.setRefreshing(false);
        mCurrentCounter = 0;
        list.clear();
        for (int i = 0; i < 20; i++) {
            list.add(data.get(i));
        }
        adapter.setNewData(list);
        mCurrentCounter = adapter.getData().size();
    }

    @Override
    public void getDataError(ApiException e, String method) {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(RankContract.Presenter presenter) {
        this.presenter = (RankPresenter) presenter;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    private boolean isErr = false;
    private int TOTAL_COUNTER = 99;
    private int mCurrentCounter = 0;

    @Override
    public void onLoadMoreRequested() {
        fanjuRcview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    adapter.loadMoreEnd();
                } else {
                    if (!isErr) {
                        //成功获取更多数据
                        List<RankingBean> add = new ArrayList<>();
                        for (int i = mCurrentCounter; i < mCurrentCounter + 20; i++) {
                            add.add(presenter.getData().get(i));
                        }
                        adapter.addData(add);
                        mCurrentCounter = adapter.getData().size();
                        adapter.loadMoreComplete();
                    } else {
                        //获取更多数据失败
                        isErr = true;
//                        Toast.makeText(get, R.string.network_err, Toast.LENGTH_LONG).show();
                        adapter.loadMoreFail();

                    }
                }
            }

        }, 1000);
    }

    public class SubareaAdapter extends BaseQuickAdapter<RankingBean, BaseViewHolder> {


        public SubareaAdapter(@LayoutRes int layoutResId, @Nullable List<RankingBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RankingBean item) {
            helper.setText(R.id.item_rk_title, item.getTitle());
            helper.setText(R.id.item_rk_num, item.getNum());
            ImageView img = helper.getView(R.id.item_rk_hot);
            if (helper.getLayoutPosition() < 10) {
                img.setVisibility(View.VISIBLE);
            } else {
                img.setVisibility(View.GONE);
            }
        }
    }
}
