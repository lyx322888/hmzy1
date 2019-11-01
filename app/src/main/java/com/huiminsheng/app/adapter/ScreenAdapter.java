package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanImageSynthesis;
import com.huiminsheng.app.bean.BeanScreenMenu;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.RoundedTextview;

import java.util.ArrayList;
import java.util.List;

//多选
public class ScreenAdapter extends BaseQuickAdapter<BeanScreenMenu.DataBean.ListBean, BaseViewHolder> {

    public ScreenAdapter(int layoutResId, @Nullable List<BeanScreenMenu.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    //选择
    public void Checked(int position){
        //勾选点击项
        getData().get(position).isChecked = !getData().get(position).isChecked;
        notifyDataSetChanged();
    }
    //返回选中项的id
    public String getCheckedId (){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).isChecked){
                stringBuilder.append(getData().get(i).getId()).append(",");
            }
        }
        if (stringBuilder.length()!=0){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        return stringBuilder.toString();
    }
    //清除选择项
    public void clearOption(){
        for (int i = 0; i < getData().size(); i++) {
            getData().get(i).isChecked = false;
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanScreenMenu.DataBean.ListBean item) {
        RoundedTextview textview =  helper.getView(R.id.rtv_code);
        textview.setTemplate(R.drawable.shape_rectangle_xz_textview,R.color.theme,R.drawable.shape_rectangle_wxz_textview,R.color.grey_999999);
        textview.setText(item.getGood_class());
        textview.setChecked(item.isChecked);
    }
}
