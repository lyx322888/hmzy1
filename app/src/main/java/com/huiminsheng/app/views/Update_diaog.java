package com.huiminsheng.app.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.huiminsheng.app.R;

import butterknife.BindView;
import butterknife.OnClick;


@SuppressLint("ValidFragment")
public class Update_diaog extends Dialog implements View.OnClickListener {

    String content;//更新内容
    TextView tvUpapp;
    ImageView lvQx;
    private boolean isupdate;//是否强制更新


    private OnOKClickListener monOKClickListener;

    public Update_diaog(@NonNull Context context, String content, boolean isupdate) {
        super(context);
        this.isupdate = isupdate;
        this.content = content;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_upapp:
                if (monOKClickListener != null) {
                    monOKClickListener.btnUpdateUpapp();
                }
                break;
            case R.id.lv_qx:
                    dismiss();
                break;
        }
    }

    public interface OnOKClickListener {

        void btnUpdateUpapp();
    }


    public void setOnClickListener(final OnOKClickListener onClickListener) {
        this.monOKClickListener = onClickListener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NO_INPUT, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        setContentView(R.layout.popwindow_upapp);
        tvUpapp = findViewById(R.id.tv_upapp);
        lvQx = findViewById(R.id.lv_qx);
        tvUpapp.setOnClickListener(this);
        lvQx.setOnClickListener(this);
        if (isupdate) {
            lvQx.setVisibility(View.GONE);
        } else {
            //需要强制更新时隐藏取消
            lvQx.setVisibility(View.VISIBLE);
        }
        final Window window = getWindow();
//       window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
        assert window != null;
        window.setLayout(-1, -2);//这2行,和上面的一样,注意顺序就行;
        //  window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //设置背景颜色,只有设置了这个属性,宽度才能全屏MATCH_PARENT
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams mWindowAttributes = window.getAttributes();
        //   mWindowAttributes.width = getWindowWidth();//这个属性需要配合透明背景颜色,才会真正的 MATCH_PARENT
        mWindowAttributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // window.setWindowAnimations(R.style.contextMenuAnim);

        Window dialogWindow = getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.x = 0;
        lp.y = 0;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        //  lp.height = 300;
        //  lp.alpha = 0f;
        dialogWindow.setAttributes(lp);
    }
}
