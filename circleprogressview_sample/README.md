> 自定义圆形进度控件CircleView，支持自定义控件背景颜色、进度颜色、宽度、阴影、开始角度

![https://github.com/KisCode/AndroidUISample/blob/develop_androidx/circleprogressview_sample/image/circleImage.png](https://github.com/KisCode/AndroidUISample/blob/develop_androidx/circleprogressview_sample/image/circleImage.png)


###### 1. 声明控件属性

```xml
<!-- 圆环进度控件声明属性  -->
<declare-styleable name="CircleProgreeView">
    <!--   圆环背景颜色     -->
    <attr name="backgroudColor" format="color" />
    <!--   圆环前景颜色，进度    -->
    <attr name="foregroudColor" format="color" />
    <!--   圆环宽度     -->
    <attr name="roundWidth" format="dimension" />
    <!--   圆环宽度     -->
    <attr name="progress" format="float" />
    <!--   圆弧开始角度     -->
    <attr name="startAngel" format="float" />
    <!--   圆弧阴影宽度     -->
    <attr name="shadowWidth" format="dimension" />
    <!--   百分比大小     -->
    <attr name="textSize" format="dimension" />
    <!--   百分比字体颜色     -->
    <attr name="textColor" format="color" />
    <!--   是否显示百分比字体     -->
    <attr name="showText" format="boolean" />

</declare-styleable>
```

###### 2.创建一个集成View的CircleView

```java
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
        /***
         * left指的是圆的最左到左屏幕的距离，
         * top是圆顶部到屏幕顶部的距离
         * right是圆最右侧到左侧屏幕巨鹿，
         * bottom是圆底部到屏幕顶部距离
         */
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

```

###### 3. 在xml布局中控件属性
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".CircleProgressViewSampleActivity">

    <com.keno.circleprogress.sample.view.CircleProgreeView
        android:id="@+id/progress_circular1"
        android:layout_width="120dp"
        android:layout_height="120dp"
        app:backgroudColor="@color/colorBgCircle"
        app:foregroudColor="@color/colorPrimary"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:progress="60"
        app:roundWidth="10dp"
        app:startAngel="90" />

    <com.keno.circleprogress.sample.view.CircleProgreeView
        android:id="@+id/progress_circular2"
        android:layout_width="120dp"
        android:layout_height="120dp"
        app:backgroudColor="@color/colorBgCircle"
        app:foregroudColor="@color/colorAccent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/progress_circular1"
        app:progress="10"
        app:roundWidth="20dp"
        app:startAngel="90" />

    <com.keno.circleprogress.sample.view.CircleProgreeView
        android:id="@+id/progress_circular3"
        android:layout_width="120dp"
        android:layout_height="120dp"
        app:backgroudColor="@color/colorBgCircle"
        app:foregroudColor="@color/colorPrimary"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/progress_circular2"
        app:progress="20"
        app:roundWidth="30dp"
        app:shadowWidth="2dp"/>

    <com.keno.circleprogress.sample.view.CircleProgreeView
        android:id="@+id/progress_circular"
        android:layout_width="160dp"
        android:layout_height="160dp"
        app:backgroudColor="@color/colorBgCircle"
        app:foregroudColor="@color/colorPrimary"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/progress_circular3"
        app:progress="30"
        app:roundWidth="10dp"
        app:shadowWidth="4dp"
        app:showText="true"
        app:startAngel="90"
        app:textColor="@color/colorPrimaryDark"
        app:textSize="24sp" />

    <Button
        android:id="@+id/btn_add"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="+"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/progress_circular" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
