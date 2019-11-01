package com.huiminsheng.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.MainActivity;
import com.huiminsheng.app.activity.login.LoginActivity;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.utils.SPUtils;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * 引导页
 * Created by Lyx on 2017/9/15.
 */

public class BannerViewHolderGuide implements MZViewHolder<Drawable> {
    private ImageView mImageView;
    private TextView tvljty;
    private Listener listener;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mImageView =  view.findViewById(R.id.banner_image);
        tvljty =  view.findViewById(R.id.tv_ljty);
        return view;
    }
    public BannerViewHolderGuide() {

    }
    public BannerViewHolderGuide(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onBind(final Context context, final int i, Drawable s) {
        mImageView.setImageDrawable(s);
        if (i==2){
            tvljty.setVisibility(View.VISIBLE);
        }else {
            tvljty.setVisibility(View.INVISIBLE);
        }
        tvljty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnclickListener(i);
                }
            }
        });

    }
    //点击事件
    public interface Listener{
        void OnclickListener(int position);
    }
}
