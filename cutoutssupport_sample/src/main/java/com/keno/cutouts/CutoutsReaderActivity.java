package com.keno.cutouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

/**
 * Description: 阅读器留海屏适配
 * Author: keno
 * CreateDate: 2020/7/29 21:49
 */
public class CutoutsReaderActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView tvContent;
    private TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, CutoutsReaderActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cutouts_reader);

        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            initOutcut();

            initWindowListener();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
        scrollView = findViewById(R.id.scrollview_reader);
        tvContent = findViewById(R.id.tv_content_reader);
        tvTitle = findViewById(R.id.tv_title_reader);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void initOutcut() {
        //设置LayoutParams为刘海屏模式
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().getDecorView().setLayoutParams(layoutParams);


        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(flags);

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void initWindowListener() {
        getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                DisplayCutout displayCutout = insets.getDisplayCutout();

                int top = displayCutout.getSafeInsetTop();
                int bottom = displayCutout.getSafeInsetBottom();
                int left = displayCutout.getSafeInsetLeft();
                int right = displayCutout.getSafeInsetRight();

                Log.i("displayCutoutReader", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + left);
                Log.i("displayCutoutReader", "安全区域距离屏幕右部的距离 SafeInsetRight:" + right);
                Log.i("displayCutoutReader", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + top);
                Log.i("displayCutoutReader", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + bottom);

//                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) scrollView.getLayoutParams();
//                layoutParams.setMargins(left, top, right, bottom);
                scrollView.setPadding(left, top, right, bottom);

                List<Rect> boundingList = displayCutout.getBoundingRects();
                if (!boundingList.isEmpty()) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) tvTitle.getLayoutParams();
                    //获取留海区域
                    Rect boundRect = boundingList.get(0);
                    if (top > 0) {
                        //竖屏在顶部
                        tvTitle.setWidth(boundRect.left);
                        tvTitle.setHeight(boundRect.bottom);
                        tvTitle.setGravity(Gravity.CENTER_VERTICAL);
                    } else if (bottom > 0) {

                    }

                }

                return insets.consumeDisplayCutout();
            }
        });
    }
}