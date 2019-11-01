package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanTeamDetail;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

public class TeamDetailAdapter extends BaseQuickAdapter<BeanTeamDetail.DataBean.ListBean, BaseViewHolder> {
    public TeamDetailAdapter(int layoutResId, @Nullable List<BeanTeamDetail.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanTeamDetail.DataBean.ListBean item) {
        ToolsImage.loaderTx(mContext,item.getHeadpic(), (ImageView) helper.getView(R.id.lv_tx));

        Glide.with(mContext).load(item.getLevel_image()).
                asBitmap().thumbnail(0.5f).into((ImageView) helper.getView(R.id.lv_dj));
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_jjtj,String.format("间接推荐:%s人",item.getIndirect()));
        helper.setText(R.id.tv_zjtj,String.format("直接推荐:%s人",item.getDirect()));
        helper.addOnClickListener(R.id.ll_phone);
        if (!TextUtils.isEmpty(item.getMobile())){
            helper.setVisible(R.id.ll_phone, true);
            if (item.getMobile().length()==11){
                String maskNumber = item.getMobile().substring(0,3)+"****"+ item.getMobile().substring(7, item.getMobile().length());
                helper.setText(R.id.tv_phone,maskNumber);
            }else {
                helper.setText(R.id.tv_phone,item.getMobile());
            }
        }else {
            helper.setVisible(R.id.ll_phone, false);
        }

    }
}
