package com.huiminsheng.app.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.R;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.MyImagView;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 首页轮播图
 * Created by Lyx on 2017/9/15.
 */

public class BannerViewHolderProduct implements MZViewHolder<String> {
    private ImageView mImageView;
    private Listener listener;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.banner_product, null);
        mImageView =  view.findViewById(R.id.banner_image);
        return view;
    }
    public BannerViewHolderProduct(){

    }
    public BannerViewHolderProduct(Listener listener) {
        // 返回页面布局文件
        this.listener = listener;
    }
    @Override
    public void onBind(Context context, int i, String s) {
        Glide.with(context).load(s).placeholder(R.mipmap.mrbc).error(R.mipmap.mrbc).bitmapTransform(new RoundedCornersTransformation(context, ToolsSize.getSize(4), 0,
                            RoundedCornersTransformation.CornerType.TOP)).into(mImageView );
//        mImageView.post(new Runnable() {
//            @Override
//            public void run() {
//                final LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
//                linearParams.height = (int)( mImageView.getWidth()*0.668);
//                mImageView.setLayoutParams(linearParams);
//            }
//        });
       mImageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (listener != null){
                   listener.OnclickListener();
               }
           }
       });
    }

    public interface Listener{
        void OnclickListener();
    }
}
