package com.huiminsheng.app.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huiminsheng.app.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//自定义小键盘
public class KeyboardView extends LinearLayout implements View.OnClickListener {

    private OnKeyboardListener onKeyboardListener;
    private final View inflater;
    private TextView dian;

    public KeyboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context).inflate(R.layout.keyboardviewlayout, this);
        init();
    }

    private void init() {
        inflater.findViewById(R.id.tv_0).setOnClickListener(this);
        inflater.findViewById(R.id.tv_1).setOnClickListener(this);
        inflater.findViewById(R.id.tv_2).setOnClickListener(this);
        inflater.findViewById(R.id.tv_3).setOnClickListener(this);
        inflater.findViewById(R.id.tv_4).setOnClickListener(this);
        inflater.findViewById(R.id.tv_5).setOnClickListener(this);
        inflater.findViewById(R.id.tv_6).setOnClickListener(this);
        inflater.findViewById(R.id.tv_7).setOnClickListener(this);
        inflater.findViewById(R.id.tv_8).setOnClickListener(this);
        inflater.findViewById(R.id.tv_9).setOnClickListener(this);
        ImageView delete = inflater.findViewById(R.id.delete);
        dian = inflater.findViewById(R.id.tv_dian);
        dian.setOnClickListener(this);
        delete.setOnClickListener(this);

    }
    //显示小数点
    public void showDecimalPoint( ){
        dian.setVisibility(VISIBLE);
    }

    public void addKeyboardListener(OnKeyboardListener onKeyboardListener){
        this.onKeyboardListener = onKeyboardListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
            case R.id.tv_2:
            case R.id.tv_3:
            case R.id.tv_4:
            case R.id.tv_5:
            case R.id.tv_6:
            case R.id.tv_7:
            case R.id.tv_8:
            case R.id.tv_9:
            case R.id.tv_0:
            case R.id.tv_dian:
                if (onKeyboardListener!=null) {
                    TextView textView = (TextView) v;
                    onKeyboardListener.outputNumber(textView.getText().toString());
                }
                break;
            case R.id.delete:
                if (onKeyboardListener!=null) {
                    onKeyboardListener.outputDelete();
                }
                break;
        }
    }

    public interface OnKeyboardListener{
        void outputNumber(String number);
        void outputDelete();
    }
}
