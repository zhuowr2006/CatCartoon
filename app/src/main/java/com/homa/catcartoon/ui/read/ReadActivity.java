package com.homa.catcartoon.ui.read;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseActivity;
import com.homa.catcartoon.data.ManHua;
import com.homa.catcartoon.data.ManHuaDaoUtils;
import com.homa.catcartoon.net.HttpApiManager;
import com.homa.catcartoon.utils.UrlUtils;
import com.litesuits.android.log.Log;
import com.litesuits.common.assist.Check;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import butterknife.BindView;
import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by Homa on 2017/11/7.
 */

public class ReadActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.photo_view)
    PhotoDraweeView photoView;
    @BindView(R.id.read_back)
    ImageView readBack;
    @BindView(R.id.read_top_layout)
    RelativeLayout readTopLayout;
    @BindView(R.id.load_layout)
    RelativeLayout loadLayout;
    @BindView(R.id.up_chapter)
    TextView upChapter;
    @BindView(R.id.up_page)
    TextView upPage;
    @BindView(R.id.next_page)
    TextView nextPage;
    @BindView(R.id.next_chapter)
    TextView nextChapter;
    @BindView(R.id.read_title)
    TextView readTitle;
    @BindView(R.id.load_text)
    TextView loadText;


    private HttpManager httpManager;

    private String upChapterStr;//上一章
    private String upPageStr;//上一页

    private String nextPageStr;//下一页
    private String nextChapterStr;//下一章

    private String SeewhereTitle;//看到哪一话

    private String nowUrl;//现在观看的地址
    private ManHua manHua;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_read;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isExtendToBaseLayout = false;
        //        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInit() {

        readBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        upChapter.setOnClickListener(this);
        upPage.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        nextChapter.setOnClickListener(this);

        manHua = (ManHua) getIntent().getSerializableExtra("data");
        SeewhereTitle = manHua.getSeewhere();
        nowUrl = manHua.getSeewhereurl();
        httpManager = new HttpManager(this, this);
        HttpApiManager.getInfo(httpManager, nowUrl);
//        loadLayout.setVisibility(View.VISIBLE);

    }


    @Override
    public void onNext(final String resulte, String method) {
        getData(resulte);
    }

    @Override
    public void onError(ApiException e, String method) {
//        if (pagenum == 1) {//刷新
//            //刷新错误
        Toast.makeText(this, "刷新失败", Toast.LENGTH_LONG).show();
//            swipeLayout.setRefreshing(false);
//        } else {
//            //获取更多数据失败
//            Toast.makeText(this, "加载失败", Toast.LENGTH_LONG).show();
//            adapter.loadMoreFail();
//        }
    }


    private void getData(String resulte) {

        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.parse(resulte);
//            System.out.println("cccccccccccc");
            String img = doc.select("div.item").select("a").select("img").attr("src").toString();
            System.out.println(doc.select("div.item").select("a").select("img").attr("src").toString());
//            System.out.println(doc.select("div.item").select("a").attr("href").toString());
//            System.out.println(doc.select("div.viewTool").toString());
//            System.out.println(doc.select("span.center").select("em").text());
            String title = doc.select("span.center").select("em").text();
//            System.out.println(UrlUtils.getName(title) + "=====");//拿这个去上一个界面对比
            SeewhereTitle = UrlUtils.getName(title);
            for (Element e : doc.select("div.viewTool").select("a")) {
                if (e.text().equals("上一章")) {
                    if (e.attr("href").contains("第一章")) {
//                        System.out.println("已是第一章");
                        upChapterStr = "";
                    } else {
                        upChapterStr = e.attr("href").toString();
//                        System.out.println(e.attr("href").toString());
                    }
                }
                if (e.text().equals("上一页")) {
                    if (e.attr("href").contains("第一页")) {
//                        System.out.println("已是第一页");
                        upPageStr = "";
                    } else {
                        upPageStr = e.attr("href").toString();
                        System.out.println(e.attr("href").toString());
                    }
                }
                if (e.text().equals("下一页")) {
                    if (e.attr("href").contains("最后一页")) {
//                        System.out.println("已是最后一页");
                        nextPageStr = "";
                    } else {
                        nextPageStr = e.attr("href").toString();
//                        System.out.println(e.attr("href").toString());
                    }

                }
                if (e.text().equals("下一章")) {
                    if (e.attr("href").contains("最后一章")) {
//                        System.out.println("已是最后一章");
                        nextChapterStr = "";
                    } else {
                        nextChapterStr = e.attr("href").toString();
//                        System.out.println(e.attr("href").toString());
                    }
                }
            }
            readTitle.setText(SeewhereTitle);
//            photoView.setPhotoUri(Uri.parse(img));

//            FrescoHelper.getFrescoImg(this, img, 0, 0, new LoadFrescoListener() {
//                @Override
//                public void onSuccess(Bitmap bitmap) {
//                    photoView.setImageBitmap(bitmap);
//                }
//
//                @Override
//                public void onFail() {
//
//                }
//            });
//            ImageLoaderUtil.loader(this,img,photoView);
            loadText.setText("加载中....");
            loadText.setVisibility(View.VISIBLE);
            Glide.with(this) // could be an issue!
                    .load(img)
                    .asBitmap().listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    loadText.setText("加载失败");
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    loadText.setVisibility(View.GONE);
                    return false;
                }
            })
                    .into(photoView);

        } catch (Exception e) {
            Log.i("mytag", e.toString());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_chapter:
                if (Check.isEmpty(upChapterStr)) {
                    Toast.makeText(this, "已经是第一章了", Toast.LENGTH_SHORT).show();
                    return;
                }
                nowUrl = UrlUtils.getComicUrl(upChapterStr);
                break;
            case R.id.up_page:
                if (upPageStr.equals("")) {
                    Toast.makeText(this, "已经是第一页了", Toast.LENGTH_SHORT).show();
                    return;
                }
                nowUrl = UrlUtils.getComicUrl(upPageStr);
                break;
            case R.id.next_page:
                if (nextPageStr.equals("")) {
                    Toast.makeText(this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    return;
                }
                nowUrl = UrlUtils.getComicUrl(nextPageStr);
                break;
            case R.id.next_chapter:
                if (nextChapterStr.equals("")) {
                    Toast.makeText(this, "已经是最后一章了", Toast.LENGTH_SHORT).show();
                    return;
                }
                nowUrl = UrlUtils.getComicUrl(nextChapterStr);
                break;

        }
        HttpApiManager.getInfo(httpManager, nowUrl);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(SeewhereTitle);
        //保存数据
        manHua.setSeewhere(SeewhereTitle);
        manHua.setSeewhereurl(nowUrl);
        manHua.setModifytime(System.currentTimeMillis());
        ManHuaDaoUtils.updateManhua(manHua);
//        System.out.println("最后保存数据=="+manHua.toString());
        super.onDestroy();
    }


    public static void toactivity(Activity activity, ManHua manHua) {
        Intent intent = new Intent(activity, ReadActivity.class);
        intent.putExtra("data", manHua);
        activity.startActivity(intent);
    }


}
