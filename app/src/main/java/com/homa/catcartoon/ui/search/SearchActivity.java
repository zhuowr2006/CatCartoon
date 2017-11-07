package com.homa.catcartoon.ui.search;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseActivity;
import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.ui.List.bean.ListBean;
import com.homa.catcartoon.utils.ImageLoaderUtil;
import com.litesuits.android.log.Log;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Homa on 2017/11/7.
 */

public class SearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private BaseQuickAdapter adapter;
    private List<ListBean> list;

    private HttpManager httpManager;
    private String nextPageUrl;//下一页的url
    private String content;
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recom;
    }

    @Override
    public void onInit() {
        list = new ArrayList<>();
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.app_color));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        fanjuRcview.setLayoutManager(layoutManager);
        adapter = new SubareaAdapter(R.layout.item_home_list, list);
        adapter.openLoadAnimation();
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        adapter.setOnLoadMoreListener(this);
//       // 默认第一次加载会进入回调，如果不需要可以配置：
//        adapter.disableLoadMoreIfNotFullPage();
        fanjuRcview.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent();
//                boolean isonline = false;
//                intent.putExtra("type", position);
//                if (isonline) {
////                    intent.setClass(mActivity, TVActivity.class);
//                    mActivity.startActivity(intent);
//                } else {
//                    intent.setClass(mActivity, MainItemActivity.class);
//                    mActivity.startActivity(intent);
//                }
            }
        });
        content=getIntent().getStringExtra("content");
        httpManager = new HttpManager(this, this);
        HttpApiManager.getSearch(httpManager,content);
        swipeLayout.setRefreshing(true);
    }
    @Override
    public void onNext(String resulte, String method) {
        if (swipeLayout.isRefreshing()) {//刷新
            swipeLayout.setRefreshing(false);
            getData(resulte, list);
            adapter.setNewData(list);
        }else {
            //加载更多
            //成功获取更多数据
            List<ListBean> add = new ArrayList<>();
            getData(resulte, add);
            adapter.addData(add);
            adapter.loadMoreComplete();
        }


    }

    @Override
    public void onError(ApiException e, String method) {
        if (swipeLayout.isRefreshing()){//刷新
            Toast.makeText(this, "刷新失败", Toast.LENGTH_LONG).show();
            swipeLayout.setRefreshing(false);
        } else {
            //获取更多数据失败
            Toast.makeText(this, "加载失败", Toast.LENGTH_LONG).show();
            adapter.loadMoreFail();
        }
    }

    private void getData(String resulte, List<ListBean> data) {
        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.parse(resulte);
            System.out.println("ttttt");
            for (Element e : doc.select("div.item")) {
//                Log.i("排行数据",e.select("span").text()+e.select("a").attr("href")+e.select("a").attr("title"));
                ListBean listBean = new ListBean();
                listBean.setUrl(e.select("a").attr("href"));
                listBean.setImg(e.select("img").attr("src"));
//                listBean.setName(e.select("div.info").select("p.line").select("span.ib").text());
//                listBean.setSummary(e.select("div.info").select("p.line").select("span.ibcont").text());
                listBean.setTitle(e.select("img").attr("title"));
                listBean.setTip(e.select("p.tip").select("a").text());
//                data.add(listBean);
//                System.out.println("=="+e.toString());
//                System.out.println("=="+e.select("img").attr("src"));
//                System.out.println("=="+e.select("img").attr("title"));
//                System.out.println("=="+e.select("p.tip").select("a").text());
            }
//            System.out.println("=="+);
            String url=doc.select("div.page").select("a.next").attr("href");
//            nextPageUrl=""//去掉前面那部分只留后面那部分，就ok了
        } catch (Exception e) {
            Log.i("mytag", e.toString());
        }

    }


    @Override
    public void onLoadMoreRequested() {
        HttpApiManager.getSearch(httpManager,nextPageUrl);
    }

    @Override
    public void onRefresh() {
        HttpApiManager.getSearch(httpManager,"/search-"+content);
    }

    public class SubareaAdapter extends BaseQuickAdapter<ListBean, BaseViewHolder> {


        public SubareaAdapter(@LayoutRes int layoutResId, @Nullable List<ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ListBean item) {
            helper.setText(R.id.item_list_num, item.getTip());
            helper.setText(R.id.item_list_title, item.getTitle());
            helper.setText(R.id.item_list_zuoze, item.getName());
            helper.setText(R.id.item_list_jianjie, item.getSummary());
            ImageView img = helper.getView(R.id.item_list_img);
            ImageLoaderUtil.loader(SearchActivity.this, item.getImg(), img);

        }
    }
}