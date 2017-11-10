package com.homa.catcartoon.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.homa.catcartoon.data.MySQLiteOpenHelper;
import com.homa.catcartoon.data.greendao.DaoMaster;
import com.homa.catcartoon.data.greendao.DaoSession;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

import skin.support.SkinCompatManager;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by WZG on 2016/10/25.
 */

public class MyApplication extends Application {

    public static String title;//用来暂时保存阅读界面看到第几话，返回目录时，刷新下目录当前选中的位置

    public static Context app;

    public static MyApplication mInstance;

    public static final String SWITCH_MODE_KEY = "mode_key";//切换主题模式

    //数据库初始化
    private MySQLiteOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app=getApplicationContext();
        RxRetrofitApp.init(this);
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
        mInstance=this;

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
        setDatabase();
    }
    public static MyApplication getInstance() {
        return mInstance;
    }

    /**
     * 设置greenDao
     */

    private void setDatabase() {

        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
//        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        //如果你想查看日志信息，请将DEBUG设置为true
        mHelper  = new MySQLiteOpenHelper(this, "notes-db",
                null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public SQLiteDatabase getDb() {
        return db;
    }
}
