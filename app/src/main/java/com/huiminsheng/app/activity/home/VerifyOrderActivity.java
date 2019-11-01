package com.huiminsheng.app.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.MainActivity;
import com.huiminsheng.app.activity.my.OrderDetailsActivity;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanOrder;
import com.huiminsheng.app.bean.PayResult;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.MyActivityManager;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//确认订单
public class VerifyOrderActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.et_realName)
    EditText etRealName;
    @BindView(R.id.et_sfz)
    EditText etSfz;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.lv_car)
    MyImagView lvCar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_dj)
    TextView tvDj;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.et_mjly)
    EditText etMjly;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_total_prices)
    TextView tvTotalPrices;
    @BindView(R.id.lv_wx)
    ImageView lvWx;
    @BindView(R.id.cb_wx)
    AppCompatRadioButton cbWx;
    @BindView(R.id.lv_zfb)
    ImageView lvZfb;
    @BindView(R.id.cb_zfb)
    AppCompatRadioButton cbZfb;
    @BindView(R.id.tv_sum_b)
    TextView tvSumB;
    @BindView(R.id.tv_total_prices_b)
    TextView tvTotalPricesB;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.tv_address_xz)
    TextView tvAddressXz;
    @BindView(R.id.tv_address_md)
    TextView tvAddressMd;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_dz)
    LinearLayout llDz;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.ll_sfz)
    LinearLayout llSfz;
    @BindView(R.id.view_sfz)
    View viewSfz;
    private String goods_id = "";
    private String logo = "";
    private String name = "";
    //商品价格
    private String prime = "";
    //商品数量
    private int count;
    private int GOODSSTORES = 1234;
    //商品id
    private String storesId = "";
    //商品类型
    private String trade_type = "";
    //支付方式 2微信 1 支付宝 默认支付宝
    private String pay_type = "1";
    //商品总额
    private String all_price = "";
    //备注
    private String desc = "";
    private Disposable dis;
    private String is_shop_crad = "";
    private String is_overseas = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_order);
        ButterKnife.bind(this);
        init();
    }

    //初始化
    private void init() {
        String digists = "abcdefghigklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        etSfz.setKeyListener(DigitsKeyListener.getInstance(digists));//设置限制文本
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            count = bundle.getInt("count");
            goods_id = bundle.getString("goods_id");
            logo = bundle.getString("logo");
            name = bundle.getString("name");
            prime = bundle.getString("prime");
            trade_type = bundle.getString("trade_type");
            is_shop_crad = bundle.getString("is_shop_crad");
            is_overseas = bundle.getString("is_overseas");
            //判断
            if (TextUtils.equals(is_shop_crad, "1")) {
                //为1不显示门店、和身份证模块
                llInfo.setVisibility(View.GONE);
            } else if (TextUtils.equals(is_shop_crad, "0")) {
                //为0显示门店并判断是否显示身份证
                if (TextUtils.equals(is_overseas, "1")) {
                    //显示身份证
                    llSfz.setVisibility(View.VISIBLE);
                    viewSfz.setVisibility(View.VISIBLE);
                } else {
                    //不显示
                    llSfz.setVisibility(View.GONE);
                    viewSfz.setVisibility(View.GONE);
                }
            }

            ToolsImage.loader(mActivity, logo, lvCar);
            tvName.setText(name);
            tvDj.setText(SpannableStringUtils.getBuilder("¥ ").setProportion((float) 0.8).append(prime).create());
            tvCount.setText("x" + count);
            tvSum.setText(SpannableStringUtils.getBuilder("共" + count + "件商品  小计:")
                    .append("¥ ").setForegroundColor(ContextCompat.getColor(mActivity, R.color.font_zscx_jg
                    )).create());
            all_price = String.valueOf(Float.valueOf(prime) * count);
            tvTotalPrices.setText(all_price);
            tvTotalPricesB.setText(all_price);
            tvSumB.setText(SpannableStringUtils.getBuilder("合计:")
                    .append("¥ ").setForegroundColor(ContextCompat.getColor(mActivity, R.color.font_zscx_jg
                    )).create());
        }
        //微信和支付宝支付单选监听
        cbWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbZfb.setChecked(false);
                pay_type = "2";
            }
        });
        cbZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbWx.setChecked(false);
                pay_type = "1";
            }
        });
    }


    @Override
    protected void setTitleView() {
        titleTitle.setText("确认订单");
    }

    @OnClick({R.id.title_back, R.id.cl_xzdz, R.id.tv_tjdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.cl_xzdz:
                //选择门店
                Intent intent = new Intent(this, GoodsStoresActivity.class);
                intent.putExtra("goods_id", goods_id);
                intent.putExtra("storesId", storesId);
                startActivityForResult(intent, GOODSSTORES);
                break;
            case R.id.tv_tjdd:
                //提交订单
                if (TextUtils.equals(is_shop_crad,"0")) {
                    if (TextUtils.isEmpty(storesId)) {
                        ToastUtils.showToast(mActivity, "请选择门店");
                        return;
                    } else if (TextUtils.isEmpty(etRealName.getText().toString())) {
                        ToastUtils.showToast(mActivity, "请填写姓名");
                        return;
                    } else if (etPhone.getText().length() < 11) {
                        ToastUtils.showToast(mActivity, "请填写11位手机号码");
                        return;
                    } else if (TextUtils.equals(is_overseas,"1")) {
                        if (TextUtils.isEmpty(etSfz.getText().toString())){
                            ToastUtils.showToast(mActivity, "请填写身份证");
                        }
                        return;
                    }
                }
                order();
                break;
        }
    }

    //用户下单请求
    private void order() {
        desc = etMjly.getText().toString().trim();
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.COMMODITY.ORDER)
                .addParam("recv_name", etRealName.getText().toString().replaceAll(" ", ""))
                .addParam("recv_mobile", etPhone.getText().toString())
                .addParam("idCard", etSfz.getText().toString())
                .addParam("pay_type", pay_type)
                .addParam("all_price", all_price)
                .addParam("count", String.valueOf(count))
                .addParam("shop_id", storesId)
                .addParam("goods_id", goods_id)
                .addParam("desc", desc)
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        if (result.getStatus() == 10000) {
                            try {
                                BeanOrder beanOrder = new Gson().fromJson(result.getResult(), BeanOrder.class);
                                alipay(beanOrder);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //吊起支付宝支付
    private void alipay(final BeanOrder beanOrder) {
        dis = Observable.create(new ObservableOnSubscribe<Map<String, String>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, String>> emitter) throws Exception {
                PayTask alipay = new PayTask(mActivity);
                emitter.onNext(alipay.payV2(beanOrder.getData().getUrl(), true));
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, String>>() {
                    @Override
                    public void accept(Map<String, String> map) throws Exception {
                        PayResult payResult = new PayResult(map);
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        switch (resultStatus) {
                            case "9000":
                                //支付成功
                                Bundle bundle = new Bundle();
                                bundle.putString("order_sn", beanOrder.getData().getSn());
                                bundle.putString("trade_type", trade_type);
                                PaymentSuccessActivity.jumpActivity(mActivity, PaymentSuccessActivity.class, bundle);
                                finish();
                                break;
                            case "4000":
                                // 4000为支付失败，包括用户主动取消支付，或者系统返回的错误
                                ToastUtils.showToast(mActivity, "支付失败");
                                break;
                            case "6001":
                                // 6001为取消支付，或者系统返回的错误
                                ToastUtils.showToast(mActivity, "取消支付");
                                //跳订单详情页
                                MyActivityManager.getInstance().finishAboveActivity(MainActivity.class);
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("order_sn",beanOrder.getData().getSn());
                                OrderDetailsActivity.jumpActivity(mActivity,OrderDetailsActivity.class,bundle1);
                                break;
                            case "8000":
                                // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                                ToastUtils.showToast(mActivity, "支付结果确认中");
                                break;
                            default:
                                // 其他为系统返回的错误
                                ToastUtils.showToast(mActivity, "支付错误");
                                break;
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dis != null && !dis.isDisposed()) {
            dis.dispose();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOODSSTORES && resultCode == 10000) {
            if (data != null) {
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                storesId = data.getStringExtra("storesId");
                if (TextUtils.isEmpty(storesId)) {
                    llDz.setVisibility(View.GONE);
                    tvAddressXz.setVisibility(View.VISIBLE);
                } else {
                    llDz.setVisibility(View.VISIBLE);
                    tvAddressXz.setVisibility(View.GONE);
                    tvAddressMd.setText(name);
                    tvAddress.setText(address);
                }
            }
        }
    }
}
