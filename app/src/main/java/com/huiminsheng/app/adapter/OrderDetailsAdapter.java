package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMyOrder;
import com.huiminsheng.app.bean.BeanOrderDetails;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

//商品列表
public class OrderDetailsAdapter extends BaseQuickAdapter<BeanOrderDetails.DataBean.ListBean, BaseViewHolder> {

    public OrderDetailsAdapter(int layoutResId, @Nullable List<BeanOrderDetails.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanOrderDetails.DataBean.ListBean item) {
        ToolsImage.loader(mContext, item.getThumb(), (ImageView) helper.getView(R.id.lv_car));
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_dj, SpannableStringUtils.getBuilder("¥ ").setProportion((float) 0.8).append(item.getPrice()).create());
        helper.setText(R.id.tv_count,"x" + item.getCount());
    }
}