package com.huiminsheng.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MyImageGetter{
    public static Html.ImageGetter imageGetter(final TextView textView, final Context context){
        return new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                final LevelListDrawable drawable = new LevelListDrawable();
             Glide.with(context).load(source)
             .asBitmap().into(new SimpleTarget<Bitmap>() {
                 @Override
                 public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (resource!=null){
                                BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(),resource);
                                int w = textView.getRight()-textView.getLeft();
                                int h = (int) (w*((float)bitmapDrawable.getIntrinsicHeight()/(float)bitmapDrawable.getIntrinsicWidth()));
                                drawable.addLevel(1, 1, bitmapDrawable);
                                drawable.setBounds(0, ToolsSize.getSize(5), w,h);
                                drawable.setLevel(1);
                                textView.invalidate();
                                textView.setText(textView.getText());
                            }
                 }
             }) ;
             return drawable;
            }
        };
    }
}
