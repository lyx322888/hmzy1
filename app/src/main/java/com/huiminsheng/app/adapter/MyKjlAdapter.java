package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanHome;
import com.huiminsheng.app.bean.BeanUserinfo;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;
//我的页面 快捷栏
public class MyKjlAdapter extends BaseQuickAdapter<BeanUserinfo.DataBean.MylistBean, BaseViewHolder> {
    public MyKjlAdapter(int layoutResId, @Nullable List<BeanUserinfo.DataBean.MylistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanUserinfo.DataBean.MylistBean item) {
        ToolsImage.loader(mContext,item.getImages_url(),(ImageView) helper.getView(R.id.lv_menu),R.mipmap.mr_menu);
        helper.setText(R.id.tv_menu,item.getName());
    }
}
