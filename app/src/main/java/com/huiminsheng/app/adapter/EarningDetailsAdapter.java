package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanEarningDetails;
import com.huiminsheng.app.utils.FormatUtils;

import java.util.List;

public class EarningDetailsAdapter extends BaseQuickAdapter<BeanEarningDetails.DataBean.ListBean, BaseViewHolder> {
    public EarningDetailsAdapter(int layoutResId, @Nullable List<BeanEarningDetails.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanEarningDetails.DataBean.ListBean item) {
        helper.setText(R.id.tv_title,item.getTypeinfo())
                .setText(R.id.tv_money,item.getMoney()+"元")
                .setText(R.id.tv_time, FormatUtils.getTime2SH(item.getAddtime()));
    }
}
