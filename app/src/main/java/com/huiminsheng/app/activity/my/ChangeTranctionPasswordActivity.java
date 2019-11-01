package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
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
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

//修改支付密码
public class ChangeTranctionPasswordActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.tvAffirm)
    TextView tvAffirm;
    @BindView(R.id.et_old_psw)
    EditText etOldPsw;
    @BindView(R.id.et_new_psw)
    EditText etNewPsw;
    private Disposable dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tranction_password);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Observable<CharSequence> observableOldpsw = RxTextView.textChanges(etOldPsw);
        Observable<CharSequence> observableNewpsw = RxTextView.textChanges(etNewPsw);
        dis = Observable.combineLatest(observableOldpsw, observableNewpsw, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2)  {
                return charSequence.length() >= 6 && charSequence2.length() >= 6;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean){
                tvAffirm.setEnabled(aBoolean);
            }
        });
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("修改支付密码");
    }

    @OnClick({R.id.title_back, R.id.tvAffirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tvAffirm:
                String oldPsw = etOldPsw.getText().toString();
                String newPsw = etNewPsw.getText().toString();
                if (!TextUtils.equals(oldPsw,newPsw)){
                    submit(oldPsw,newPsw);
                }else {
                    ToastUtils.showToast(mActivity,"新密码和原密码相同");
                }
                break;
        }
    }

    private void submit(String oldPsw, final String newPsw) {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.SET_PAY_PWD)
                .addParam("old_trade_pwd",oldPsw)
                .addParam("trade_pwd",newPsw)
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity,"已为您修改密码，请记住新密码");
                        SPUtils.put(mActivity, ApiUrls.KEY.PSW, newPsw);
                        finish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!dis.isDisposed()){
            dis.dispose();
        }
    }
}
