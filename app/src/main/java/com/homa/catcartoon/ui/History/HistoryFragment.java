package com.homa.catcartoon.ui.History;


import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.homa.catcartoon.R;
import com.homa.catcartoon.base.BaseFragment;
import com.homa.catcartoon.data.ManHua;
import com.homa.catcartoon.data.ManHuaDaoUtils;
import com.homa.catcartoon.ui.info.InfoActivity;
import com.homa.catcartoon.utils.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/11.
 */

public class HistoryFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener{

    private static final String TAG = "HistoryFragment";

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;


    private BaseQuickAdapter adapter;
    private List<ManHua> list;
    private int page=0;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_history;
    }

    @Override
    public void onInit() {
        list=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(mActivity);
        fanjuRcview.setLayoutManager(layoutManager);
        adapter = new SubareaAdapter(R.layout.item_home_history, list);
        fanjuRcview.setAdapter(adapter);
        adapter.openLoadAnimation();
        // 滑动最后一个Item的时候回调onLoadMoreRequested方法
        adapter.setOnLoadMoreListener(this);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent i = new Intent(getActivity(), InfoActivity.class);
                i.putExtra("data", list.get(position));
                startActivity(i);
            }
        });
        getData();
    }
    private void  getData(){
        adapter.setNewData(ManHuaDaoUtils.queryPage(10,page));
        list=adapter.getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        page=0;
        getData();
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        fanjuRcview.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ManHua> manHuas=ManHuaDaoUtils.queryPage(10,page);
                if (manHuas.size()<=0) {
                    //数据全部加载完毕
                    adapter.loadMoreEnd();
                } else {
//                    if (!isErr) {
                        //成功获取更多数据
                        adapter.addData(manHuas);
                        list = adapter.getData();
                        adapter.loadMoreComplete();
//                    } else {
//                        //获取更多数据失败
//                        isErr = true;
////                        Toast.makeText(get, R.string.network_err, Toast.LENGTH_LONG).show();
//                        adapter.loadMoreFail();

//                    }
                }
            }

        }, 1000);
    }

    public class SubareaAdapter extends BaseQuickAdapter<ManHua, BaseViewHolder> {


        public SubareaAdapter(@LayoutRes int layoutResId, @Nullable List<ManHua> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ManHua item) {
            helper.setText(R.id.history_num, item.getSeewhere());
            helper.setText(R.id.history_author, item.getAuthor());
            helper.setText(R.id.history_title, item.getTitle());
            helper.setText(R.id.history_updata,"更新至:"+item.getUpdatahere());
            ImageView img=helper.getView(R.id.history_preview);
            ImageLoaderUtil.loader(getActivity(),item.getImgurl(),img);
        }
    }
}
