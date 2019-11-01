package com.huiminsheng.app.unify.loadsir.loadcallback;

import com.huiminsheng.app.R;
import com.kingja.loadsir.callback.Callback;
//网络加载错误
public class TimeoutCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_timeout;
    }
}
