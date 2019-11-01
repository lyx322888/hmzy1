package com.huiminsheng.app.activity.my;

import android.os.Bundle;
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
import com.huiminsheng.app.utils.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

//添加支付宝账号
public class AlipayAccountActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.et_realName)
    EditText etRealName;
    @BindView(R.id.et_aliAccount)
    EditText etAliAccount;
    @BindView(R.id.tvAffirm)
    TextView tvAffirm;
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

    private String name;
    private String alipayAccount;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay_account);
        ButterKnife.bind(this);
        init();
    }
    //初始化
    private void init() {
        final Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        name =bundle.getString("name","");
        alipayAccount =bundle.getString("alipayAccount","");
        etRealName.setText(name);
        etAliAccount.setText(alipayAccount);

        //创建输入框的监听
        Observable<CharSequence> etRealNameOb = RxTextView.textChanges(etRealName);
        //创建输入框的监听
        Observable<CharSequence> etAliAccountOb = RxTextView.textChanges(etAliAccount);
        //使用combineLatest操作符,传入我们创建的那些监听   可以多个
        disposable = Observable.combineLatest(etRealNameOb, etAliAccountOb, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return charSequence.length() > 0 && charSequence2.length() > 0;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                tvAffirm.setEnabled(aBoolean);
            }
        });

    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("我的支付宝");
    }

    @OnClick({R.id.title_back, R.id.tvAffirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tvAffirm:
                submit();
                break;
        }
    }
    //提交
    private void submit(){
        alipayAccount = etAliAccount.getText().toString().trim();
        name = etRealName.getText().toString().trim();
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.SETALIACCOUNT)
                .addParam("ali_account",alipayAccount)
                .addParam("ali_name",name)
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity,"已为您绑定支付宝");
                        finish();
                    }
                });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null){
            disposable.dispose();
        }
    }
}
