package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.home.PaymentSuccessActivity;
import com.huiminsheng.app.adapter.OrderDetailsAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanOrder;
import com.huiminsheng.app.bean.BeanOrderDetails;
import com.huiminsheng.app.bean.EvenBean.EvenBean_myOrder;
import com.huiminsheng.app.bean.PayResult;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.FormatUtils;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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

//订单详情
public class OrderDetailsActivity extends BaseActivity {
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
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_address_md)
    TextView tvAddressMd;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_dz)
    LinearLayout llDz;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.cl_xzdz)
    RelativeLayout clXzdz;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_sfz)
    TextView tvSfz;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.rv_order_details)
    RecyclerView rvOrderDetails;
    @BindView(R.id.tv_psfs)
    TextView tvPsfs;
    @BindView(R.id.tv_total_prices)
    TextView tvTotalPrices;
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
    @BindView(R.id.tv_total_prices_b)
    TextView tvTotalPricesB;
    @BindView(R.id.tv_qxdd)
    TextView tvQxdd;
    @BindView(R.id.tv_tjdd)
    TextView tvTjdd;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.lv_status)
    ImageView lvStatus;
    @BindView(R.id.view_md)
    View viewMd;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.view_info)
    View viewInfo;
    private String order_sn;
    private OrderDetailsAdapter myorderAdapter;
    private Disposable dis;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    //适配器
    private void initAdapter() {
        //商品列表
        rvOrderDetails.setLayoutManager(new LinearLayoutManager(mActivity) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        myorderAdapter = new OrderDetailsAdapter(R.layout.adapter_myorder_item_item, new ArrayList<BeanOrderDetails.DataBean.ListBean>());
        rvOrderDetails.setAdapter(myorderAdapter);
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
        status = data.getStatus();
        switch (status) {
            case "-1":
                //已取消 删除订单
                tvStatus.setText("订单已取消");
                tvTjdd.setVisibility(View.GONE);
                tvQxdd.setVisibility(View.VISIBLE);
                tvQxdd.setText("删除订单");
                break;
            case "0":
                //待付款  取消订单 立即支付
                tvStatus.setText("等待买家付款");
                tvQxdd.setVisibility(View.VISIBLE);
                tvQxdd.setText("取消订单");
                lvStatus.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.order_payment));
                break;
            case "1":
                //待发货
                tvTjdd.setVisibility(View.GONE);
                lvStatus.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.order_delivery));
                break;
            case "2":
                //待收货
                tvTjdd.setVisibility(View.GONE);
                lvStatus.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.order_receive));
                break;
            case "3":
                //交易完成
                tvStatus.setText("交易完成");
                tvTjdd.setVisibility(View.GONE);
                tvQxdd.setVisibility(View.VISIBLE);
                tvQxdd.setText("删除订单");
                lvStatus.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.order_finish));
                break;
            default:
                break;
        }
        //判断是否显示门店之类的
        String is_shop_crad = data.getList().get(0).getIs_shop_crad();
        String is_overseas = data.getList().get(0).getIs_overseas();
        if (TextUtils.equals(is_shop_crad, "1")) {
            //为1不显示门店、和身份证模块
            llInfo.setVisibility(View.GONE);
            viewInfo.setVisibility(View.GONE);
            viewMd.setVisibility(View.GONE);
            clXzdz.setVisibility(View.GONE);
        } else if (TextUtils.equals(is_shop_crad, "0")) {
            //为0显示门店并判断是否显示身份证
            llInfo.setVisibility(View.VISIBLE);
            clXzdz.setVisibility(View.VISIBLE);
            viewInfo.setVisibility(View.VISIBLE);
            viewMd.setVisibility(View.VISIBLE);
            if (TextUtils.equals(is_overseas, "1")) {
                //显示身份证
                if (TextUtils.isEmpty(data.getIdCard())) {
                    tvSfz.setVisibility(View.GONE);
                } else {
                    tvSfz.setText(data.getIdCard());
                }
            } else {
                //不显示
                tvSfz.setVisibility(View.GONE);
            }
        }
        tvAddressMd.setText(data.getShop_name());
        tvAddress.setText(data.getShop_address());
        tvUserName.setText(data.getRecv_name());
        tvPhone.setText(data.getRecv_mobile());

        if (!TextUtils.isEmpty(data.getBeizhu())) {
            tvMsg.setText(data.getBeizhu());
        }
        myorderAdapter.setNewData(data.getList());
        tvTotalPrices.setText(SpannableStringUtils.getBuilder("¥ ").setProportion((float) 0.8).append(data.getAll_price()).create());
        tvTotalPricesB.setText(SpannableStringUtils.getBuilder("合计:").setProportion((float) 0.8).setForegroundColor(ContextCompat.getColor(mActivity, R.color.font_h))
                .append("¥ ").setProportion((float) 0.8).append(data.getAll_price()).create());

        //判断是否是充值卡 空不是
        if (TextUtils.isEmpty(data.getCardAccount())) {
            //正常订单
            llKh.setVisibility(View.GONE);
            llNormal.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(data.getAddtime())) {
                tvTime.setText(FormatUtils.getTime2SH(data.getAddtime()));
            }
            tvOrderNumber.setText(data.getOrder_sn());
        } else {
            //充值卡
            llKh.setVisibility(View.VISIBLE);
            llNormal.setVisibility(View.GONE);
            tvCardNumber.setText(data.getCardAccount());
            tvCardPsw.setText(data.getCardPassword());
        }


    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        order_sn = bundle.getString("order_sn", "");
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("订单详情");
    }

    @OnClick({R.id.title_back, R.id.tv_qxdd, R.id.tv_tjdd, R.id.tv_copy_card, R.id.tv_copy_psw, R.id.tv_copy_order_number,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_copy_card:
                //复制卡号
                FormatUtils.copy(mActivity, tvCardNumber.getText().toString(), "已复制");
                break;
            case R.id.tv_copy_psw:
                //复制密码
                FormatUtils.copy(mActivity, tvCardPsw.getText().toString(), "已复制");
                break;
            case R.id.tv_copy_order_number:
                //复制订单号
                FormatUtils.copy(mActivity, tvOrderNumber.getText().toString(), "已复制");
                break;
            case R.id.tv_qxdd:
                //取消订单
                switch (status) {
                    case "0":
                        //待付款  取消订单
                        //取消订单
                        PopupWindowGather.getAffirmPop(mActivity, getString(R.string.dialog_msg_cancle_order), new AffirmPopListener() {
                            @Override
                            public void onAffirmPopListener() {
                                cancelOrder(order_sn);
                            }
                        });
                        break;
                    case "-1":
                    case "3":
                        //删除订单
                        PopupWindowGather.getAffirmPop(mActivity, getString(R.string.dialog_msg_delete_order), new AffirmPopListener() {
                            @Override
                            public void onAffirmPopListener() {
                                removeOrder(order_sn);
                            }
                        });
                        break;
                }
                break;
            case R.id.tv_tjdd:
                //提交订单
                calture(order_sn);
                break;
        }
    }

    //取消订单
    private void cancelOrder(String order_sn) {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.COMMODITY.CANCELORDERS)
                .addParam("order_sn", order_sn)
                .showDialog()
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity, "已为您取消订单");
                        // 通知刷新
                        EventBus.getDefault().post(new EvenBean_myOrder(ApiUrls.KEY.REFRESH));
                        finish();
                    }
                });
    }

    //删除订单
    private void removeOrder(String order_sn) {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.COMMODITY.REMOVEORDERS)
                .addParam("order_sn", order_sn)
                .showDialog()
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity, "订单删除成功");
                        // 通知刷新
                        EventBus.getDefault().post(new EvenBean_myOrder(ApiUrls.KEY.REFRESH));
                        finish();
                    }
                });
    }

    //立即支付
    private void calture(String order_sn) {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.COMMODITY.CALTURE)
                .addParam("order_sn", order_sn)
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
                                // 通知刷新
                                EventBus.getDefault().post(new EvenBean_myOrder(ApiUrls.KEY.REFRESH));
                                //跳成功页面
                                Bundle bundle = new Bundle();
                                bundle.putString("order_sn", beanOrder.getData().getSn());
                                bundle.putString("trade_type", "myOrder");
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
}
