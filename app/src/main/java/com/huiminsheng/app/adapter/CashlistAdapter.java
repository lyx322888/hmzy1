package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanCashlist;
import com.huiminsheng.app.utils.FormatUtils;

import java.util.List;
//提现记录
public class CashlistAdapter extends BaseQuickAdapter<BeanCashlist.DataBean.ListBean, BaseViewHolder> {
    public CashlistAdapter(int layoutResId, @Nullable List<BeanCashlist.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanCashlist.DataBean.ListBean item) {
        helper.setText(R.id.tv_ct_name,item.getPc_name())
                .setText(R.id.tv_price,"-"+item.getMoney()+"元")
                .setText(R.id.tv_time, FormatUtils.getTime2SH(item.getAddtime()));
        if (TextUtils.equals(item.getStatus(),"0")){
            helper.setText(R.id.tv_status,"提现中");
        }else {
            helper.setText(R.id.tv_status,"提现成功");
        }
    }
}
