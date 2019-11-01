package com.huiminsheng.app.activity.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.zxing.WriterException;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.MainActivity;
import com.huiminsheng.app.adapter.ImageSynthesisAdapter;
import com.huiminsheng.app.adapter.ImageSynthesisOptionAdapter;
import com.huiminsheng.app.base.BaseActivity;
import com.huiminsheng.app.bean.BeanImageSynthesis;
import com.huiminsheng.app.net.ApiUrls;
import com.huiminsheng.app.unify.PopupWindowGather;
import com.huiminsheng.app.unify.listener.AffirmPopListener;
import com.huiminsheng.app.utils.EncodingHandler;
import com.huiminsheng.app.utils.FormatUtils;
import com.huiminsheng.app.utils.SPUtils;
import com.huiminsheng.app.utils.ToastUtils;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.utils.ToolsSize;
import com.huiminsheng.app.views.CustomPopWindow;
import com.huiminsheng.app.views.MyImagView_yuan;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.ArrayList;

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

//图片合成
public class ImageSynthesisActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.lv_code_bc)
    ImageView lvCodeBc;
    @BindView(R.id.rv_code)
    RecyclerView rvCode;
    @BindView(R.id.rv_code_multiplechoice)
    RecyclerView rvCodeMultiplechoice;
    @BindView(R.id.fl_image_synthesis)
    FrameLayout flImageSynthesis;
    @BindView(R.id.lv_code)
    ImageView lvCode;
    @BindView(R.id.lv_tx)
    MyImagView_yuan lvTx;
    @BindView(R.id.tv_nc)
    TextView tvNc;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_dj)
    TextView tvDj;
    @BindView(R.id.tv_dh)
    TextView tvDh;
    @BindView(R.id.lv_code_bottom)
    ImageView lvCodeBottom;
    @BindView(R.id.ll_code_bc)
    LinearLayout llCodeBc;
    private ArrayList<String> arrayList;
    private String url;
    private String share_desc;
    private String msg;
    private ImageSynthesisAdapter imageSynthesisAdapter;
    private ImageSynthesisOptionAdapter imageSynthesisOptionAdapter;

    //内部提供跳入
    public static void jumpActivity(Activity activity, String url,String share_desc,String msg, ArrayList<String> arrayList) {
        Intent intent = new Intent(activity, ImageSynthesisActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("share_desc", share_desc);
        intent.putExtra("msg", msg);
        intent.putStringArrayListExtra("arrayList", arrayList);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_synthesis);
        ButterKnife.bind(this);
        init();
    }

    //初始化
    private void init() {
        arrayList = getIntent().getStringArrayListExtra("arrayList");
        url = getIntent().getStringExtra("url");
        msg = getIntent().getStringExtra("msg");
        share_desc = getIntent().getStringExtra("share_desc");
        initAdapter();
        //重新设置图片尺寸
        lvCodeBc.post(new Runnable() {
            @Override
            public void run() {
                //图片

//                final RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) flImageSynthesis.getLayoutParams();
//                linearParams.width = (int) (lvCodeBc.getHeight() * 0.6);
//                flImageSynthesis.setLayoutParams(linearParams);
                ToolsImage.loader(mActivity, arrayList.get(0), lvCodeBc,R.mipmap.mr_fx);
                //二维码位置
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.height =params.width = (int) ( lvCodeBc.getHeight()*0.13856);
                params.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
                params.bottomMargin = (int) (flImageSynthesis.getHeight()*0.0739);
                lvCode.setLayoutParams(params);

                //自定义海报 适配
                int ll_w = lvCodeBc.getWidth()+1;
                int h = (int) (lvCodeBc.getHeight()*0.225122);

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ll_w, h);
                layoutParams.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
                llCodeBc.setLayoutParams(layoutParams);

                lvTx.getLayoutParams().width = layoutParams.height/2;
                lvTx.getLayoutParams().height = layoutParams.height/2;

                lvCodeBottom.getLayoutParams().height = layoutParams.height/2;
                lvCodeBottom.getLayoutParams().width = layoutParams.height/2;
            }
        });

        //生成二维码
        getCodeBitmap();
        //填充信息
        String txUlr = (String) SPUtils.get(mActivity, ApiUrls.KEY.HEADPIC,"");
        if (!TextUtils.isEmpty(txUlr)){
            ToolsImage.loaderYt(mActivity, txUlr,lvTx);
        }
        tvNc.setText("昵称:"+(String) SPUtils.get(mActivity, ApiUrls.KEY.NICK_NAME,""));
        tvName.setText("姓名:"+(String) SPUtils.get(mActivity, ApiUrls.KEY.NAME,""));
        tvDj.setText("等级:"+(String) SPUtils.get(mActivity, ApiUrls.KEY.LEVELNAME,""));
        tvDh.setText("电话:"+(String) SPUtils.get(mActivity, ApiUrls.KEY.PHONE,""));
    }

    // 初始化适配器
    private void initAdapter() {
        //单选
        ArrayList<BeanImageSynthesis> beanImageSynthesisArrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            BeanImageSynthesis beanImageSynthesis = new BeanImageSynthesis();
            beanImageSynthesis.content = String.valueOf(i + 1);
            if (i == 0) {
                beanImageSynthesis.isChecked = true;
            }
            beanImageSynthesisArrayList.add(beanImageSynthesis);
        }
        imageSynthesisAdapter = new ImageSynthesisAdapter(R.layout.code_cont_item, beanImageSynthesisArrayList);
        rvCode.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvCode);
        rvCode.setAdapter(imageSynthesisAdapter);
        imageSynthesisAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                imageSynthesisAdapter.Checked(position);
                //生成图片
                ToolsImage.loader(mActivity, arrayList.get(position), lvCodeBc,R.mipmap.mr_fx);
            }
        });
        //下面菜单多选
        ArrayList<BeanImageSynthesis> beanImageSynthesisOptionArrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BeanImageSynthesis beanImageSynthesis = new BeanImageSynthesis();
            switch (i) {
                case 0:
                    beanImageSynthesis.content = "昵称";
                    break;
                case 1:
                    beanImageSynthesis.content = "姓名";
                    break;
                case 2:
                    beanImageSynthesis.content = "电话";
                    break;
                case 3:
                    beanImageSynthesis.content = "等级";
                    break;
                default:
                    break;
            }
            beanImageSynthesisOptionArrayList.add(beanImageSynthesis);
        }
        imageSynthesisOptionAdapter = new ImageSynthesisOptionAdapter(R.layout.code_option_item, beanImageSynthesisOptionArrayList);
        rvCodeMultiplechoice.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCodeMultiplechoice.setAdapter(imageSynthesisOptionAdapter);
        imageSynthesisOptionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                imageSynthesisOptionAdapter.Checked(position);
                imageSynthesis();
                switch (position){
                    case 0:
                        if (tvNc.getVisibility()==View.GONE){
                            tvNc.setVisibility(View.VISIBLE);
                        }else {
                            tvNc.setVisibility(View.GONE);
                        }
                        break;
                    case 1:
                        if (tvName.getVisibility()==View.GONE){
                            tvName.setVisibility(View.VISIBLE);
                        }else {
                            tvName.setVisibility(View.GONE);
                        }
                        break;
                    case 2:
                        if (tvDh.getVisibility()==View.GONE){
                            tvDh.setVisibility(View.VISIBLE);
                        }else {
                            tvDh.setVisibility(View.GONE);
                        }
                        break;
                    case 3:
                        if (tvDj.getVisibility()==View.GONE){
                            tvDj.setVisibility(View.VISIBLE);
                        }else {
                            tvDj.setVisibility(View.GONE);
                        }
                        break;
                }
            }
        });
    }
    //图片合成
    public void imageSynthesis() {
        if (imageSynthesisOptionAdapter.getCheckedList().size() == 0) {
            //没有添加自定义
            llCodeBc.setVisibility(View.GONE);
            lvCode.setVisibility(View.VISIBLE);
        } else {
            //有选择自定义项
            llCodeBc.setVisibility(View.VISIBLE);
            lvCode.setVisibility(View.GONE);
        }
    }
    //生成二维码
    private void getCodeBitmap() {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                    emitter.onNext(EncodingHandler.createQRCodeNoPading(url, 800));
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
                lvCode.setImageBitmap(bitmap);
                lvCodeBottom.setImageBitmap(bitmap);
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
    @Override
    protected void setTitleView() {
        titleTitle.setText("惠民庄园");
    }
    @OnClick({R.id.title_back, R.id.tv_save, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_save:
                //保存图片
                showPop();
                break;
            case R.id.tv_share:
                share();
                break;
        }
    }
    //分享
    private void share(){
        final UMWeb umWeb = new UMWeb(url);
        UMImage thumb  = new UMImage(mActivity, R.mipmap.ic_hmzy);
        umWeb.setDescription(share_desc);
        umWeb.setTitle(msg);
        umWeb.setThumb(thumb);
        new ShareAction(mActivity)
                .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)//分享平台
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        new ShareAction(mActivity)
                                .setPlatform(share_media)
                                .withMedia(umWeb)
                                .setCallback(umShareListener)
                                .share();
                    }
                })//面板点击监听器
                .open();
    }
    //分享回调
    private  UMShareListener umShareListener = new UMShareListener() {
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
    //弹出再次确认
    private void showPop(){
        PopupWindowGather.getAffirmPop(mActivity, "您确定保存上面图片到手机吗？", new AffirmPopListener() {
            @Override
            public void onAffirmPopListener() {
                ToolsImage.saveView(mActivity, flImageSynthesis);
            }
        });
    }
    //分享回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
