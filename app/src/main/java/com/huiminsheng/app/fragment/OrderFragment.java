package com.huiminsheng.app.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.home.PaymentSuccessActivity;
import com.huiminsheng.app.activity.my.OrderDetailsActivity;
import com.huiminsheng.app.adapter.MyorderAdapter;
import com.huiminsheng.app.base.BaseFragment;
import com.huiminsheng.app.bean.BeanMyOrder;
import com.huiminsheng.app.bean.BeanOrder;
import com.huiminsheng.app.bean.EvenBean.EvenBean_collect;
import com.huiminsheng.app.bean.EvenBean.EvenBean_myOrder;
import com.huiminsheng.app.bean.PayResult;
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
import com.huiminsheng.app.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//订单列表
public class OrderFragment extends BaseFragment {

    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.swrl_order)
    SwipeRefreshLayout swrlOrder;
    private String status = "";//订单状态
    private MyorderAdapter myorderAdapter;
    private Disposable dis;

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_order;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        assert bundle != null;
        status = bundle.getString("status");
        swrlOrder.setColorSchemeResources(R.color.theme);
        swrlOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swrlOrder.setRefreshing(true);
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
        initAdapter();
        showLoading();
        getData();
    }

    //初始化适配器
    private void initAdapter() {
        myorderAdapter = new MyorderAdapter(R.layout.adapter_myorder_item,new ArrayList<BeanMyOrder.DataBean.ListBeanX>());
        myorderAdapter.setEmptyView(EmptyViewUtils.getview(getContext(),"没有相关订单", ContextCompat.getDrawable(mContext,R.mipmap.empty_dd)));
        rvOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrder.setAdapter(myorderAdapter);
        //加载更多
        myorderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rvOrder);
        //点击事件 跳订单详情
        myorderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final String order_sn = myorderAdapter.getData().get(position).getOrder_sn();
                Bundle bundle = new Bundle();
                bundle.putString("order_sn",order_sn);
                OrderDetailsActivity.jumpActivity(getActivity(),OrderDetailsActivity.class,bundle);
            }
        });
        //按钮
        myorderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
             String status =  myorderAdapter.getData().get(position).getStatus();
             final String order_sn = myorderAdapter.getData().get(position).getOrder_sn();
             switch (status){
                 case "-1":
                     //已取消 删除订单
                     if(view.getId()==R.id.tv_scdd){
                         PopupWindowGather.getAffirmPop(getActivity(), getString(R.string.dialog_msg_delete_order), new AffirmPopListener() {
                             @Override
                             public void onAffirmPopListener() {
                                 removeOrder(order_sn);
                             }
                         });
                     }
                     break;
                 case "0":
                     //待付款  取消订单 立即支付
                     switch (view.getId()){
                         case R.id.tv_scdd:
                             //取消订单
                             PopupWindowGather.getAffirmPop(getActivity(), getString(R.string.dialog_msg_cancle_order), new AffirmPopListener() {
                                 @Override
                                 public void onAffirmPopListener() {
                                     cancelOrder(order_sn);
                                 }
                             });
                             break;
                         case R.id.tv_zf:
                             //立即支付
                             calture(order_sn);
                             break;
                             default:break;
                     }
                     break;
                 case "1":
                     //待发货
                     break;
                 case "2":
                     //待收货
                     break;
                 case "3":
                     //交易完成
                     if(view.getId()==R.id.tv_scdd){
                         PopupWindowGather.getAffirmPop(getActivity(), getString(R.string.dialog_msg_delete_order), new AffirmPopListener() {
                             @Override
                             public void onAffirmPopListener() {
                                 removeOrder(order_sn);
                             }
                         });
                     }
                     break;
                 default:
                     break;
             }
            }
        });
    }
    //取消订单
    private void cancelOrder(String order_sn){
        NoGo.create(mContext)
                .get()
                .setUrl(ApiUrls.COMMODITY.CANCELORDERS)
                .addParam("order_sn",order_sn)
                .showDialog()
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(getContext(),"已为您取消订单");
                        // 通知刷新
                        EventBus.getDefault().post(new EvenBean_myOrder(ApiUrls.KEY.REFRESH));
                    }
                });
    }
    //删除订单
    private void removeOrder(String order_sn){
        NoGo.create(mContext)
                .get()
                .setUrl(ApiUrls.COMMODITY.REMOVEORDERS)
                .addParam("order_sn",order_sn)
                .showDialog()
                .request(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(getContext(),"订单删除成功");
                        // 通知刷新
                        EventBus.getDefault().post(new EvenBean_myOrder(ApiUrls.KEY.REFRESH));
                    }
                });
    }
    //获取数据
    private void getData() {
        NoGo.create(getContext())
                .get()
                .setSign(this)
                .setUrl(ApiUrls.COMMODITY.GETORDERS)
                .addParam("p", String.valueOf(pagination))
                .addParam("status",status)
                .request(BeanMyOrder.class, new BaseListener<BeanMyOrder>() {
                    @Override
                    public void onSucceed(int what, Result<BeanMyOrder> result) {
                        showSuccess();
                        AdapterUtils.setData(myorderAdapter,result.getResult().getData().getList(),net_action);
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            myorderAdapter.loadMoreFail();
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
                        swrlOrder.setRefreshing(false);
                    }
                });
    }
    //立即支付
    private void calture(String order_sn){
        NoGo.create(mContext)
                .get()
                .setUrl(ApiUrls.COMMODITY.CALTURE)
                .addParam("order_sn",order_sn)
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        if (result.getStatus()==10000){
                            try {
                                BeanOrder beanOrder = new Gson().fromJson(result.getResult(),BeanOrder.class);
                                alipay(beanOrder);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //吊起支付宝支付
    private void alipay(final BeanOrder beanOrder){
        dis = Observable.create(new ObservableOnSubscribe<Map<String, String>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, String>> emitter) throws Exception {
                PayTask alipay = new PayTask(getActivity());
                emitter.onNext(alipay.payV2(beanOrder.getData().getUrl(), true));
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, String>>() {
                    @Override
                    public void accept(Map<String, String> map) throws Exception {
                        PayResult payResult = new PayResult(map);
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        switch (resultStatus) {
                            case "9000":
                                //支付成功
                                // 通知刷新
                                EventBus.getDefault().post(new EvenBean_myOrder(ApiUrls.KEY.REFRESH));
                                //跳成功页面
                                Bundle bundle = new Bundle();
                                bundle.putString("order_sn", beanOrder.getData().getSn());
                                bundle.putString("trade_type", "myOrder");
                                PaymentSuccessActivity.jumpActivity(getActivity(), PaymentSuccessActivity.class, bundle);
                                break;
                            case "4000":
                                // 4000为支付失败，包括用户主动取消支付，或者系统返回的错误
                                ToastUtils.showToast(mContext, "支付失败");
                                break;
                            case "6001":
                                // 6001为取消支付，或者系统返回的错误
                                ToastUtils.showToast(mContext, "取消支付");
                                //刷新列表
                                swrlOrder.setRefreshing(true);
                                net_action = Loadtype.ACTION_PULL_DOWN;
                                pagination = 1;
                                getData();
                                break;
                            case "8000":
                                // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                                ToastUtils.showToast(mContext, "支付结果确认中");
                                break;
                            default:
                                // 其他为系统返回的错误
                                ToastUtils.showToast(mContext, "支付错误");
                                break;
                        }
                    }
                });
    }

    // 刷新列表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  even(EvenBean_myOrder evenBean_collect){
        if (TextUtils.equals(evenBean_collect.msg,ApiUrls.KEY.REFRESH)){
            swrlOrder.setRefreshing(true);
            net_action = Loadtype.ACTION_PULL_DOWN;
            pagination = 1;
            getData();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dis!=null&&!dis.isDisposed()){
            dis.dispose();
        }
        EventBus.getDefault().unregister(this);
    }


}
