package com.huiminsheng.app.activity.my;

import android.graphics.Color;
import android.graphics.Picture;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.net.BaseListener;
import com.huiminsheng.app.net.NoGo;
import com.huiminsheng.app.net.Result;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.CustomPopWindow;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//微信二维码上传
public class WxQrcodeActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.complete_info_wxcode)
    ImageView completeInfoWxcode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    private String wxqrcoedeUrl="";//传过来的图片
    private String wxqrcoedepath="";//本地图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_qrcode);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("微信二维码");
    }

    //初始化
    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        wxqrcoedeUrl = bundle.getString("wxqrcoedeUrl","");
        if (!TextUtils.isEmpty(wxqrcoedeUrl)){
            ToolsImage.loader(mActivity, ApiUrls.UrlHead+wxqrcoedeUrl,completeInfoWxcode);
        }
    }

    @OnClick({R.id.title_back, R.id.complete_info_wxcode, R.id.tv_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.complete_info_wxcode:
                //选择要二维码图片
                showPop();
                break;
            case R.id.tv_code:
                //上传
                if (TextUtils.isEmpty(wxqrcoedepath)){
                    ToastUtils.showToast(mActivity,"请点击上方上传二维码");
                }else {
                    uploadPictures();
                }
                break;
        }
    }
    //弹窗
    private void showPop(){
        //创建并显示popWindow
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.popwindowlayout_bottom, null, false);
        final CustomPopWindow mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(inflate)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .create()
                .showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        TextView tvQx = inflate.findViewById(R.id.quxiao);
        TextView tvText1 = inflate.findViewById(R.id.text1);
        TextView tvText2 = inflate.findViewById(R.id.text2);
        tvText1.setText("选择图片");
        tvText2.setText("拍照");
        tvText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelection();
                mCustomPopWindow.dissmiss();
            }
        });
        tvText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
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
    //图片选择
    private void imageSelection(){
        Album.image(this)
                .multipleChoice()
                .widget( Widget.newDarkBuilder(this)
                        .title("选择图片") // Title.
                        .statusBarColor(ContextCompat.getColor(mActivity,R.color.theme))
                        .toolBarColor(ContextCompat.getColor(mActivity,R.color.theme)).build())// Toolbar color..build())// StatusBar color.)
                .camera(false)
                .columnCount(4)
                .selectCount(1)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        if (result.size()>0){
                            wxqrcoedepath = result.get(0).getPath();
                            ToolsImage.loader(mActivity,wxqrcoedepath,completeInfoWxcode);
                        }
                    }
                }).start();
    }
    //拍照
    private void takePicture(){
        Album.camera(this)
                .image()
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        wxqrcoedepath = result;
                        ToolsImage.loader(mActivity,wxqrcoedepath,completeInfoWxcode);
                    }
                })
                .start();
    }
    //上传图片
    private void uploadPictures(){
        NoGo.create(mActivity)
                .setUrl(ApiUrls.MY.UPWXCODE)
                .addFile("wxcode",new File(wxqrcoedepath))
                .showDialog()
                .requestString(new BaseListener<String>() {
                    @Override
                    public void onSucceed(int what, Result<String> result) {
                        ToastUtils.showToast(mActivity,result.getMsg());
                    }
                });
    }
}
