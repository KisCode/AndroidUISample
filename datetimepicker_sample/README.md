在Android上实现一个类似iOS的滚轮控件，如果开发一个自定义控件还是较为复杂的，在Android官方api 21即Android 5.0以后中相似的的替代方案 **NumberPicker**;

1. 默认申明一个NumberPicker布局即可实现各类滚轮效果
```xml
<NumberPicker
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layoutMode="opticalBounds"
    android:layout_margin="8dp"
/>
```

2. 如果需要实现一个==日期选择器==可以使用官方DatePicker
```xml
<DatePicker
    android:id="@+id/datepicker_dialog"
    style="@android:style/Theme.Holo.Light.NoActionBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:datePickerMode="spinner"
    android:text="Button"
    android:calendarViewShown="false"
    app:layout_constraintEnd_toStartOf="@+id/timepicker_dialog"
    app:layout_constraintHorizontal_chainStyle="packed"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tv_cancle_dialog" />
```
此处需要注意android:datePickerMode属性
- android:datePickerMode="calendar" 日期选择器则显示Android默认日历风格
- android:datePickerMode="spinner" 滚轮选择器风格

[日期选择器监听回调适配低版本](https://stackoverflow.com/questions/2051153/android-ond)
DatePicker 设置日期变化监听DatePicker.OnDateChangedListener时需注意低版本适配，
- 日期选择器在低于Android 8.0版本必须在init方法设置回调监听
- 高于Android 8.0可使用datepicker.setOnDateChangedListener设置回调

```java
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
           @Override
           public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               Log.i("onTimeChanged", year + "-" + monthOfYear + "-" + dayOfMonth);
           }
       });
}else {
    //日期选择器在低于Android8.0版本必须在init方法设置回调监听，如果使用 则会抛出异常 参考{@link https://stackoverflow.com/questions/2051153/android-ondatechangedlistener-how-do-you-set-this}
    datepicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.i("onTimeChanged", year + "-" + monthOfYear + "-" + dayOfMonth);】】、
    });
}
```



3. 如果需要实现一个==时间选择器==可以使用官方TimePicker,并设置选择器属性
   android:timePickerMode="spinner"

```xml
<TimePicker
    android:id="@+id/timepicker_dialog"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Button"
    android:timePickerMode="spinner"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintStart_toEndOf="@+id/datepicker_dialog"
    app:layout_constraintTop_toTopOf="@id/datepicker_dialog" />
```

4. 最后，实现日期+时间选择器，通过DatePicker + TimePicker即可实现
   ![滚轮日期选择器](https://github.com/KisCode/AndroidUISample/blob/develop/datetimepicker_sample/image/Screenshot_wheel.png)

![仿IOS风格底部弹出的日期选择器](https://github.com/KisCode/AndroidUISample/blob/develop/datetimepicker_sample/image/Screenshot_datetime_picker.pn