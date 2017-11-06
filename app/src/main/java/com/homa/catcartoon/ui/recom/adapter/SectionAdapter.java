package com.homa.catcartoon.ui.recom.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.homa.catcartoon.R;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.homa.catcartoon.ui.recom.bean.RecomBean;
import com.homa.catcartoon.utils.ImageLoaderUtil;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
   private  Context context;


    public SectionAdapter(Context context, int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {

//       if ( helper.getView(R.id.header)==null){
//           Log.i(TAG, "convertHead: ==="+item.header);
//       }
//        helper.setText(R.id.header, item.header);
        helper.setImageResource(R.id.img,item.getImgid());
    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        RecomBean recomBean = (RecomBean) item.t;
        ImageView img=helper.getView(R.id.recom_preview);
        ImageLoaderUtil.loader(context,recomBean.getImg(),img);
        helper.setText(R.id.recom_num,recomBean.getNum());
        helper.setText(R.id.recom_title,recomBean.getTitle());
    }
}
