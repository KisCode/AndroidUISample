package com.keno.cutouts;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
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
public class CutOutsSupportSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            initCutouts();
        }

        setContentView(R.layout.activity_cut_outs_support_sample);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getDisplayCutouts();
            getNotchParams();
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void getDisplayCutouts() {
        View decorView = getWindow().getDecorView();
        if (decorView == null) {
            return;
        }

        // 官方建议：避免对状态栏高度进行硬编码，因为这样做可能会导致内容重叠或被切断。如有可能，请使用 WindowInsetsCompat 检索状态栏高度，并确定要对您的内容应用的适当内边距
        WindowInsets windowInsets = decorView.getRootWindowInsets();
        if (windowInsets == null) {
            return;
        }

        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        if (displayCutout == null) {
            return;
        }
        //getBoundingRects返回List<Rect>,没一个list表示一个不可显示的区域，即刘海屏，可以遍历这个list中的Rect,
        //即可以获得每一个刘海屏的坐标位置,当然你也可以用类似getSafeInsetBottom的api
        Log.d("displayCutout", "**controlView**" + displayCutout.getBoundingRects());
        Log.d("displayCutout", "**controlView**" + displayCutout.getSafeInsetBottom());
        Log.d("displayCutout", "**controlView**" + displayCutout.getSafeInsetLeft());
        Log.d("displayCutout", "**controlView**" + displayCutout.getSafeInsetRight());
        Log.d("displayCutout", "**controlView**" + displayCutout.getSafeInsetTop());
    }

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
