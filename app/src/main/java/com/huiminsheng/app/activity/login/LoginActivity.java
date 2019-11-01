package com.huiminsheng.app.activity.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huiminsheng.app.MyAplication;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.MainActivity;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.EvenBean.EvenBean_wx;
import com.huiminsheng.app.bean.Login;
import com.huiminsheng.app.jiguang.TagAliasOperatorHelper;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.weixin_login_img)
    ImageView weixinLoginImg;
    @BindView(R.id.login_user)
    EditText loginUser;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_body)
    LinearLayout loginBody;
    @BindView(R.id.login_remember_pwd)
    CheckBox loginRememberPwd;
    @BindView(R.id.login_in)
    TextView loginIn;
    @BindView(R.id.login_forget)
    TextView loginForget;
    Activity activity;
    @BindView(R.id.login_pwd_mw)
    CheckBox loginPwdMw;
    private long exitTime=0;
    private Disposable dispwd;
    private Disposable dispwdmw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activity = this;
        EventBus.getDefault().register(this);
        init();
    }

    @SuppressLint("CheckResult")
    public void init() {
        //记住密码
        if ("true".equals(SPUtils.get(this, "checked_jzmm", ""))) {
            loginRememberPwd.setChecked(true);
        } else {
            loginRememberPwd.setChecked(false);
        }
        dispwd = RxCompoundButton.checkedChanges(loginRememberPwd).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    SPUtils.put(activity, "checked_jzmm", "true");
                } else {
                    SPUtils.put(activity, "checked_jzmm", "false");
                }
            }
        });

        dispwdmw = RxCompoundButton.checkedChanges(loginPwdMw).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    loginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        if ("true".equals(SPUtils.get(this, "checked_jzmm", ""))) {
            //读取储存号码 密码
            String phone = (String) SPUtils.get(this, ApiUrls.KEY.PHONE, "");
            loginUser.setText(phone);
            String psw = (String) SPUtils.get(this, ApiUrls.KEY.PSW, "");
            loginPassword.setText(psw);
        }
    }


    @OnClick({R.id.weixin_login_img, R.id.login_pwd_mw, R.id.login_in, R.id.login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin_login_img:
                //微信登录
                wxlogin();
                break;
            case R.id.login_pwd_mw:
                //密码是否明文
                break;
            case R.id.login_in:
                //登录
                loginPrepare();
                break;
            case R.id.login_forget:
                //忘记密码
                ForgetPswActivity.jumpActivity(mActivity,ForgetPswActivity.class,null);
                break;
        }
    }

    //登录判断
    private void loginPrepare() {
        String account = loginUser.getText().toString().trim();
        String psw = loginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showToast(this, "账号不能为空");
            return;
        }
        if (account.length() < 11) {
            ToastUtils.showToast(this, "请输入11位手机号码");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            ToastUtils.showToast(this, "密码不能为空");
            return;
        }
        if (psw.length() < 6) {
            ToastUtils.showToast(this, "密码长度不能小于6位数");
            return;
        }
        loginPost(account, psw);
    }

    //登录
    private void loginPost(String account, String psw) {
        NoGo.create(this)
                .setUrl(ApiUrls.LOGIN.LOGIN)
                .addParam("mobile", account)
                .addParam("password", psw)
                .addParam("PRO", "1")
                .showDialog()
                .request(Login.class, new BaseListener<Login>() {
                    @Override
                    public void onSucceed(int what, Result<Login> result) {
                        phoneloginSuccess(result.getResult().getData());
                    }
                });
    }

    /**
     * 保存信息
     * @param data 数据
     */
    private void saveInfo(Login.DataBean data ){
        //token
        SPUtils.put(activity, ApiUrls.KEY.ACCESS_TOKEN, data.getAccess_token());
        //用户id
        SPUtils.put(activity, ApiUrls.KEY.MCHID, data.getProfile().getMchid());
        //头像
        SPUtils.put(mActivity, ApiUrls.KEY.HEADPIC,data.getProfile().getHeadpic());
        //昵称
        SPUtils.put(mActivity, ApiUrls.KEY.NICK_NAME, data.getProfile().getNick_name());

    }
    //账号登录成功
    private void phoneloginSuccess(Login.DataBean data ){
        //保存登录状态
        SPUtils.put(activity, ApiUrls.KEY.LOGGING_STATUS, "true");
        saveInfo(data);
        SPUtils.put(activity, ApiUrls.KEY.PHONE, data.getProfile().getMobile());
        //根据是否记住密码保存密码
        if (loginRememberPwd.isClickable()) {
            SPUtils.put(activity, ApiUrls.KEY.PSW, loginPassword.getText().toString());
        } else {
            SPUtils.put(activity, ApiUrls.KEY.PSW, "");
        }
        //极光
//        if (JPushInterface.isPushStopped(MyAplication.context)) {
//            JPushInterface.resumePush(MyAplication.context);
//        }

        /*注册极光推送消息*/
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = TagAliasOperatorHelper.ACTION_SET;
        tagAliasBean.alias = data.getProfile().getMchid();
        tagAliasBean.isAliasAction = true;
        TagAliasOperatorHelper.getInstance().handleAction(mActivity,100,tagAliasBean);
        //跳首页
        startActivity(new Intent(activity, MainActivity.class));
        finish();
    }

    /**
     * 微信登录
     */
    private void wxlogin() {
        if (MyAplication.api == null) {
            MyAplication.api = WXAPIFactory.createWXAPI(this, MyAplication.APP_ID, true);
        }
        if (!MyAplication.api.isWXAppInstalled()) {
            ToastUtils.showToast(this, "您手机尚未安装微信，请安装后再登录");
            return;
        }
        MyAplication.api.registerApp(MyAplication.APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_xb_live_state";//官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
        MyAplication.api.sendReq(req);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void even(EvenBean_wx messageEvent) {
        if (TextUtils.equals(messageEvent.getMessage(), "wx")) {
            //微信授权成功到后台获取用户信息
            NoGo.create(mActivity)
                    .get()
                    .setDebugName("dfdf_wx")
                    .setUrl(ApiUrls.LOGIN.JUDGE_BINDING_WX)
                    .addParam("type", "1")
                    .addParam("wxId", messageEvent.getWxid())
                    .addParam("wxnick", messageEvent.getNick_name())
                    .addParam("headpic", messageEvent.getHeadpic())
                    .addParam("mobile", "")
                    .showDialog()
                    .addParam("password", "")
                    .request(Login.class, new BaseListener<Login>() {
                        @Override
                        public void onSucceed(int what, Result<Login> result) {
                            //是 1 绑定 0 未绑定
                            if (TextUtils.equals(result.getResult().getData().getIs_mobile(),"0")){
                                saveInfo(result.getResult().getData());
                                //去绑定手机
                                Bundle bundle = new Bundle();
                                bundle.putString("Superior_moblie",result.getResult().getData().getSuperior_moblie());
                                bundle.putString("Mchid",result.getResult().getData().getProfile().getMchid());
                                BindingActivity.jumpActivity(mActivity,BindingActivity.class,bundle);
                            }else {
                                //保存登录信息
                                phoneloginSuccess(result.getResult().getData());
                            }
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispwd.dispose();
        dispwdmw.dispose();
        EventBus.getDefault().unregister(this);
    }

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
