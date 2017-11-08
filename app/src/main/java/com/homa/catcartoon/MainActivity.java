package com.homa.catcartoon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.homa.catcartoon.base.MyApplication;
import com.homa.catcartoon.base.TranslucentBarBaseActivity;
import com.homa.catcartoon.ui.category.categoryFragment;
import com.homa.catcartoon.ui.ranking.RankingFragment;
import com.homa.catcartoon.ui.recom.recomFragment;
import com.homa.catcartoon.ui.search.SearchActivity;
import com.homa.catcartoon.utils.PreferenceUtil;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import skin.support.SkinCompatManager;

import static com.homa.catcartoon.R.id.main_ap_layout;


public class MainActivity extends TranslucentBarBaseActivity implements NavigationView.OnNavigationItemSelectedListener,IOnSearchClickListener {
    //顶部布局
    @BindView(R.id.huancunx)
    ImageView huancunx;
    @BindView(R.id.soushuo)
    ImageView soushuo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(main_ap_layout)
    AppBarLayout mainApLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.main_layout)
    DrawerLayout mainLayout;

    private Adapter adapter;
    private SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNavigationView();
    }
    /**
     * 初始化NavigationView
     */
    private void initNavigationView() {
        searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setItemTextColor(null);//取消掉默认的选择图标和字体颜色
        navigationView.setItemIconTintList(null);
        soushuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
            }
        });
//        ImageLoaderUtil.loaderCircle(this, R.drawable.userlogo ,logo);
//        userlogo

//        //设置头像
//        mUserAvatarView.setImageResource(R.drawable.userlogo);
//        //设置用户名 签名
//        mUserName.setText(getResources().getText(R.string.test_username));
//        mUserSign.setText(getResources().getText(R.string.test_username));


        final ImageView img= (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        //设置日夜间模式切换

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean flag = PreferenceUtil.getBoolean(MyApplication.SWITCH_MODE_KEY, false);
                if (!flag){
                    SkinCompatManager.getInstance().loadSkin("night.skin", null, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
                    PreferenceUtil.putBoolean(MyApplication.SWITCH_MODE_KEY, true);
                    img.setImageResource(R.drawable.ic_switch_daily);
                }else {
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                    PreferenceUtil.putBoolean(MyApplication.SWITCH_MODE_KEY, false);
                    img.setImageResource(R.drawable.ic_switch_night);
                }
            }
        });
        final boolean flag = PreferenceUtil.getBoolean(MyApplication.SWITCH_MODE_KEY, false);
        if (flag)
        {
            img.setImageResource(R.drawable.ic_switch_daily);
        } else
        {
            img.setImageResource(R.drawable.ic_switch_night);
        }


//        if (boolValue) {
//            SkinCompatManager.getInstance().loadSkin("night.skin", null, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
//        } else {
//            SkinCompatManager.getInstance().restoreDefaultTheme();
//        }
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            //将侧边栏顶部延伸至status bar
            mainLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            mainLayout.setClipToPadding(false);
//            }
        }
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//平均分配tabitem
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());

        String[] strs = new String[]{"推荐", "分类","排行","历史"};

        adapter.addFragment(new recomFragment(), strs[0]);
        adapter.addFragment(new categoryFragment(), strs[1]);
        adapter.addFragment(new RankingFragment(), strs[2]);
//        adapter.addFragment(new recomFragment(), strs[3]);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);//预加载当前可见fragment页面的左右两边，2个页面

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void OnSearchClick(String keyword) {
        //搜索
        Intent intent=new Intent(this, SearchActivity.class);
        intent.putExtra("content",keyword);
        startActivity(intent);
    }


    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
