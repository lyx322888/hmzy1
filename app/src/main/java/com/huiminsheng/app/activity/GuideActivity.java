package com.huiminsheng.app.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.huiminsheng.app.R;

import com.huiminsheng.app.activity.login.LoginActivity;
import com.huiminsheng.app.adapter.BannerViewHolderGuide;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.utils.SPUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//引导页
public class GuideActivity extends BaseActivity {

    @BindView(R.id.banner)
    MZBannerView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ArrayList<Drawable> arrayListd = new ArrayList<Drawable>();
        arrayListd.add(ContextCompat.getDrawable(this,R.mipmap.guide1)) ;
        arrayListd.add(ContextCompat.getDrawable(this,R.mipmap.guide2)) ;
        arrayListd.add(ContextCompat.getDrawable(this,R.mipmap.guide3)) ;
        banner.setIndicatorRes(R.drawable.banner_indicator_unselect,R.drawable.banner_indicator_select);
        banner.setPages(arrayListd, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolderGuide(new BannerViewHolderGuide.Listener() {
                    @Override
                    public void OnclickListener(int position) {
                        //是否登录
                        if ("true".equals(SPUtils.get(mActivity, ApiUrls.KEY.LOGGING_STATUS, ""))) {
                            Intent intent = new Intent(mActivity, MainActivity.class);
                            startActivity(intent);
                        }else {
                            startActivity(new Intent(mActivity, LoginActivity.class));
                        }
                        finish();
                    }
                });
            }
        });
    }


}
