package com.huiminsheng.app.activity.web;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseWebActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.rl_web)
    RelativeLayout rlWeb;
    @BindView(R.id.title_exit)
    ImageView titleExit;
    @BindView(R.id.title_Right)
    ImageView titleRight;
    private AgentWeb mAgentWeb;
    private Bundle bundle;

    //外部跳转
    public static void jumpActivity(Activity activity, Class calss, Bundle bundle) {
        Intent intent = new Intent(activity, calss);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void setTitleView() {
        titleExit.setVisibility(View.VISIBLE);
        titleExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化
    private void init() {
        Intent intent = getIntent();
        bundle = intent.getExtras();
        assert bundle != null;
        String title = bundle.getString("title");
        if (!TextUtils.isEmpty(title)) {
            titleTitle.setText(title);
        }
        String url = bundle.getString("url");
        mAgentWeb = AgentWeb.with(mActivity)//传入Activity or Fragment
                .setAgentWebParent(rlWeb, new RelativeLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator(ContextCompat.getColor(mActivity,R.color.theme))//进度条颜色
                .setWebChromeClient(mWebChromeClient)
                .setMainFrameErrorView(R.layout.layout_timeout, -1)
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()
                .ready()
                .go(url);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mAgentWeb.back()) {
//            essayWeb.goBack(); //goBack()表示返回WebView的上一页面
            mAgentWeb.back();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }

    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
            Log.e("dfdf", "onProgressChanged: " + newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            titleTitle.setText(title);
        }
    };

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        if (mAgentWeb.back()) {
            mAgentWeb.back();
        } else {
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }
}
