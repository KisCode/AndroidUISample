package com.keno.calendar.util;

import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Date : 2023/5/8 14:55
 **/
public class LocalDateUtil {
    public static List<LocalDate> getDateListByMonth(int year, int month) {
        List<LocalDate> list = new ArrayList<>();
        LocalDate firstDay = LocalDate.of(year, month, 1);
        //起止月份的上一个月 减1天为当月最后一天
        int lastDayNumber = firstDay.plusMonths(1).minusDays(1).getDayOfMonth();
        for (int i = 1; i <= lastDayNumber; i++) {
            list.add(LocalDate.of(year, month, i));
        }
        return list;
    }
}
