package com.huiminsheng.app.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.MainActivity;
import com.huiminsheng.app.activity.my.OrderDetailsActivity;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanOrderDetails;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.FormatUtils;
import com.huiminsheng.app.utils.MyActivityManager;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//支付成功后
public class PaymentSuccessActivity extends BaseActivity {
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
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.tv_copy_card)
    TextView tvCopyCard;
    @BindView(R.id.tv_card_psw)
    TextView tvCardPsw;
    @BindView(R.id.tv_copy_psw)
    TextView tvCopyPsw;
    @BindView(R.id.ll_kh)
    LinearLayout llKh;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_copy_order_number)
    TextView tvCopyOrderNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_normal)
    LinearLayout llNormal;
    @BindView(R.id.tv_jxgw)
    TextView tvJxgw;
    @BindView(R.id.tv_ckdd)
    TextView tvCkdd;
    //订单号
    private String order_sn ="";
    private String orderNumber = "";
    private String cardNumber = "";
    private String cardPsw = "";
    private String trade_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        ButterKnife.bind(this);
        init();
        showLoading();
        getData();
    }

    //初始化
    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        order_sn = bundle.getString("order_sn");
        trade_type = bundle.getString("trade_type");
    }
    //请求订单详情
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.COMMODITY.GETORDERINFO)
                .addParam("order_sn", order_sn)
                .request(BeanOrderDetails.class, new BaseListener<BeanOrderDetails>() {
                    @Override
                    public void onSucceed(int what, Result<BeanOrderDetails> result) {
                        showSuccess();
                        setinfo(result.getResult().getData());
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
    private void setinfo(BeanOrderDetails.DataBean data) {
        //判断是否是充值卡 空不是
        if (TextUtils.isEmpty(data.getCardAccount())){
            //正常订单
            llKh.setVisibility(View.GONE);
            llNormal.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(data.getPay_time())){
                tvTime.setText(FormatUtils.getTime2SH(data.getPay_time()));
            }
            orderNumber = data.getOrder_sn();
          tvOrderNumber.setText(data.getOrder_sn());
        }else {
            //充值卡
            llKh.setVisibility(View.VISIBLE);
            llNormal.setVisibility(View.GONE);
            cardNumber = data.getCardAccount();
            cardPsw = data.getCardPassword();
            tvCardNumber.setText(data.getCardAccount());
            tvCardPsw.setText(data.getCardPassword());
        }
        tvPrice.setText(data.getAll_price());
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("支付成功");
    }

    @OnClick({R.id.title_back, R.id.tv_copy_card, R.id.tv_copy_psw, R.id.tv_copy_order_number, R.id.tv_jxgw, R.id.tv_ckdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_copy_card:
                //复制卡号
                FormatUtils.copy(mActivity,cardNumber,"已复制");
                break;
            case R.id.tv_copy_psw:
                //复制密码
                FormatUtils.copy(mActivity,cardPsw,"已复制");
                break;
            case R.id.tv_copy_order_number:
                //复制订单号
                FormatUtils.copy(mActivity,orderNumber,"已复制");
                break;
            case R.id.tv_jxgw:
                //先判断是否是从我的订单过来  如果是则跳回我的订单
                //继续购物 一般跳回首页  成为店长页面则跳详情页
                if (TextUtils.equals(trade_type,"myOrder")){
                    //从我的订单过来
                    finish();
                }else if (TextUtils.equals(trade_type,"6")){
                    //从成为店长页面过来
                    MyActivityManager.getInstance().finishAboveActivity(ProductDetailsActivity.class);
                }else {
                    Intent intent = new Intent(mActivity, MainActivity.class);
                    //栈内复用
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    startActivity(intent);
                }

                break;
            case R.id.tv_ckdd:
                //查看订单
                Bundle bundle = new Bundle();
                bundle.putString("order_sn",order_sn);
                OrderDetailsActivity.jumpActivity(mActivity,OrderDetailsActivity.class,bundle);
                finish();
                break;
        }
    }
}
