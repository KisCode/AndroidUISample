package com.keno.datetimepicker.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: DatePicker/TimePicker 工具类，支持重新设置其item宽度，最终实现 日期 + 时间 两个Picker并且
 * Author: keno
 * Date : 2020/7/23 16:51
 * <p>
 * 参考自
 * <a href="https://www.cnblogs.com/ljxxz/p/3925040.html">
 **/
public class PickerViewUtil {

    /**
     * @param frameLayout PickerView
     * @param itemWidth   PickerView每一列宽度
     */
    public static void resizePikcer(FrameLayout frameLayout, int itemWidth) {
        List<NumberPicker> numberPickerList = findNumberPicker(frameLayout);
        for (NumberPicker picker : numberPickerList) {
            resizeNumberPicker(picker, itemWidth);
        }
    }

    /***
     *  设置DatePicker/TimePicker 选中分割线颜色
     * @param context 上下文
     * @param frameLayout DatePicker/TimePicker 父类控件
     * @param colorRes 分割线颜色
     */
    public static void setDividerColor(Context context, FrameLayout frameLayout, @ColorRes int colorRes) {
        List<NumberPicker> numberPickerList = findNumberPicker(frameLayout);
        for (NumberPicker picker : numberPickerList) {
            setNumberPickerDividerColor(context, picker, colorRes);
            resizeNumberPicker(picker, colorRes);
        }
    }

    /*
     * 调整numberpicker大小
     */
    private static void resizeNumberPicker(NumberPicker np, int width) {
        int padding = width / 10;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(padding, 0, padding, 0);
        np.setLayoutParams(params);
    }


    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private static List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> numberPickerList = new ArrayList<>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    numberPickerList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return numberPickerList;
    }

    /***
     * 设置NumberPicker分割线颜色
     * @param context 上下文
     * @param numberPicker NumberPicker滚轮
     * @param colorRes 分割线颜色
     */
    private static void setNumberPickerDividerColor(Context context, NumberPicker numberPicker, @ColorRes int colorRes) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(numberPicker, new ColorDrawable(ContextCompat.getColor(context, colorRes)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
