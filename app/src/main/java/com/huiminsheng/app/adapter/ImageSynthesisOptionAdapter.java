package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanImageSynthesis;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.RoundedTextview;

import java.util.ArrayList;
import java.util.List;
//多选
public class ImageSynthesisOptionAdapter extends BaseQuickAdapter<BeanImageSynthesis, BaseViewHolder> {
    public ImageSynthesisOptionAdapter(int layoutResId, @Nullable List<BeanImageSynthesis> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanImageSynthesis item) {
        final ViewGroup.LayoutParams linearParams =  helper.itemView.getLayoutParams();
        linearParams.width = (int) ( ToolsSize.getScreenWidth() *0.24);
        helper.itemView.setLayoutParams(linearParams);
        RoundedTextview textview =  helper.getView(R.id.rtv_code);
        textview.setTemplate(R.drawable.shape_rectangle_xz_textview,R.color.theme,R.drawable.shape_rectangle_wxz_textview,R.color.grey_999999);
        textview.setText(item.content);
        textview.setChecked(item.isChecked);
    }
    //选择
    public void Checked(int position){
        //勾选点击项
        getData().get(position).isChecked = !getData().get(position).isChecked;
        notifyDataSetChanged();
    }
    //返回选中项数组
    public ArrayList<Integer> getCheckedList (){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).isChecked){
                arrayList.add(i);
            }
        }
        return arrayList;
    }

}
