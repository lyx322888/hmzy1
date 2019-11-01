package com.huiminsheng.app.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanCollect;
import com.huiminsheng.app.bean.BeanHomeZscx;
import com.huiminsheng.app.utils.ToolsImage;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import java.util.List;

import io.reactivex.functions.Consumer;

/*w我的收藏*/
public class CollectAdapter extends BaseQuickAdapter<BeanCollect.DataBean.ListBean, BaseViewHolder> {
    private boolean isCompile = false;//是否是编辑状态

    public CollectAdapter(int layoutResId, @Nullable List<BeanCollect.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BeanCollect.DataBean.ListBean item) {
        ImageView compatCheckBox = helper.getView(R.id.cb_collect);
        if (isCompile){
            compatCheckBox.setVisibility(View.VISIBLE);
        }else {
            compatCheckBox.setVisibility(View.GONE);
        }
        //是否选择
       if (item.isChoose){
           compatCheckBox.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.withdraw_select));
       }else {
           compatCheckBox.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.withdraw_default));
       }
        ToolsImage.loader(mContext,item.getResult().getLogo(),(ImageView) helper.getView(R.id.imgv_zscx_logo));
        //名字
        helper.setText(R.id.tv_zscx_name,item.getResult().getName());
        helper.setText(R.id.tv_zscx_desc,item.getResult().getDesc());
        helper.setText(R.id.tv_zscx_jg,item.getResult().getPrice());
        helper.setText(R.id.tv_zscx_tag,item.getResult().getTag_name());
        helper.setText(R.id.tv_zscx_zdj,"厂商价"+item.getResult().getOri_price());
        TextView tv_zdj = helper.getView(R.id.tv_zscx_zdj);
        tv_zdj.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        helper.setText(R.id.tv_zxcx_yf,"预付"+item.getResult().getPrime()+"立即锁定爱车");
    }
    //开启编辑状态
    public void openCompile(boolean isCompile){
        this.isCompile = isCompile;
        notifyDataSetChanged();
    }
    //获取编辑状态
    public boolean getisCompile(){
        return isCompile;
    }
    //获取所有选择的项
    public String getChooseArr(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).isChoose){
                stringBuilder.append(getData().get(i).getId()).append(",");
            }
        }
        if (stringBuilder.length()!=0){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        return stringBuilder.toString();
    }
}
