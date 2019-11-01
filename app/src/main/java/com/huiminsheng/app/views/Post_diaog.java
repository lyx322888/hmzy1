package com.huiminsheng.app.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.huiminsheng.app.R;
/**
 * 加载中
 * Created by lyx on 2017/10/30.
 */
@SuppressLint("ValidFragment")
public class Post_diaog extends Dialog  {

    public Post_diaog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NO_INPUT, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        setContentView(R.layout.loadingpost);
        final Window window = getWindow();
       window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
        window.setLayout(-1, -2);//这2行,和上面的一样,注意顺序就行;
          window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        //设置背景颜色,只有设置了这个属性,宽度才能全屏MATCH_PARENT
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams mWindowAttributes = window.getAttributes();
           mWindowAttributes.width = WindowManager.LayoutParams.MATCH_PARENT;//这个属性需要配合透明背景颜色,才会真正的 MATCH_PARENT
        mWindowAttributes.height = WindowManager.LayoutParams.MATCH_PARENT;
    }
}
