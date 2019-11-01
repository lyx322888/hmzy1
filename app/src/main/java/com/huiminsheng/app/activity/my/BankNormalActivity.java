package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanMybank;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView_yuan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*w我的的银行卡  有的情况*/
public class BankNormalActivity extends BaseActivity {

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
    @BindView(R.id.iv_bank_logo)
    MyImagView_yuan ivBankLogo;
    @BindView(R.id.tv_bankName)
    TextView tvBankName;
    @BindView(R.id.tv_ghcxk)
    TextView tvGhcxk;
    @BindView(R.id.tv_bank_number)
    TextView tVbankNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_normal);
        ButterKnife.bind(this);
        showLoading();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("我的银行卡");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.MYBANK)
                .request(BeanMybank.class, new BaseListener<BeanMybank>() {
                    @Override
                    public void onSucceed(int what, Result<BeanMybank> result) {
                        showSuccess();
                        setBankView(result.getResult().getData());
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
                });
    }

    //填充数据
    private void setBankView(BeanMybank.DataBean data) {
        BeanMybank.DataBean.DepositBean depositBean = data.getDeposit().get(0);
        ToolsImage.loaderYt(mActivity, depositBean.getBank_logo(), ivBankLogo);
        tvBankName.setText(depositBean.getBank_name());
        tVbankNumber.setText(depositBean.getBank_no());
    }

    @OnClick({R.id.title_back, R.id.tv_ghcxk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_ghcxk:
                PopupWindowGather.getAffirmPop(mActivity, "更换储蓄卡后原有的储蓄卡将会被替换，您确定更换储蓄卡吗？", new AffirmPopListener() {
                    @Override
                    public void onAffirmPopListener() {
                        BankActivity.jumpActivity(mActivity,BankActivity.class,null);
                    }
                });
                break;
        }
    }
}
