package com.kiscode.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kiscode.widget.provider.WaterWidgetProvider;

public class MainActivity extends AppCompatActivity {
    private Button btnAddWaterWidget;

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
    }

    //添加桌面小挂件
    @SuppressLint("WrongConstant")
    private void addWaterWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName componentName = new ComponentName(this, WaterWidgetProvider.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!appWidgetManager.isRequestPinAppWidgetSupported()) {
                Toast.makeText(this,"Not support Request Pin AppWidget",Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, WaterWidgetProvider.class);
            int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ?
                    PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, intent, flags);
            appWidgetManager.requestPinAppWidget(componentName, null, successCallback);
        }

    }
}