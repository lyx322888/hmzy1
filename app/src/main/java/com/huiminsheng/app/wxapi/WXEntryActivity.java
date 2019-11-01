package com.huiminsheng.app.wxapi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huiminsheng.app.MyAplication;
import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanWXinfo;
import com.huiminsheng.app.bean.BeanWx;
import com.huiminsheng.app.bean.EvenBean.EvenBean_wx;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;

/*微信*/
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题
        MyAplication.api.handleIntent(getIntent(), this);
        mActivity = this;

    }
    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtils.showToast(this,"您已拒绝授权");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //ERR_OK = 0(用户同意) ERR_AUTH_DENIED = -4（用户拒绝授权） ERR_USER_CANCEL = -2（用户取消）
                if (RETURN_MSG_TYPE_SHARE == baseResp.getType()) {
                    ToastUtils.showToast(this,"您已取消分享");
                    finish();
                }
                else{
                    ToastUtils.showToast(this,"您已取消授权");
                    finish();
                }

                break;
            case BaseResp.ErrCode.ERR_OK:
                //授权成功
                switch (baseResp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) baseResp).code;
                        getToken(code);
                        break;
                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtils.showToast(this,"分享成功");
                        finish();
                    break;
                }
            break;
        }
    }
    //获取token
    private void getToken(String code) {
        NoGo.create(this)
                .setUrl(ApiUrls.WXURl)
                .addParam("appid",MyAplication.APP_ID)
                .addParam("secret",MyAplication.SECRET)
                .addParam("code",code)
                .addParam("grant_type","authorization_code")
                .normallyString(new OnResponseListener<String>() {
                    @Override
                    public void onStart(int what) {

                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        String data = response.get();
                                if (data!=null){
                                    try {
                                        BeanWx beanWx = new Gson().fromJson(data,BeanWx.class);
                                        getUserinfo(beanWx.getOpenid(),beanWx.getAccess_token());
                                    } catch (JsonSyntaxException e) {
                                        e.printStackTrace();
                                        ToastUtils.showToast(mActivity, "请重新登录");
                                        finish();
                                    }
                                }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        ToastUtils.showToast(mActivity, "网络出现问题啦，请重新授权登录");
                        finish();
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
    }
    //获取用户信息
   private void getUserinfo(String openid, final String access_token){
        NoGo.create(this)
                .setUrl(ApiUrls.WXLOGIN)
                .addParam("access_token", access_token)
                .addParam("openid", openid)
                .normallyString(new OnResponseListener<String>() {
                    @Override
                    public void onStart(int what) {

                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        String data = response.get();
                        if (data!=null){
                            try {
                                BeanWXinfo beanWXinfo = new Gson().fromJson(data,BeanWXinfo.class);
                                //token
                                //微信名称
                                String nickname = beanWXinfo.getNickname();
                                String wxid = beanWXinfo.getUnionid();
                                String headpic = beanWXinfo.getHeadimgurl();
                                SPUtils.put(mActivity,ApiUrls.KEY.NICK_NAME,nickname);
                                //微信头像
                                SPUtils.put(mActivity,ApiUrls.KEY.HEADPIC,beanWXinfo.getHeadimgurl());
                                //用户id
                                SPUtils.put(mActivity,ApiUrls.KEY.WXID,beanWXinfo.getUnionid());
                                finish();
                                EventBus.getDefault().post(new EvenBean_wx("wx",nickname,headpic,wxid));
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        ToastUtils.showToast(mActivity, "网络出现问题啦，请重新授权登录");
                        finish();
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });

   }
}
