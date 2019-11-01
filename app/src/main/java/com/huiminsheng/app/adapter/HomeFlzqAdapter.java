package com.huiminsheng.app.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.bean.BeanHomeFlzq;
import com.huiminsheng.app.R;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

/*
* 首页福利专区*/
public class HomeFlzqAdapter extends BaseQuickAdapter<BeanHomeFlzq.DataBean.ListBean, BaseViewHolder> {

    public HomeFlzqAdapter(int layoutResId, @Nullable List<BeanHomeFlzq.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanHomeFlzq.DataBean.ListBean item) {
        ToolsImage.loader(mContext,item.getImage(),(ImageView) helper.getView(R.id.imgv_flzq_bc));
        helper.setText(R.id.tv_flzq_title,item.getType_name()).setTextColor(R.id.tv_flzq_title, Color.parseColor(item.getTop_color()));
        helper.setText(R.id.tv_flzq_content,item.getDetails()).setTextColor(R.id.tv_flzq_content, Color.parseColor(item.getDetails_color()));
    }
}
