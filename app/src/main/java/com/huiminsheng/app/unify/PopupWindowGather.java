package com.huiminsheng.app.unify;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.views.CustomPopWindow;

public class PopupWindowGather {
    //弹出确认框
    public static void getAffirmPop(final Activity mActivity, String content, final AffirmPopListener listener){
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_confirm, null, false);
        final CustomPopWindow mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create()
                .showAtLocation(inflate, Gravity.CENTER, 0, 0);
        TextView tvContent = inflate.findViewById(R.id.tv_pop_content);
        tvContent.setText(content);
        TextView qx = inflate.findViewById(R.id.tv_pop_quxiao);
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
        TextView qr = inflate.findViewById(R.id.tv_pop_qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAffirmPopListener();
                mCustomPopWindow.dissmiss();
            }
        });
    }
    //弹出确认框
    public static void getAffirmPop(final Activity mActivity, String content,String rightStr, final AffirmPopListener listener){
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_confirm, null, false);
        final CustomPopWindow mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create()
                .showAtLocation(inflate, Gravity.CENTER, 0, 0);
        TextView tvContent = inflate.findViewById(R.id.tv_pop_content);
        tvContent.setText(content);
        TextView qx = inflate.findViewById(R.id.tv_pop_quxiao);
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
        TextView qr = inflate.findViewById(R.id.tv_pop_qr);
        qr.setText(rightStr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAffirmPopListener();
                mCustomPopWindow.dissmiss();
            }
        });
    }
}
