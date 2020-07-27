package com.keno.datetimepicker;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.keno.datetimepicker.util.PickerViewUtil;

/**
 * Description:
 * Author: keno
 * Date : 2020/7/23 13:27
 *
 **/
public class DateTimeMainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePicker datepicker;
    private TimePicker timePicker;
    private TextView tvCurrentDatetime;
    private Button btnOk;
    private Button btnShow;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datetime_main);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取屏幕宽度，设置每DatePicker 每一列的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int itemWidth = dm.widthPixels / 7;

        PickerViewUtil.setDividerColor(this, datepicker, R.color.colorAccent);
        PickerViewUtil.setDividerColor(this, timePicker, R.color.colorAccent);
        PickerViewUtil.resizePikcer(datepicker, itemWidth);
        PickerViewUtil.resizePikcer(timePicker, itemWidth);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView() {
        datepicker = findViewById(R.id.datepicker);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);
        datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i("onTimeChanged", year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.i("onTimeChanged", hourOfDay + ":" + minute);
            }
        });


        tvCurrentDatetime = findViewById(R.id.tv_current_datetime);
        tvCurrentDatetime.setOnClickListener(this);
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        btnShow = findViewById(R.id.btn_show_dialog);
        btnShow.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //重新计算宽度
        PickerViewUtil.resizePikcer(datepicker,120);
        PickerViewUtil.resizePikcer(timePicker,120);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                String currentDateTime = datepicker.getYear() + "-" + (datepicker.getMonth() + 1) + "-" + datepicker.getDayOfMonth()
                        + "\t"
                        + timePicker.getHour() + ":" + timePicker.getMinute();
                tvCurrentDatetime.setText(currentDateTime);
                break;
            case R.id.btn_show_dialog: //弹框形式实现时间选择器
                PickDateTimeDialog dialog = new PickDateTimeDialog();
                dialog.setOnPickedListener(new PickDateTimeDialog.OnPickedListener() {
                    @Override
                    public void onPickedDate(long datetime) {

                    }
                });
                dialog.show(getSupportFragmentManager(),"DateTimeDialog");
                break;
        }
    }


}
