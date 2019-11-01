package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMonth;
import com.huiminsheng.app.utils.SpannableStringUtils;

import java.util.List;

public class MonthAdpgter extends BaseQuickAdapter<BeanMonth, BaseViewHolder> {
    public MonthAdpgter(int layoutResId, @Nullable List<BeanMonth> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanMonth item) {
        helper.getView(R.id.tv_month).setSelected(item.isSelect());
        helper.setText(R.id.tv_month,SpannableStringUtils.getBuilder(String.valueOf(item.getMonth())).append("月").setProportion((float) 0.6).create());
    }

    //设置单选
    public void Checked(int position){
        //取消所有选择
        for (int i = 0; i <getData().size(); i++) {
            getData().get(i).setSelect(false);
        }
        //勾选点击项
        getData().get(position).setSelect(true);
        notifyDataSetChanged();
    }
    //当前选中项
    public int getCheckedPosition(){
        for (int i = 0; i <getData().size(); i++) {
            if (getData().get(i).isSelect())
                return  i;
        }
        return 0;
    }
}
