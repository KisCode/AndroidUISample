package com.kiscode.activity.single;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.kiscode.activity.R;
import com.kiscode.activity.lifecycle.ActivityStackManager;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/24 10:36
 **/
public class BActivity extends Activity {
    private static final String TAG = "BActivity";
//    private ConfirmCloseDialogFragment confirmCloseDialog;

    private Button btnPrevious, btnNext, btnCloseAll;

    public static void start(Context context) {
        Intent intent = new Intent(context, BActivity.class);
        //即将启动的Activity 如果在当前任务栈内则直接从栈内拿出放在栈顶复用
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent in BActivity");
    }

    private void initView() {
        btnPrevious = findViewById(R.id.btn_previous_confirm);
        btnNext = findViewById(R.id.btn_next_confirm);
        btnCloseAll = findViewById(R.id.btn_close_all_confirm);

        btnNext.setOnClickListener(v -> {
            CActivity.start(this);
        });

        btnPrevious.setOnClickListener(v -> {
            finish();
        });

        btnCloseAll.setOnClickListener(v -> {
            ActivityStackManager.closeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });
    }

}