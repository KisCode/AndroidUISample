package com.keno.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.keno.calendar.adapter.CalendarItemAdapter;
import com.keno.calendar.databinding.ActivityRecyclerviewCalendarBinding;
import com.keno.calendar.pojo.CalendarItem;
import com.keno.calendar.util.LocalDateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecyclerviewCalendarActivity extends AppCompatActivity {
    ActivityRecyclerviewCalendarBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRecyclerviewCalendarBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this, 7));
        List<LocalDate> dateListByMonth = LocalDateUtil.getDateListByMonth(2023, 3);

        List<CalendarItem> calendarItemList = dateListByMonth.stream()
                .map(localDate -> new CalendarItem(MonthDayType.CURRENT, localDate)).collect(Collectors.toList());

        //非本月第一天，则补上月末几天
        int lastMonthDay = dateListByMonth.stream().findFirst().get().getDayOfWeek().getValue() - 1;
        int nextMonthDay = 7 - dateListByMonth.get(dateListByMonth.size() - 1).getDayOfWeek().getValue();
        for (int i = 0; i < lastMonthDay; i++) {
            LocalDate firstDay = dateListByMonth.get(0);
            calendarItemList.add(0, new CalendarItem(MonthDayType.LAST, firstDay.minusDays(1)));
        }

        for (int i = 0; i < nextMonthDay; i++) {
            LocalDate lastDay = dateListByMonth.get(dateListByMonth.size() - 1);
            calendarItemList.add(new CalendarItem(MonthDayType.NEXT, lastDay.plusDays(1)));
        }


        CalendarItemAdapter adapter = new CalendarItemAdapter(this, calendarItemList);
        mBinding.recyclerview.setAdapter(adapter);
    }
}