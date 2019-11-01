package com.huiminsheng.app.activity.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.RankingAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanPhb;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView_yuan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*排行榜*/
public class RankinglistActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.lv_phb)
    MyImagView_yuan lvPhb;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_zsy)
    TextView tvZsy;
    @BindView(R.id.tv_dqpm)
    TextView tvDqpm;
    @BindView(R.id.rl_phb)
    RecyclerView rvPhb;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private RankingAdapter rankingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankinglist);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        swipe.setColorSchemeResources(R.color.theme);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        net_action = Loadtype.ACTION_PULL_DOWN;
                        pagination = 1;
                        initData();
                    }
                }, 500);
            }
        });
        initAdapter();
        showLoading();
        initData();
    }

    private void initAdapter() {
        rankingAdapter = new RankingAdapter(R.layout.adapter_item_ranking, new ArrayList<BeanPhb.DataBean.ListBean>());
        rvPhb.setLayoutManager(new LinearLayoutManager(this));
        rvPhb.setAdapter(rankingAdapter);
        //加载更多
        rankingAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                initData();
            }
        }, rvPhb);
    }

    private void initData() {
        NoGo.create(this)
                .setUrl(ApiUrls.HOME.RANKINGLIST)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .request(BeanPhb.class, new BaseListener<BeanPhb>() {
                    @Override
                    public void onSucceed(int what, Result<BeanPhb> result) {
                        AdapterUtils.setData(rankingAdapter, result.getResult().getData().getList(), net_action);
                        showSuccess();
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action==Loadtype.ACTION_LOAD_MORE){
                            pagination-=1;
                            rankingAdapter.loadMoreFail();
                        }else {
                            showTimeout(new LoadListener() {
                                @Override
                                public void loadlistener() {
                                    net_action = Loadtype.ACTION_PULL_DOWN;
                                    pagination = 1;
                                    showLoading();
                                    initData();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFinish(int what) {
                        super.onFinish(what);
                        swipe.setRefreshing(false);
                    }
                });
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
