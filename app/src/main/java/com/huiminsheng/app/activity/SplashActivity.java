package com.huiminsheng.app.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;


import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.login.LoginActivity;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.utils.CountdownUtils;
import com.huiminsheng.app.utils.PermissionsChecker;
import com.huiminsheng.app.utils.SPUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


//启动页
public class SplashActivity extends Activity {
    private Observer<Long> observer;//观察者
    private Disposable disposable;//观察者管理
    private int count = 1;
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO
    };
    private SplashActivity context;
    private static final int REQUEST_CODE = 0; // 请求码
    private PermissionsChecker mPermissionsChecker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_splash);
        context = this;
        mPermissionsChecker = new PermissionsChecker(this);
        //观察者注册
        initObserver();
    }

    private void initObserver() {
        observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                //开始
                disposable = d;
            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                StartMain();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(context, REQUEST_CODE, PERMISSIONS);
            finish();//进入权限页面就不在返回启动页
        } else {
            CountdownUtils.countdown(observer, count);//订阅计时器
//            StartMain();
        }
    }

    private void StartMain() {
        if (TextUtils.isEmpty((String) SPUtils.get(this, ApiUrls.KEY.FIRST, ""))){
            SPUtils.put(this, ApiUrls.KEY.FIRST,"true");
            startActivity(new Intent(this,GuideActivity.class));
        }else {
            //是否登录
            if ("true".equals(SPUtils.get(this, ApiUrls.KEY.LOGGING_STATUS, ""))) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        /**
         * event.getRepeatCount() 重复次数,点后退键的时候，为了防止点得过快，触发两次后退事件，故做此设置。
         *
         * 建议保留这个判断，增强程序健壮性。
         */
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
