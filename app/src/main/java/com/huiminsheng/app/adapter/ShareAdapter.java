package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanShare;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;
//分享页面适配器
public class ShareAdapter extends BaseQuickAdapter<BeanShare.DataBean.ListBean, BaseViewHolder > {
    public ShareAdapter(int layoutResId, @Nullable List<BeanShare.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanShare.DataBean.ListBean item) {
        ToolsImage.loaderYt(mContext,item.getLogo(),(ImageView) helper.getView(R.id.lv_share_left));
        helper.setText(R.id.tv_share_item_r,item.getDesc());
        helper.setText(R.id.tv_share_item_title,item.getName());
    }
}
