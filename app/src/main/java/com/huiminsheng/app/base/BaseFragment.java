package com.huiminsheng.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.unify.Loadtype;
import com.huiminsheng.app.unify.loadsir.LoadListener;
import com.huiminsheng.app.unify.loadsir.loadcallback.LoadingCallback;
import com.huiminsheng.app.unify.loadsir.loadcallback.TimeoutCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected LoadService loadService;
    protected Unbinder unbinder;
    LoadListener loadListener;
    protected Context mContext;
    //页数
    protected int pagination = 1;
    //网络加载方式
    protected int net_action = Loadtype.ACTION_PULL_DOWN;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = View.inflate(getActivity(), onCreateFragmentView(), null);
        unbinder = ButterKnife.bind(this, rootView);
        mContext = getActivity();
        loadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                if (loadListener!=null){
                    loadListener.loadlistener();
                }
            }
        });
        //网络错误回调
        init();
        return loadService.getLoadLayout();
    }
    //显示网络错误加载页面
    public void showTimeout(final LoadListener loadListener){
        this.loadListener  = loadListener;
        loadService.showCallback(TimeoutCallback.class);
    }
    protected abstract int onCreateFragmentView();
    protected abstract void init();
    //显示加载中
    public void showLoading(){
        loadService.showCallback(LoadingCallback.class);
    }
    //显示加载后的页面
    public void showSuccess(){
        loadService.showSuccess();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        NoGo.cancelBySign(this);
    }
}
