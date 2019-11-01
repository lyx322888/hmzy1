package com.huiminsheng.app.unify.loadsir.loadcallback;
import com.huiminsheng.app.R;
import com.kingja.loadsir.callback.Callback;

/**
 * 加载失败的页面回调
 */
public class ErrorCallback extends Callback{
    @Override
    protected int onCreateView() {
        return R.layout.layout_error;
    }

}
