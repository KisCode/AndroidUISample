# RadionButton Sample

# RadioButton

### 自定义文字颜色selector
当RadioButton选中和取消选中分别显示不同的颜色
1. 在res/color 路径下创建seletor ,**selector_radion_button_text**.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:color="@color/colorCheck"></item>
    <item android:state_checked="false" android:color="@color/colorNormal"></item>
</selector>
```
2. xml 布局中textColor中指定即可生效

```xml
<RadioButton
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:text="option 2-text check color"
    android:textColor="@color/selector_radion_button_text"
    android:textSize="16sp" />
```

### 自定义按钮颜色
以上控制了RadioButton文字颜色，接下来需要设置RadioButton按钮颜色；RadioButton默认选中颜色为App主题颜色，自定义按钮步骤如下：
1. 在style中定义RadionButton的样式，继承自**Widget.AppCompat.CompoundButton.RadioButton**
```xml
<style name="style_radio_button" parent="Widget.AppCompat.CompoundButton.RadioButton">
    <!--选中样式-->
    <item name="android:colorControlActivated">@color/colorCheck</item>
    <!--为选中样式-->
    <item name="android:colorControlNormal">@color/colorNormal</item>
</style>
```
2. 在页面布局xml中引用，此处需要将style设置RadioButton的**android:theme**才会生效
```xml
<RadioButton
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:text="option 1-button check color"
    android:textSize="16sp"
    android:theme="@style/style_radio_button" />
```

