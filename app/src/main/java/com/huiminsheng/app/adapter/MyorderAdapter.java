package com.huiminsheng.app.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huiminsheng.app.R;
import com.huiminsheng.app.bean.BeanMyOrder;
import com.huiminsheng.app.utils.SpannableStringUtils;
import com.huiminsheng.app.utils.ToolsImage;

import java.util.List;
//我的订单适配器
public class MyorderAdapter extends BaseQuickAdapter<BeanMyOrder.DataBean.ListBeanX, BaseViewHolder> {
    MyorderAdapter myorderAdapter;

    public MyorderAdapter(int layoutResId, @Nullable List<BeanMyOrder.DataBean.ListBeanX> data) {
        super(layoutResId, data);
        myorderAdapter = this;
    }

    @Override
    protected void convert(final BaseViewHolder helper, BeanMyOrder.DataBean.ListBeanX item) {
        TextView tvSum = helper.getView(R.id.tv_sum);
        tvSum.setText(SpannableStringUtils.getBuilder("共" + item.getGoods_count() + "件商品  合计:")
                .append("￥").create());
         helper.setText(R.id.tv_total_prices,item.getAll_price());
         //商品列表
        RecyclerView recyclerView = helper.getView(R.id.rv_myorder_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        MyorderAdapterInformation myorderAdapterInformation = new MyorderAdapterInformation(R.layout.adapter_myorder_item_item,item.getList());
        recyclerView.setAdapter(myorderAdapterInformation);
        myorderAdapterInformation.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击事件 传给父view
                myorderAdapter.getOnItemClickListener().onItemClick(myorderAdapter,helper.itemView,helper.getAdapterPosition());
            }
        });
        //状态
        TextView tvscdd = helper.getView(R.id.tv_scdd);
        TextView tvzf = helper.getView(R.id.tv_zf);
        helper.addOnClickListener(R.id.tv_scdd).addOnClickListener(R.id.tv_zf);
        switch (item.getStatus()){
            case "-1":
                //已取消
                helper.setText(R.id.tv_transaction_status,"已取消");
                tvscdd.setText("删除订单");
                tvscdd.setVisibility(View.VISIBLE);
                tvzf.setVisibility(View.GONE);
                break;
            case "0":
                //待付款
                helper.setText(R.id.tv_transaction_status,"等待买家付款");
                tvscdd.setText("取消订单");
                tvzf.setText("立即支付");
                tvscdd.setVisibility(View.VISIBLE);
                tvzf.setVisibility(View.VISIBLE);
                break;
            case "1":
                //待发货
                helper.setText(R.id.tv_transaction_status,"待发货");
                tvzf.setText("申请退款");
                tvscdd.setVisibility(View.GONE);
                tvzf.setVisibility(View.VISIBLE);
                break;
            case "2":
                //待收货
                helper.setText(R.id.tv_transaction_status,"待收货");
                tvscdd.setText("物流动态");
                tvzf.setText("确认收货");
                tvscdd.setVisibility(View.VISIBLE);
                tvzf.setVisibility(View.VISIBLE);
                break;
            case "3":
                //交易完成
                helper.setText(R.id.tv_transaction_status,"交易完成");
                tvscdd.setText("删除订单");
                tvscdd.setVisibility(View.VISIBLE);
                tvzf.setVisibility(View.GONE);
                break;
                default:
                    tvscdd.setVisibility(View.GONE);
                    tvzf.setVisibility(View.GONE);
                    break;
        }
    }

}
