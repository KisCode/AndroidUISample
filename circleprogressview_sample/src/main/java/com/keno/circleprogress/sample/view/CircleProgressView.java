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
public class CircleProgressView extends View {

    private final int mBackgroundColor;
    private final int mForegroundColor;
    private final int mRoundWidth;
    private final int mShadowWidth; //圆弧阴影宽度
    private final int mTextSize; //文字大小
    private final int mTextColor; //文字颜色
    private final boolean mShowText; //是否显示文字
    private final float mStartAngel; //圆弧开始角度
    private final Paint mPaint;
    private float mProgress; //进度 0-100

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

        //获取控件属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        mBackgroundColor = typedArray.getColor(R.styleable.CircleProgressView_backgroundColor, Color.WHITE);
        mForegroundColor = typedArray.getColor(R.styleable.CircleProgressView_foregroundColor, Color.RED);
        mRoundWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_roundWidth, 10);
        mProgress = typedArray.getFloat(R.styleable.CircleProgressView_progress, 0);
        mStartAngel = typedArray.getFloat(R.styleable.CircleProgressView_startAngel, 0);
        mShadowWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_shadowWidth, 0);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_textSize, 18);
        mTextColor = typedArray.getColor(R.styleable.CircleProgressView_textColor, Color.BLACK);
        mShowText = typedArray.getBoolean(R.styleable.CircleProgressView_showText, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制控件
        int center = getWidth() / 2; // 获取圆心的x坐标
        //半径 = 圆心（控件一半宽度） -  圆环宽度 - 阴影宽度
        int radius = center - mRoundWidth - mShadowWidth;// 半径
        mPaint.setStrokeWidth(mRoundWidth); // 设置圆环的宽度 background
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心

        //1. 绘制背景圆环
        mPaint.setColor(mBackgroundColor);
        canvas.drawCircle(center, center, radius, mPaint);

        //2. 绘制圆弧 进度
        mPaint.setColor(mForegroundColor);
        mPaint.setStrokeWidth(mRoundWidth + mShadowWidth);
        //定义圆弧的形状和大小
        //上下左右分别是
        int left = center - radius - mShadowWidth / 2;
        int right = center + radius + mShadowWidth / 2;
        int top = center - radius - mShadowWidth / 2;
        int bottom = center + radius + mShadowWidth / 2;
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
            float textHeight = textWidth / percentText.length();
            canvas.drawText(percentText, center - textWidth / 2, center + textHeight / 2, mPaint);
        }
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }
}
