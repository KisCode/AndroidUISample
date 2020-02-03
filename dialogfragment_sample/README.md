# DialogFragment

## 概述
在Android 3.0后，Google官方推荐使用DialogFragment替代Dialog
优点：
1. 具有Fragment的方便的生命周期管理
2. 旋转屏幕时更好的适配（如一个包含EditText输入对话框传统Dialog 输入内容后旋转屏幕后，输入内容消失且后台会打印崩溃日志，Activity销毁时不允许对话框未关闭
后台崩溃日志：
```
android.view.WindowLeaked: Activity com.keno.dialogfragment.sample.DialogFragmentSampleActivity has leaked window DecorView@54552c[] that was originally added here）
```

 
## DialogFragment两种用法
创建一个继承自DialogFragment的java类
创建2种方式：
1. 在onCreateView中指定dialog对应布局xml，用于复杂自定义Dialog页面
2. 在onCreateDialog中指定对应dialog对象（AlertDialog）


## DialogFragment设置全屏的两种方式
### 1. 定义对话框style设置全屏
定义对话框style，在DialogFragment的onCreate方法中设置setStyle即可
setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppDialogTheme);

声明style样式
```xml
<!-- Define your custom dialog theme here extending from base -->
    <style name="AppDialogTheme" parent="Theme.AppCompat.Light.Dialog">
        <!-- Define color properties as desired -->
        <item name="colorPrimary">@color/dark_blue</item>
        <item name="colorAccent">@color/dark_blue</item>
        <item name="colorPrimaryDark">#000</item>
        <item name="colorControlNormal">@color/dark_blue</item>
        <item name="android:textColorHighlight">@color/light_blue</item>
        <!-- Define window properties as desired -->
        <!--是否显示title-->
        <item name="android:windowNoTitle">false</item>
        <!--是否是全屏-->
        <item name="android:windowFullscreen">true</item>
        <!--是否浮现在activity之上-->
        <item name="android:windowIsFloating">false</item>
        <!--背景颜色-->
        <item name="android:windowBackground">@color/medium_green</item>
        <!--点击空白是否消失-->
        <item name="android:windowCloseOnTouchOutside">true</item>
    </style>
```

### 2. 获取屏幕宽高，设定Window宽高设置全屏
在onCreateView方法内设置Window宽高
```java
Window window = this.getDialog().getWindow();
//去掉dialog默认的padding
window.getDecorView().setPadding(0, 0, 0, 0);
WindowManager.LayoutParams lp = window.getAttributes();
lp.width = WindowManager.LayoutParams.MATCH_PARENT;
lp.height = WindowManager.LayoutParams.MATCH_PARENT;
window.setAttributes(lp);
```

## DialogFragment显示
```java
EditDialogFragment editDialogFragment = new EditDialogFragment();
// style dialog 设置dialogFragment样式
//                editDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
editDialogFragment.show(getSupportFragmentManager(), "EditDialogFragment");
```



[Android官方文档Using-DialogFragment](https://github.com/codepath/android_guides/wiki/Using-DialogFragment)


