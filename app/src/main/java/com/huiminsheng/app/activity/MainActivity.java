package com.huiminsheng.app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.activity.web.BaseWebActivity;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanUpApp;
import com.huiminsheng.app.bean.BeanUserinfo_head;
import com.huiminsheng.app.fragment.HomeFragment;
import com.huiminsheng.app.fragment.MyFragment;
import com.huiminsheng.app.fragment.ShareFragment;
import com.huiminsheng.app.R;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.CollectedUtils;
import com.huiminsheng.app.utils.PopwindownUtils;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.CustomPopWindow;
import com.huiminsheng.app.views.Update_diaog;
import com.yanzhenjie.sofia.Sofia;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/*
 * 主页
 * */
public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.home_imag)
    ImageView homeImag;
    @BindView(R.id.home_layout)
    LinearLayout homeLayout;
    @BindView(R.id.share_imag)
    ImageView shareImag;
    @BindView(R.id.share_layout)
    LinearLayout shareLayout;
    @BindView(R.id.my_imag)
    ImageView myImag;
    @BindView(R.id.my_layout)
    LinearLayout myLayout;
    @BindView(R.id.linear)
    LinearLayout linear;
    private HomeFragment homeFragment;
    private ShareFragment shareFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    /*设置导航栏透明*/
    @Override
    protected void setStatusBar() {
        Sofia.with(this).invasionStatusBar().statusBarBackgroundAlpha(0).statusBarDarkFont();
    }

    //初始化
    private void init() {
        initFragmentHome();
        //看是否需要显示广告弹窗
        Advertising();
        //是否需要更新
        upApp();
    }
    //是否需要更新app
    private void upApp() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.LOGIN.UPGRADE)
                .addParam("version_code", String.valueOf(CollectedUtils.getVersionCode(mActivity)))
                .request(BeanUpApp.class, new BaseListener<BeanUpApp>() {
                    @Override
                    public void onSucceed(int what, Result<BeanUpApp> result) {
                        switch (result.getResult().getData().getIs_update()){
                            /**0不需更新 ;1 需更新 2:需强制更新*/
                            case "0":
                                break;
                            case "1":
                                //需更新
                                showUpappPop(result.getResult().getData());
                                break;
                            case "2":
                                //需强制更新
                                showUpappPop(result.getResult().getData());
                                break;
                                default:break;
                        }
                    }
                });
    }
    //更新提示框
    private void showUpappPop(final BeanUpApp.DataBean dataBean){
        //不需更新
        Update_diaog update_diaog = new Update_diaog(mActivity,"",TextUtils.equals(dataBean.getIs_update(),"2"));
        update_diaog.setOnClickListener(new Update_diaog.OnOKClickListener() {
            @Override
            public void btnUpdateUpapp() {
                //更新
                Uri uri = Uri.parse(dataBean.getDown_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        //拦截返回键
        update_diaog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
            }
        });
        update_diaog.setCanceledOnTouchOutside(false);
        update_diaog.show();
    }

    //是否显示广告
    private void Advertising() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.INFO)
                .request(BeanUserinfo_head.class, new BaseListener<BeanUserinfo_head>() {
                    @Override
                    public void onSucceed(int what, Result<BeanUserinfo_head> result) {
                        if (!TextUtils.isEmpty(result.getResult().getData().getAd().getIs_full())){
                            switch (result.getResult().getData().getAd().getIs_full()){
                                case "-1":
                                    //不显示广告
                                    break;
                                case "0":
                                    //0 非全屏
                                case "1":
                                    //1 全屏
                                    showAdvertising(result.getResult().getData().getAd());
                                    break;
                                default:break;
                            }
                        }
                    }
                });
    }
    //显示广告窗
    private void showAdvertising(final BeanUserinfo_head.DataBean.AdBean adBean){
        final View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_advertising, null, false);
        final PopupWindow popupWindow = new PopwindownUtils().initView(mActivity, inflate, true);
        popupWindow.setAnimationStyle(0);
        if (TextUtils.equals(adBean.getIs_full(),"-1")){
            popupWindow.showAtLocation(inflate, Gravity.CENTER, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }else {
            //6.0前黑图标不可设置
            if (Build.VERSION.SDK_INT<23){
                Sofia.with(this).statusBarDarkFont().statusBarBackgroundAlpha(20);
            }else {
                Sofia.with(this).statusBarDarkFont().statusBarBackground(ContextCompat.getColor(this, R.color.white));
            }
            popupWindow.setContentView(inflate);
            popupWindow.showAtLocation(inflate, Gravity.CENTER, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        final ImageView imageView = inflate.findViewById(R.id.lv_picture);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                if (TextUtils.equals(adBean.getIs_full(),"-1")){
                    layoutParams.setMargins(ToolsSize.getSize(35),0,ToolsSize.getSize(35),0);
                }
                //设置图片比例
                layoutParams.dimensionRatio = String.format("%s:%s",adBean.getWidth(),adBean.getHeight());
                imageView.setLayoutParams(layoutParams);
                //加载图片
                Glide.with(mActivity).load(ApiUrls.UrlHead+adBean.getImage()).asBitmap().thumbnail(0.5f).into(imageView);
                //跳转web
                imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", adBean.getUrl());
                BaseWebActivity.jumpActivity(mActivity, BaseWebActivity.class, bundle);
                popupWindow.dismiss();
            }
        });
        if (TextUtils.equals(adBean.getIs_full(),"1")){
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                    Sofia.with(mActivity).invasionStatusBar().statusBarBackgroundAlpha(0).statusBarDarkFont();
                    new PopwindownUtils().setBackgroundAlpha(mActivity, 1);
                    mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                }
            });
        }
    }

    @OnClick({R.id.home_layout, R.id.share_layout, R.id.my_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_layout:
                initFragmentHome();
                break;
            case R.id.share_layout:
                initFragmentShare();
                break;
            case R.id.my_layout:
                initFragmentMy();
                break;
        }
    }

    //取消选择
    private void select() {
        homeLayout.setSelected(false);
        shareLayout.setSelected(false);
        myLayout.setSelected(false);
    }

    /**
     * 显示homefragment
     */
    private void initFragmentHome() {
        select();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeLayout.setSelected(true);
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fragment, homeFragment);
        }
        hideFragment(transaction);
        transaction.show(homeFragment);
        transaction.commit();
    }

    /**
     * 显示sharefragment
     */
    private void initFragmentShare() {
        select();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        shareLayout.setSelected(true);
        if (shareFragment == null) {
            shareFragment = new ShareFragment();
            transaction.add(R.id.fragment, shareFragment);
        }
        hideFragment(transaction);
        transaction.show(shareFragment);
        transaction.commit();
    }

    /**
     * 显示myfragment
     */
    private void initFragmentMy() {
        select();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        myLayout.setSelected(true);
        if (myFragment == null) {
            myFragment = new MyFragment();
            transaction.add(R.id.fragment, myFragment);
        }
        hideFragment(transaction);
        transaction.show(myFragment);
        transaction.commit();
    }

    /**
     * 记得要隐藏所有已经添加的fragment
     *
     * @param transaction FragmentTransaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (shareFragment != null) {
            transaction.hide(shareFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void exit(){
        if((System.currentTimeMillis()-exitTime)>2000) {
            Toast.makeText(getApplicationContext(),
                    "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }
        else{
                finish();
                System.exit(0);
            }
        }
}
