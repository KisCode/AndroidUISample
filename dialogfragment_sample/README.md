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
<style name="AppDialogFragmentFullScreenTheme" parent="Theme.AppCompat.Light.Dialog">
    <!-- Define window properties as desired -->
    <item name="android:background">@android:color/transparent</item>
    <item name="android:windowBackground">@android:color/transparent</item>
    <!--是否显示title-->
    <item name="android:windowNoTitle">true</item>
    <!--是否是全屏-->
    <item name="android:windowFullscreen">true</item>
    <!--是否浮现在activity之上-->
    <item name="android:windowIsFloating">false</item>
    <!--点击空白是否消失-->
    <item name="android:windowCloseOnTouchOutside">false</item>
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

## 声明一个自定义宽高的DialogFragment
#### 1.声明dialog样式
```xml
<!--    自定义对话框样式-->
<style name="styleNormalDialog" parent="Theme.AppCompat.Light.Dialog">
    <item name="android:windowFrame">@null</item><!--边框-->
    <item name="android:windowIsFloating">false</item><!--是否浮现在activity之上-->
    <item name="android:windowIsTranslucent">true</item><!--半透明-->
    <item name="android:windowNoTitle">false</item><!--无标题-->
    <item name="android:windowFullscreen">true</item><!--全屏-->
    <item name="android:windowBackground">@drawable/shape_bg_black_dialog</item><!--背景透明-->
    <!--        <item name="android:windowBackground">@null</item>&lt;!&ndash;背景透明&ndash;&gt;-->
    <item name="android:backgroundDimEnabled">true</item><!--模糊-->
    <item name="android:windowCloseOnTouchOutside">true</item><!--点击空白是否消失-->
</style>
```

#### 2.在onCreate方法中设置对话框样式
```java
@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_TITLE, R.style.styleNormalDialog);
}
```

#### 3.onStart方法通过getDialog().getWindow().setLayout(width, height) 设置对话框的宽高
```java
@Override
public void onStart() {
    super.onStart();

//  getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    if (getArguments() == null) {
        return;
    }
    int width = getArguments().getInt(KEY_WIDTH);
    int height = getArguments().getInt(KEY_HEIGHT);

    Window dialogWindow = getDialog().getWindow();
//        dialogWindow.setAttributes(lp);
    dialogWindow.setLayout(width, height);
}
```

![image](https://github.com/KisCode/AndroidUISample/blob/develop/dialogfragment_sample/image/Screenshot_1595856521.png)

![image](https://github.com/KisCode/AndroidUISample/blob/develop/dialogfragment_sample/image/Screenshot_1595856532.png)


[Android官方文档Using-DialogFragment](https://github.com/codepath/android_guides/wiki/Using-DialogFragment)


