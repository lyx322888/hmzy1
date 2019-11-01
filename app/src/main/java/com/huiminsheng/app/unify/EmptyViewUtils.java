package com.huiminsheng.app.unify;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huiminsheng.app.R;


/**
 * Created by Lyx on 2017/9/14.
 */

public class EmptyViewUtils {
    /**
     *
     * @param context
     * @param content 提示内容
     * @return
     */
    public static View getview(Context context, String content){
        View emptyView  = LayoutInflater.from(context).inflate(R.layout.emptyview,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView empty_text = emptyView.findViewById(R.id.empty_text);
        empty_text.setText(content);
        return emptyView;
    }
    /**
     *
     * @param context
     * @param content 提示内容
     * @param drawable 图片
     * @return ff
     */
    public static View getview(Context context, String content, Drawable drawable){
        View emptyView  = LayoutInflater.from(context).inflate(R.layout.emptyview,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        ImageView imageView = emptyView.findViewById(R.id.lv_empty);
        imageView.setImageDrawable(drawable);
        TextView empty_text = emptyView.findViewById(R.id.empty_text);
        empty_text.setText(content);
        return emptyView;
    }
    /**
     *
     * @param context
     * @param resId 资源id 提示内容
     * @return
     */
    public static View getview(Context context, int  resId){
        View emptyView  = LayoutInflater.from(context).inflate(R.layout.emptyview,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView empty_text = (TextView)emptyView.findViewById(R.id.empty_text);
        empty_text.setText(context.getString(resId));
        return emptyView;
    }

    /**
     *
     * @param context
     * @param resId 资源id 提示内容
     * @return
     */
    public static View getview_item(Context context, int  resId){
        View emptyView  = LayoutInflater.from(context).inflate(R.layout.activity_new_emptyview_item,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView empty_text = (TextView)emptyView.findViewById(R.id.empty_text);
        empty_text.setText(context.getString(resId));
        return emptyView;
    }

}
