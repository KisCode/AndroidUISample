package com.keno.datetimepicker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

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
//
//        //设置颜色
//        PickerViewUtil.setNumberPickerDividerColor(this, numberPicker, R.color.colorPrimary);

        //设置数据源
        numberPicker.setDisplayedValues(displayDataList);
        //是否循环滚动
        numberPicker.setWrapSelectorWheel(false);
//        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_divider);
//        numberPicker.setDividerDrawable(drawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            numberPicker.setSelectionDividerHeight(0);
        }
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(3);
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