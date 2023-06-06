package com.keno.calendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.keno.calendar.adapter.NCalendarSampleAdapter;
import com.necer.calendar.NCalendar;
import com.necer.painter.InnerPainter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:  开源日历 NCalendar使用Demo
 * { @link <a href="https://github.com/yannecer/NCalendar">NCalendar Github 地址</a>}
 * Author:
 * Date : 2023/5/11 16:13
 **/
public class NCalendarActivity extends AppCompatActivity {
    private NCalendar nCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_calendar);

        nCalendar = findViewById(R.id.miui9Calendar);

        List<String> pointList = Arrays.asList("2023-05-11", "2023-05-19", "2023-05-29");
        InnerPainter innerPainter = (InnerPainter) nCalendar.getCalendarPainter();
        innerPainter.setPointList(pointList);
//        nCalendar.setCheckedDates(Arrays.asList("2023-05-21", "2023-05-29"));
//        nCalendar.setCalendarAdapter(new NCalendarSampleAdapter());

    }

}