package com.keno.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.keno.calendar.util.LocalDateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        calendarView = findViewById(R.id.calendar);

    }


    private void initData() {
     /*   DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        String nowStr = now.format(format);
        Log.i("nowStr",nowStr);*/

        LocalDate firstDay = LocalDate.of(2023, 2, 1);

        List<LocalDate> dateListByMonth = LocalDateUtil.getDateListByMonth(2023, 2);
        for (LocalDate localDate : dateListByMonth) {
            Log.i("nowStr", localDate.toString());
        }
    }

}