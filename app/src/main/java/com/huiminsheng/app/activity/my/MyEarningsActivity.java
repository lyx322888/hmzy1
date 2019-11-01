package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.web.BaseWebActivity;
import com.huiminsheng.app.adapter.MyenringsAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanMyEarnings;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView_yuan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的收益
public class MyEarningsActivity extends BaseActivity {

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
    @BindView(R.id.iv_my_head)
    MyImagView_yuan ivMyHead;
    @BindView(R.id.lv_my_dz)
    ImageView lvMyDz;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.iv_my_medium)
    ImageView ivMyMedium;
    @BindView(R.id.tv_my_zz)
    TextView tvMyZz;
    @BindView(R.id.tv_fysm)
    TextView tvFysm;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_tx)
    TextView tvTx;
    @BindView(R.id.tv_all_profit)
    TextView tvAllProfit;
    @BindView(R.id.tv_today_profit)
    TextView tvTodayProfit;
    @BindView(R.id.tv_yesterday_profit)
    TextView tvYesterdayProfit;
    private String fyUrl = "";
    private MyenringsAdapter myenringsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earnings);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }
    //适配器
    private void initAdapter() {
        myenringsAdapter = new MyenringsAdapter(R.layout.adapter_item_myearnings,new ArrayList<BeanMyEarnings.DataBean.ListBean.ChannelsBean>());
        rv.setLayoutManager(new LinearLayoutManager(mActivity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rv.addItemDecoration(new DividerItemDecoration(1, 12, 12,mActivity));
        rv.setAdapter(myenringsAdapter);
        myenringsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String type = myenringsAdapter.getData().get(position).getType();
                String desc = myenringsAdapter.getData().get(position).getDesc();
                Bundle bundle = new Bundle();
                bundle.putString("type",type);
                bundle.putString("desc",desc);
                EarningDetailsActivity.jumpActivity(mActivity,EarningDetailsActivity.class,bundle);
            }
        });
    }

    //初始化
    private void init() {
        Bundle bundle =getIntent().getExtras();
        if (bundle!=null){
            String headpis = bundle.getString("headpis");
            String nickName = bundle.getString("nickName");
            String is_Shopowner = bundle.getString("is_Shopowner");
            String leveName = bundle.getString("leveName");
            //头像
            if ( headpis != null) {
                ToolsImage.loaderTx(mActivity, headpis, ivMyHead);
            }
            //昵称
            tvMyName.setText(nickName);
            //是不是店长  1是0不是
            if (TextUtils.equals(is_Shopowner, "1")) {
                lvMyDz.setVisibility(View.VISIBLE);
            } else {
                lvMyDz.setVisibility(View.GONE);
            }
            //等级
            tvMyZz.setText(leveName);
        }
    }

    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.MY.ACCOUNT)
                .request(BeanMyEarnings.class, new BaseListener<BeanMyEarnings>() {
                    @Override
                    public void onSucceed(int what, Result<BeanMyEarnings> result) {
                        showSuccess();
                        fyUrl = result.getResult().getData().getList().getProfit_url();
                        setInfoView(result.getResult().getData().getList());
                        myenringsAdapter.setNewData(result.getResult().getData().getList().getChannels());
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
                });
    }

    //填充数据
    private void setInfoView(BeanMyEarnings.DataBean.ListBean list) {
        tvAllProfit.setText(list.getAll_profit());
        tvTodayProfit.setText(list.getToday_profit());
        tvYesterdayProfit.setText(list.getYesterday_profit());
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("我的收益");
    }

    @OnClick({R.id.title_back, R.id.tv_fysm, R.id.tv_tx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_fysm:
                //返佣说明
                if (!TextUtils.isEmpty(fyUrl)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", fyUrl);
                    BaseWebActivity.jumpActivity(mActivity, BaseWebActivity.class, bundle);
                }
                break;
            case R.id.tv_tx:
                //立即提现
                WithdrawDepositActivity.jumpActivity(mActivity, WithdrawDepositActivity.class, null);
                break;
        }
    }
}
