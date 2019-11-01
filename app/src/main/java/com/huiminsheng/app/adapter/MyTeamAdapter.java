package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMyTeam;

import java.util.List;
//我的团队
public class MyTeamAdapter extends BaseQuickAdapter<BeanMyTeam.DataBean.ListBean, BaseViewHolder> {
    public MyTeamAdapter(int layoutResId, @Nullable List<BeanMyTeam.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanMyTeam.DataBean.ListBean item) {
        Glide.with(mContext).load(item.getImage_url()).
                asBitmap().thumbnail(0.5f).into((ImageView) helper.getView(R.id.lv_icon));
        helper.setText(R.id.tv_dj,item.getLf_name());
            switch (helper.getAdapterPosition()){
                case 0:
                    helper.setTextColor(R.id.tv_dj, ContextCompat.getColor(mContext,R.color.font_team1));
                    break;
                case 1:
                    helper.setTextColor(R.id.tv_dj, ContextCompat.getColor(mContext,R.color.font_team2));
                    break;
                case 2:
                    helper.setTextColor(R.id.tv_dj, ContextCompat.getColor(mContext,R.color.font_team3));
                    break;
                    default:
                    helper.setTextColor(R.id.tv_dj, ContextCompat.getColor(mContext,R.color.font_order));
                        break;
            }
            helper.setText(R.id.tv_zj,String.format("直接%s人  间接%s人",item.getDirect_count(),item.getIndirect_count()));
            helper.setText(R.id.tv_rs,item.getCount()+"人");
    }
}
