package com.keno.cutouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


/**
 * Description:  留海适配方案
 * 1. 官方方案 Andriod 9.0及以后
 * 2. 非官方方案 Android 9.0以前 各厂商不同设备适配
 * https://developer.android.google.cn/guide/topics/display-cutout
 * Author: keno
 * Date : 2020/7/28 19:31
 **/
public class CutOutsFullScreenActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CutOutsFullScreenActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //虚拟按键背景透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.activity_cutouts_fullscreen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            initCutouts();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getNotchParams();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /***
     * 设置全面屏适配
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void initCutouts() {
        /**
         * 1. WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 默认，内部不显示到留海区域
         * 2. WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 内部显示到留海区域
         * 3. WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不显示
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        View decorView = getWindow().getDecorView();
        decorView.setLayoutParams(layoutParams);
        //设置状态栏 沉浸式，内容真正融入状态栏内
        /*Android 可能不允许内容视图与系统栏重叠。要替换此行为并强制内容延伸到刘海区域，请通过 View.setSystemUiVisibility(int) 方法将以下任一标志应用于视图可见性：
        SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        SYSTEM_UI_FLAG_LAYOUT_STABLE*/
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        systemUiVisibility |= flags;
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);

    }

    /***
     * 获取留海参数设置
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void getNotchParams() {
        final View decorView = getWindow().getDecorView();

        decorView.post(new Runnable() {
            @Override
            public void run() {
                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                Log.i("displayCutout", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
                Log.i("displayCutout", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
                Log.i("displayCutout", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
                Log.i("displayCutout", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());

                List<Rect> rects = displayCutout.getBoundingRects();
                if (rects == null || rects.size() == 0) {
                    Log.i("displayCutout", "不是刘海屏");
                } else {
                    Log.i("displayCutout", "刘海屏数量:" + rects.size());
                    for (Rect rect : rects) {
                        Log.i("displayCutout", "刘海屏区域：" + rect);
                    }
                }
            }
        });
    }
}
