package com.kiscode.persistent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private int distanceTime = 5;
    private Button btnHomepage;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            distanceTime--;
            if (distanceTime == 0) {
                handler.removeCallbacks(this);
                toHome();
            } else {
                btnHomepage.setText(getString(R.string.cutdownTime, distanceTime));
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SplashActivity", "isTaskRoot :" + isTaskRoot());
        if (!isTaskRoot()) {
            /* isTaskRoot() 当前页面是启动栈内第一个activity
             Return whether this activity is the root of a task.  The root is the first activity in a task.
            */
            // 如果不是栈内第一个activity则不重复启动
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        initView();
        initCutDown();
    }

    private void initCutDown() {
        handler.post(runnable);
    }

    private void initView() {
        btnHomepage = findViewById(R.id.btn_homepage);

        btnHomepage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_homepage:
                toHome();
                break;
        }
    }

    private void toHome() {
        startActivity(new Intent(this, MainTabActivity.class));
        finish();
    }
}
