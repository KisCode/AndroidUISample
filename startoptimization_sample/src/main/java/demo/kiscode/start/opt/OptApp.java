package demo.kiscode.start.opt;

import android.app.Application;

/**
 * Description:
 * Author: kanjianxiong
 * Date : 2020/9/9 11:35
 **/
public class OptApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        try {
            //模拟初始化耗时
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}