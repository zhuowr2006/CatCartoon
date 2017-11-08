package com.homa.catcartoon.ui.category;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseFragment;
import com.homa.catcartoon.ui.List.ListActivity;
import com.homa.catcartoon.utils.ImageLoaderUtil;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/11.
 */

public class categoryFragment extends BaseFragment{

    private static final String TAG = "categoryFragment";

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    private BaseQuickAdapter adapter;

    private String[] mVals = new String[]
            {"连载中", "已完结", "搞笑", "热血", "悬疑", "格斗", "恐怖", "科幻", "魔幻", "励志", "校园", "爱情","武侠","日漫","港漫","国漫","美漫"};

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_recom;
    }

    @Override
    public void onInit() {
        swipeLayout.setEnabled(false);
        GridLayoutManager layoutManager=new GridLayoutManager(mActivity, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fanjuRcview.setLayoutManager(layoutManager);
        adapter = new SubareaAdapter(R.layout.item_home_subarea, mVals);
        adapter.openLoadAnimation();
        fanjuRcview.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("type", position);
                intent.putExtra("title", mVals[position]);
                    intent.setClass(mActivity, ListActivity.class);
                    mActivity.startActivity(intent);
                }
        });
    }


    public class SubareaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public SubareaAdapter(int layoutResId, String[] data) {
            super(layoutResId, Arrays.asList(data));
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_sb_title, item);
            ImageView imgv=helper.getView(R.id.item_sb_img);
            String[] mVals = new String[]
                    {"连载中", "已完结", "搞笑", "热血", "悬疑", "格斗", "恐怖", "科幻", "魔幻", "励志", "校园", "爱情","武侠","日漫","港漫","国漫","美漫"};
            switch (item) {
                case "连载中":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.lianzai,imgv);
//                    imgv.setImageResource(R.mipmap.lianzai);
                    break;
                case "已完结":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.wanjie,imgv);
                    break;
                case "搞笑":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.gaoxiao,imgv);
                    break;
                case "热血":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.rexue,imgv);
                    break;
                case "悬疑":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.xuanyi,imgv);
                    break;
                case "科幻":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.kehuan,imgv);
                    break;
                case "格斗":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.gedou,imgv);
                    break;
                case "恐怖":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.kongbu,imgv);
                    break;
                case "魔幻":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.mohuan,imgv);
                    break;
                case "励志":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.lianzai,imgv);
                    break;
                case "校园":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.xiaoyuan,imgv);
                    break;
                case "爱情":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.aiqing,imgv);
                    break;
                case "武侠":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.qihuan,imgv);
                    break;
                case "日漫":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.richang,imgv);
                    break;
                case "港漫":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.xiaoshuo,imgv);
                    break;
                case "国漫":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.qita,imgv);
                    break;
                case "美漫":
                    ImageLoaderUtil.loaderCircle(getActivity(),R.mipmap.zhanzheng,imgv);
                    break;
            }

        }
    }
}
