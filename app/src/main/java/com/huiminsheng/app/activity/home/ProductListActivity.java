package com.huiminsheng.app.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.ProductListAdapter;
import com.huiminsheng.app.adapter.ScreenAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanProductList;
import com.huiminsheng.app.bean.BeanScreenMenu;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.EmptyViewUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.CustomPopWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*商品列表*/
public class ProductListActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.lv_switchover)
    ImageView lvSwitchover;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.tv_sx_item1)
    TextView tvSxItem1;
    @BindView(R.id.tv_sx_item2)
    TextView tvSxItem2;
    @BindView(R.id.tv_sx_item3)
    TextView tvSxItem3;
    @BindView(R.id.lv_sx_item3)
    ImageView lvSxItem3;
    @BindView(R.id.ll_sx_item3)
    LinearLayout llSxItem3;
    @BindView(R.id.tv_sx_item4)
    TextView tvSxItem4;
    @BindView(R.id.lv_sx_item4)
    ImageView lvSxItem4;
    @BindView(R.id.ll_sx_item4)
    LinearLayout llSxItem4;
    @BindView(R.id.rv_sx)
    RecyclerView rvSx;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.ll_sx)
    LinearLayout llSx;
    @BindView(R.id.lv_scrolltop)
    ImageView lvScrolltop;
    @BindView(R.id.app_bar)
    CoordinatorLayout appBar;

    private boolean switchover_bag = true;//大图列表模式
    //销量1 价格降序2  升序3
    private String choice = "";
    //列表id
    private String trade_type;
    //筛选栏目当前选择项
    private CustomPopWindow mCustomPopWindow;
    private ScreenAdapter screenAdapter;
    private ProductListAdapter productListAdapter;
    private View inflate;
    private String search_name = "";
    private String class_id = "";
    private int scrollDy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        init();
    }

    //控件初始化
    private void init() {
        Bundle bundle = new Bundle();
        trade_type = bundle.getString("id");
        editSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    search_name = editSearch.getText().toString().trim();
                    net_action = Loadtype.ACTION_PULL_DOWN;
                    pagination = 1;
                    initData();
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
        initsxView();
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
        showLoading();
        llSx.post(new Runnable() {
            @Override
            public void run() {
                getscreenMenuData();
            }
        });
        initAdapter();
        initData();
    }

    //筛选下拉框布局
    private void initsxView() {
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindowlayout_screen, null, false);
        //menu 适配器ScreenAdapter
        screenAdapter = new ScreenAdapter(R.layout.screen_item, new ArrayList<BeanScreenMenu.DataBean.ListBean>());
        View empty = EmptyViewUtils.getview(mActivity, "数据丢失啦，请点击重新获取！");
        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getscreenMenuData();
            }
        });
        screenAdapter.setEmptyView(empty);
        RecyclerView recyclerView = inflate.findViewById(R.id.rv_screen);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        recyclerView.setAdapter(screenAdapter);
        screenAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                screenAdapter.Checked(position);
            }
        });
    }

    @OnClick({R.id.title_back, R.id.lv_switchover, R.id.tv_sx_item1, R.id.tv_sx_item2, R.id.ll_sx_item3, R.id.ll_sx_item4,R.id.lv_scrolltop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.lv_switchover:
                //视图切换
                if (switchover_bag) {
                    switchover_bag = false;
                    if (productListAdapter != null) {
                        productListAdapter.setSwitchover_bag(switchover_bag);
                        lvSwitchover.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.list_vertical));
                    }
                } else {
                    switchover_bag = true;
                    lvSwitchover.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.list_level));
                    if (productListAdapter != null) {
                        productListAdapter.setSwitchover_bag(switchover_bag);
                    }
                }
                break;
            case R.id.tv_sx_item1:
                //综合
                fySx();
                pagination = 1;
                choice = "";
                class_id = "";
                screenAdapter.clearOption();
                tvSxItem1.setTextColor(ContextCompat.getColor(mActivity, R.color.theme));
                swipe.setRefreshing(true);
                net_action = Loadtype.ACTION_PULL_DOWN;
                initData();
                break;
            case R.id.tv_sx_item2:
                //销量
                fySx();
                choice = "1";
                class_id = "";
                screenAdapter.clearOption();
                pagination = 1;
                initData();
                swipe.setRefreshing(true);
                net_action = Loadtype.ACTION_PULL_DOWN;
                tvSxItem2.setTextColor(ContextCompat.getColor(mActivity, R.color.theme));
                mCustomPopWindow.dissmiss();
                break;
            case R.id.ll_sx_item3:
                //价格
                fySx();
                if (!TextUtils.equals(choice, "2")) {
                    choice = "2";
                    lvSxItem3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.list_down));
                } else {
                    choice = "3";
                    lvSxItem3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.list_up));
                }
                pagination = 1;
                class_id = "";
                screenAdapter.clearOption();
                initData();
                net_action = Loadtype.ACTION_PULL_DOWN;
                swipe.setRefreshing(true);
                tvSxItem3.setTextColor(ContextCompat.getColor(mActivity, R.color.theme));
                break;
            case R.id.ll_sx_item4:
                //筛选
                fySx();
                choice = "";
                tvSxItem4.setTextColor(ContextCompat.getColor(mActivity, R.color.theme));
                lvSxItem4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.list_singleup));
                mCustomPopWindow.showAsDropDown(llSx);
                break;
            case R.id.lv_scrolltop:
                rvSx.scrollToPosition(0);
                scrollDy = 0;
                break;
        }
    }
    //复原筛选栏目
    public void fySx() {
        tvSxItem1.setTextColor(ContextCompat.getColor(mActivity, R.color.font_h));
        tvSxItem2.setTextColor(ContextCompat.getColor(mActivity, R.color.font_h));
        tvSxItem3.setTextColor(ContextCompat.getColor(mActivity, R.color.font_h));
        tvSxItem4.setTextColor(ContextCompat.getColor(mActivity, R.color.font_h));
        lvSxItem3.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.list_normal));
        lvSxItem4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.list_singledown));
    }
    //筛选框栏目内容
    private void getscreenMenuData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.COMMODITY.GOODSCLASS)
                .request(BeanScreenMenu.class, new BaseListener<BeanScreenMenu>() {
                    @Override
                    public void onSucceed(int what, Result<BeanScreenMenu> result) {
                        screenAdapter.setNewData(result.getResult().getData().getList());
                    }

                    @Override
                    public void onFailed(int what) {

                    }

                    @Override
                    public void onFinish(int what) {
                        super.onFinish(what);
                        popinit();
                    }
                });
    }
    //列表数据
    private void initData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.COMMODITY.GOODLIST)
                .addParam("trade_type", trade_type)
                .addParam("search_name", search_name)
                .addParam("choice_id", choice)
                .addParam("class_id", class_id)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .request(BeanProductList.class, new BaseListener<BeanProductList>() {
                    @Override
                    public void onSucceed(int what, Result<BeanProductList> result) {
                        showSuccess();
                        AdapterUtils.setData(productListAdapter, result.getResult().getData().getList(), net_action);
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            productListAdapter.loadMoreFail();
                        }else {
                            showTimeout(new LoadListener() {
                                @Override
                                public void loadlistener() {
                                    net_action = Loadtype.ACTION_PULL_DOWN;
                                    pagination = 1;
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
    //筛选下拉框
    private void popinit() {
        //pop
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create();
        mCustomPopWindow.dissmiss();
        mCustomPopWindow.addDismisslistenq(new CustomPopWindow.addDismissListen() {
            @Override
            public void addonDismissListen() {
                lvSxItem4.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.list_singledown));
            }
        });

        //按钮
        TextView qxsx = inflate.findViewById(R.id.tv_qxsx);
        TextView qr = inflate.findViewById(R.id.tv_qr);
        //取消筛选
        qxsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenAdapter.clearOption();
                class_id = "";
                net_action = Loadtype.ACTION_PULL_DOWN;
                initData();
                mCustomPopWindow.dissmiss();
            }
        });
        //确认
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class_id = screenAdapter.getCheckedId();
                pagination = 1;
                net_action = Loadtype.ACTION_PULL_DOWN;
                swipe.setRefreshing(true);
                initData();
                mCustomPopWindow.dissmiss();
            }
        });
    }
    //列表适配器
    private void initAdapter() {
        productListAdapter = new ProductListAdapter(R.layout.adpter_productlist, new ArrayList<BeanProductList.DataBean.ListBean>());
        rvSx.setLayoutManager(new LinearLayoutManager(mActivity));
        View empty = EmptyViewUtils.getview(mActivity, R.string.empty_z);
        productListAdapter.setEmptyView(empty);
        rvSx.addItemDecoration(new DividerItemDecoration(1, ToolsSize.getSize(12),  ToolsSize.getSize(12), mActivity));
        rvSx.setAdapter(productListAdapter);

        //加载更多
        productListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                initData();
            }
        }, rvSx);
        productListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                //商品id
                bundle.putString("goods_id",productListAdapter.getData().get(position).getGoods_id());
                ProductDetailsActivity.jumpActivity(mActivity,ProductDetailsActivity.class,bundle);
            }
        });

        rvSx.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollDy += dy;
                if (scrollDy>200){
                    lvScrolltop.setVisibility(View.VISIBLE);
                }else {
                    lvScrolltop.setVisibility(View.GONE);
                }
            }
        });
    }

}
