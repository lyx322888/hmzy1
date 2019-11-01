package com.huiminsheng.app.activity.my;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanUserinfo_head;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.PopwindownUtils;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.views.KeyboardView;
import com.huiminsheng.app.views.TransactionPasswordView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//提现
public class WithdrawDepositActivity extends BaseActivity {

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
    @BindView(R.id.tv_qbtx)
    TextView tvQbtx;
    @BindView(R.id.tv_tx)
    TextView tvTx;
    @BindView(R.id.tv_ktxye)
    TextView tvKtxye;

    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_marked_words)
    TextView tvMarkedWords;
    private PopupWindow popupWindow;
    private double usable;//可用余额
    private Disposable dis;
    private Disposable disPsw;
    //是否设置交易密码
    private boolean isSettingPayPsw = false;
    private TransactionPasswordView tpv_mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_deposit);
        ButterKnife.bind(this);
        init();
        showLoading();
    }

    //初始化
    private void init() {
        //在我们点EditText的时候弹出我们的软键盘
        hideSystemSofeKeyboard(etPrice);
        etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showKeyboardviewPop();
                }
            }
        });
        etPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboardviewPop();
            }
        });
        etPrice.setSelection(etPrice.getText().length());

        dis = RxTextView.textChanges(etPrice).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                if (!TextUtils.isEmpty(charSequence) && Double.parseDouble(charSequence.toString()) > 0) {
                    tvTx.setEnabled(true);
                } else {
                    tvTx.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("立即提现");
        titleRightTx.setText("提现记录");
        titleRightTx.setVisibility(View.VISIBLE);
    }

    // 初始化
    private void getData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.INFO)
                .get()
                .request(BeanUserinfo_head.class, new BaseListener<BeanUserinfo_head>() {
                            @Override
                            public void onSucceed(int what, Result<BeanUserinfo_head> result) {
                                showSuccess();
                                setinfo(result.getResult().getData());
                                showKeyboardviewPop();
                            }

                            @Override
                            public void onFailed(int what) {
                                super.onFailed(what);
                                showTimeout(new LoadListener() {
                                    @Override
                                    public void loadlistener() {
                                        getData();
                                    }
                                });
                            }
                        }
                );
    }

    //设置信息
    private void setinfo(BeanUserinfo_head.DataBean data) {
        usable = Double.parseDouble(data.getSales_usable());
        tvKtxye.setText(SpannableStringUtils.getBuilder("账户总余额可提").append("¥").append(data.getSales_usable())
                .setForegroundColor(ContextCompat.getColor(mActivity, R.color.font_zscx_jg)).append("元").create());
        //是否设置支付密码
        isSettingPayPsw = TextUtils.equals(data.getProfile().getTrade_pwd_set(), "1");
        //提示语
        tvMarkedWords.setText(data.getCash_msg());
    }

    @OnClick({R.id.title_back, R.id.tv_qbtx, R.id.title_Right_tx, R.id.tv_tx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_Right_tx:
                //提现记录
                WithdrawalRecordActivity.jumpActivity(mActivity, WithdrawalRecordActivity.class, null);
                break;
            case R.id.tv_qbtx:
                //全部提现
                etPrice.setText(String.valueOf(usable));
                etPrice.setSelection(etPrice.getText().length());
                break;
            case R.id.tv_tx:
                //提现
                if (TextUtils.isEmpty(etPrice.getText())) {
                    ToastUtils.showToast(mActivity, "请输入要提现的金额");
                } else if (Double.parseDouble(etPrice.getText().toString()) > usable) {
                    ToastUtils.showToast(mActivity, "超过可提现金额");
                } else {
                    //看是否设置密码
                    if (isSettingPayPsw) {
                        showpayPsw();
                    } else {
                        PopupWindowGather.getAffirmPop(mActivity, "请先设置交易密码", "去设置", new AffirmPopListener() {
                            @Override
                            public void onAffirmPopListener() {
                                //设置交易密码
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("type", "0");
                                TransactionPasswordActivity.jumpActivity(mActivity, TransactionPasswordActivity.class, bundle1);
                            }
                        });
                    }
                }
                break;
        }
    }

    //验证支付密码
    private void submitPsw(String trade_pwd) {
        NoGo.create(mActivity)
                .get().setUrl(ApiUrls.MY.VERIFY_PAY_PWD)
                .addParam("trade_pwd", trade_pwd)
                .showDialog()
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        if (result.getStatus() == 10000) {
                            submit();
                        } else {
                            if (tpv_mm != null) {
                                tpv_mm.setPassword("");
                            }
                            ToastUtils.showToast(mActivity, result.getMsg());
                        }
                    }
                });
    }

    //提现请求
    private void submit() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.SPLITCASH)
                .addParam("money", etPrice.getText().toString())
                .showDialog()
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        if (result.getStatus() == 10000) {
                            ToastUtils.showToast(mActivity, "提现成功");
                            getData();
                        } else if (result.getStatus() == 10001) {
                            //去绑定银行卡
                            PopupWindowGather.getAffirmPop(mActivity, "请绑定银行卡", "去绑定", new AffirmPopListener() {
                                @Override
                                public void onAffirmPopListener() {
                                    BankActivity.jumpActivity(mActivity, BankActivity.class, null);
                                }
                            });
                        } else if (result.getStatus() == 10002) {
                            //去绑定支付宝
                            PopupWindowGather.getAffirmPop(mActivity, "请绑定支付宝", "去绑定", new AffirmPopListener() {
                                @Override
                                public void onAffirmPopListener() {
                                    Bundle bundlezfb = new Bundle();
                                    bundlezfb.putString("name", "");
                                    bundlezfb.putString("alipayAccount", "");
                                    AlipayAccountActivity.jumpActivity(mActivity, AlipayAccountActivity.class, bundlezfb);
                                }
                            });
                        } else {
                            ToastUtils.showToast(mActivity, result.getMsg());
                        }
                    }
                });
    }

    //显示键盘
    private void showKeyboardviewPop() {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_keyboardview, null, false);
        if (popupWindow == null) {
            popupWindow = new PopwindownUtils().initView(mActivity, inflate, false);
            popupWindow.setAnimationStyle(0);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(false);
        }
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        RelativeLayout rl = inflate.findViewById(R.id.rl_down);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        KeyboardView keyboardView = inflate.findViewById(R.id.kv_jp);
        keyboardView.showDecimalPoint();//显示小数点
        keyboardView.addKeyboardListener(new KeyboardView.OnKeyboardListener() {
            @Override
            public void outputNumber(String number) {
                String content = etPrice.getText() + number;
                //首位是小数点时自动补0.
                if (TextUtils.equals(content, ".")) {
                    content = "0.";
                }
                //只允许存在一个小数点
                if (!TextUtils.equals(number, ".") || !etPrice.getText().toString().contains(".")) {
                    etPrice.setText(content);
                    etPrice.setSelection(etPrice.getText().length());
                }

            }

            @Override
            public void outputDelete() {
                if (etPrice.length() != 0) {
                    String content = etPrice.getText().toString().substring(0, etPrice.getText().length() - 1);
                    etPrice.setText(content);
                    etPrice.setSelection(etPrice.getText().length());
                } else {
                    etPrice.setText("");
                    etPrice.setSelection(etPrice.getText().length());
                }
            }
        });
    }

    //支付密码
    private void showpayPsw() {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_withdraw_psw, null, false);
        final PopupWindow popupWindowPsw = new PopwindownUtils().initView(mActivity, inflate, true);
        popupWindowPsw.setAnimationStyle(0);
        popupWindowPsw.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        ImageView lvExit = inflate.findViewById(R.id.lv_exit);
        TextView tv_money = inflate.findViewById(R.id.tv_money);
        TextView tv_wjmm = inflate.findViewById(R.id.tv_wjmm);
        tpv_mm = inflate.findViewById(R.id.tpv_jyma);
        KeyboardView keyboardView = inflate.findViewById(R.id.kv_jp);
        //退出
        lvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowPsw.dismiss();
            }
        });
        //格式化数字
        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
        String money = myformat.format(Double.parseDouble(etPrice.getText().toString()));
        etPrice.setText(money);
        etPrice.setSelection(etPrice.getText().length());
        tv_money.setText("提现金额¥" +money );
        //忘记密码
        tv_wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResettradepwdActivity.jumpActivity(mActivity, ResettradepwdActivity.class, null);
            }
        });
        tpv_mm.addKeyboard(keyboardView);
        disPsw = RxTextView.textChanges(tpv_mm).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                if (charSequence.length() == 6) {
                    //验证支付密码
                    NoGo.create(mActivity)
                            .get().setUrl(ApiUrls.MY.VERIFY_PAY_PWD)
                            .addParam("trade_pwd", charSequence.toString())
                            .showDialog()
                            .request(new BaseListener<String>() {
                                @Override
                                public void onSucceed(int what, Result<String> result) {
                                    if (result.getStatus() == 10000) {
                                        submit();
                                        popupWindowPsw.dismiss();
                                    } else {
                                        if (tpv_mm != null) {
                                            tpv_mm.setPassword("");
                                        }
                                        ToastUtils.showToast(mActivity, result.getMsg());
                                    }
                                }
                            });
                }
            }
        });
    }

    /**
     * 隐藏系统键盘
     *
     * @param editText
     */
    public void hideSystemSofeKeyboard(EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dis != null && !dis.isDisposed()) {
            dis.dispose();
        }
        if (disPsw != null && !disPsw.isDisposed()) {
            disPsw.dispose();
        }
    }
}