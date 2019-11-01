package com.huiminsheng.app.activity.my;

import android.content.Context;
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
import com.huiminsheng.app.activity.home.ProductDetailsActivity;
import com.huiminsheng.app.adapter.CollectAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanCollect;
import com.huiminsheng.app.bean.EvenBean.EvenBean_collect;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.EmptyViewUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的收藏
public class CollectActivity extends BaseActivity {

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
    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private String search_name = "0";
    private CollectAdapter collectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    //初始化
    private void init() {
        editSearch.setOnKeyListener(new View.OnKeyListener() {
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
    //初始化适配器
    private void initAdapter() {
        collectAdapter = new CollectAdapter(R.layout.adapter_collect_item, new ArrayList<BeanCollect.DataBean.ListBean>());
        collectAdapter.setEmptyView(EmptyViewUtils.getview(mActivity, "没有相关收藏"));
        rvCollect.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCollect.addItemDecoration(new DividerItemDecoration(1, 18, 18, mActivity));
        rvCollect.setAdapter(collectAdapter);
        //加载更多
        collectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rvCollect);
        collectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (collectAdapter.getisCompile()){
                    //编辑中 点击直接选择
                    collectAdapter.getData().get(position).isChoose = !collectAdapter.getData().get(position).isChoose;
                    collectAdapter.notifyItemChanged(position);
                    if (collectAdapter.getChooseArr().length()==0){
                        titleRightTx.setText("返回");
                    }else {
                        titleRightTx.setText("删除");
                    }
                }else {
                    //不是编辑状态就跳详情
                    Bundle bundle = new Bundle();
                    //商品id
                    bundle.putString("goods_id",collectAdapter.getData().get(position).getGoods_id());
                    ProductDetailsActivity.jumpActivity(mActivity,ProductDetailsActivity.class,bundle);
                }
            }
        });
    }
    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.COMMODITY.MYCOLLECTION)
                .get()
                .addParam("p", String.valueOf(pagination))
                .addParam("search_name", search_name)
                .addParam("psize", "10")
                .request(BeanCollect.class, new BaseListener<BeanCollect>() {
                    @Override
                    public void onSucceed(int what, Result<BeanCollect> result) {
                        showSuccess();
                        AdapterUtils.setData(collectAdapter, result.getResult().getData().getList(), net_action);
                        if (collectAdapter.getisCompile()){
                            if (collectAdapter.getChooseArr().length()==0){
                                titleRightTx.setText("返回");
                            }else {
                                titleRightTx.setText("删除");
                            }
                        }
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            collectAdapter.loadMoreFail();
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
    @Override
    protected void setTitleView() {
        titleTitle.setText("我的收藏");
        titleRightTx.setText("管理");
        titleRightTx.setVisibility(View.VISIBLE);
    }
    @OnClick({R.id.title_back, R.id.title_Right_tx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_Right_tx:
                //管理
                if (collectAdapter.getisCompile()){
                    if (titleRightTx.getText().equals("返回")){
                        titleRightTx.setText("管理");
                        collectAdapter.openCompile(false);
                    }else {
                        //删除选中想项
                        PopupWindowGather.getAffirmPop(mActivity, "您确定删除所选商品吗", new AffirmPopListener() {
                            @Override
                            public void onAffirmPopListener() {
                                if (!TextUtils.isEmpty(collectAdapter.getChooseArr())){
                                    deleteData(collectAdapter.getChooseArr());
                                }
                            }
                        });
                    }
                }else {
                    titleRightTx.setText("返回");
                    collectAdapter.openCompile(true);
                }
                break;
        }
    }
    //删除请求
    private void  deleteData(String id_ary){
        NoGo.create(mActivity)
                .setUrl(ApiUrls.COMMODITY.DELETECOLLECTION)
                .showDialog()
                .get()
                .addParam("id_ary",id_ary)
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        net_action = Loadtype.ACTION_PULL_DOWN;
                        pagination = 1;
                        getData();
                        titleRightTx.setText("返回");
                    }
                });
    }
    //用户取消收藏时 刷新列表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  even(EvenBean_collect evenBean_collect){
        if (TextUtils.equals(evenBean_collect.msg,ApiUrls.KEY.REFRESH)){
            swipe.setRefreshing(true);
            net_action = Loadtype.ACTION_PULL_DOWN;
            pagination = 1;
            getData();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
