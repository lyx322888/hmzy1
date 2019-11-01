package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.EarningDetailsAdapter;
import com.huiminsheng.app.adapter.MonthAdpgter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanEarningDetails;
import com.huiminsheng.app.bean.BeanMonth;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.EmptyViewUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;
import com.huiminsheng.app.utils.PopwindownUtils;
import com.huiminsheng.app.utils.SpannableStringUtils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 分享返佣明细
public class EarningDetailsActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.ll_screen)
    LinearLayout llScreen;
    @BindView(R.id.rv_earning)
    RecyclerView rvEarning;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    private String type;//类别
    private EarningDetailsAdapter earningDetailsAdapter;
    private int year;
    private int month;
    private ArrayList<BeanMonth> arrayList;
    private String monthStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_details);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    //初始化
    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        type = bundle.getString("type", "");
        String desc = bundle.getString("desc", "");
        if (!TextUtils.isEmpty(desc)) {
            titleTitle.setText(desc);
        } else {
            titleTitle.setText("分享返佣");
        }

        Calendar calendar = Calendar.getInstance();  //获取当前时间，作为图标的名字
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;

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
                        getData();
                    }
                }, 500);
            }
        });

        arrayList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            BeanMonth month = new BeanMonth(i + 1);
            arrayList.add(month);
        }
    }

    //初始化适配器
    private void initAdapter() {
        earningDetailsAdapter = new EarningDetailsAdapter(R.layout.adapter_item_earning_detail, new ArrayList<BeanEarningDetails.DataBean.ListBean>());
        earningDetailsAdapter.setEmptyView(EmptyViewUtils.getview(mActivity, "没有相关信息"));
        rvEarning.setLayoutManager(new LinearLayoutManager(mActivity));
        rvEarning.addItemDecoration(new DividerItemDecoration(1, 12, 12, mActivity));
        rvEarning.setAdapter(earningDetailsAdapter);
        //加载更多
        earningDetailsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rvEarning);
    }

    //获取数据
    private void getData() {
        if (month<10){
            monthStr = "0"+month;
        }else {
            monthStr = ""+month;
        }
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.SPLITLIST)
                .get()
                .addParam("type", type)
                .addParam("month", year + "-" + monthStr)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .request(BeanEarningDetails.class, new BaseListener<BeanEarningDetails>() {
                    @Override
                    public void onSucceed(int what, Result<BeanEarningDetails> result) {
                        showSuccess();
                        tvMoney.setText(SpannableStringUtils.getBuilder(result.getResult().getData().getTotal())
                                .append("元").setProportion((float) 0.4).setForegroundColor(ContextCompat.getColor(mActivity, R.color.phb_font)).create());
                        AdapterUtils.setData(earningDetailsAdapter, result.getResult().getData().getList(), net_action);
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            earningDetailsAdapter.loadMoreFail();
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

    @OnClick({R.id.title_back, R.id.ll_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_screen:
                //筛选
                showPopMonth();
                break;
        }
    }

    //选择时间弹窗
    private void showPopMonth() {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_time_select, null, false);
        final PopupWindow popupWindow = new PopwindownUtils().initView(mActivity, inflate, true);
        popupWindow.setAnimationStyle(0);
        popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
        final TextView tv_year = inflate.findViewById(R.id.tv_year);
        tv_year.setText(year + "年");
        ImageView lv_left = inflate.findViewById(R.id.lv_left);
        ImageView lv_right = inflate.findViewById(R.id.lv_right);
        lv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year -= 1;
                tv_year.setText(year + "年");
            }
        });
        lv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year += 1;
                tv_year.setText(year + "年");
            }
        });
        RecyclerView rvMonth = inflate.findViewById(R.id.rv_month);
        rvMonth.setLayoutManager(new GridLayoutManager(mActivity, 4));

        final MonthAdpgter monthAdpgter = new MonthAdpgter(R.layout.adapter_month, arrayList);
        monthAdpgter.Checked(month - 1);
        rvMonth.setAdapter(monthAdpgter);
        rvMonth.addItemDecoration(new DividerItemDecoration(mActivity, 2, 1, ContextCompat.getColor(mActivity, R.color.edit_font)));
        monthAdpgter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                monthAdpgter.Checked(position);
            }
        });
        TextView qx = inflate.findViewById(R.id.tv_pop_quxiao);
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        TextView qr = inflate.findViewById(R.id.tv_pop_qr);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                month = monthAdpgter.getCheckedPosition() + 1;
                tvMonth.setText(year+"-"+month+"月");

                swipe.setRefreshing(true);
                net_action = Loadtype.ACTION_PULL_DOWN;
                pagination = 1;
                getData();
            }
        });
    }
}
