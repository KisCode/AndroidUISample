package demo.kiscode.start.opt;

import android.app.Application;
import android.os.Debug;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:应用启动优化App
 * 1.adb命令号查看启动耗时： adb shell am start -S -W demo.kiscode.start.opt/demo.kiscode.start.opt.StartOptMainActivity
 * 2.通过Debug.startMethodTracing 追踪指定方法块执行耗时
 * Author: kanjianxiong
 * Date : 2020/9/9 11:35
 **/
public class OptApplication extends Application {
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

        List<String> list = new ArrayList<>();
        for (String string : list) {

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