package com.huiminsheng.app.utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.huiminsheng.app.R;


public class PopwindownUtils {
    private PopupWindow popupWindow;

    /**
     *
     * @param context
     * @param view
     * @param enableBackgroundDark 背景是否变暗
     * @return
     */
    public PopupWindow initView(final Activity context, View view,boolean enableBackgroundDark) {
        popupWindow = new PopupWindow(view,
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置动画
        popupWindow.setAnimationStyle(R.style.PopWindowAnimStyle);
        //设置背景色
        if (enableBackgroundDark){
            setBackgroundAlpha(context, 0.5f);
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(context, 1);
                context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });
        return popupWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     */
    public void setBackgroundAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }
}
