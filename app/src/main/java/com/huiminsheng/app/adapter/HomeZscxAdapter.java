package com.huiminsheng.app.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.bean.BeanHomeZscx;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView;
import com.huiminsheng.app.R;

import java.util.List;
/*在售车型适配器*/
public class HomeZscxAdapter extends BaseQuickAdapter<BeanHomeZscx.DataBean.ListBean, BaseViewHolder> {


    public HomeZscxAdapter(int layoutResId, @Nullable List<BeanHomeZscx.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanHomeZscx.DataBean.ListBean item) {
        ToolsImage.loader(mContext,item.getLogo(),(ImageView) helper.getView(R.id.imgv_zscx_logo));
        //名字
        helper.setText(R.id.tv_zscx_name,item.getName());
        helper.setText(R.id.tv_zscx_desc,item.getDesc());
        helper.setText(R.id.tv_zscx_jg,item.getPrice());
        helper.setText(R.id.tv_zscx_tag,item.getTag_name());
        Log.e(TAG, "convert: "+ item.getOri_price());
        helper.setText(R.id.tv_zscx_zdj,"厂商价"+item.getOri_price());
        TextView tv_zdj = helper.getView(R.id.tv_zscx_zdj);
        tv_zdj.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        helper.setText(R.id.tv_zxcx_yf,"预付"+item.getPrime()+"立即锁定爱车");
    }
}
