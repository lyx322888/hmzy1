package com.huiminsheng.app.views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.huiminsheng.app.R;

//圆形可选择Texview
public class RoundedTextview extends android.support.v7.widget.AppCompatTextView {
    //是否选择
    private Context context;
    private boolean isChecked = false;
    //已选中的背景
    private int CheckedBD = R.drawable.shape_round_xz_textview;
    //未选中的背景
    private int WCheckedBD = R.drawable.shape_round_wxz_textview;
    //已选中的字体颜色
    private int CheckedTC = R.color.white;
    //未选中的字体颜色
    private int WCheckedTC = R.color.grey_999999;
    public RoundedTextview(Context context) {
        super(context);
        this.context = context;
        setChecked(false);
    }

    public RoundedTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setChecked(false);
    }

    public RoundedTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setChecked(false);
    }
    //设置选择状态
    public void setChecked(boolean isChecked ){
        this.isChecked = isChecked;
        setRoundedBackground();
    }

    public boolean isChecked(){
        return  isChecked;
    }

    //设置背景
    private void setRoundedBackground(){
        if (isChecked){
            setBackground(ContextCompat.getDrawable(context,CheckedBD));
            setTextColor(ContextCompat.getColor(context,CheckedTC));
        }else {
            setBackground(ContextCompat.getDrawable(context,WCheckedBD));
            setTextColor(ContextCompat.getColor(context,WCheckedTC));
        }
    }

    /**
     *
     * @param CheckedBD 选中时的背景
     * @param CheckedTC 选中时的字体颜色
     * @param WCheckedBD 未选中时的背景
     * @param WCheckedTC 未选中时的字体颜色
     */
    public void setTemplate(int CheckedBD,int CheckedTC,int WCheckedBD,int WCheckedTC){
        this.CheckedBD = CheckedBD;
        this.CheckedTC = CheckedTC;
        this.WCheckedBD = WCheckedBD;
        this.WCheckedTC = WCheckedTC;
    }
}
