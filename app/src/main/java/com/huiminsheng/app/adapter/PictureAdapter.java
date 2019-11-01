package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

public class PictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PictureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ToolsImage.loader(mContext,item, (ImageView) helper.getView(R.id.lv));
    }
}
