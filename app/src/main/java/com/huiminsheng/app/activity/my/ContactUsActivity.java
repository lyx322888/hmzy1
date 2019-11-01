package com.huiminsheng.app.activity.my;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanConnectionInfo;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.CustomPopWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//联系我们
public class ContactUsActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.iv_officialaccount)
    ImageView ivOfficialaccount;
    @BindView(R.id.layout_card)
    CardView layoutCard;
    @BindView(R.id.iv_wechatcode)
    ImageView ivWechatcode;
    @BindView(R.id.tv_zx)
    TextView tvZx;
    @BindView(R.id.tv_sm)
    TextView tvSm;
    private Activity activity;
    private CustomPopWindow mCustomPopWindow;
    private String officialUrl;
    private String wechatcodeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectus);
        ButterKnife.bind(this);
        activity = this;
        init();
    }

    public void init() {
        showLoading();
        getData();
        ivOfficialaccount.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!TextUtils.isEmpty(officialUrl)) {
                    showPopwindown(officialUrl);
                }
                return false;
            }
        });
        ivWechatcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!TextUtils.isEmpty(wechatcodeUrl)) {
                    showPopwindown(wechatcodeUrl);
                }
                return false;
            }
        });
    }

    private void getData() {
        NoGo.create(this)
                .setUrl(ApiUrls.MY.CONNETIONUS)
                .request(BeanConnectionInfo.class, new BaseListener<BeanConnectionInfo>() {
                    @Override
                    public void onSucceed(int what, Result<BeanConnectionInfo> result) {
                        showSuccess();
                        officialUrl = result.getResult().getData().getOfficial_img();
                        wechatcodeUrl = result.getResult().getData().getService_img();
                        ToolsImage.loader(activity, officialUrl, ivOfficialaccount);
                        ToolsImage.loader(activity, wechatcodeUrl, ivWechatcode);
                        tvZx.setText(result.getResult().getData().getOfficial_title());
                        tvSm.setText(result.getResult().getData().getService_title());
                    }
                });
    }

    // 显示pop
    private void showPopwindown(final String url) {
        //创建并显示popWindow
        View inflate = LayoutInflater.from(activity).inflate(R.layout.popwindowlayout_bottom, null, false);
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create()
                .showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
                    TextView tvQx = inflate.findViewById(R.id.quxiao);
                    TextView tvText1 = inflate.findViewById(R.id.text1);
                    TextView tvText2 = inflate.findViewById(R.id.text2);
                    tvText2.setVisibility(View.GONE);
                    tvText1.setText("保存二维码图片");
                    tvText1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            savePicture(url);
                            mCustomPopWindow.dissmiss();
            }
        });
        tvQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }

    //保存二维码
    private void savePicture(String url) {
        ToolsImage.savaUrl(activity, url);
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("联系我们");
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
