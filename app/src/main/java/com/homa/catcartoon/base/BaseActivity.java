package com.homa.catcartoon.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.homa.catcartoon.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import butterknife.ButterKnife;

/**
 * Created by administrator on 2017/8/31.
 * 基础Activity类
 */

public abstract class BaseActivity extends RxAppCompatActivity implements HttpOnNextListener, BaseInit {
    /**
     *基础界面控件 */
    private TextView toptext;
    private Button back;
    private LinearLayout contextLayout;

    protected  boolean isExtendToBaseLayout=true;//是否继承界面
    protected boolean isRestartTimer = false;
    /**
     * 公用HttpManager
     * */
    protected HttpManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //去除title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //去掉Activity上面的状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * 是否继承界面*/
        if (isExtendToBaseLayout){
            setContentViewWithTop();
        }else {
            setContentView(getLayoutResId());
        }
        ActivityManager.getInstance().addActivity(this);//添加界面到activity管理列表
        ButterKnife.bind(this);//注释绑定控件
        manager = new HttpManager(this, this);//初始化网络请求工具
        /**界面操作*/
        setListener();
        onInit();
    }

    // 子类使用这个加载布局文件，可继承一个标题栏
    protected void setContentViewWithTop() {
        setContentView(R.layout.activity_base);
        initView();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(getLayoutResId(), null);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        contextLayout.addView(view);
    }

    /**
     * 初始化View
     */
    private void initView() {
        contextLayout = (LinearLayout) findViewById(R.id.context_layout);
        toptext = (TextView) findViewById(R.id.top_bar_titleTv);
        back = (Button) findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBacktoDo();
            }
        });
    }
    public void setBacktoDo(){
        finish();
    }
    public void setToptext(String title){
        toptext.setText(title);
    }


    /**界面操作*/
    @Override
    public void onInit() {
    }

    @Override
    public void setListener() {

    }

    /**获取LayoutId*/
    public abstract
    @LayoutRes
    int getLayoutResId();

    /**网络请求返回监听*/
    @Override
    public void onNext(String resulte, String method) {
    }

    @Override
    public void onError(ApiException e, String method) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);//从activity管理列表中移除
    }
}
