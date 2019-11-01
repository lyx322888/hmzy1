package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.TeamDetailAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanTeamDetail;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.EmptyViewUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.CollectedUtils;
import com.huiminsheng.app.utils.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//成员列表
public class TeamDetailActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rv_team)
    RecyclerView rvTeam;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.title_title)
    TextView titleTitle;
    private String lfid;
    private TeamDetailAdapter teamDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        init();
        initAdpter();
        showLoading();
        getData();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("成员列表");
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        lfid = bundle.getString("lfid", "");
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
    //适配器
    private void initAdpter() {
        teamDetailAdapter = new TeamDetailAdapter(R.layout.adapter_item_team_detail, new ArrayList<BeanTeamDetail.DataBean.ListBean>());
        teamDetailAdapter.setEmptyView(EmptyViewUtils.getview(mActivity, "没有相关消息"));
        rvTeam.setLayoutManager(new LinearLayoutManager(mActivity));
        rvTeam.addItemDecoration(new DividerItemDecoration(1, 12, 12, mActivity));
        rvTeam.setAdapter(teamDetailAdapter);
        //加载更多
        teamDetailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rvTeam);
        teamDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_phone) {
                    CollectedUtils.callPhone(mActivity, teamDetailAdapter.getData().get(position).getMobile());
                }
            }
        });
    }

    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.CHILDINFOLIST)
                .addParam("lfid", lfid)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .addParam("type", "1")
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        showSuccess();
                        if (result.getStatus() == 10000) {
                            BeanTeamDetail teamDetail = new Gson().fromJson(result.getResult(), BeanTeamDetail.class);
                            AdapterUtils.setData(teamDetailAdapter, teamDetail.getData().getList(), net_action);
                        } else {
                            if (net_action == Loadtype.ACTION_LOAD_MORE) {
                                teamDetailAdapter.loadMoreEnd();
                            }
                        }
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            teamDetailAdapter.loadMoreFail();
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
