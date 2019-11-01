package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanStrores;

import java.util.List;

public class GoodsStroresAdapter extends BaseQuickAdapter<BeanStrores.DataBean.ListBean, BaseViewHolder> {
    public GoodsStroresAdapter(int layoutResId, @Nullable List<BeanStrores.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanStrores.DataBean.ListBean item) {
        ImageView compatCheckBox = helper.getView(R.id.cb_collect);
        //是否选择
        if (item.isChecked){
            compatCheckBox.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.withdraw_select));
        }else {
            compatCheckBox.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.withdraw_default));
        }
        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_md,item.getName());
        }
        if (!TextUtils.isEmpty(item.getContact())){
            helper.setText(R.id.tv_name,item.getContact());
        }
        if (!TextUtils.isEmpty(item.getMobile())){
            helper.setText(R.id.tv_phone,item.getMobile());
        }
        if (!TextUtils.isEmpty(item.getAddress())){
            helper.setText(R.id.tv_address ,item.getAddress());
        }
    }
    //选择
    public void Checked(String addressId){
        //取消所有选择
        for (int i = 0; i <getData().size(); i++) {
            getData().get(i).isChecked =false;
        }
        //勾选点击项
        for (int i = 0; i <getData().size(); i++) {
            if (TextUtils.equals(getData().get(i).getId(),addressId)){
                getData().get(i).isChecked =true;
                notifyDataSetChanged();
                return;
            }
        }
    }

    //设置单选
    public void Checked(int position){
        //取消所有选择
        for (int i = 0; i <getData().size(); i++) {
            getData().get(i).isChecked =false;
        }
        //勾选点击项
        getData().get(position).isChecked =true;
        notifyDataSetChanged();
    }
}
