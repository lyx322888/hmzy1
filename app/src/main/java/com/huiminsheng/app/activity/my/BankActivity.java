package com.huiminsheng.app.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function5;

//我的银行卡
public class BankActivity extends BaseActivity {

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
    @BindView(R.id.et_realName)
    EditText etRealName;
    @BindView(R.id.et_sfz)
    EditText etSfz;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yh)
    TextView etYh;
    @BindView(R.id.rl_xzyh)
    RelativeLayout rlXzyh;
    @BindView(R.id.et_yhkh)
    EditText etYhkh;
    @BindView(R.id.tvAffirm)
    TextView tvAffirm;
    private Disposable disposable;
    private final int REQUEST_CODE = 10000;
    private String bankId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        ButterKnife.bind(this);
        init();
    }
    //初始化
    private void init() {
        String digists = "abcdefghigklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        etSfz.setKeyListener(DigitsKeyListener.getInstance(digists));//设置限制文本
        setEditJt();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("我的银行卡");
    }

    //设置监听
    private void setEditJt(){
        //创建输入框的监听
        Observable<CharSequence> etRealNameOb = RxTextView.textChanges(etRealName);
        Observable<CharSequence> etSfzOb = RxTextView.textChanges(etSfz);
        Observable<CharSequence> etPhoneOb = RxTextView.textChanges(etPhone);
        Observable<CharSequence> etYhOb = RxTextView.textChanges(etYh);
        Observable<CharSequence> etYhkhOb = RxTextView.textChanges(etYhkh);
        //使用combineLatest操作符,传入我们创建的那些监听   可以多个
        disposable = Observable.combineLatest(etRealNameOb, etSfzOb, etPhoneOb, etYhOb, etYhkhOb, new Function5<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence etRealNameOb, CharSequence etSfzOb, CharSequence etPhoneOb, CharSequence etYhOb, CharSequence etYhkhOb) throws Exception {
                return etRealNameOb.length() != 0 && etSfzOb.length() != 0 && etPhoneOb.length() != 0 && etYhOb.length() != 0 && etYhkhOb.length() != 0;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                tvAffirm.setEnabled(aBoolean);
            }
        });
    }

    @OnClick({R.id.title_back, R.id.rl_xzyh, R.id.tvAffirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_xzyh:
                //选择银行
                startActivityForResult(new Intent(mActivity,SelectBankActivity.class),REQUEST_CODE);
                break;
            case R.id.tvAffirm:
                //添加银行卡
                submit();
                break;
        }
    }
    //提交
    private void submit(){
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.BANKADD)
                .addParam("bank_id",bankId)
                .addParam("bank_no",etYhkh.getText().toString().trim())
                .addParam("name",etRealName.getText().toString().trim())
                .addParam("idcard",etSfz.getText().toString().trim())
                .addParam("phone",etPhone.getText().toString().trim())
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity,"添加银行卡成功");
                        finish();
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE&&resultCode==21546){
            if (data!=null){
                String bankName = data.getStringExtra("bankName");
                bankId = data.getStringExtra("bankId");
                etYh.setText(bankName);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
