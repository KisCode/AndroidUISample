package keno.android.ui.sample.util;


import android.util.Log;

import keno.android.ui.sample.BuildConfig;

/**** @ProjectName: AndroidUISample
 * @Package: keno.android.ui.sample.util
 * @ClassName: LogUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/6 20:56
 * @UpdateUser: 更新者： 
 * @UpdateDate: 2020/1/6 20:56
 * @UpdateRemark: 更新说明： 
 * @Version: 1.0
 */
public class LogUtils {
    private static final String TAG = "LogUtils";

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }


    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }


    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
