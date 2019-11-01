package com.huiminsheng.app.activity.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.login.LoginActivity;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanUpApp;
import com.huiminsheng.app.bean.BeanUserinfo_head;
import com.huiminsheng.app.jiguang.TagAliasOperatorHelper;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.CollectedUtils;
import com.huiminsheng.app.utils.DataCleanManager;
import com.huiminsheng.app.utils.MyActivityManager;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView_yuan;
import com.huiminsheng.app.views.Update_diaog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//设置
public class SettingActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.lv_tx)
    MyImagView_yuan lvTx;
    @BindView(R.id.lv_tx_back)
    ImageView lvTxBack;
    @BindView(R.id.rl_tx)
    RelativeLayout rlTx;
    @BindView(R.id.tv_nc)
    TextView tvNc;
    @BindView(R.id.lv_nc_back)
    ImageView lvNcBack;
    @BindView(R.id.rl_nc)
    RelativeLayout rlNc;
    @BindView(R.id.tv_wx_ewm)
    TextView tvWxEwm;
    @BindView(R.id.lv_ewm_back)
    ImageView lvEwmBack;
    @BindView(R.id.rl_wxewm)
    RelativeLayout rlWxewm;
    @BindView(R.id.tv_zfb)
    TextView tvZfb;
    @BindView(R.id.lv_zfb_back)
    ImageView lvZfbBack;
    @BindView(R.id.rl_zfb)
    RelativeLayout rlZfb;
    @BindView(R.id.tv_yhk)
    TextView tvYhk;
    @BindView(R.id.lv_yhk_back)
    ImageView lvYhkBack;
    @BindView(R.id.rl_yhk)
    RelativeLayout rlYhk;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.lv_phone_back)
    ImageView lvPhoneBack;
    @BindView(R.id.rl_sjh)
    RelativeLayout rlSjh;
    @BindView(R.id.rl_xgdlmm)
    RelativeLayout rlXgdlmm;
    @BindView(R.id.rl_xgjymm)
    RelativeLayout rlXgjymm;
    @BindView(R.id.rl_wjjymm)
    RelativeLayout rlWjjymm;
    @BindView(R.id.tv_hc)
    TextView tvHc;
    @BindView(R.id.lv_hc_back)
    ImageView lvHcBack;
    @BindView(R.id.rl_qchc)
    RelativeLayout rlQchc;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.tv_xgjymm)
    TextView tvXgjymm;
    @BindView(R.id.tv_bbh)
    TextView tvBbh;
    private String wxqrcoedeUrl = "";
    private String name = "";
    private String alipayAccount = "";
    private String bankStr = "";
    private String trade_pwd_set = "";
    private BeanUpApp beanUpApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        init();
        showLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        upApp();
    }

    private void init() {
        getHzSize();
        //版本号
        tvBbh.setText(SpannableStringUtils.getBuilder("当前版本:").append(CollectedUtils.getVersionName(mActivity)).create());
    }

    //初始化
    private void getHzSize() {
        try {
            //获取缓存大小
            tvHc.setText(DataCleanManager.getTotalCacheSize(mActivity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化
    private void initData() {
        //头像相关
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.INFO)
                .request(BeanUserinfo_head.class, new BaseListener<BeanUserinfo_head>() {
                    @Override
                    public void onSucceed(int what, Result<BeanUserinfo_head> result) {
                        setUserinof(result.getResult());
                        showSuccess();
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        showTimeout(new LoadListener() {
                            @Override
                            public void loadlistener() {
                                initData();
                                showLoading();
                            }
                        });
                    }
                });

    }

    //填充数据
    private void setUserinof(BeanUserinfo_head result) {
        BeanUserinfo_head.DataBean dataBean = result.getData();
        //头像
        ToolsImage.loaderTx(mActivity, result.getData().getProfile().getHeadpic(), lvTx);
        //昵称
        tvNc.setText(dataBean.getProfile().getNick_name());
        //微信二维吗
        if (TextUtils.isEmpty(dataBean.getWxcode())) {
            tvWxEwm.setText("上传");
        } else {
            tvWxEwm.setText("修改");
            wxqrcoedeUrl = dataBean.getWxcode();
        }
        //我的支付宝
        if (TextUtils.isEmpty(dataBean.getAlipay_account())) {
            tvZfb.setText("未绑定");
        } else {
            tvZfb.setText(dataBean.getAlipay_account());
            alipayAccount = dataBean.getAlipay_account();
            name = dataBean.getAlipay_name();
        }
        //银行卡
        bankStr = dataBean.getBank_str();
        if (TextUtils.isEmpty(bankStr)) {
            tvYhk.setText("未绑定");
        } else {
            tvYhk.setText(bankStr);
        }
        //手机号
        if (TextUtils.isEmpty(dataBean.getProfile().getMobile())) {
            tvPhone.setText("未绑定");
            lvPhoneBack.setVisibility(View.VISIBLE);
        } else {
            String mobile = dataBean.getProfile().getMobile();
            if (mobile.length() == 11) {
                String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
                tvPhone.setText(SpannableStringUtils.getBuilder(maskNumber).append("(已绑定)").create());
            } else {
                tvPhone.setText(SpannableStringUtils.getBuilder(mobile).append("(已绑定)").create());
            }
            lvPhoneBack.setVisibility(View.INVISIBLE);
        }
        //是否设置过交易密码 0未设置 1已设置
        trade_pwd_set = dataBean.getProfile().getTrade_pwd_set();
        if (TextUtils.equals(trade_pwd_set, "0")) {
            tvXgjymm.setText("设置交易密码");
        } else {
            tvXgjymm.setText("修改交易密码");
        }
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("个人中心");
    }

    @OnClick({R.id.title_back, R.id.rl_wxewm, R.id.rl_tx, R.id.rl_nc, R.id.rl_zfb, R.id.rl_yhk, R.id.rl_sjh, R.id.rl_xgdlmm,
            R.id.rl_xgjymm, R.id.rl_wjjymm, R.id.rl_qchc, R.id.tv_exit, R.id.rl_jcgx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_tx:
                break;
            case R.id.rl_wxewm:
                //微信二维码
                Bundle bundle = new Bundle();
                bundle.putString("wxqrcoedeUrl", wxqrcoedeUrl);
                WxQrcodeActivity.jumpActivity(mActivity, WxQrcodeActivity.class, bundle);
                break;
            case R.id.rl_nc:
                break;
            case R.id.rl_zfb:
                //支付宝
                Bundle bundlezfb = new Bundle();
                bundlezfb.putString("name", name);
                bundlezfb.putString("alipayAccount", alipayAccount);
                AlipayAccountActivity.jumpActivity(mActivity, AlipayAccountActivity.class, bundlezfb);
                break;
            case R.id.rl_yhk:
                //银行卡
                if (TextUtils.isEmpty(bankStr)) {
                    BankActivity.jumpActivity(mActivity, BankActivity.class, null);
                } else {
                    BankNormalActivity.jumpActivity(mActivity, BankNormalActivity.class, null);
                }
                break;
            case R.id.rl_sjh:
                break;
            case R.id.rl_xgdlmm:
                //修改登录密码
                ChangeLoginPasswordActivity.jumpActivity(mActivity, ChangeLoginPasswordActivity.class, null);
                break;
            case R.id.rl_xgjymm:
                if (TextUtils.equals(trade_pwd_set, "0")) {
                    //设置交易密码
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("type", "0");
                    TransactionPasswordActivity.jumpActivity(mActivity, TransactionPasswordActivity.class, bundle1);
                } else if (TextUtils.equals(trade_pwd_set, "1")) {
                    //修改交易密码
                    ChangeTranctionPasswordActivity.jumpActivity(mActivity, ChangeTranctionPasswordActivity.class, null);
                }
                break;
            case R.id.rl_wjjymm:
                //忘记交易密码
                ResettradepwdActivity.jumpActivity(mActivity, ResettradepwdActivity.class, null);
                break;
            case R.id.rl_qchc:
                //清楚缓存
                PopupWindowGather.getAffirmPop(mActivity, "清除缓存将会删除下载内容，您确定清除吗？", new AffirmPopListener() {
                    @Override
                    public void onAffirmPopListener() {
                        DataCleanManager.clearAllCache(mActivity);
                        getHzSize();
                    }
                });
                break;
            case R.id.tv_exit:
                //退出登录
                PopupWindowGather.getAffirmPop(mActivity, "您确定退出登录吗？", new AffirmPopListener() {
                    @Override
                    public void onAffirmPopListener() {
                        //极光
                        //暂停推送的接收
//                        JPushInterface.stopPush(getApplicationContext());
                        //清除别名
                        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
                        tagAliasBean.action = TagAliasOperatorHelper.ACTION_DELETE;
                        tagAliasBean.isAliasAction = true;
                        TagAliasOperatorHelper.getInstance().handleAction(mActivity, 100, tagAliasBean);
                        //不清除电话 密码 是否第一次登录
                        String phone = (String) SPUtils.get(mActivity, ApiUrls.KEY.PHONE, "");
                        String psw = (String) SPUtils.get(mActivity, ApiUrls.KEY.PSW, "");
                        String first = (String) SPUtils.get(mActivity, ApiUrls.KEY.FIRST, "");
                        String checked_jzmm = (String) SPUtils.get(mActivity, "checked_jzmm", "");
                        SPUtils.clear(mActivity);
                        SPUtils.put(mActivity, ApiUrls.KEY.PHONE, phone);
                        SPUtils.put(mActivity, ApiUrls.KEY.PSW, psw);
                        SPUtils.put(mActivity, ApiUrls.KEY.FIRST, first);
                        SPUtils.put(mActivity, "checked_jzmm", checked_jzmm);
                        MyActivityManager.getInstance().finishAllActivity();
                        LoginActivity.jumpActivity(mActivity, LoginActivity.class, null);
                    }
                });
                break;
            case R.id.rl_jcgx:
                //检测更新
                if (beanUpApp != null) {
                    //检测更新
                    switch (beanUpApp.getData().getIs_update()) {
                        /*0不需更新 ;1 需更新 2:需强制更新*/
                        case "0":
                            ToastUtils.showToast(mActivity, "已是最新版");
                            break;
                        case "1":
                            //需更新
                            showUpappPop(beanUpApp.getData());
                            break;
                        case "2":
                            //需强制更新
                            showUpappPop(beanUpApp.getData());
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
    }

    //是否需要更新app
    private void upApp() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.LOGIN.UPGRADE)
                .addParam("version_code", String.valueOf(CollectedUtils.getVersionCode(mActivity)))
                .request(BeanUpApp.class, new BaseListener<BeanUpApp>() {
                    @Override
                    public void onSucceed(int what, Result<BeanUpApp> result) {
                        beanUpApp = result.getResult();
                        if (!TextUtils.equals(beanUpApp.getData().getIs_update(),"0")){
                            tvBbh.setText(SpannableStringUtils.getBuilder("当前版本:").append(CollectedUtils.getVersionName(mActivity))
                            .append(" (有新版)").setForegroundColor(ContextCompat.getColor(mActivity,R.color.text_red)).setProportion((float) 0.8).create());
                        }
                    }
                });
    }

    //更新提示框
    private void showUpappPop(final BeanUpApp.DataBean dataBean) {
        //不需更新
        Update_diaog update_diaog = new Update_diaog(mActivity, "", TextUtils.equals(dataBean.getIs_update(), "2"));
        update_diaog.setOnClickListener(new Update_diaog.OnOKClickListener() {
            @Override
            public void btnUpdateUpapp() {
                //更新
                Uri uri = Uri.parse(dataBean.getDown_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        //拦截返回键
        update_diaog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
            }
        });
        update_diaog.setCanceledOnTouchOutside(false);
        update_diaog.show();
    }
}
