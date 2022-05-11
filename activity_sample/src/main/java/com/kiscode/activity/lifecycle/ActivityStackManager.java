package com.kiscode.activity.lifecycle;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:  Activity任务栈管理，实现批量关闭所有Activity
 * Author: keno
 * Date : 2022/5/11 16:51
 **/
public class ActivityStackManager {
    private static List<Activity> mActivityStack = new ArrayList<Activity>();


    public static void add(Activity activity) {
        //移除后再添加，将activity数组最末，和Activity栈顺序一致
        mActivityStack.remove(activity);
        mActivityStack.add(activity);
    }

    public static void remove(Activity activity) {
        mActivityStack.remove(activity);
    }

    public static void closeAll() {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        while (mActivityStack.size() > 0) {
            Activity activity = mActivityStack.get(mActivityStack.size() - 1);
            remove(activity);

            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
} 