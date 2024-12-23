package com.kiscode.widget.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

import com.kiscode.widget.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时钟小组件.
 * 主要展示了：小组件定时更新.
 */
public class ClockWidgetProvider extends AppWidgetProvider {

    private static final String TAG = "ClockWidgetProvider";
    private static final int JOB_ID = 1;
    private Context mContext;

    private static final int UPDATE_CLOCK = 1;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == UPDATE_CLOCK) {
                if (mContext != null) {
                    updateClock(mContext);
                }
                return true;
            }
            return false;
        }
    });

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
//        mContext = context;
//        Log.i(TAG, "onUpdate");
//        for (int appWidgetId : appWidgetIds) {
//            updateWidget(context, appWidgetId);
//        }

        Log.i(TAG, "onUpdate");
        updateClock(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i(TAG, "onEnabled");
//        startClockUpdate(context);


//        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(context, ClockUpdateJobService.class));
//        // 设置条件，例如设备充电时更新
//        builder.setRequiresCharging(true);
//        // 可以设置网络条件等其他条件
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//        builder.setPeriodic(2000);
//        jobScheduler.schedule(builder.build());
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i(TAG, "onEnabled");
//        stopClockUpdate(context);
    }


    private void startClockUpdate(Context context) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateClock(context);
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void stopClockUpdate(Context context) {
        handler.removeCallbacksAndMessages(null);
    }

    private void updateWidget(Context context, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_clock_widget);
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views);
    }

    private void updateClock(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, ClockWidgetProvider.class));
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_clock_widget);
            views.setTextViewText(R.id.tv_clock, currentTime);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
