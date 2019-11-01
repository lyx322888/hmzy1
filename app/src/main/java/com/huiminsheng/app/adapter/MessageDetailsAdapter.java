package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMessage;
import com.huiminsheng.app.bean.BeanMsgDetails;
import com.huiminsheng.app.utils.FormatUtils;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;

/*消息列表*/
public class MessageDetailsAdapter extends BaseQuickAdapter<BeanMsgDetails.DataBean.ListBean, BaseViewHolder> {
    public MessageDetailsAdapter(int layoutResId, @Nullable List<BeanMsgDetails.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanMsgDetails.DataBean.ListBean item) {

        helper.setText(R.id.tv_msg_title,item.getTitle())
                .setText(R.id.tv_msg_time, FormatUtils.getTime2SH(item.getPubtime()));
            helper.setText(R.id.tv_msg_content,item.getContent());
    }
}
