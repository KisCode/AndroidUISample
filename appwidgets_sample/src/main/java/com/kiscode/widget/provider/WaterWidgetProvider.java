package com.kiscode.widget.provider;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.kiscode.widget.R;
import com.kiscode.widget.WaterActivity;
import com.kiscode.widget.manager.WatterManager;

/**
 * 喝水小组件.
 * 主要展示了：
 * 1. 桌面小组件的添加配置
 * 2. 小组件点击交互事件(更新组件、打开应用)
 */
public class WaterWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "WaterWidgetProvider";
    private static final String ACTION_PLUS = "ACTION_PLUS";
    private static final String ACTION_REDUCE = "ACTION_REDUCE";


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        //接收广播实现
        Log.i(TAG, "onReceive:" + intent.getAction());

        //根据广播 刷新页面显示内容
        if (ACTION_PLUS.equals(intent.getAction()) || ACTION_REDUCE.equals(intent.getAction())) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_water_widget);
            if (ACTION_PLUS.equals(intent.getAction())) {
                remoteViews.setTextViewText(R.id.tv_content, context.getString(R.string.text_watter_count, WatterManager.plus()));
            }
            if (ACTION_REDUCE.equals(intent.getAction())) {
                remoteViews.setTextViewText(R.id.tv_content, context.getString(R.string.text_watter_count, WatterManager.reduce()));
            }

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, WaterWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        // 首次添加桌面组件以及调整组件大小时回调，可以根据用户调整的大小来控制显示的内容。
        Log.i(TAG, "onAppWidgetOptionsChanged 首次添加桌面组件以及调整组件大小时回调，可以根据用户调整的大小来控制显示的内容。");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        super.onUpdate(context, appWidgetManager, appWidgetIds);
        // 以AppWidgetProviderInfo中设置的updatePeriodMillis为间隔，周期性的回调此方法。
        // 用户添加小组件时也会回调此方法。
        Log.i(TAG, "onUpdate 用户添加小组件时也会回调此方法");

        // Perform this loop procedure for each widget that belongs to this
        // provider.
        for (int i = 0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];
            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, WaterActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    /* context = */ context,
                    /* requestCode = */ 0,
                    /* intent = */ intent,
                    /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            // Get the layout for the widget and attach an onClick listener to
            // the button.
            //点击+号挑战页面
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_water_widget);
            views.setOnClickPendingIntent(R.id.btn_open, pendingIntent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                Intent plusIntent = new Intent(context, WaterWidgetProvider.class);
                plusIntent.setAction(ACTION_PLUS);
                PendingIntent plusPendingIntent = PendingIntent.getBroadcast(context, 0, plusIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                views.setOnClickPendingIntent(R.id.btn_plus, plusPendingIntent);


                Intent reduceIntent = new Intent(context, WaterWidgetProvider.class);
                reduceIntent.setAction(ACTION_REDUCE);
                PendingIntent reducePendingIntent = PendingIntent.getBroadcast(context, 0, reduceIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                views.setOnClickPendingIntent(R.id.btn_reduce, reducePendingIntent);
            }

            // Tell the AppWidgetManager to perform an update on the current app
            // widget.
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        // 当小组件被移除时回调此方法。
        Log.i(TAG, "onDeleted 当小组件被移除时回调此方法");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        // 当小组件的所有实例都被移除时回调此方法，适合执行释放缓存代码。
        Log.i(TAG, "onDisabled 当小组件的所有实例都被移除时回调此方法，适合执行释放缓存代码。");
    }
}
