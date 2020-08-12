# 应用退到后台点击桌面图标引导页面重复启动问题

> 场景：打开应用 启动引导页A ，然后自动跳转首页B闭引导页A, 应用退到桌面后点击桌面图标会 会再次启动A-->B;我们希望能做到退到桌面时点击图标能直接打开首页B；

为了实现以上场景，我们需要在 引导页A 的onCreate生命周期方法中判断引导页A是否初次启动，是否栈内第一个Activity。
可以通过Activity的 **isTaskRoot()** 方法进行判断

isTaskRoot()系统源码如下：
```
/**java
 * Return whether this activity is the root of a task.  The root is the
 * first activity in a task.
 *
 * @return True if this is the root activity, else false.
 */
@Override
public boolean isTaskRoot() {
    try {
        return ActivityTaskManager.getService().getTaskForActivity(mToken, true) >= 0;
    } catch (RemoteException e) {
        return false;
    }
}
```

 - 判断引导页如果不是栈内第一个activity则直接关闭引导页
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.i("SplashActivity", "isTaskRoot :" + isTaskRoot());
    if (!isTaskRoot()) {
        // 如果不是栈内第一个activity则不重复启动
        finish();
        return;
    }
    setContentView(R.layout.activity_splash);
}
```

- 首页点击返回退到桌面 但不销毁页面
```java
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    //点击返回回到桌面
    if (keyCode == KeyEvent.KEYCODE_BACK) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
```

