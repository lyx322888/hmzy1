package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMyEarnings;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

public class MyenringsAdapter extends BaseQuickAdapter<BeanMyEarnings.DataBean.ListBean.ChannelsBean, BaseViewHolder> {
    public MyenringsAdapter(int layoutResId, @Nullable List<BeanMyEarnings.DataBean.ListBean.ChannelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanMyEarnings.DataBean.ListBean.ChannelsBean item) {
        ToolsImage.loaderYt(mContext,item.getImages_url(), (ImageView) helper.getView(R.id.lv_img));
        helper.setText(R.id.tv_title,item.getDesc())
                .setText(R.id.tv_enrings_r,item.getPrice_profit());
    }
}
