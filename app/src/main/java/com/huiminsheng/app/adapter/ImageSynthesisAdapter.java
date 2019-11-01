package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanImageSynthesis;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.RoundedTextview;

import java.util.List;

public class ImageSynthesisAdapter extends BaseQuickAdapter<BeanImageSynthesis, BaseViewHolder> {
    public ImageSynthesisAdapter(int layoutResId, @Nullable List<BeanImageSynthesis> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, BeanImageSynthesis item) {
        final ViewGroup.LayoutParams linearParams =  helper.itemView.getLayoutParams();
                linearParams.width = (int)( ToolsSize.getScreenWidth() *0.19);
        helper.itemView.setLayoutParams(linearParams);
        RoundedTextview textview =  helper.getView(R.id.rtv_code);
        textview.setText(item.content);
        textview.setChecked(item.isChecked);
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
    //当前选中项
    public int getCheckedPosition(){
        for (int i = 0; i <getData().size(); i++) {
            if (getData().get(i).isChecked)
           return  i;
        }
        return 0;
    }
}
