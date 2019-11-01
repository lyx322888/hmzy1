package com.huiminsheng.app.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function4;

//忘记密码
public class ForgetPswActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.tv_dx)
    TextView tvDx;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_qr)
    EditText etPasswordQr;
    @BindView(R.id.login_body)
    LinearLayout loginBody;
    @BindView(R.id.tvAffirm)
    TextView tvAffirm;

    private Observer<Long> observer;//观察者
    private Disposable disposable;//观察者管理
    private static final int MAXTIME = 60; //短信有效时间
    private Disposable dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //观察者注册
        initObserver();

        Observable<CharSequence> observableOldpsw = RxTextView.textChanges(etUser);
        Observable<CharSequence> observableNewpsw = RxTextView.textChanges(etPassword);
        Observable<CharSequence> observableYzm = RxTextView.textChanges(etYzm);
        Observable<CharSequence> observableNewpswqr = RxTextView.textChanges(etPasswordQr);
        dis = Observable.combineLatest(observableOldpsw, observableNewpsw, observableYzm, observableNewpswqr, new Function4<CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) throws Exception {
                return charSequence.length()>0&&charSequence2.length()>0&&charSequence3.length()>0&&charSequence4.length()>0;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                tvAffirm.setEnabled(aBoolean);
            }
        });
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
    @Override
    protected void setTitleView() {
        titleTitle.setText("忘记密码");
    }

    @OnClick({R.id.title_back, R.id.tv_dx, R.id.tvAffirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_dx:
                //发送短信
                if (etUser.length()<11){
                    ToastUtils.showToast(this, "请输入11位手机号码");
                }else {
                    //发送短信
                    getVerificationCode(etUser.getText().toString());
                }
                break;
            case R.id.tvAffirm:
                //重置密码
                if (etUser.length()<11){
                    ToastUtils.showToast(this, "请输入11位手机号码");
                }else if (etYzm.length()==0){
                    ToastUtils.showToast(this, "请输入验证码");
                }else if (etPassword.length()<6){
                    ToastUtils.showToast(this, "密码长度不能小于6位数");
                }else if (!TextUtils.equals(etPassword.getText(),etPasswordQr.getText())){
                    ToastUtils.showToast(this, "两次密码不一致");
                }else {
                    //绑定
                    bindingPhone(etUser.getText().toString(),etPassword.getText().toString(),etYzm.getText().toString());
                }
                break;
        }
    }
    //请求短信验证码
    private void getVerificationCode(String phone){
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.LOGIN.CODE_RETRIEVE)
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
                .setUrl(ApiUrls.LOGIN.RETRIEVE)
                .addParam("password",password)
                .addParam("mobile",mobile)
                .addParam("code",code)
                .showDialog()
                .request(BeanBinding.class, new BaseListener<BeanBinding>() {
                    @Override
                    public void onSucceed(int what, Result<BeanBinding> result) {
                        //绑定成功
                        ToastUtils.showToast(mActivity,"已为您修改密码，请记住新密码！");
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
        super.onDestroy();
    }
}
