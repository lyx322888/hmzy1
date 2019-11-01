package com.huiminsheng.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.huiminsheng.app.R;
import com.huiminsheng.app.net.NoGo;

import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.unify.loadsir.loadcallback.LoadingCallback;
import com.huiminsheng.app.unify.loadsir.loadcallback.TimeoutCallback;
import com.huiminsheng.app.utils.MyActivityManager;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yanzhenjie.sofia.Sofia;
/**
 * Created by 李亚雄 .
 * 基底activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected LoadService loadService;
    LoadListener loadListener;
    protected Activity mActivity;
    protected int pagination=1;
    protected int net_action = Loadtype.ACTION_PULL_DOWN;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        MyActivityManager.getInstance().addActivity(this);
        setStatusBar();
        screenRotation();
        //状态页面实例
        loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                if (loadListener!=null){
                    loadListener.loadlistener();
                }
            }
        });
        mActivity = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitleView();
    }
    //外部跳转
    public static void jumpActivity (Activity activity, Class calss,Bundle bundle){
       Intent intent = new Intent(activity,calss);
       if (bundle!=null){
           intent.putExtras(bundle);
       }
        activity.startActivity(intent);
    }
    //设置标题
    protected void setTitleView(){ }
    //显示网络错误加载页面
    public void showTimeout(final LoadListener loadListener){
        this.loadListener  = loadListener;
        loadService.showCallback(TimeoutCallback.class);
    }
    //显示加载中
    public void showLoading(){
        loadService.showCallback(LoadingCallback.class);
    }
    //显示加载后的页面
    public void showSuccess(){
        loadService.showSuccess();
    }
    //禁止屏幕旋转
    protected void screenRotation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    //设置状态栏颜色
    protected void setStatusBar() {
        //6.0前黑图标不可设置
        if (Build.VERSION.SDK_INT<23){
            Sofia.with(this).statusBarDarkFont().statusBarBackgroundAlpha(20);
        }else {
            Sofia.with(this).statusBarDarkFont().statusBarBackground(ContextCompat.getColor(this, R.color.white));
        }
    }

    //点击外部隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //页面销毁时取消请求
        NoGo.cancelBySign(this);
        MyActivityManager.getInstance().removeActivity(this);
    }
}
