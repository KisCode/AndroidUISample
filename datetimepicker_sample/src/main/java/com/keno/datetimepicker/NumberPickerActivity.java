package com.keno.datetimepicker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.keno.datetimepicker.util.PickerViewUtil;

public class NumberPickerActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, NumberPickerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        initView();
    }

    private void initView() {
        String[] displayDataList = {"Java", "iOS", "Android", "Swift"};
        NumberPicker numberPicker = findViewById(R.id.number_picker);
        //设置数据源
        numberPicker.setDisplayedValues(displayDataList);
        //是否循环滚动
        numberPicker.setWrapSelectorWheel(false);
        //设置内容不可编辑
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //设置分割线透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            numberPicker.setSelectionDividerHeight(0);
        } else {
            PickerViewUtil.setNumberPickerDividerColor(this, numberPicker, android.R.color.transparent);
        }
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(displayDataList.length - 1);
        numberPicker.setValue(1);


        String[] displayNumberDataList = {"1", "2", "5", "10", "12", "25", "30"};
        NumberPicker numberPickerValue = findViewById(R.id.number_picker_value);
        numberPickerValue.setDisplayedValues(displayNumberDataList);
        //是否循环滚动
        numberPickerValue.setWrapSelectorWheel(false);
        numberPicker.setMinValue(0);
        numberPickerValue.setMaxValue(displayNumberDataList.length - 1);
        numberPicker.setValue(1);
    }
}