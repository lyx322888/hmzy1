package com.huiminsheng.app.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.activity.home.ProductDetailsActivity;
import com.huiminsheng.app.bean.BeanProductList;
import com.huiminsheng.app.utils.ToolsImage;
import com.huiminsheng.app.views.MyImagView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//商品列表
public class ProductListAdapter extends BaseQuickAdapter<BeanProductList.DataBean.ListBean, BaseViewHolder> {
    private boolean switchover_bag = true;//大图列表模式
    public ProductListAdapter(int layoutResId, @Nullable List<BeanProductList.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    public void setSwitchover_bag(boolean isswitchover_bag){
        switchover_bag = isswitchover_bag;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(final BaseViewHolder helper, final BeanProductList.DataBean.ListBean item) {
        LinearLayout productlist_ll_normal = helper.getView(R.id.productlist_ll_normal);
        LinearLayout productlist_ll = helper.getView(R.id.productlist_ll);
        if (switchover_bag){
            //大图模式
            productlist_ll_normal.setVisibility(View.VISIBLE);
            productlist_ll.setVisibility(View.GONE);
            MZBannerView banner_product = helper.getView(R.id.banner_product);
            banner_product.setPages(item.getImages(), new MZHolderCreator() {
                @Override
                public MZViewHolder createViewHolder() {
                    return new BannerViewHolderProduct(new BannerViewHolderProduct.Listener() {
                        @Override
                        public void OnclickListener() {
                            Bundle bundle = new Bundle();
//                    //商品id
                    bundle.putString("goods_id",getData().get(helper.getAdapterPosition()).getGoods_id());
                    ProductDetailsActivity.jumpActivity((Activity) mContext,ProductDetailsActivity.class,bundle);
                        }
                    });
                }
            });
//            banner_product.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
//                @Override
//                public void onPageClick(View view, int i) {
//                    Bundle bundle = new Bundle();
//                    //商品id
//                    bundle.putString("goods_id",getData().get(helper.getAdapterPosition()).getGoods_id());
//                    ProductDetailsActivity.jumpActivity((Activity) mContext,ProductDetailsActivity.class,bundle);
//                }
//            });
            helper.setText(R.id.tv_normal_title,item.getName());
            helper.setText(R.id.tv_normal_describe,item.getDesc());
            helper.setText(R.id.tv_normal_tag,item.getTag_name());
            helper.setText(R.id.tv_normal_jg,item.getPrice());
            helper.setText(R.id.tv_normal__yf,"预付"+item.getPrime()+"立即锁定爱车");
            helper.setText(R.id.tv_normal_zdj,"厂商价:"+item.getOri_price());
            TextView tv_zdj = helper.getView(R.id.tv_normal_zdj);
            tv_zdj.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

        }else {
            //xiao图模式
            productlist_ll_normal.setVisibility(View.GONE);
            productlist_ll.setVisibility(View.VISIBLE);

            ToolsImage.loader(mContext,item.getLogo(),(MyImagView) helper.getView(R.id.imgv_zscx_logo));
            //名字
            helper.setText(R.id.tv_zscx_name,item.getName());
            helper.setText(R.id.tv_zscx_desc,item.getDesc());
            helper.setText(R.id.tv_zscx_jg,item.getPrice());
            helper.setText(R.id.tv_zscx_tag,item.getTag_name());
            helper.setText(R.id.tv_zscx_zdj,"厂商价"+item.getOri_price());
            TextView tv_zdj = helper.getView(R.id.tv_zscx_zdj);
            tv_zdj.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            helper.setText(R.id.tv_zxcx_yf,"预付"+item.getPrime()+"立即锁定爱车");
        }
    }
}
