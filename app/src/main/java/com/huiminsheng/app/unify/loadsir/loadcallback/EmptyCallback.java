package com.huiminsheng.app.unify.loadsir.loadcallback;

import com.huiminsheng.app.R;
import com.kingja.loadsir.callback.Callback;

public class EmptyCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }
}
