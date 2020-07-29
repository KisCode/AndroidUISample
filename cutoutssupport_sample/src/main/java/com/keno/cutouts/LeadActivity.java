package com.keno.cutouts;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Description: 引导页 实现留海屏全屏显示引导图片的效果
 * Author: keno
 * CreateDate: 2020/7/28 21:14
 */

public class LeadActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int DELAY_MILLIS = 1000;
    private int countDownTime = 10;

    private TextView tvTime;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tvTime.setText(getString(R.string.tip_count_down, countDownTime));
            if (countDownTime > 0) {
                countDownTime--;
                handler.postDelayed(this, DELAY_MILLIS);
            } else {
                CutOutsFullScreenActivity.start(LeadActivity.this);
                finish();
            }
        }
    };
    private TextView textView;
    private Button btnCutoutsFullscreen;
    private Button btnStoryCutouts;
    private Button btnCustomviewCutouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            initCutouts();
        }

        setContentView(R.layout.activity_lead);
        initView();
        /*
        startCountDown();  //开始倒计时
        */
    }

    /***
     * 当Activity 获得or失去焦点时触发
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("FocusChanged", "onWindowFocusChanged hasFocus:" + hasFocus);
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void initCutouts() {/*
        //通过WindowManager.LayoutParams 设置当前页面rootView 为全屏（内容区域显示到状态栏）
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().getDecorView().setLayoutParams(layoutParams);
*/

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

    private void initView() {
        tvTime = findViewById(R.id.tv_time);
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);
        btnCutoutsFullscreen = (Button) findViewById(R.id.btn_cutouts_fullscreen);
        btnCutoutsFullscreen.setOnClickListener(this);
        btnStoryCutouts = (Button) findViewById(R.id.btn_story_cutouts);
        btnStoryCutouts.setOnClickListener(this);
        btnCustomviewCutouts = (Button) findViewById(R.id.btn_customview_cutouts);
        btnCustomviewCutouts.setOnClickListener(this);
    }

    private void startCountDown() {
        handler.postDelayed(runnable, DELAY_MILLIS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cutouts_fullscreen:
                //Cutouts Full Screen 通过代码实现全面适配
                CutOutsFullScreenActivity.start(this);
                finish();
                break;
            case R.id.btn_story_cutouts:
                //实现一个小说阅读器的全屏页面，并将小说标题强制写入 状态栏区域
                CutoutsReaderActivity.start(this);
                break;
            case R.id.btn_customview_cutouts:
                //自定义布局 适配留海屏 旋转
                CustomLayoutRotateActivity.start(this);
                finish();
                break;
        }
    }
}