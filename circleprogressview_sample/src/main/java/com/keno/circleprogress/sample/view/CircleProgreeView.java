package com.keno.circleprogress.sample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.keno.circleprogress.sample.R;

/**
 * Description:  自定义圆形进度控件
 * Author: keno
 * CreateDate: 2020/7/11 9:38
 */

public class CircleProgreeView extends View {

    private int mBackgroudColor;
    private int mForegroudColor;
    private int mRoundWidth;
    private int mShadowWidth; //圆弧阴影宽度
    private int mTextSize; //文字大小
    private int mTextColor; //文字颜色
    private boolean mShowText; //是否显示文字
    private float mProgress; //进度 0-100
    private float mStartAngel; //圆弧开始角度
    private Paint mPaint;

    public CircleProgreeView(Context context) {
        this(context, null);
    }

    public CircleProgreeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgreeView);
        mBackgroudColor = typedArray.getColor(R.styleable.CircleProgreeView_backgroudColor, Color.WHITE);
        mForegroudColor = typedArray.getColor(R.styleable.CircleProgreeView_foregroudColor, Color.RED);
        mRoundWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgreeView_roundWidth, 10);
        mProgress = typedArray.getFloat(R.styleable.CircleProgreeView_progress, 0);
        mStartAngel = typedArray.getFloat(R.styleable.CircleProgreeView_startAngel, 0);
        mShadowWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgreeView_shadowWidth, 0);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleProgreeView_textSize, 18);
        mTextColor = typedArray.getColor(R.styleable.CircleProgreeView_textColor, Color.BLACK);
        mShowText = typedArray.getBoolean(R.styleable.CircleProgreeView_showText, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制控件

        int center = getWidth() / 2; // 获取圆心的x坐标
        //半径 = 圆心（控件一半宽度） -  圆环宽度 - 阴影宽度
        int radius = center - mRoundWidth - mShadowWidth;// 半径
        mPaint.setStrokeWidth(mRoundWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心

        //1. 绘制背景圆环
        mPaint.setColor(mBackgroudColor);
        canvas.drawCircle(center, center, radius, mPaint);

        //2. 绘制圆弧
        mPaint.setColor(mForegroudColor);
        mPaint.setStrokeWidth(mRoundWidth + mShadowWidth);
        //定义圆弧的形状和大小
        //上下左右分别是
        int left = center - radius -mShadowWidth/2;
        int right = center + radius +mShadowWidth/2;
        int top = center - radius- mShadowWidth/2;
        int bottom = center + radius +mShadowWidth/2;
        RectF oval = new RectF(left, top, right, bottom);
        //根据比例计算圆弧扫过的比例
        float sweepAngle = mProgress * 360 / 100;
        //绘制指定的圆弧，该圆弧将按比例缩放以适合指定椭圆的内部。
        canvas.drawArc(oval, mStartAngel, sweepAngle, false, mPaint);

        //3. 绘制文字百分比
        if (mShowText) {
            String percentText = mProgress + "%";
            mPaint.setStrokeWidth(0);
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            mPaint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
            float textWidth = mPaint.measureText(percentText);
            float textHeigt = textWidth / percentText.length();
            canvas.drawText(percentText, center - textWidth / 2, center + textHeigt / 2, mPaint);
        }
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
//        postInvalidate();
    }

    public float getProgress() {
        return mProgress;
    }
}
