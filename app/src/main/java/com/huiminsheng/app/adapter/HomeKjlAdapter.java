package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanHome;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

public class HomeKjlAdapter extends BaseQuickAdapter<BeanHome.DataBean.ListBean, BaseViewHolder> {
    public HomeKjlAdapter(int layoutResId, @Nullable List<BeanHome.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanHome.DataBean.ListBean item) {
        ToolsImage.loader(mContext,item.getImage(),(ImageView) helper.getView(R.id.lv_menu),R.mipmap.mr_menu);
        helper.setText(R.id.tv_menu,item.getName());
    }
}
