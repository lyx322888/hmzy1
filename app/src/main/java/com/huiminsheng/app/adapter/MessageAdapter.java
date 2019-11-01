package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMessage;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;
/*消息列表*/
public class MessageAdapter extends BaseQuickAdapter<BeanMessage.DataBean.ListBean, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<BeanMessage.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanMessage.DataBean.ListBean item) {
        ToolsImage.loader(mContext,item.getIcon(), (ImageView) helper.getView(R.id.lv_msg_icon));
        helper.setText(R.id.tv_msg_title,item.getTitle())
                .setText(R.id.tv_msg_time,item.getTime());
        if (TextUtils.isEmpty(item.getMsg())){
            helper.setText(R.id.tv_msg_content,"没有相关消息");
        }else {
            helper.setText(R.id.tv_msg_content,item.getMsg());
        }

    }
}
