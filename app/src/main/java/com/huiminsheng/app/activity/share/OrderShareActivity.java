package com.huiminsheng.app.activity.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.R;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.utils.EncodingHandler;
import com.huiminsheng.app.utils.FormatUtils;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.utils.ToolsSize;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//商品分享
public class OrderShareActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_Right_tx)
    TextView titleRightTx;
    @BindView(R.id.lv_order)
    ImageView lvOrder;
    @BindView(R.id.tv_normal_title)
    TextView tvNormalTitle;
    @BindView(R.id.tv_normal_describe)
    TextView tvNormalDescribe;
    @BindView(R.id.tv_normal_zdj)
    TextView tvNormalZdj;
    @BindView(R.id.tv_normal_jg)
    TextView tvNormalJg;
    @BindView(R.id.tv_normal_tag)
    TextView tvNormalTag;
    @BindView(R.id.lv_qr_code)
    ImageView lvQrCode;
    @BindView(R.id.ll_ewm)
    LinearLayout llEwm;
    @BindView(R.id.ll_wx)
    LinearLayout llWx;
    @BindView(R.id.ll_pyq)
    LinearLayout llPyq;
    @BindView(R.id.lv_qq)
    LinearLayout lvQq;
    @BindView(R.id.ll_copy)
    LinearLayout llCopy;
    @BindView(R.id.ll_share_content)
    LinearLayout llShareContent;
    private String name = "";
    private String desc = "";
    private String share_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_share);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void setTitleView() {
        titleTitle.setText("创建分享");
        titleRightTx.setText("保存");
        titleRightTx.setVisibility(View.VISIBLE);
    }
    //初始化
    private void init() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String logo = bundle.getString("logo","");
        name  =  bundle.getString("name");
        String tag_name = bundle.getString("tag_name","");
        String ori_price = bundle.getString("ori_price","");
        String price = bundle.getString("price","");
        desc  =  bundle.getString("desc");
        share_url  =  bundle.getString("share_url");

        tvNormalTitle.setText(name);
        tvNormalDescribe.setText(desc);
        tvNormalZdj.setText(SpannableStringUtils.getBuilder("厂商价:").append(ori_price).create().toString());
        tvNormalZdj.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        tvNormalJg.setText(price);
        tvNormalTag.setText(tag_name);
        Glide.with(mActivity).load(logo).placeholder(R.mipmap.mrbc).error(R.mipmap.mrbc).bitmapTransform(new RoundedCornersTransformation(mActivity, ToolsSize.getSize(4), 0,
                RoundedCornersTransformation.CornerType.TOP)).into(lvOrder );
        //生成二维码
        getCodeBitmap();
    }
    //生成二维码
    private void getCodeBitmap() {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                emitter.onNext(EncodingHandler.createQRCodeNoPading(share_url, 800));
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        lvQrCode.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(mActivity, "抱歉!二维码出错啦");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @OnClick({R.id.title_back, R.id.title_Right_tx, R.id.ll_wx, R.id.ll_pyq, R.id.lv_qq, R.id.ll_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_Right_tx:
                //保存图片
                PopupWindowGather.getAffirmPop(mActivity, "您确定保存图片到手机吗？", new AffirmPopListener() {
                    @Override
                    public void onAffirmPopListener() {
                        ToolsImage.saveView(mActivity, llShareContent);
                    }
                });
                break;
            case R.id.ll_wx:
                share(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.ll_pyq:
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.lv_qq:
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.ll_copy:
                //复制
                FormatUtils.copy(mActivity,share_url,"已复制");
                break;
        }
    }
    //分享
    public void share(SHARE_MEDIA share_media){
        final UMWeb umWeb = new UMWeb(share_url);
        UMImage thumb  = new UMImage(mActivity, R.mipmap.ic_hmzy);
        umWeb.setDescription(desc);
        umWeb.setTitle(name);
        umWeb.setThumb(thumb);
        new ShareAction(mActivity)
                .setPlatform(share_media)
                .withMedia(umWeb)
                .setCallback(umShareListener)
                .share();
    }
    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity, " 收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity, " 分享成功", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable throwable) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST
                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity, " 分享失败啦,请查看是否安装相关应用", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
    //分享回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
