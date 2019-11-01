package com.huiminsheng.app.activity.share;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.BypromotingAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanBypromoting;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.unify.AdapterUtils;
import com.huiminsheng.app.unify.EmptyViewUtils;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.utils.DividerItemDecoration;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.views.Post_diaog;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.download.SyncDownloadExecutor;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//图文推广
public class BypromotingActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private BypromotingAdapter bypromotingAdapter;
    private Disposable dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bypromoting);
        ButterKnife.bind(this);
        init();
        initAdapter();
        showLoading();
        getData();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
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
    private void initAdapter() {
        bypromotingAdapter = new BypromotingAdapter(R.layout.adapter_item_bypromoting,new ArrayList<BeanBypromoting.DataBean.ListBean>());
        bypromotingAdapter.setEmptyView(EmptyViewUtils.getview(mActivity, "暂无相关数据"));
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.addItemDecoration(new DividerItemDecoration(1, 0, 0, mActivity));
        rv.setAdapter(bypromotingAdapter);
        //加载更多
        bypromotingAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                net_action = Loadtype.ACTION_LOAD_MORE;//设置为加载更多
                pagination++;
                getData();
            }
        }, rv);
        bypromotingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_copy:
                        //复制文案
                        //获取剪贴板管理器：
                        ClipboardManager cm = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                        // 创建普通字符型ClipData
                        ClipData mClipData = ClipData.newPlainText("Label", bypromotingAdapter.getData().get(position).getContent());
                        // 将ClipData内容放到系统剪贴板里。
                        cm.setPrimaryClip(mClipData);
                        ToastUtils.showToast(mActivity,"已将文案复制到剪切板");
                        break;
                    case R.id.ll_img_xz:
                        //保存图片
                        if (bypromotingAdapter.getData().get(position).getImgs().size()>0){
                            download(position,bypromotingAdapter.getData().get(position).getCid(),bypromotingAdapter.getData().get(position).getImgs());
                        }
                        break;
                        default:break;
                }
            }
        });
    }
    //多文件下载
    private void download(final int position, final String cid, final ArrayList<String> list){

         Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (int i = 0; i < list.size(); i++) {
                    DownloadRequest restRequest = new DownloadRequest(list.get(i), RequestMethod.POST, ApiUrls.KEY.PATH_SAVE, System.currentTimeMillis() + ".png", false, true);
                    SyncDownloadExecutor.INSTANCE.execute(i, restRequest, downloadListener);
                    emitter.onNext(String.valueOf(i));
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            Dialog dialog = new Post_diaog(mActivity);
            @Override
            public void onSubscribe(Disposable d) {
                dis = d;
                dialog.show();
            }

            @Override
            public void onNext(String o) {
                Log.e("dfdf", "onFinish: "+o );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                dialog.dismiss();
                documentsCount(cid,position);
                ToastUtils.showToast(mActivity,"已为您保存图片");
            }
        });

    }
    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onDownloadError(int what, Exception exception) {

        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {

        }

        @Override
        public void onFinish(int what, String filePath) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(filePath))));
        }

        @Override
        public void onCancel(int what) {

        }
    };
    //下载完后通知后台统计次数
    private void documentsCount(String cid, final int position){
        NoGo.create(mActivity)
                .setUrl(ApiUrls.SHARE.DOWN_COUNT)
                .addParam("cid",cid)
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        bypromotingAdapter.getData().get(position).setCounts(Integer.parseInt(bypromotingAdapter.getData().get(position).getCounts())+1+"");
                        bypromotingAdapter.notifyItemChanged(position);
                    }
                });
    }
    //获取数据
    private void getData() {
        NoGo.create(mActivity)
                .get()
                .setUrl(ApiUrls.SHARE.SHARE_DOWNLOAD)
                .addParam("p", String.valueOf(pagination))
                .addParam("psize", "10")
                .request(BeanBypromoting.class, new BaseListener<BeanBypromoting>() {
                    @Override
                    public void onSucceed(int what, Result<BeanBypromoting> result) {
                        showSuccess();
                        AdapterUtils.setData(bypromotingAdapter, result.getResult().getData().getList(), net_action);
                    }

                    @Override
                    public void onFailed(int what) {
                        super.onFailed(what);
                        if (net_action == Loadtype.ACTION_LOAD_MORE) {
                            pagination -= 1;
                            bypromotingAdapter.loadMoreFail();
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
        titleTitle.setText("图文推广");
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dis!=null&&!dis.isDisposed()){
            dis.dispose();
        }
    }
}
