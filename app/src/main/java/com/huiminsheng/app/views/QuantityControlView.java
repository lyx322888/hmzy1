package com.huiminsheng.app.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuantityControlView extends LinearLayout {
    //最大数  默认99
    int maxcount = 5;
    int count = 1;
    private final View inflater;
    @BindView(R.id.tv_reduce)
    TextView tvReduce;
    @BindView(R.id.count)
    TextView tvCount;
    @BindView(R.id.tv_add)
    TextView tvAdd;


    public QuantityControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context).inflate(R.layout.quntitycontroview_layout, this);
        ButterKnife.bind(this, inflater);
    }

    public void setMaxcount(int maxcount){
        this.maxcount = maxcount;
    }
    public int getcount(){
        return count;
    }

    @OnClick({R.id.tv_reduce, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reduce:
                if (count>1){
                    count-=1;
                }else {
                    count=1;
                }
                tvCount.setText(String.valueOf(count));
                break;
            case R.id.tv_add:
                if (count<maxcount){
                    count+=1;
                }else {
                    count=maxcount;
                    ToastUtils.showToast(getContext(),"已达最大可购买商品数");
                }
                tvCount.setText(String.valueOf(count));
                break;
        }
    }
}
