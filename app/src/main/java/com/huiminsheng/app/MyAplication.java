package com.huiminsheng.app;

import android.app.Application;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.webkit.WebView;


import com.huiminsheng.app.unify.MediaLoader;
import com.huiminsheng.app.unify.loadsir.loadcallback.EmptyCallback;
import com.huiminsheng.app.unify.loadsir.loadcallback.ErrorCallback;
import com.huiminsheng.app.unify.loadsir.loadcallback.LoadingCallback;
import com.huiminsheng.app.unify.loadsir.loadcallback.TimeoutCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

import cn.jpush.android.api.JPushInterface;


public class MyAplication extends Application {
    //微信
    public static String APP_ID="wx3bf2abeb892d937a";
    public static String SECRET="905719026ece82d2a143da8827259401";
    public static IWXAPI api;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        try {
            //极光推送
//            JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
            JPushInterface.init(this);     		// 初始化 JPush

            //3.1.创建框架的配置参数对象
            InitializationConfig iconfig = InitializationConfig.newBuilder(this)
                    .connectionTimeout(3000)//连接超时
                    .build();
            NoHttp.initialize(iconfig);        //请求debug
            Logger.setDebug(true);
            Logger.setTag("dfdf");// 打印Log的tag。

            /*微信*/
            api = WXAPIFactory.createWXAPI(this, APP_ID, true);
            api.registerApp(APP_ID);
            //页面加载
            LoadSir.beginBuilder()
                    .addCallback(new ErrorCallback())//添加各种状态页
                    .addCallback(new EmptyCallback())
                    .addCallback(new LoadingCallback())
                    .addCallback(new TimeoutCallback())
                    .setDefaultCallback(SuccessCallback.class)//设置默认状态页
                    .commit();
            //分享
            UMConfigure.setLogEnabled(true);
            UMConfigure.init(this,"5cc168cf570df31d3000008a"
                    ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");
            //web预启动
            WebView mWebView=new WebView(new MutableContextWrapper(this));
            //图片选择框架

            Album.initialize(AlbumConfig.newBuilder(this)
                    .setAlbumLoader(new MediaLoader()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //字大小不随系统
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
    //配置信息
    {
        PlatformConfig.setQQZone("1108860932", "yfhr0nBzG0NFP5I4");
        PlatformConfig.setWeixin("wx3bf2abeb892d937a", "905719026ece82d2a143da8827259401");
    }
}
