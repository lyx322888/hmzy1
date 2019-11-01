package com.huiminsheng.app.activity.home;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.share.OrderShareActivity;
import com.huiminsheng.app.adapter.BannerViewHolder;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanBypromoting;
import com.huiminsheng.app.bean.BeanDetails;
import com.huiminsheng.app.bean.Beancode;
import com.huiminsheng.app.bean.EvenBean.EvenBean_collect;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.MyImageGetter;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.CustomPopWindow;
import com.huiminsheng.app.views.QuantityControlView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//商品详情页
public class ProductDetailsActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_Right)
    ImageView titleRight;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.banner_details)
    MZBannerView bannerDetails;
    @BindView(R.id.tv_details_jg)
    TextView tvDetailsJg;
    @BindView(R.id.tv_details_zdj)
    TextView tvDetailsZdj;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.tv_details_title)
    TextView tvDetailsTitle;
    @BindView(R.id.tv_details_describe)
    TextView tvDetailsDescribe;
    @BindView(R.id.tv_details_djzx_jg)
    TextView tvDetailsDzjg;
    @BindView(R.id.tv_details_dz_d)
    TextView tvDetailsDzD;
    @BindView(R.id.tv_cwdz)
    TextView tvCwdz;
    @BindView(R.id.lv_cwdz)
    ImageView lvCwdz;
    @BindView(R.id.lv_cr)
    ImageView lvCr;
    @BindView(R.id.tv_cr_content)
    TextView tvCrContent;
    @BindView(R.id.rl_SelectNb)
    RelativeLayout rlSelectNb;
    @BindView(R.id.lv_scrolltop)
    ImageView lvScrolltop;
    @BindView(R.id.tv_zxkf)
    TextView tvZxkf;
    @BindView(R.id.tv_ljgm)
    TextView tvLjgm;
    @BindView(R.id.swrl_home)
    LinearLayout swrlHome;
    @BindView(R.id.tv_dj)
    TextView tvDj;
    @BindView(R.id.tv_html)
    TextView tvHtml;
    @BindView(R.id.slv_details)
    ScrollView slvDetails;
    @BindView(R.id.tv_nb)
    TextView tvNb;
    @BindView(R.id.title_exit)
    ImageView titleExit;
    @BindView(R.id.ll_dz)
    LinearLayout llDz;
    private String goods_id = "";
    //成为店长的跳转id
    private String cwdz_goods_id = "";
    //二维码
    private String ewmUrl = "";
    private BeanDetails.DataBean dataBean;
    //选择的商品数量
    private int selectedNumber = 1;
    //商品类型
    private String trade_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        init();
        showLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    //初始化
    private void init() {
        Bundle bundle = getIntent().getExtras();
        //商品id
        if (bundle != null) {
            goods_id = bundle.getString("goods_id");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            lvScrolltop.setVisibility(View.GONE);
            slvDetails.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (oldScrollY > 50) {
                        lvScrolltop.setVisibility(View.VISIBLE);
                    } else {
                        lvScrolltop.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("商品详情");
        titleRight.setVisibility(View.VISIBLE);
    }

    //获取数据
    private void initData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.COMMODITY.DETAILS)
                .get()
                .addParam("goods_id", goods_id)
                .request(BeanDetails.class, new BaseListener<BeanDetails>() {
                    @Override
                    public void onSucceed(int what, Result<BeanDetails> result) {
                        showSuccess();
                        setDataView(result.getResult().getData());
                    }
                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        showTimeout(new LoadListener() {
                            @Override
                            public void loadlistener() {
                                showLoading();
                                initData();
                            }
                        });
                    }
                });
    }

    //填充数据
    private void setDataView(BeanDetails.DataBean data) {
        tvNb.setText("您选择了"+selectedNumber+"件商品");
        dataBean = data;
        //6 成为店长页面  5电话卡  不显示成为店长模块
        trade_type = data.getTrade_type();
        if (!TextUtils.equals(trade_type,"5")&&!TextUtils.equals(trade_type,"6")){
            llDz.setVisibility(View.VISIBLE);
            tvTag.setVisibility(View.VISIBLE);
            //店家价格
            tvDetailsDzjg.setText(data.getShopowner_ary().getShopowner_price());
            //店家描述
            tvDetailsDzD.setText(data.getShopowner_ary().getShopowner_desc());
            //店家等级图片
            ToolsImage.loader(mActivity, data.getShopowner_ary().getShopowner_url(), lvCwdz);
            //成为店长的id
            cwdz_goods_id = data.getShopowner_ary().getGoods_id();
            //是否是店长 0不是店长 1是
            if (TextUtils.equals(data.getIs_Shopowner(), "0")) {
                tvCwdz.setVisibility(View.VISIBLE);
            }else {
                tvCwdz.setVisibility(View.GONE);
            }
        } else {
            llDz.setVisibility(View.GONE);
            tvTag.setVisibility(View.GONE);
        }


        //轮播图
        bannerDetails.setPages(data.getImages(), new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        bannerDetails.setDelayedTime(3000);
        bannerDetails.setIndicatorVisible(true);
        bannerDetails.start();//开始轮播
        //价格
        tvDetailsJg.setText(data.getPrice());
        tvDj.setText("定金¥" + data.getPrime());
        //指导价
        tvDetailsZdj.setText(SpannableStringUtils.getBuilder("厂商价:").append(data.getOri_price()).create().toString());
        tvDetailsZdj.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        //标签
        tvTag.setText(data.getTag_name());
        //标题
        tvDetailsTitle.setText(data.getName());
        //描述
        tvDetailsDescribe.setText(data.getDesc());

        //h5标签
        tvHtml.setText(Html.fromHtml(data.getDetails(), MyImageGetter.imageGetter(tvHtml, mActivity), null));
        //是否收藏
        if (TextUtils.equals(data.getCollection(), "0")) {
            //1已收藏 0未收藏
            titleRight.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.favorite));
        } else {
            titleRight.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.favorite_selected));
        }
        //48小时内发货
        tvCrContent.setText(data.getEnsure_info());

    }

    @OnClick({R.id.title_back, R.id.title_Right, R.id.ll_share, R.id.tv_cwdz, R.id.rl_SelectNb, R.id.tv_zxkf, R.id.tv_ljgm, R.id.lv_scrolltop})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_Right:
                //收藏
                collection();
                break;
            case R.id.ll_share:
                //分享
                bundle.putString("goods_id", goods_id);
                bundle.putString("logo", dataBean.getLogo());
                bundle.putString("name", dataBean.getName());
                bundle.putString("tag_name", dataBean.getTag_name());
                bundle.putString("ori_price", dataBean.getOri_price());
                bundle.putString("price", dataBean.getPrice());
                bundle.putString("desc", dataBean.getDesc());
                bundle.putString("share_url", dataBean.getShare_url());
                OrderShareActivity.jumpActivity(mActivity,OrderShareActivity.class,bundle);
                break;
            case R.id.tv_cwdz:
                //成为店长
                if (!TextUtils.isEmpty(cwdz_goods_id)) {
                    bundle.putString("goods_id", cwdz_goods_id);
                    ProductDetailsActivity.jumpActivity(mActivity, ProductDetailsActivity.class, bundle);
                }
                break;
            case R.id.rl_SelectNb:
                //选择数量
                if (dataBean!=null){
                    showCountPop();
                }
                break;
            case R.id.tv_zxkf:
                //客服
                if (!TextUtils.isEmpty(ewmUrl)) {
                    showQRcode();
                } else {
                    getEwmRul();
                }
                break;
            case R.id.tv_ljgm:
                //立即购买
                if (dataBean!=null){
                    bundle.putInt("count", selectedNumber);
                    bundle.putString("goods_id", goods_id);
                    bundle.putString("logo", dataBean.getLogo());
                    bundle.putString("name", dataBean.getName());
                    bundle.putString("prime", dataBean.getPrime());
                    bundle.putString("trade_type", trade_type);
                    bundle.putString("is_shop_crad", dataBean.getIs_shop_crad());
                    bundle.putString("is_overseas", dataBean.getIs_overseas());
                    VerifyOrderActivity.jumpActivity(mActivity, VerifyOrderActivity.class,bundle);
                }
                break;
            case R.id.lv_scrolltop:
                //滑动到最顶端
                slvDetails.fullScroll(View.FOCUS_UP);
                break;
        }
    }

    //收藏
    public void collection() {
        NoGo.create(this)
                .get()
                .setUrl(ApiUrls.COMMODITY.COLLECTION)
                .addParam("goods_id", goods_id)
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        if (TextUtils.equals(result.getMsg(), "收藏成功")) {
                            titleRight.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.favorite_selected));
                        } else if (TextUtils.equals(result.getMsg(), "取消收藏")) {
                            titleRight.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.favorite));
                        }
                        //通知收藏列表刷新
                        EventBus.getDefault().post(new EvenBean_collect(ApiUrls.KEY.REFRESH));
                    }
                });
    }

    //显示客服二维码
    public void showQRcode() {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindowlayout_qrcode, null, false);
        final CustomPopWindow mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create()
                .showAtLocation(inflate, Gravity.CENTER, 0, 0);
        ImageView imageView = inflate.findViewById(R.id.lv_code);
        Glide.with(mActivity).load(ewmUrl).asBitmap().into(imageView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ToolsImage.savaUrl(mActivity, ewmUrl);
                return false;
            }
        });
        ImageView lvqx = inflate.findViewById(R.id.lv_qx);
        lvqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });

    }
    //选择数量
    private void showCountPop(){
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindowlayout_details_amount, null, false);
        final CustomPopWindow mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create()
                .showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        ImageView imageView = inflate.findViewById(R.id.lv_car);
        Glide.with(mActivity).load(dataBean.getLogo()).asBitmap().into(imageView);
        final QuantityControlView quantityControlView = inflate.findViewById(R.id.qcv);
        if (dataBean.getLimit() != null) {
            quantityControlView.setMaxcount(Integer.parseInt(dataBean.getLimit()));
        };
        TextView next = inflate.findViewById(R.id.tvAffirm);
        TextView tv_name = inflate.findViewById(R.id.tv_name);
        TextView tv_dj = inflate.findViewById(R.id.tv_dj);
        tv_name.setText(dataBean.getName());
        tv_dj.setText("¥" + dataBean.getPrime());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNumber = quantityControlView.getcount();
                tvNb.setText("您选择了"+selectedNumber+"件商品");
                mCustomPopWindow.dissmiss();
            }
        });
    }
    //获取客服二维码
    private void getEwmRul() {
        NoGo.create(mActivity)
                .showDialog()
                .setUrl(ApiUrls.COMMODITY.CONTACT)
                .request(Beancode.class, new BaseListener<Beancode>() {
                    @Override
                    public void onSucceed(int what, Result<Beancode> result) {
                        ewmUrl = result.getResult().data.service_img;
                        showQRcode();
                    }
                });
    }
}
