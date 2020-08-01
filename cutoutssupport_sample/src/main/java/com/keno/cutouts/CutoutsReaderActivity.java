package com.keno.cutouts;

import android.content.Context;
import android.content.Intent;
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
                //获取刘海信息
                DisplayCutout displayCutout = insets.getDisplayCutout();

                int top = displayCutout.getSafeInsetTop();
                int bottom = displayCutout.getSafeInsetBottom();
                int left = displayCutout.getSafeInsetLeft();
                int right = displayCutout.getSafeInsetRight();

                //通过scrollView padding设置内容不显示在安全区域 确保scrollView不与安全区域重合
                scrollView.setPadding(left, top, right, bottom);

                //获取刘海上下左右位置，从而计算出刘海宽高
                List<Rect> boundingList = displayCutout.getBoundingRects();
                if (!boundingList.isEmpty()) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) scrollView.getLayoutParams();
                    //获取留海区域
                    Rect boundRect = boundingList.get(0);
                    //通过设置 scrollView和顶部标题间距
                    Log.i("displayRect", "left:" + boundRect.left + " top:" + boundRect.top + " right:" + boundRect.right + " bottom:" + boundRect.bottom);
                    if (top > 0) {
                        //竖屏在顶部
                        //标题强制显示在安全留海区域，且宽度为顶部留海区域左侧宽度
                        tvTitle.setWidth(boundRect.left);
                        tvTitle.setHeight(boundRect.bottom);
                        tvTitle.setGravity(Gravity.CENTER_VERTICAL);
                        layoutParams.setMargins(0, 0, 0, 0);
                    } else if (left > 0 || right > 0) {
                        //留海在左 or 在右
                        int height = tvTitle.getMeasuredHeight();
                        layoutParams.setMargins(0, height, 0, 0);
                    }
                }

                return insets.consumeDisplayCutout();
            }
        });
    }
}