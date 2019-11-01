package com.huiminsheng.app.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.share.BypromotingActivity;
import com.huiminsheng.app.activity.share.ImageSynthesisActivity;
import com.huiminsheng.app.adapter.ShareAdapter;
import com.huiminsheng.app.base.BaseFragment;
import com.huiminsheng.app.bean.BeanShare;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;


public class ShareFragment extends BaseFragment {

    @BindView(R.id.rv_share)
    RecyclerView rvShare;
    @BindView(R.id.swrl_share)
    SwipeRefreshLayout swrlShare;
    Unbinder unbinder;
    private int net_action = Loadtype.ACTION_PULL_DOWN;
    private ShareAdapter shareAdapter;

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_share;
    }

    @Override
    protected void init() {
        swrlShare.setColorSchemeResources(R.color.theme);
        swrlShare.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swrlShare.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        net_action = Loadtype.ACTION_PULL_DOWN;
                        initData();
                        swrlShare.setRefreshing(false);
                    }
                }, 500);
            }
        });
        iniAdapter();
        showLoading();
        initData();
    }

    private void iniAdapter() {
        shareAdapter = new ShareAdapter(R.layout.share_item, new ArrayList<BeanShare.DataBean.ListBean>());
        rvShare.setLayoutManager(new LinearLayoutManager(getContext()));
        rvShare.setAdapter(shareAdapter);
        //加载更多
        shareAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                iniAdapter();
            }
        }, rvShare);
        shareAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (shareAdapter.getData().get(position).getType()){
                    case "1"://二维码推广
                        ImageSynthesisActivity.jumpActivity(getActivity(),shareAdapter.getData().get(position).getUrl(),shareAdapter.getData().get(position).getShare_desc()
                                ,shareAdapter.getData().get(position).getMsg(),shareAdapter.getData().get(position).getImages());
                        break;
                    case "2"://图文推广
                        Bundle bundle =new Bundle();
                        bundle.putString("type","2");
                        BypromotingActivity.jumpActivity(getActivity(),BypromotingActivity.class,bundle);
                        break;
                }
            }
        });
    }

    private void initData() {
        NoGo.create(getContext())
                .setSign(this)
                .get()
                .setUrl(ApiUrls.SHARE.SHARE)
                .request(BeanShare.class, new BaseListener<BeanShare>() {
                    @Override
                    public void onSucceed(int what, Result<BeanShare> result) {
                        showSuccess();
                        AdapterUtils.setData(shareAdapter,result.getResult().getData().getList(),net_action);
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        showTimeout(new LoadListener() {
                            @Override
                            public void loadlistener() {
                                initData();
                            }
                        });
                    }
                });
    }


}
