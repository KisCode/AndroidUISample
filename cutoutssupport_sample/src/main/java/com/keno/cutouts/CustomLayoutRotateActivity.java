package com.keno.cutouts;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Description: 自定义布局适配留海屏
 * 主要动态计算留海区域 WindowInsets.getDisplayCutout()，实现按钮等交互控件不被留海覆盖
 * Author: keno
 * Date : 2020/7/29 18:04
 **/
public class CustomLayoutRotateActivity extends AppCompatActivity {

    private Button btnTop;
    private Button btnLeft;
    private ConstraintLayout rootLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomLayoutRotateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_custom_layout_rotate);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            initCutouts();
            setWindowListener();
        }


    }

    private void initView() {
        btnTop = findViewById(R.id.btn_top);
        btnLeft = findViewById(R.id.btn_left);
        rootLayout = findViewById(R.id.root_layout_custom);


    }

    /***
     * 监听根布局，初始化或屏幕旋转时触发， 动态计算留海区位置，从而设置对应子控件的位置
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void setWindowListener() {
        rootLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                //获取留海区域
                DisplayCutout displayCutout = insets.getDisplayCutout();
                if (displayCutout != null) {
                    int top = displayCutout.getSafeInsetTop();
                    int bottom = displayCutout.getSafeInsetBottom();
                    int left = displayCutout.getSafeInsetLeft();
                    int right = displayCutout.getSafeInsetRight();

                    Log.i("displayCutout", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + left);
                    Log.i("displayCutout", "安全区域距离屏幕右部的距离 SafeInsetRight:" + right);
                    Log.i("displayCutout", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + top);
                    Log.i("displayCutout", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + bottom);
                    ConstraintLayout.LayoutParams topLayoutParams = (ConstraintLayout.LayoutParams) btnTop.getLayoutParams();
                    ConstraintLayout.LayoutParams leftLayoutParams = (ConstraintLayout.LayoutParams) btnLeft.getLayoutParams();
//                    topLayoutParams.setMargins(left, top, right, bottom);
//                    leftLayoutParams.setMargins(left, top, right, bottom);

                    if (top > 0) {
                        //
                        topLayoutParams.setMargins(0, top, 0, bottom);
                    } else if (left > 0 || right > 0) {
                        leftLayoutParams.setMargins(left, 0, right, 0);

                    }
                }
                return insets.consumeDisplayCutout();
            }
        });
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

        /*设置状态栏 沉浸式，内容真正融入状态栏内
         * Android 可能不允许内容视图与系统栏重叠。要替换此行为并强制内容延伸到刘海区域，请通过 View.setSystemUiVisibility(int) 方法将以下任一标志应用于视图可见性：
         * View.SYSTEM_UI_FLAG_LAYOUT_STABLE 配合View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
         * 为页面布局设置一个稳定的区域，如果底部存在软键盘，则内容区域显示在软键盘之上
         * */
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(flags);
    }
}
