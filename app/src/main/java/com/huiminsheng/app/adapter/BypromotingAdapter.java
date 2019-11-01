package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanBypromoting;
import com.huiminsheng.app.utils.TimeUtils;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.api.widget.Widget;
import java.util.List;

public class BypromotingAdapter extends BaseQuickAdapter<BeanBypromoting.DataBean.ListBean, BaseViewHolder> {
    public BypromotingAdapter(int layoutResId, @Nullable List<BeanBypromoting.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BeanBypromoting.DataBean.ListBean item) {
        helper.setText(R.id.etv_content,item.getContent());
        RecyclerView recyclerView = helper.getView(R.id.rv_img);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        PictureAdapter pictureAdapter = new PictureAdapter(R.layout.adapter_item_imgs,item.getImgs());
        pictureAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Album.gallery(mContext)
                        .widget( Widget.newDarkBuilder(mContext)
                                .title("图文推广").build()) // Title.
                        .checkedList(item.getImgs()) // List of image to view: ArrayList<String>.
                        .currentPosition(position)
                        .start();
            }
        });
        recyclerView.setAdapter(pictureAdapter);
        helper.setText(R.id.tv_time, TimeUtils.getInstance().getTime(item.getAddtime(), true, 7) + "  下载 " + item.getCounts());
        helper.addOnClickListener(R.id.ll_copy);
        helper.addOnClickListener(R.id.ll_img_xz);
    }
}
