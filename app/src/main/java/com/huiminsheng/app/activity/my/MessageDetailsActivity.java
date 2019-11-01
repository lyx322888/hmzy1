package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.MessageDetailsAdapter;
import com.huiminsheng.app.base.BaseActivity;
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

/*详情*/
public class MessageDetailsActivity extends BaseActivity {

    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.title_title)
    TextView titleTitle;
    private MessageDetailsAdapter messageAdapter;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
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
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        type = bundle.getString("type");
        titleTitle.setText(bundle.getString("title"));
    }

    private void initAdapter() {
        messageAdapter = new MessageDetailsAdapter(R.layout.adapter_item_msg_details, new ArrayList<BeanMsgDetails.DataBean.ListBean>());
        messageAdapter.setEmptyView(EmptyViewUtils.getview(mActivity, "没有相关消息"));
        rvMessage.setLayoutManager(new LinearLayoutManager(mActivity));
        rvMessage.addItemDecoration(new DividerItemDecoration(1, 0, 0, mActivity));
        rvMessage.setAdapter(messageAdapter);
        //加载更多
        messageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rvMessage);

    }

    private void getData() {
        NoGo.create(mActivity)
                .get().setUrl(ApiUrls.MY.JLIST)
                .addParam("type", type)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        showSuccess();
                        if (result.getStatus() == 10000) {
                            BeanMsgDetails beanMsgDetails = new Gson().fromJson(result.getResult(), BeanMsgDetails.class);
                            AdapterUtils.setData(messageAdapter, beanMsgDetails.getData().getList(), net_action);
                        } else {
                            if (net_action == Loadtype.ACTION_LOAD_MORE) {
                                messageAdapter.loadMoreEnd();
                            }
                        }
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            messageAdapter.loadMoreFail();
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
