package com.homa.catcartoon.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseActivity;
import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.ui.info.bean.ChapterBean;
import com.homa.catcartoon.ui.info.bean.OtherBean;
import com.homa.catcartoon.utils.ImageLoaderUtil;
import com.homa.catcartoon.utils.UrlUtils;
import com.litesuits.android.log.Log;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Homa on 2017/11/7.
 */

public class InfoActivity extends BaseActivity {


    @BindView(R.id.info_img)
    ImageView infoImg;
    @BindView(R.id.info_title)
    TextView infoTitle;
    @BindView(R.id.info_zuoze)
    TextView infoZuoze;
    @BindView(R.id.info_jianjie)
    TextView infoJianjie;
    @BindView(R.id.info_state)
    TextView infoState;
    @BindView(R.id.info_time)
    TextView infoTime;
    @BindView(R.id.info_jishu_rcview)
    RecyclerView infoJishuRcview;
    @BindView(R.id.info_tuijian_rcview)
    RecyclerView infoTuijianRcview;
    @BindView(R.id.load_layout)
    RelativeLayout loadLayout;

    private SubareaAdapter adapter;
    private BaseQuickAdapter bottomadapter;
    private List<ChapterBean> chapterList;
    private List<OtherBean> otherMoreList;

    private String url;

    private HttpManager httpManager;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_info;
    }

    @Override
    public void onInit() {

        chapterList = new ArrayList<>();
        otherMoreList = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        infoJishuRcview.setLayoutManager(layoutManager);
        adapter = new SubareaAdapter(R.layout.item_info, chapterList);

        adapter.openLoadAnimation();
        infoJishuRcview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapterx, View view, int position) {
                adapter.setDefSelect(position);
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

        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setInitialPrefetchItemCount(2);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        infoTuijianRcview.setLayoutManager(ms);
        bottomadapter = new SubareaAdapter2(R.layout.item_info_bottom, otherMoreList);
        bottomadapter.openLoadAnimation();
        infoTuijianRcview.setAdapter(bottomadapter);
        bottomadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OtherBean bean = otherMoreList.get(position);
//                    System.out.println("=="+UrlUtils.getComicUrl(bean.getUrl()));
                Intent i = new Intent(InfoActivity.this, InfoActivity.class);
                i.putExtra("url", UrlUtils.getComicUrl(bean.getUrl()));
                startActivity(i);
            }
        });


        url = getIntent().getStringExtra("url");
//        String title = getIntent().getStringExtra("title");
        setToptext("详情");

        httpManager = new HttpManager(this, this);
        HttpApiManager.getInfo(httpManager, url);
    }

    @Override
    public void onNext(final String resulte, String method) {
        getData(resulte, chapterList, otherMoreList);
        infoJishuRcview.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(chapterList);
                bottomadapter.setNewData(otherMoreList);
                loadLayout.setVisibility(View.GONE);
            }
        }, 1000);
    }

    @Override
    public void onError(ApiException e, String method) {
        Toast.makeText(this, "加载失败", Toast.LENGTH_LONG).show();
    }

    private void getData(String resulte, List<ChapterBean> data, List<OtherBean> OtherBean) {
        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.parse(resulte);
//            System.out.println("ttttt"+doc.select("div.comicInfo").select("div.img").select("img").attr("src"));
            ImageLoaderUtil.loader(this, doc.select("div.comicInfo").select("div.img").select("img").attr("src"), infoImg);

//            System.out.println("ttttt"+doc.select("div.comicInfo").select("div.img").select("img").attr("title"));
            infoTitle.setText(doc.select("div.comicInfo").select("div.img").select("img").attr("title"));
            boolean isend = false;
            for (Element e : doc.select("div.comicInfo").select("div.info").select("span")) {
//                System.out.println("ttt"+e.text());
                if (e.text().contains("作 者")) {
//                    System.out.println("ttttt"+e.select("a").text());
                    infoZuoze.setText(e.select("a").text());
                }
                if (e.text().contains("更 新")) {
//                    System.out.println("ttttt"+e.text());
                    infoTime.setText(e.text());
                }
                if (e.text().contains("状 态")) {
//                    System.out.println("ttttt"+e.text());
                    infoState.setText(e.text());
                    isend = true;
                }
                if (isend && !e.text().contains("状 态")) {
                    infoJianjie.setText(e.text());
                    isend = false;
                }
            }

            for (Element e : doc.select("div.chapterList").select("div.list").select("a")) {
                data.add(new ChapterBean(e.text(), e.attr("href")));
            }
            for (Element e : doc.select("div.otherMore").select("div.item")) {

//                System.out.println("--=="+e.select("a").attr("href"));
//                System.out.println("--=="+e.select("img").attr("src"));
//                System.out.println("--=="+e.select("img").attr("title"));
//                System.out.println("--=="+e.select("span").text());
                otherMoreList.add(new OtherBean(e.select("img").attr("title"), e.select("span").text(), e.select("img").attr("src"), e.select("a").attr("href")));
            }

        } catch (Exception e) {
            Log.i("mytag", e.toString());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class SubareaAdapter extends BaseQuickAdapter<ChapterBean, BaseViewHolder> {
        private int defItem = -1;


        public SubareaAdapter(@LayoutRes int layoutResId, @Nullable List<ChapterBean> data) {
            super(layoutResId, data);
        }

        public void setDefSelect(int position) {
            this.defItem = position;
            notifyDataSetChanged();
        }


        @Override
        protected void convert(BaseViewHolder helper, ChapterBean item) {
            CardView card = helper.getView(R.id.card_view);
            if (defItem != -1) {
                if (defItem == helper.getPosition()) {
                    card.setCardBackgroundColor(getResources().getColor(R.color.app_color));
                } else {
                    card.setCardBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
            }
            helper.setText(R.id.item_info_jishu, item.getName());
        }

    }

    public class SubareaAdapter2 extends BaseQuickAdapter<OtherBean, BaseViewHolder> {


        public SubareaAdapter2(@LayoutRes int layoutResId, @Nullable List<OtherBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OtherBean item) {
            helper.setText(R.id.item_srarch_title, item.getTitle());
            helper.setText(R.id.item_srarch_jianjie, item.getTip());
            ImageView img = helper.getView(R.id.item_srarch_img);
            ImageLoaderUtil.loader(InfoActivity.this, item.getImg(), img);
        }

    }
}
