package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.views.KeyboardView;
import com.huiminsheng.app.views.TransactionPasswordView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//交易密码
public class TransactionPasswordActivity extends BaseActivity {

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
    @BindView(R.id.tpv_jyma)
    TransactionPasswordView tpvJyma;
    @BindView(R.id.kv_jp)
    KeyboardView kvJp;
    @BindView(R.id.reset_pay_pwd_hints1)
    TextView resetPayPwdHints1;
    @BindView(R.id.tvAffirm)
    TextView tvAffirm;
    private Disposable dis;
    private String type;
    private String oldpsw;
    private String smsCode = "";//从重置密码进来

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_password);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("type");
            oldpsw = bundle.getString("oldpsw");
            smsCode = bundle.getString("smsCode");
            //1是再次确认密码 0是第一次
            if (TextUtils.equals(type, "1")) {
                tvAffirm.setVisibility(View.VISIBLE);
                resetPayPwdHints1.setText("请确认交易密码");
            } else {
                tvAffirm.setVisibility(View.GONE);
                resetPayPwdHints1.setText("请设置交易密码");
            }
        }

        tpvJyma.addKeyboard(kvJp);
        dis = RxTextView.textChanges(tpvJyma).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                if (charSequence.length() == 6) {
                    //验证支付密码
                    if (TextUtils.equals(type, "0")) {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("type", "1");
                        bundle1.putString("oldpsw", charSequence.toString());
                        if (!TextUtils.isEmpty(smsCode)){
                            bundle1.putString("smsCode", smsCode);
                        }
                        TransactionPasswordActivity.jumpActivity(mActivity, TransactionPasswordActivity.class, bundle1);
                        finish();
                    } else {
                        if (TextUtils.equals(oldpsw,charSequence.toString())){
                            tvAffirm.setEnabled(true);
                        }else {
                            ToastUtils.showToast(mActivity,"两次密码不一致，请重新输入");
                            tpvJyma.setPassword("");
                        }
                    }
                } else {
                    if (TextUtils.equals(type, "1")) {
                        tvAffirm.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("设置交易密码");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        PopupWindowGather.getAffirmPop(mActivity, "您是否退出密码设置？", new AffirmPopListener() {
            @Override
            public void onAffirmPopListener() {
                finish();
            }
        });
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dis != null) {
            if (!dis.isDisposed()) {
                dis.dispose();
            }
        }
    }

    @OnClick({R.id.title_back, R.id.tvAffirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                PopupWindowGather.getAffirmPop(mActivity, "您是否退出密码设置？", new AffirmPopListener() {
                    @Override
                    public void onAffirmPopListener() {
                        finish();
                    }
                });
                break;
            case R.id.tvAffirm:
                if (TextUtils.isEmpty(smsCode)){
                    submit();
                }else {
                    resetPsw();
                }
                break;
        }
    }
    //提交支付密码
    private void submit(){
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.SET_PAY_PWD)
                .addParam("trade_pwd",oldpsw)
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity,"支付密码设置成功，请记住");
                        finish();
                    }
                });
    }
    //重置支付密码
    private void resetPsw(){
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.FORGET_PAY_PWD)
                .get()
                .addParam("code",smsCode)
                .addParam("mobile", (String) SPUtils.get(mActivity,ApiUrls.KEY.PHONE,""))
                .addParam("trade_pwd",oldpsw)
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity,"支付密码重置成功，请记住");
                        finish();
                    }
                });
    }
}
