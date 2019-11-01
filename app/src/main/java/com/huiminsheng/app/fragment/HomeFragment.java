package com.huiminsheng.app.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.home.ProductDetailsActivity;
import com.huiminsheng.app.activity.home.ProductListActivity;
import com.huiminsheng.app.activity.web.BaseWebActivity;
import com.huiminsheng.app.adapter.BannerViewHolder;
import com.huiminsheng.app.adapter.BannerViewHolderFlzq;
import com.huiminsheng.app.adapter.HomeFlzqAdapter;
import com.huiminsheng.app.adapter.HomeKjlAdapter;
import com.huiminsheng.app.adapter.HomeZscxAdapter;
import com.huiminsheng.app.base.BaseFragment;
import com.huiminsheng.app.bean.BeanHome;
import com.huiminsheng.app.bean.BeanHomeFlzq;
import com.huiminsheng.app.bean.BeanHomeZscx;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.GridSpacingItemDecoration;
import com.huiminsheng.app.utils.ToolsSize;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.swrl_home)
    SwipeRefreshLayout swrlHome;
    @BindView(R.id.lv_scrolltop)
    ImageView lvScrolltop;
    Unbinder unbinder;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;

    HomeZscxAdapter homeZscxAdapter;
    //福利专区适配器
    private HomeFlzqAdapter homeFlzqAdapter;
    //快捷栏
    private HomeKjlAdapter homeKjlAdapter;
    private RecyclerView rvFlzq;
    private RecyclerView rvKjl;
    private MZBannerView bannerFlzq;
    private MZBannerView banner;
    private TextView tevZscxTitle;
    private TextView tevZscxmore;
    private TextView tevFlzqmore;
    private int scrollDy = 0;

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_home_fragment_g;
    }

    //初始化控件
    protected void init() {
        swrlHome.setColorSchemeResources(R.color.theme);
        swrlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swrlHome.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        net_action = Loadtype.ACTION_PULL_DOWN;
                        pagination = 1;
                        initData();
                        swrlHome.setRefreshing(false);
                    }
                }, 500);
            }
        });
        initZscxAdapter();
        showLoading();
        initData();
    }

    //初始化在售车型适配器
    private void initZscxAdapter() {
        //rv头部
        final View rvHead = getLayoutInflater().inflate(R.layout.fragment_rv_head, null);
        initHeadView(rvHead);
        //在售车型适配器
        homeZscxAdapter = new HomeZscxAdapter(R.layout.home_zscx_item, new ArrayList<BeanHomeZscx.DataBean.ListBean>());
        homeZscxAdapter.addHeaderView(rvHead);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvHome.addItemDecoration(new DividerItemDecoration(1, 18, 18, getContext()));
        rvHome.setAdapter(homeZscxAdapter);
        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollDy += dy;
                if (scrollDy>0){
                    llTitle.setVisibility(View.VISIBLE);
                }else {
                    llTitle.setVisibility(View.GONE);
                }
                llTitle.setAlpha((float) scrollDy/banner.getBottom());
                if (scrollDy > rvHead.getBottom()) {
                    lvScrolltop.setVisibility(View.VISIBLE);
                } else {
                    lvScrolltop.setVisibility(View.GONE);
                }
            }
        });
        //加载更多
        homeZscxAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getZxcxData();
            }
        }, rvHome);
        homeZscxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                //商品id
                bundle.putString("goods_id", homeZscxAdapter.getData().get(position).getGoods_id());
                ProductDetailsActivity.jumpActivity(getActivity(), ProductDetailsActivity.class, bundle);
            }
        });
    }

    //快捷栏路口适配器
    private void initKjlAdapter() {
        homeKjlAdapter = new HomeKjlAdapter(R.layout.adapter_item_kjl, new ArrayList<BeanHome.DataBean.ListBean>());
        rvKjl.setLayoutManager(new GridLayoutManager(getContext(), 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvKjl.setAdapter(homeKjlAdapter);
        homeKjlAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //先判断url是否为空 不为空跳h5 为空判断type 1跳详情 2跳列表
                if (!TextUtils.isEmpty(homeKjlAdapter.getData().get(position).getUrl())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", homeKjlAdapter.getData().get(position).getUrl());
                    BaseWebActivity.jumpActivity(getActivity(), BaseWebActivity.class, bundle);
                } else {
                    if (TextUtils.equals(homeKjlAdapter.getData().get(position).getType(), "2")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", homeKjlAdapter.getData().get(position).getId());
                        ProductListActivity.jumpActivity(getActivity(), ProductListActivity.class, bundle);
                    } else {
                        Bundle bundle = new Bundle();
                        //商品id
                        bundle.putString("goods_id", homeKjlAdapter.getData().get(position).getGoods_id());
                        ProductDetailsActivity.jumpActivity(getActivity(), ProductDetailsActivity.class, bundle);
                    }
                }
            }
        });
    }

    //初始化头部视图控件
    @SuppressLint("CutPasteId")
    private void initHeadView(View view) {
        rvFlzq = view.findViewById(R.id.rv_flzq);
        bannerFlzq = view.findViewById(R.id.banner_flzq);
        banner = view.findViewById(R.id.banner);
        tevZscxTitle = view.findViewById(R.id.tv_honme_zscx_title);
        tevZscxmore = view.findViewById(R.id.tv_home_zscx_more);
        tevFlzqmore = view.findViewById(R.id.tv_flzq_title);
        rvKjl = view.findViewById(R.id.rv_kjl);
        tevZscxmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", "");
                ProductListActivity.jumpActivity(getActivity(), ProductListActivity.class, bundle);
            }
        });
        initKjlAdapter();
    }

    /*数据加载*/
    private void initData() {
        //轮播图
        NoGo.create(getContext()).get()
                .setSign(this)
                .setUrl(ApiUrls.HOME.HONMEBANNER)
                .request(BeanHome.class, new BaseListener<BeanHome>() {
                            @Override
                            public void onSucceed(int what, Result<BeanHome> result) {
                                setBannerData(result.getResult().getData());
                                showSuccess();
                            }

                            @Override
                            public void onFailed(int what) {
                                super.onFailed(what);
                                showTimeout(new LoadListener() {
                                    @Override
                                    public void loadlistener() {
                                        net_action = Loadtype.ACTION_PULL_DOWN;
                                        pagination = 1;
                                        initData();
                                        showLoading();
                                    }
                                });
                            }
                        }
                );
        //福利专区
        NoGo.create(getContext()).get()
                .setUrl(ApiUrls.HOME.HAPPTEMPLATEOME)
                .setSign(this)
                .request(BeanHomeFlzq.class, new BaseListener<BeanHomeFlzq>() {
                    @Override
                    public void onSucceed(int what, Result<BeanHomeFlzq> result) {
                        tevFlzqmore.setText(result.getResult().getData().getBanner_Middle().getMiddle_top());
                        setFlzqData(result.getResult().getData());
                    }
                });
        //在售车型
        net_action = Loadtype.ACTION_PULL_DOWN;
        getZxcxData();
    }

    //展示福利专区数据
    private void setFlzqData(final BeanHomeFlzq.DataBean dataBean) {
        //轮播图
        setBannerFlzq(dataBean);
        //列表
        if (homeFlzqAdapter == null) {
            homeFlzqAdapter = new HomeFlzqAdapter(R.layout.home_flzq_rv_item, dataBean.getList());
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            rvFlzq.addItemDecoration(new GridSpacingItemDecoration(2, ToolsSize.getSize(12),true));
            rvFlzq.setLayoutManager(layoutManager);
            rvFlzq.setNestedScrollingEnabled(false);
            rvFlzq.setAdapter(homeFlzqAdapter);
        } else {
            homeFlzqAdapter.setNewData(dataBean.getList());
        }
        homeFlzqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", dataBean.getList().get(position).getId());
                ProductListActivity.jumpActivity(getActivity(), ProductListActivity.class, bundle);
            }
        });

    }

    //设置视图
    private void setBannerData(final BeanHome.DataBean dataBean) {
        //设置轮播图
        setBanner(dataBean.getBanner());
        //快捷栏
        homeKjlAdapter.setNewData(dataBean.getList());
    }

    //设置开头轮播图
    private void setBanner(final List<BeanHome.DataBean.BannerBean> bannerBeans) {
        ArrayList<String> arrayListd = new ArrayList<String>();
        for (int i = 0; i < bannerBeans.size(); i++) {
            arrayListd.add(bannerBeans.get(i).getBanner_url());
        }
        banner.setPages(arrayListd, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder(new BannerViewHolder.Listener() {
                    @Override
                    public void OnclickListener(int position) {
                        if (!TextUtils.isEmpty(bannerBeans.get(position).getLink_url())) {
                            Bundle bundle = new Bundle();
                            bundle.putString("url", bannerBeans.get(position).getLink_url());
                            BaseWebActivity.jumpActivity(getActivity(), BaseWebActivity.class, bundle);
                        }
                    }
                });
            }
        });
        banner.setDelayedTime(3000);
        banner.setIndicatorVisible(true);
        banner.start();//开始轮播
    }

    //福利专区轮播图
    private void setBannerFlzq(final BeanHomeFlzq.DataBean dataBean) {
        ArrayList<String> arrayListd = new ArrayList<String>();
        for (int i = 0; i < dataBean.getBanner_Middle().getBanner_ary().size(); i++) {
            arrayListd.add(dataBean.getBanner_Middle().getBanner_ary().get(i).getImages_url());
        }
        bannerFlzq.setPages(arrayListd, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolderFlzq(new BannerViewHolder.Listener() {
                    @Override
                    public void OnclickListener(int position) {
                        String url = dataBean.getBanner_Middle().getBanner_ary().get(position).getLink_url();
                        if (!TextUtils.isEmpty(url)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("url", url);
                            BaseWebActivity.jumpActivity(getActivity(), BaseWebActivity.class, bundle);
                        } else {
                            Bundle bundle = new Bundle();
                            //商品id
                            bundle.putString("goods_id", dataBean.getBanner_Middle().getBanner_ary().get(position).getGoods_id());
                            ProductDetailsActivity.jumpActivity(getActivity(), ProductDetailsActivity.class, bundle);
                        }
                    }
                });
            }
        });

        bannerFlzq.setIndicatorVisible(false);
    }

    //获取在售车型数据
    public void getZxcxData() {
        NoGo.create(getContext())
                .get().setUrl(ApiUrls.HOME.SELLCAR)
                .setSign(this)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .request(BeanHomeZscx.class, new BaseListener<BeanHomeZscx>() {
                    @Override
                    public void onSucceed(int what, Result<BeanHomeZscx> result) {
                        tevZscxTitle.setText(result.getResult().getData().getIndex_goodslist().getName_top());
                        tevZscxmore.setText(result.getResult().getData().getIndex_goodslist().getName_left());
                        AdapterUtils.setData(homeZscxAdapter, result.getResult().getData().getList(), net_action);
                    }

                    @Override
                    public void onFailed(int what) {
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            homeZscxAdapter.loadMoreFail();
                        }
                    }
                });
    }

    @OnClick(R.id.lv_scrolltop)
    public void onViewClicked() {
        scrollDy = 0;
        rvHome.scrollToPosition(0);
    }
}
