package com.huiminsheng.app.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.GoodsStroresAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanStrores;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//选择门店地址
public class GoodsStoresActivity extends BaseActivity {

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
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.rv_stores)
    RecyclerView rvStores;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private String search_name = "";
    private GoodsStroresAdapter goodsStroresAdapter1;
    private String goods_id = "";
    //已经选择的门店地址id
    private String storesId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_stores);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        goods_id = bundle.getString("goods_id");
        storesId = bundle.getString("storesId");        editSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    search_name = editSearch.getText().toString().trim();
                    net_action = Loadtype.ACTION_PULL_DOWN;
                    getData();
                    pagination = 1;
                    //收起键盘
                    v = getCurrentFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        assert v != null;
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return false;//返回true会导致键盘的删除键不好使
            }
        });
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

    @Override
    protected void setTitleView() {
        titleTitle.setText("选择门店");
    }

    //加载数据
    private void getData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.COMMODITY.STORES)
                .addParam("goods_id", goods_id)
                .addParam("search_name", search_name)
                .addParam("p", String.valueOf(pagination))
                .request(BeanStrores.class, new BaseListener<BeanStrores>() {
                    @Override
                    public void onSucceed(int what, Result<BeanStrores> result) {
                        showSuccess();
                        AdapterUtils.setData(goodsStroresAdapter1, result.getResult().getData().getList(), net_action);
                        if (!TextUtils.isEmpty(storesId)) {
                            goodsStroresAdapter1.Checked(storesId);
                        }
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            goodsStroresAdapter1.loadMoreFail();
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
                        swipe.setRefreshing(false);
                    }
                });
    }

    //初始化适配器
    private void initAdapter() {
        goodsStroresAdapter1 = new GoodsStroresAdapter(R.layout.adapter_item_goods_stores, new ArrayList<BeanStrores.DataBean.ListBean>());
        rvStores.setLayoutManager(new LinearLayoutManager(mActivity));
        rvStores.addItemDecoration(new DividerItemDecoration(1, 18, 18, mActivity));
        rvStores.setAdapter(goodsStroresAdapter1);
        //加载更多
        goodsStroresAdapter1.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rvStores);
        goodsStroresAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goodsStroresAdapter1.Checked(position);
                String address = goodsStroresAdapter1.getData().get(position).getAddress();
                String name = goodsStroresAdapter1.getData().get(position).getName();
                storesId = goodsStroresAdapter1.getData().get(position).getId();
                Intent intent = new Intent();
                intent.putExtra("address", address);
                intent.putExtra("name", name);
                intent.putExtra("storesId", storesId);
                setResult(10000, intent);
                finish();
            }
        });
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
