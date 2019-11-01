package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanBankList;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;
//银行列表
public class BankListAdapter extends BaseQuickAdapter<BeanBankList.DataBean.ListBean, BaseViewHolder> {
    public BankListAdapter(int layoutResId, @Nullable List<BeanBankList.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanBankList.DataBean.ListBean item) {
        helper.setText( R.id.tv_content,item.getName());
        ToolsImage.loaderYt(mContext, ApiUrls.UrlHead+item.getLogo(), (ImageView) helper.getView(R.id.lv_bank_icon));
    }
}
