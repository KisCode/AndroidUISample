package demo.kiscode.start.opt;

import android.app.Application;
import android.os.Debug;
import android.util.Log;

import java.io.File;

/**
 * Description:应用启动优化App
 * 1.adb命令号查看启动耗时： adb shell am start -S -W demo.kiscode.start.opt/demo.kiscode.start.opt.StartOptMainActivity
 * 2.通过Debug.startMethodTracing 追踪指定方法块执行耗时
 * Author: kanjianxiong
 * Date : 2020/9/9 11:35
 **/
public class OptApplication extends Application {
    /*1.ADB命令监控应用启动
     adb shell am start -S -W packageName/activityName

结果：
    Stopping: demo.kiscode.start.opt
    Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=demo.kiscode.start.opt/.StartOptMainActivity }
    Status: ok
    LaunchState: COLD
    Activity: demo.kiscode.start.opt/.StartOptMainActivity
    TotalTime: 4520
    WaitTime: 4546
    Complete


    ThisTime:最后一个启动的Activity的启动耗时；
    TotalTime:自己的所有Activity的启动耗时，包括创建进程 + Application初始化 + Activity初始化到界面显示的过程；
    WaitTime: ActivityManagerService启动App的Activity时的总时间（包括当前Activity的onPause()和自己Activity的启动）
    */
    private static final String TAG = "OptApplication";


    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {
            init();
        } else {//追踪文件存储路径
            File file = new File(getExternalFilesDir(null), "application.trace");
            Log.i(TAG, file.getAbsolutePath());
            //开启追踪
            Debug.startMethodTracing(file.getAbsolutePath());
            init();
            //结束追踪
            Debug.stopMethodTracing();
        }
    }

    private void init() {
        initA();
        initB();
    }

    private void initA() {
        try {
            //模拟初始化耗时
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initC();
    }

    private void initB() {
        try {
            //模拟初始化耗时
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initC() {
        try {
            //模拟初始化耗时
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}