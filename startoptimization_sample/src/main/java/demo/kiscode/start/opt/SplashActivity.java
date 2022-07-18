package demo.kiscode.start.opt;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.ref.WeakReference;

/**
 * Description: 模拟开屏广告倒计时逻辑启动优化
 * 1. 冷启动显示默认占位图 并全屏显示
 * 2. 刘海屏适配
 * Author: keno
 * Date : 2022/7/18 16:19
 **/
public class SplashActivity extends AppCompatActivity {
    private static final int TIME_SKIP = 5000;
    private int remainingTime = TIME_SKIP;
    private Button btnSkip;
    private TimeHandler timeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initTimer();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            adaptCutouts();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initTimer() {
        Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                if (remainingTime <= 0) {
                    btnSkip.setText(getString(R.string.skip));
                } else {
                    btnSkip.setText(getString(R.string.remainingTime, remainingTime / 1000));
                    remainingTime -= 1000;
                    timeHandler.postDelayed(this, 1000);
                }
            }
        };
        timeHandler = new TimeHandler(this);
        timeHandler.post(timeRunnable);
    }

    private void initView() {
        btnSkip = findViewById(R.id.btnHome);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });
    }

    /***
     * 监听根布局，初始化或屏幕旋转时触发， 动态计算刘海区位置，从而设置对应子控件的位置
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void adaptCutouts() {
        btnSkip.getRootView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                //获取刘海区域
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
                    ConstraintLayout.LayoutParams topLayoutParams = (ConstraintLayout.LayoutParams) btnSkip.getLayoutParams();
                    topLayoutParams.setMargins(left, top, right, bottom);
                }
                return insets.consumeDisplayCutout();
            }
        });
    }


    private void toHome() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, StartOptMainActivity.class));
                finish();
            }
        });
    }

    private static class TimeHandler extends Handler {

        private WeakReference<SplashActivity> contextReference;

        public TimeHandler(SplashActivity context) {
            this.contextReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (contextReference == null || contextReference.get() == null) {
                return;
            }
            SplashActivity splashActivity = contextReference.get();
        }
    }


}