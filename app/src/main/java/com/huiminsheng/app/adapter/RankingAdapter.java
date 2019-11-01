package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanPhb;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

//排行榜
public class RankingAdapter extends BaseQuickAdapter<BeanPhb.DataBean.ListBean, BaseViewHolder> {
    public RankingAdapter(int layoutResId, @Nullable List<BeanPhb.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanPhb.DataBean.ListBean item) {
        switch (item.getRanking()){
            case "1":
               helper.setTextColor(R.id.tv_ranking, ContextCompat.getColor(mContext,R.color.phb_ranking1));
                break;
            case "2":
                helper.setTextColor(R.id.tv_ranking, ContextCompat.getColor(mContext,R.color.phb_ranking2));
                break;
            case "3":
                helper.setTextColor(R.id.tv_ranking, ContextCompat.getColor(mContext,R.color.phb_ranking3));
                break;
                default:
                    helper.setTextColor(R.id.tv_ranking, ContextCompat.getColor(mContext,R.color.phb_font));
                    break;
        }
        helper.setText(R.id.tv_ranking,item.getRanking());
        helper.setText(R.id.tv_zsy,item.getSplit_total());
        helper.setText(R.id.tv_name,item.getWxnickname());
        helper.setText(R.id.tv_phone,item.getMobile());
        ToolsImage.loader(mContext,item.getHeadimgurl(),(ImageView)helper.getView(R.id.lv_ranking));

    }
}
