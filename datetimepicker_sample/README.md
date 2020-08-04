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

![仿IOS风格底部弹出的日期选择器](https://github.com/KisCode/AndroidUISample/blob/develop/datetimepicker_sample/image/Screenshot_datetime_picker.png)