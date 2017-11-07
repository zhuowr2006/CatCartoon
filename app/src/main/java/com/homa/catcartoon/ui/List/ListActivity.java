package com.homa.catcartoon.ui.List;

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

public class ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private BaseQuickAdapter adapter;
    private List<ListBean> list;

    private int pagenum = 1;
    private int type = 0;
    private HttpManager httpManager;

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
        type = getIntent().getIntExtra("type", 0);
        httpManager = new HttpManager(this, this);
        HttpApiManager.getCategory(httpManager, type, pagenum);
    }

    @Override
    public void onNext(String resulte, String method) {
        if (pagenum == 1) {//刷新
            swipeLayout.setRefreshing(false);
            getData(resulte, list);
            adapter.setNewData(list);
        } else {//加载更多
            //成功获取更多数据
            List<ListBean> add = new ArrayList<>();
            getData(resulte, add);
            adapter.addData(add);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onError(ApiException e, String method) {
        if (pagenum == 1) {//刷新
            //刷新错误
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

            for (Element e : doc.select("div.container").select("div.list").select("div.item")) {
//                Log.i("排行数据",e.select("span").text()+e.select("a").attr("href")+e.select("a").attr("title"));
                ListBean listBean = new ListBean();
                listBean.setUrl(e.select("a").attr("href"));
                listBean.setImg(e.select("div.img").select("img").attr("src"));
                listBean.setName(e.select("div.info").select("p.line").select("span.ib").text());
                listBean.setSummary(e.select("div.info").select("p.line").select("span.ibcont").text());
                listBean.setTitle(e.select("div.img").select("img").attr("title"));
                listBean.setTip(e.select("div.img").select("a").text());
                data.add(listBean);
//                System.out.println("=="+e.select("div.img").select("a").text());
            }
        } catch (Exception e) {
            Log.i("mytag", e.toString());
        }

    }


    @Override
    public void onLoadMoreRequested() {
        pagenum++;
        HttpApiManager.getCategory(httpManager, type, pagenum);
    }

    @Override
    public void onRefresh() {
        pagenum = 1;
        HttpApiManager.getCategory(httpManager, type, pagenum);
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
            ImageLoaderUtil.loader(ListActivity.this, item.getImg(), img);

        }
    }
}