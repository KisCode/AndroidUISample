package com.kiscode.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

import com.kiscode.widget.provider.ClockWidgetProvider;
import com.kiscode.widget.provider.WaterWidgetProvider;
import com.kiscode.widget.service.ClockWork;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button btnAddWaterWidget, btnAddClockWidget, btnDoWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
    }

    private void initViews() {
        btnAddWaterWidget = findViewById(R.id.btn_add_watter_widget);
        btnAddWaterWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWaterWidget();
            }
        });


        btnAddClockWidget = findViewById(R.id.btn_add_clock_widget);
        btnAddClockWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClockWidget();
            }
        });


        btnDoWork = findViewById(R.id.btn_do_work);
        btnDoWork.setOnClickListener(v -> doWork());
    }

    //添加桌面小挂件
    @SuppressLint("WrongConstant")
    private void addWaterWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName componentName = new ComponentName(this, WaterWidgetProvider.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!appWidgetManager.isRequestPinAppWidgetSupported()) {
                Toast.makeText(this, "Not support Request Pin AppWidget", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, WaterWidgetProvider.class);
            int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ?
                    PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, intent, flags);
            appWidgetManager.requestPinAppWidget(componentName, null, successCallback);
        }
    }


    //添加桌面小挂件
    @SuppressLint("WrongConstant")
    private void addClockWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName componentName = new ComponentName(this, ClockWidgetProvider.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!appWidgetManager.isRequestPinAppWidgetSupported()) {
                Toast.makeText(this, "Not support Request Pin AppWidget", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, WaterWidgetProvider.class);
            int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ?
                    PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, intent, flags);
            appWidgetManager.requestPinAppWidget(componentName, null, successCallback);
        }
    }


    //添加桌面小挂件
    private void doWork() {
        //定义触发条件
        Constraints constraints = new Constraints.Builder()
                //NetworkType.NOT REQUIRED: 对网络没有要求
                //NetworkType.CONNECTED: 网络连接的时候执行
                //NetworkType.UNMETERED:环计费的网络比如WIFI下执行
                //NetworkType.NOT ROAMING:非漫游网络状态执行
                //NetworkType.METERED: 计费网络比如3G，4G下执行
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();

        // 配置一次性任务
//        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ClockWork.class)
//                .setConstraints(constraints)
//                .build();

        //时间间隔20分钟
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(ClockWork.class,20, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();

        //提交任务执行
        WorkManager workManager = WorkManager.getInstance(this);
//        workManager.enqueue(workRequest);
        workManager.enqueueUniquePeriodicWork("ptask", ExistingPeriodicWorkPolicy.KEEP, workRequest);

        //监听
        workManager.getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        Log.i("onChanged", "onChanged:" + workInfo.toString());
                    }
                });
    }
}