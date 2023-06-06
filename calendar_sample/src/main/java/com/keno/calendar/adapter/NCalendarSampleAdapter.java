package com.keno.calendar.adapter;

import android.content.Context;

import com.keno.calendar.R;
import com.necer.painter.CalendarAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.List;

public class NCalendarSampleAdapter extends CalendarAdapter {

    @Override
    public View getCalendarItemView(Context context) {
        View calendarItemView = LayoutInflater.from(context).inflate(R.layout.item_calendar, null,false);
        CalendarItemViewHolder viewHolder = new CalendarItemViewHolder(calendarItemView);
        calendarItemView.setTag(viewHolder);
        return calendarItemView;
    }

    @Override
    public void onBindToadyView(View calendarItemView, LocalDate localDate, List<LocalDate> totalCheckedDateList) {
        CalendarItemViewHolder viewHolder = (CalendarItemViewHolder) calendarItemView.getTag();
        viewHolder.tvDate.setText(localDate.toString("dd日"));
    }

    @Override
    public void onBindCurrentMonthOrWeekView(View calendarItemView, LocalDate localDate, List<LocalDate> totalCheckedDateList) {
        //绑定当前月的日期
        CalendarItemViewHolder viewHolder = (CalendarItemViewHolder) calendarItemView.getTag();
        viewHolder.tvDate.setTextColor(Color.BLACK);
        viewHolder.tvDate.setBackground(null);
        viewHolder.tvDate.setText(localDate.toString("dd"));
    }

    @Override
    public void onBindLastOrNextMonthView(View calendarItemView, LocalDate localDate, List<LocalDate> totalCheckedDateList) {
        CalendarItemViewHolder viewHolder = (CalendarItemViewHolder) calendarItemView.getTag();
        viewHolder.tvDate.setTextColor(Color.LTGRAY);
        viewHolder.tvDate.setBackground(null);
        viewHolder.tvDate.setText(localDate.toString("dd"));
    }

    public static class CalendarItemViewHolder {
        public TextView tvDate;

        public CalendarItemViewHolder(View itemView) {
            tvDate= itemView.findViewById(R.id.tv_day);
        }
    }
}