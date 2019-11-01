package com.huiminsheng.app.activity.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huiminsheng.app.R;
import com.huiminsheng.app.adapter.QrCodeAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.unify.EmptyViewUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//二维码推广
public class QrCodePromotionActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.rv_share_code)
    RecyclerView rvShareCode;
    private Activity activity;
    //内部提供跳入
    public static void  jumpActivity(Activity activity,ArrayList<String> arrayList){
        Intent intent = new Intent(activity,QrCodePromotionActivity.class);
        intent.putStringArrayListExtra("list",arrayList);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_promotion);
        ButterKnife.bind(this);
        activity = this;
        init();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("惠民庄园");
    }

    //初始化
    private void init() {
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        final QrCodeAdapter qrCodeAdapter = new QrCodeAdapter(R.layout.item_code_bg,new ArrayList<String>());
        rvShareCode.setLayoutManager(new GridLayoutManager(this,3));
        rvShareCode.setAdapter(qrCodeAdapter);
        View emptyView = EmptyViewUtils.getview(this, R.string.empty_k);
        qrCodeAdapter.setEmptyView(emptyView);
        qrCodeAdapter.setNewData(list);
        qrCodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
