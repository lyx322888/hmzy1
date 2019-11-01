package com.huiminsheng.app.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.R;
import com.huiminsheng.app.utils.ToolsImage;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * 首页轮播图
 * Created by Lyx on 2017/9/15.
 */

public class BannerViewHolder  implements MZViewHolder<String> {
    private ImageView mImageView;
    private Listener listener;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_new_banner_item, null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }
    public BannerViewHolder() {

    }
    public BannerViewHolder(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onBind(Context context, final int i, String s) {
        ToolsImage.loader(context,s,mImageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnclickListener(i);
                }
            }
        });
//        mImageView.post(new Runnable() {
//            @Override
//            public void run() {
//                final LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
//                linearParams.height = (int)( mImageView.getWidth()*0.42);
//                mImageView.setLayoutParams(linearParams);
//            }
//        });
    }
    //点击事件
    public interface Listener{
        void OnclickListener(int position);
    }
}
