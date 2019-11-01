package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.utils.ToolsImage;
import java.util.List;

public class QrCodeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public QrCodeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        final ImageView imageView = helper.getView(R.id.item_code_bg_icon);
        final CardView cardView = helper.getView(R.id.layout_card);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                final LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) cardView.getLayoutParams();
                linearParams.height = (int)( cardView.getWidth()*1.1);
                cardView.setLayoutParams(linearParams);
                ToolsImage.loader(mContext,item,imageView);

            }
        });
    }
}
