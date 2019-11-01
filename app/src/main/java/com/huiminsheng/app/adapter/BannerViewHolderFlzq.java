package com.huiminsheng.app.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView;
import com.huiminsheng.app.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * 首页轮播图
 * Created by Lyx on 2017/9/15.
 */
public class BannerViewHolderFlzq implements MZViewHolder<String> {
    private MyImagView mImageView;
    private BannerViewHolder.Listener listener;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_new_banner_flzq_item, null);
        mImageView = (MyImagView) view.findViewById(R.id.banner_image);
        return view;
    }
    public BannerViewHolderFlzq() {

    }
    public BannerViewHolderFlzq(BannerViewHolder.Listener listener) {
        this.listener = listener;
    }
    @Override
    public void onBind(Context context, final int i, String s) {
        ToolsImage.loader(context,s,mImageView,R.mipmap.mr_xck);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnclickListener(i);
                }
            }
        });
        mImageView.setTranslationZ(10);
        mImageView.setElevation(10);
//        mImageView.post(new Runnable() {
//            @Override
//            public void run() {
//                final LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
//                linearParams.height = (int)( mImageView.getWidth()*0.47);
//                mImageView.setLayoutParams(linearParams);
//            }
//        });
    }
    //点击事件
    public interface Listener{
        void OnclickListener(int position);
    }
}
