package com.huiminsheng.app.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.MainActivity;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanBinding;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.CountdownUtils;
import com.huiminsheng.app.utils.MyActivityManager;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//绑定页面
public class BindingActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_exit)
    ImageView titleExit;
    @BindView(R.id.title_Right)
    ImageView titleRight;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.tv_tjr)
    TextView tvTjr;
    @BindView(R.id.binding_user)
    EditText bindingUser;
    @BindView(R.id.binding_yzm)
    EditText bindingYzm;
    @BindView(R.id.binding_password)
    EditText bindingPassword;
    @BindView(R.id.binding_pwd_mw)
    CheckBox bindingPwdMw;
    @BindView(R.id.login_body)
    LinearLayout loginBody;
    @BindView(R.id.binding_affirm)
    TextView bindingAffirm;
    @BindView(R.id.tv_dx)
    TextView tvDx;

    private Observer<Long> observer;//观察者
    private Disposable disposable;//观察者管理
    private static final int MAXTIME = 60; //短信有效时间
    private String access_token= "";
    private String mchid = "";
    private Disposable dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("绑定手机号");
    }

    //初始化
    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        //推荐人
        String superiorMblie = bundle.getString("Superior_moblie");
        access_token = bundle.getString("Access_token");
        mchid = bundle.getString("Mchid");
        if (!TextUtils.isEmpty(superiorMblie)) {
            tvTjr.setText("推荐人:" + superiorMblie);
        }
        //密码是否显示明文
        dis = RxCompoundButton.checkedChanges(bindingPwdMw).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    bindingPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    bindingPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        //观察者注册
        initObserver();
    }

    //观察者注册_倒计时
    private void initObserver() {
        observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                //开始
                disposable = d;
                tvDx.setClickable(false);
            }

            @Override
            public void onNext(Long aLong) {
                tvDx.setText(String.format("剩余%ss", aLong));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                //倒计时完成
                tvDx.setClickable(true);
                tvDx.setText(getResources().getString(R.string.tv_dx));
            }
        };
    }


    @OnClick({R.id.title_back, R.id.tv_dx, R.id.binding_affirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_dx:
                if (bindingUser.length()<11){
                    ToastUtils.showToast(this, "请输入11位手机号码");
                }else {
                    //发送短信
                    getVerificationCode(bindingUser.getText().toString());
                }
                break;
            case R.id.binding_affirm:
                if (bindingUser.length()<11){
                ToastUtils.showToast(this, "请输入11位手机号码");
            }else if (bindingYzm.length()==0){
                ToastUtils.showToast(this, "请输入验证码");
            }else if (bindingPassword.length()<6){
                ToastUtils.showToast(this, "密码长度不能小于6位数");
            }else {
                //绑定
                bindingPhone(bindingUser.getText().toString(),bindingPassword.getText().toString(),bindingYzm.getText().toString());
            }
                break;
        }
    }
    //请求短信验证码
    private void getVerificationCode(String phone){
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.LOGIN.REGSMS)
                .setDebugName("dfdf_dx")
                .addParam("mobile",phone)
                .request(new BaseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        super.onStart(what);
                        tvDx.setClickable(false);
                        tvDx.setText("短信发送中");
                    }

                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        if (result.getStatus()==10000){
                            //验证码已发送  //进入倒计时
                            ToastUtils.showToast(mActivity,result.getMsg());
                            CountdownUtils.countdown(observer, MAXTIME);//订阅计时器
                        }else {
                            ToastUtils.showToast(mActivity,result.getMsg());
                            tvDx.setClickable(true);
                            tvDx.setText(getResources().getString(R.string.tv_dx));
                        }
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        tvDx.setClickable(true);
                        tvDx.setText(getResources().getString(R.string.tv_dx));
                    }
                });
    }

    //绑定请求
    private void  bindingPhone(String mobile,String password,String code){
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.LOGIN.BIND)
                .addParam("password",password)
                .addParam("mobile",mobile)
                .showDialog()
                .addParam("code",code)
                .request(BeanBinding.class, new BaseListener<BeanBinding>() {
                    @Override
                    public void onSucceed(int what, Result<BeanBinding> result) {
                        //绑定成功
                        SPUtils.put(mActivity, ApiUrls.KEY.PHONE, result.getResult().getData().getMobile());
                        //保存登录状态
                        SPUtils.put(mActivity, ApiUrls.KEY.LOGGING_STATUS, "true");
                        //极光
                        if (JPushInterface.isPushStopped(getApplicationContext())) {
                            JPushInterface.resumePush(getApplicationContext());
                        }
                        /*注册极光推送消息*/
                        JPushInterface.setAlias(mActivity, 0, mchid);
                        MyActivityManager.getInstance().finishActivity(LoginActivity.class);
                        Intent intent = new Intent(mActivity, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        //取消订阅
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        dis.dispose();
        super.onDestroy();
    }
}
