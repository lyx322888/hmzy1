package com.huiminsheng.app.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.BankListAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanBankList;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.utils.DividerItemDecoration;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//选择银行
public class SelectBankActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.rv_bank)
    RecyclerView rvBank;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private BankListAdapter bankListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("选择银行");
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
                        getData();
                    }
                }, 500);
            }
        });
    }
    //适配器
    private void initAdapter() {
        bankListAdapter = new BankListAdapter(R.layout.adapter_seclectbank_item,new ArrayList<BeanBankList.DataBean.ListBean>());
        rvBank.setLayoutManager(new LinearLayoutManager(mActivity));
        rvBank.addItemDecoration(new DividerItemDecoration(1, 0, 0, mActivity));
        rvBank.setAdapter(bankListAdapter);
        bankListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent();
                intent.putExtra("bankName", bankListAdapter.getData().get(position).getName());
                intent.putExtra("bankId", bankListAdapter.getData().get(position).getId());
                setResult(21546,intent);
                finish();
            }
        });
    }
    //加载数据
    private void getData() {
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.BANKLIST)
                .request(BeanBankList.class, new BaseListener<BeanBankList>() {
                    @Override
                    public void onSucceed(int what, Result<BeanBankList> result) {
                        showSuccess();
                        AdapterUtils.setData(bankListAdapter,result.getResult().getData().getList(),net_action);
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
