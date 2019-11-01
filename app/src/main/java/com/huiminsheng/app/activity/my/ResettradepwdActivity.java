package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.CountdownUtils;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/*重置交易密码*/
public class ResettradepwdActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_exit)
    ImageView titleExit;
    @BindView(R.id.title_Right)
    ImageView titleRight;
    @BindView(R.id.title_Right_tx)
    TextView titleRightTx;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.forget_pwd_sms_hints)
    TextView forgetPwdSmsHints;
    @BindView(R.id.binding_yzm)
    EditText bindingYzm;
    @BindView(R.id.tv_dx)
    TextView tvDx;
    @BindView(R.id.tvAffirm)
    TextView tvAffirm;
    private Observer<Long> observer;//观察者
    private Disposable disposable;//观察者管理
    private static final int MAXTIME = 60; //短信有效时间
    private String mobile = "";
    private Disposable dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resettradepwd);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("忘记交易密码");
    }

    private void init() {
        mobile = (String) SPUtils.get(mActivity, ApiUrls.KEY.PHONE,"");
        Log.e("dfdf", "init: "+mobile );
        if (!TextUtils.isEmpty(mobile)){
            String maskNumber = mobile.substring(0,3)+"****"+ mobile.substring(7, mobile.length());
            forgetPwdSmsHints.setText("您的手机"+ maskNumber);
        }
        //观察者注册
        initObserver();

        dis = RxTextView.textChanges(bindingYzm).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                if (charSequence.length()==4){
                    tvAffirm.setEnabled(true);
                }else {
                    tvAffirm.setEnabled(false);
                }
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
        //请求短信验证码
    private void getVerificationCode(String phone){
            NoGo.create(mActivity)
                    .get()
                    .setUrl(ApiUrls.MY.FORGET_PWD_SMS)
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
    @OnClick({R.id.title_back, R.id.tv_dx, R.id.tvAffirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_dx:
                //获取短信
                if (!TextUtils.isEmpty(mobile)){
                    getVerificationCode(mobile);
                }
                break;
            case R.id.tvAffirm:
                //下一步
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "0");
                bundle1.putString("smsCode", bindingYzm.getText().toString());
                TransactionPasswordActivity.jumpActivity(mActivity, TransactionPasswordActivity.class, bundle1);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
        dis.dispose();
    }
}
