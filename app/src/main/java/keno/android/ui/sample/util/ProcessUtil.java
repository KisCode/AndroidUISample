package keno.android.ui.sample.util;

import android.app.Application;
import android.os.Build;

import java.lang.reflect.Method;

public class ProcessUtil {
    /**
     * 获取当前进程名
     */
    public static String getCurrentProcessName() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //通过Application新的API获取进程名，无需反射，无需IPC，效率最高
            return Application.getProcessName();
        }
        return getCurrentProcessNameByActivityThread();
    }

    /**
     * 通过反射ActivityThread获取进程名
     */
    private static String getCurrentProcessNameByActivityThread() {
        String processName = null;
        try {
            final Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader())
                    .getDeclaredMethod("currentProcessName", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            final Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                processName = (String) invoke;
            }
        } catch (Throwable e) {
        }
        return processName;
    }
} 