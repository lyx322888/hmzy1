package com.huiminsheng.app.net;

import android.app.Dialog;
import android.content.Context;

import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.R;
import com.huiminsheng.app.views.Post_diaog;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.lang.ref.WeakReference;

/**
 * 请求回调封装 返回1或-1的回调
 * @author Lyx
 * @date 2017/11/17
 */

public  class BaseResponseListener<T> implements OnResponseListener<Result<T>> {
    private final WeakReference<Context> weakReference;
    private BaseListener<T> listener;
    private MyRestRequest<T> myRestRequest;
    private Dialog dialog;
    BaseResponseListener(Context context, boolean isShowDiaog, BaseListener<T> listener, MyRestRequest<T> myRestRequest) {
        weakReference = new WeakReference<>(context);
        this.listener = listener;
        this.myRestRequest = myRestRequest;
        if (isShowDiaog){
            this.dialog = new Post_diaog(context);
        }
    }

    @Override
    public void onStart(int what) {
        if (dialog!=null){
            dialog.show();
        }
        if (listener!=null){
            listener.onStart(what);
        }
    }

    @Override
    public void onSucceed(int what, Response<Result<T>> response) {

        int status = response.get().getStatus();
        if (status==10000|status==-1){
            //成功  -1时有可能为无数据 或列表页的没有更多  所以这里归类为succeed 让各页面处理
           listener.onSucceed(what,response.get());
        }else if (status ==123){
            //解析错误
            ToastUtils.showToast(weakReference.get(), R.string.error_view_hint);
            //提交给友盟
//            MobclickAgent.reportError(weakReference.get(),response.get().getMsg());
        }else if (status==110){
            //异地处理
//            Panduanzt.yidizx(weakReference.get(),String.valueOf(response.get()));
        }else {
            //其他异常
            ToastUtils.showToast(weakReference.get(),response.get().getMsg());
        }
    }

    @Override
    public void onFailed(int what, Response<Result<T>> response) {
        if (!myRestRequest.isCanceled()){
            if (weakReference.get()!=null) {
                ToastUtils.showToast(weakReference.get(), weakReference.get().getString(R.string.no_network_view_hint));
            }
            if (listener!=null){
                listener.onFailed(what);
            }
        }
    }

    @Override
    public void onFinish(int what) {
        if (dialog!=null){
            dialog.dismiss();
        }
       if (!myRestRequest.isCanceled()){
           listener.onFinish(what);
       }
    }



}
