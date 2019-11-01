package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.CashlistAdapter;
import com.huiminsheng.app.adapter.MessageDetailsAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanCashlist;
import com.huiminsheng.app.bean.BeanMsgDetails;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.EmptyViewUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//提现记录
public class WithdrawalRecordActivity extends BaseActivity {

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
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private CashlistAdapter cashlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_record);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("提现记录");
    }

    private void init() {
        swipe.setColorSchemeResources(R.color.theme);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        net_action = Loadtype.ACTION_PULL_DOWN;
                        pagination = 1;
                        getData();
                    }
                }, 500);
            }
        });

    }
    //初始化适配器
    private void initAdapter() {
        cashlistAdapter = new CashlistAdapter(R.layout.adapter_item_txjl, new ArrayList<BeanCashlist.DataBean.ListBean>());
        cashlistAdapter.setEmptyView(EmptyViewUtils.getview(mActivity, "没有相关记录"));
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.addItemDecoration(new DividerItemDecoration(1, 0, 0, mActivity));
        rv.setAdapter(cashlistAdapter);
        //加载更多
        cashlistAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rv);
    }
    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.CASHLIST)
                .addParam("type","-2")
                .addParam("psize", "10")
                .addParam("p",String.valueOf(pagination))
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        showSuccess();
                        if (result.getStatus() == 10000) {
                            BeanCashlist beanCashlist = new Gson().fromJson(result.getResult(), BeanCashlist.class);
                            AdapterUtils.setData(cashlistAdapter, beanCashlist.getData().getList(), net_action);
                        } else {
                            if (net_action == Loadtype.ACTION_LOAD_MORE) {
                                cashlistAdapter.loadMoreEnd();
                            }
                        }
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            cashlistAdapter.loadMoreFail();
                        } else {
                            showTimeout(new LoadListener() {
                                @Override
                                public void loadlistener() {
                                    net_action = Loadtype.ACTION_PULL_DOWN;
                                    pagination = 1;
                                    getData();
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
