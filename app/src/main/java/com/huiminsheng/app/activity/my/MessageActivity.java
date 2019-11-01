package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.MessageAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanMessage;
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

/*消息中心*/
public class MessageActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;

    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
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
    //适配器
    private void initAdapter() {
        messageAdapter = new MessageAdapter(R.layout.adapter_item_msg,new ArrayList<BeanMessage.DataBean.ListBean>());
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
        messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转消息详情
                Bundle bundle = new Bundle();
                bundle.putString("title",messageAdapter.getData().get(position).getTitle());
                bundle.putString("type",messageAdapter.getData().get(position).getType());
                MessageDetailsActivity.jumpActivity(mActivity,MessageDetailsActivity.class,bundle);
            }
        });
    }
    //数据加载
    private void getData() {
        NoGo.create(mActivity)
                .get().setUrl(ApiUrls.MY.GETMSGHEADER)
                .addParam("p",String.valueOf(pagination))
                .addParam("psize","10")
                .request(BeanMessage.class, new BaseListener<BeanMessage>() {
                    @Override
                    public void onSucceed(int what, Result<BeanMessage> result) {
                        showSuccess();
                        AdapterUtils.setData(messageAdapter, result.getResult().getData().getList(), net_action);
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
    @Override
    protected void setTitleView() {
        titleTitle.setText("消息中心");
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
