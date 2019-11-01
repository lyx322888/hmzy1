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
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.MyTeamAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanMyTeam;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的团队
public class MyTeamActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.tv_totalnumber)
    TextView tvTotalnumber;
    @BindView(R.id.tv_zhijienumber)
    TextView tvZhijienumber;
    @BindView(R.id.tv_jianjienumber)
    TextView tvJianjienumber;
    private MyTeamAdapter myTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        ButterKnife.bind(this);
        init();
        initAdpter();
        showLoading();
        getData();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("我的团队");
    }

    //初始化
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
    private void initAdpter() {
        myTeamAdapter = new MyTeamAdapter(R.layout.adapter_item_myteam, new ArrayList<BeanMyTeam.DataBean.ListBean>());
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(myTeamAdapter);
        myTeamAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转 团队详情
                Bundle bundle = new Bundle();
                bundle.putString("lfid",myTeamAdapter.getData().get(position).getLf_id());
                TeamDetailActivity.jumpActivity(mActivity,TeamDetailActivity.class,bundle);
            }
        });
    }

    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.CHILDLEVELSCOUNT)
                .request(BeanMyTeam.class, new BaseListener<BeanMyTeam>() {
                    @Override
                    public void onSucceed(int what, Result<BeanMyTeam> result) {
                        showSuccess();
                        myTeamAdapter.setNewData(result.getResult().getData().getList());
                        tvTotalnumber.setText(result.getResult().getData().getTotal());
                        tvJianjienumber.setText(result.getResult().getData().getIndirect_count());
                        tvZhijienumber.setText(result.getResult().getData().getDirect_count());
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
