package com.huiminsheng.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import java.util.Collection;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

//交易密码框
public class TransactionPasswordView extends android.support.v7.widget.AppCompatTextView {

    private int width;//view 宽
    private int height;//view 高
    //密码最大数位 默认为6
    int maxCount = 6;
    //每个格子开始的坐标
    private int divideLineWStartX;
    private int startX;
    private int startY;
    private int bottomLineLength;
    private RectF rectF = new RectF();
    //画笔
    private Paint borderPaint;
    private Paint divideLinePaint;
    //圆角
    private float rectAngle = 0;
    //小圆圈
    private int radius = 10;
    //边框颜色
    private int borderColor = Color.parseColor("#CCCCCC");
    //分割线颜色
    private int divideLineColor = Color.parseColor("#CCCCCC");
    //圆球颜色
    private int circleColor = Color.parseColor("#1D1E1F");

    private String password ="";
    private Paint circlePaint;
    private KeyboardView keyboardView;

    public TransactionPasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intPaint();
        if (getMaxLines()>10){
            setMaxLines(maxCount);
        }else {
            maxCount = getMaxLines();
        }
    }
    //初始化画笔
    private void intPaint() {
        //边框
        borderPaint = getPaint(3, Paint.Style.STROKE, borderColor);
        //分割线
        divideLinePaint = getPaint(2, Paint.Style.FILL, divideLineColor);
        //圆
        circlePaint = getPaint(5, Paint.Style.FILL, circleColor);
    }
    /**
     * 设置画笔
     *
     * @param strokeWidth 画笔宽度
     * @param style       画笔风格
     * @param color       画笔颜色
     * @return
     */
    private Paint getPaint(int strokeWidth, Paint.Style style, int color) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;

        divideLineWStartX = w / maxCount;

        startX = w / maxCount / 2; //第一个圆心的x坐标
        startY = h / 2; //第一个圆心的y坐标

        bottomLineLength = w / (maxCount + 2);

        rectF.set(0, 0, width, height);
    }

    @Override
    public int getMaxLines() {
        return super.getMaxLines();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画边框
        canvas.drawRoundRect(rectF, rectAngle, rectAngle, borderPaint);
        //通过循环画出每个分割线
        for (int i = 0; i < maxCount - 1; i++) {
            canvas.drawLine((i + 1) * divideLineWStartX,
                    0,
                    (i + 1) * divideLineWStartX,
                    height,
                    divideLinePaint);
        }

        drawPsdCircle(canvas);
//        super.onDraw(canvas);
    }
    /**
     * 画密码实心圆
     *
     * @param canvas
     */
    private void drawPsdCircle(Canvas canvas) {
        for (int i = 0; i < password.length(); i++) {
            canvas.drawCircle(startX + i * 2 * startX,
                    startY,
                    radius,
                    circlePaint);
        }
    }
    public void setPassword(String password){
        if (password.length()<=maxCount){
            this.password = password;
            setText(password);
            invalidate();
        }
    }
    //点击键盘
    public void addKeyboard(KeyboardView keyboardView){
        this.keyboardView = keyboardView;
        keyboardView.addKeyboardListener(new KeyboardView.OnKeyboardListener() {
            @Override
            public void outputNumber(String number) {
                setPassword(password+number);
            }

            @Override
            public void outputDelete() {
                if (password.length()!=0){
                    password = password.substring(0,password.length()-1);
                    setPassword(password);
                }else {
                    setPassword("");
                }
            }
        });
    }


}
