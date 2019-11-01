package com.huiminsheng.app.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.home.PaymentSuccessActivity;
import com.huiminsheng.app.activity.home.ProductDetailsActivity;
import com.huiminsheng.app.activity.home.ProductListActivity;
import com.huiminsheng.app.activity.my.CollectActivity;
import com.huiminsheng.app.activity.my.ContactUsActivity;
import com.huiminsheng.app.activity.my.MessageActivity;
import com.huiminsheng.app.activity.my.MyEarningsActivity;
import com.huiminsheng.app.activity.my.MyTeamActivity;
import com.huiminsheng.app.activity.my.OrderActivity;
import com.huiminsheng.app.activity.my.SettingActivity;
import com.huiminsheng.app.activity.my.WithdrawDepositActivity;
import com.huiminsheng.app.activity.web.BaseWebActivity;
import com.huiminsheng.app.adapter.BannerViewHolder;
import com.huiminsheng.app.adapter.MyKjlAdapter;
import com.huiminsheng.app.base.BaseFragment;
import com.huiminsheng.app.bean.BeanUserinfo;
import com.huiminsheng.app.bean.BeanUserinfo_head;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView_yuan;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 个人中心
 */
public class MyFragment extends BaseFragment {


    Unbinder unbinder1;
    @BindView(R.id.iv_my_msg)
    ImageView ivMyMsg;
    @BindView(R.id.iv_my_set)
    ImageView ivMySet;
    @BindView(R.id.iv_my_head)
    MyImagView_yuan ivMyHead;
    @BindView(R.id.lv_my_dz)
    ImageView lvMyDg;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.iv_my_medium)
    ImageView ivMyMedium;
    @BindView(R.id.tv_my_zz)
    TextView tvMyZz;
    @BindView(R.id.tv_my_dfk_nb)
    TextView tvMyDfkNb;
    @BindView(R.id.tv_my_dfk)
    TextView tvMyDfk;
    @BindView(R.id.tv_my_dfh_nb)
    TextView tvMyDfhNb;
    @BindView(R.id.tv_my_dfh)
    TextView tvMyDfh;
    @BindView(R.id.tv_my_dsh_nb)
    TextView tvMyDshNb;
    @BindView(R.id.tv_my_dsh)
    TextView tvMyDsh;
    @BindView(R.id.tv_my_ywc_nb)
    TextView tvMyYwcNb;
    @BindView(R.id.tv_my_ywc)
    TextView tvMyYwc;
    @BindView(R.id.tv_my_sh_nb)
    TextView tvMyShNb;
    @BindView(R.id.tv_my_sh)
    TextView tvMySh;
    @BindView(R.id.ll_dd)
    LinearLayout llDd;
    @BindView(R.id.rv_kjl)
    RecyclerView rvKjl;
    @BindView(R.id.banner_my_gg)
    MZBannerView bannerMyGg;
    @BindView(R.id.ll_my_gg_bc)
    ConstraintLayout llMyGgBc;
    @BindView(R.id.iv_wdsc)
    ImageView ivWdsc;
    @BindView(R.id.rl_my_wdsc)
    RelativeLayout rlMyWdsc;
    @BindView(R.id.iv_cjwt)
    ImageView ivCjwt;
    @BindView(R.id.rl_my_cjwt)
    RelativeLayout rlMyCjwt;
    @BindView(R.id.iv_lxwm)
    ImageView ivLxwm;
    @BindView(R.id.rl_my_lxwm)
    RelativeLayout rlMyLxwm;
    @BindView(R.id.swrl_my)
    SwipeRefreshLayout swrlMy;
    private Context context;
    private MyKjlAdapter myKjlAdapter;
    private String headpis;
    private String nickName;
    private String is_Shopowner;
    private String leveName;

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init() {
        context = getActivity();
        showLoading();
        initview();
        initAdatper();
    }

    //初始化快捷栏适配器
    private void initAdatper() {
        myKjlAdapter = new MyKjlAdapter(R.layout.adapter_item_my_kjl, new ArrayList<BeanUserinfo.DataBean.MylistBean>());
        rvKjl.setLayoutManager(new GridLayoutManager(getContext(), 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvKjl.setAdapter(myKjlAdapter);
        myKjlAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        //等级
                        Bundle bundle = new Bundle();
                        bundle.putString("url", myKjlAdapter.getData().get(position).getLink_url());
                        BaseWebActivity.jumpActivity(getActivity(), BaseWebActivity.class, bundle);
                        break;
                    case 1:
                        //我的团队
                        MyTeamActivity.jumpActivity(getActivity(),MyTeamActivity.class,null);
                        break;
                    case 2:
                        //我的收益  private String headpis;
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("headpis",headpis);
                        bundle1.putString("nickName",nickName);
                        bundle1.putString("is_Shopowner",is_Shopowner);
                        bundle1.putString("leveName",leveName);
                        MyEarningsActivity.jumpActivity(getActivity(),MyEarningsActivity.class,bundle1);
                        break;
                    case 3:
                        //提现
                        WithdrawDepositActivity.jumpActivity(getActivity(),WithdrawDepositActivity.class,null);
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    //初始化
    private void initview() {
        swrlMy.setColorSchemeResources(R.color.theme);
        swrlMy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swrlMy.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        swrlMy.setRefreshing(false);
                    }
                }, 500);
            }
        });
    }

    //获取数据
    private void getData() {
        //头像相关
        NoGo.create(getContext())
                .get()
                .setSign(this)
                .setUrl(ApiUrls.MY.INFO)
                .request(BeanUserinfo_head.class, new BaseListener<BeanUserinfo_head>() {
                    @Override
                    public void onSucceed(int what, Result<BeanUserinfo_head> result) {
                        setUserinof_head(result.getResult());
                        showSuccess();
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        showTimeout(new LoadListener() {
                            @Override
                            public void loadlistener() {
                                getData();
                                showLoading();
                            }
                        });
                    }
                });
        //其他
        NoGo.create(getContext())
                .setUrl(ApiUrls.MY.GETCOUNTS)
                .get()
                .setSign(this)
                .addParam(ApiUrls.KEY.MCHID, (String) SPUtils.get(context, ApiUrls.KEY.MCHID, ""))
                .request(BeanUserinfo.class, new BaseListener<BeanUserinfo>() {
                    @Override
                    public void onSucceed(int what, Result<BeanUserinfo> result) {
                        setUserinofView(result.getResult());
                    }
                });
    }

    //填充头像等级信息
    private void setUserinof_head(BeanUserinfo_head result) {
        //头像
        headpis = result.getData().getProfile().getHeadpic();
        if ( headpis != null) {
            ToolsImage.loaderTx(getContext(), result.getData().getProfile().getHeadpic(), ivMyHead);
        }
        //昵称
        nickName = result.getData().getProfile().getNick_name();
        tvMyName.setText(nickName);
        is_Shopowner = result.getData().getIs_Shopowner();
        //是不是店长  1是0不是
        if (TextUtils.equals(result.getData().getIs_Shopowner(), "1")) {
            lvMyDg.setVisibility(View.VISIBLE);
        } else {
            lvMyDg.setVisibility(View.GONE);
        }
        //等级
        leveName = result.getData().getProfile().getLevel().getName();
        tvMyZz.setText(leveName);
        Glide.with(getContext()).load(result.getData().getProfile().getLevel().getImage_url()).thumbnail(0.5f).into(ivMyMedium);
        if (getActivity() != null) {
            //保存信息
            SPUtils.put(getActivity(), ApiUrls.KEY.HEADPIC, result.getData().getProfile().getHeadpic());
            SPUtils.put(getActivity(), ApiUrls.KEY.NAME, result.getData().getProfile().getName());
            SPUtils.put(getActivity(), ApiUrls.KEY.NICK_NAME, result.getData().getProfile().getNick_name());
            SPUtils.put(getActivity(), ApiUrls.KEY.LEVELNAME, result.getData().getProfile().getLevel().getName());
        }
    }

    //填充数据菜单
    private void setUserinofView(final BeanUserinfo result) {
        //快捷栏
        myKjlAdapter.setNewData(result.getData().getMylist());
        //头像下面数据
        tvMyDfkNb.setText(result.getData().getList().getCount1());
        tvMyDfhNb.setText(result.getData().getList().getCount2());
        tvMyDshNb.setText(result.getData().getList().getCount3());
        tvMyYwcNb.setText(result.getData().getList().getCount4());
        tvMyShNb.setText(result.getData().getList().getCount5());

        //轮播图
        if (result.getData().getBanner() == null || result.getData().getBanner().getBanner_url() == null) {
            //判断有没有广告 没有就隐藏
            llMyGgBc.setVisibility(View.GONE);
        } else {
            llMyGgBc.setVisibility(View.VISIBLE);
            ArrayList<String> arrayListd = new ArrayList<String>();
            arrayListd.add(result.getData().getBanner().getBanner_url());
            bannerMyGg.setPages(arrayListd, new MZHolderCreator() {
                @Override
                public MZViewHolder createViewHolder() {
                    return new BannerViewHolder(new BannerViewHolder.Listener() {
                        @Override
                        public void OnclickListener(int position) {
                            if (!TextUtils.isEmpty(result.getData().getBanner().getLink_url())) {
                                Bundle bundle = new Bundle();
                                bundle.putString("url", result.getData().getBanner().getLink_url());
                                BaseWebActivity.jumpActivity(getActivity(), BaseWebActivity.class, bundle);
                            } else if (!TextUtils.isEmpty(result.getData().getBanner().getGoods_id())) {
                                Bundle bundle = new Bundle();
                                //商品id
                                bundle.putString("goods_id", result.getData().getBanner().getGoods_id());
                                ProductDetailsActivity.jumpActivity(getActivity(), ProductDetailsActivity.class, bundle);
                            }
                        }
                    });
                }
            });

            bannerMyGg.setIndicatorVisible(false);
        }

    }

    @OnClick({R.id.iv_my_head, R.id.iv_my_msg, R.id.iv_my_set, R.id.rl_my_wdsc, R.id.rl_my_cjwt, R.id.rl_my_lxwm,
            R.id.rl_order_dfk, R.id.rl_order_dfh, R.id.rl_order_dsh, R.id.rl_order_ywc, R.id.rl_order_sh})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_my_msg:
                MessageActivity.jumpActivity(getActivity(), MessageActivity.class, null);
                break;
            case R.id.iv_my_head:
            case R.id.iv_my_set:
                SettingActivity.jumpActivity(getActivity(), SettingActivity.class, null);
                break;
            case R.id.rl_order_dfk:
                //代付款
                bundle.putInt("count",1);
                OrderActivity.jumpActivity(getActivity(), OrderActivity.class, bundle);
                break;
            case R.id.rl_order_dfh:
                //代发货
                bundle.putInt("count",2);
                OrderActivity.jumpActivity(getActivity(), OrderActivity.class, bundle);
                break;
            case R.id.rl_order_dsh:
                //待收货
                bundle.putInt("count",3);
                OrderActivity.jumpActivity(getActivity(), OrderActivity.class, bundle);
                break;
            case R.id.rl_order_ywc:
                //已完成
                bundle.putInt("count",4);
                OrderActivity.jumpActivity(getActivity(), OrderActivity.class, bundle);
                break;
            case R.id.rl_order_sh:
                //售后
                break;
            case R.id.rl_my_wdsc:
                CollectActivity.jumpActivity(getActivity(), CollectActivity.class, null);
                break;
            case R.id.rl_my_cjwt:
                bundle.putString("url", "http://hms.zhuoranxiaoming.cn/wxother/guide.html");
                BaseWebActivity.jumpActivity(getActivity(), BaseWebActivity.class, bundle);
                break;
            case R.id.rl_my_lxwm:
                startActivity(new Intent(getActivity(), ContactUsActivity.class));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
