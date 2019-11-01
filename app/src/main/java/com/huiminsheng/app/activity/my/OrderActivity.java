package com.huiminsheng.app.activity.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.fragment.OrderFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的订单
public class OrderActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.stl)
    SlidingTabLayout stl;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("我的订单");
    }
    //初始化
    private void init() {
        String[] title = new String[]{"全部","待付款","待发货","待收货","已完成"};
        //  status= 100全部 0代付款 1代发货 2待收货 3已完成
        String[] status = new String[]{"100","0","1","2","3"};
        Bundle bundle  = getIntent().getExtras();
        assert bundle != null;
        int count =bundle.getInt("count",0);
        final ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            OrderFragment orderFragment = new OrderFragment();
            Bundle bundlef = new Bundle();
            bundlef.putString("status",status[i]); //订单类型
            orderFragment.setArguments(bundlef);
            fragments.add(orderFragment);
        }
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        stl.setViewPager(viewPager,title);
        stl.setCurrentTab(count);

    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
