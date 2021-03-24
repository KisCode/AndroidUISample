package com.kiscode.activity.single;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.kiscode.activity.BaseActionBarActivity;
import com.kiscode.activity.R;

/**
 * Description: Actvity 单例复用，确保任务栈内该activity单例，
 * 对于即将启动的Activity
 * 1. 如果当前任务栈内无对应Activity则新建
 * 2. 如果在当前任务栈内则直接从栈内拿出放在栈顶复用，并通过onNewIntent 解析参数
 * Author: keno
 * Date : 2021/3/24 10:36
 **/
public class AActivity extends BaseActionBarActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, AActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent in AActivity");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void next(View view) {
        BActivity.start(this);
    }
}