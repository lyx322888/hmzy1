package com.huiminsheng.app.unify.loadsir.loadcallback;

import com.huiminsheng.app.R;
import com.kingja.loadsir.callback.Callback;
//加载中
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }
}
