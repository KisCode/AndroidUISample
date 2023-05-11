package com.keno.calendar.pojo;

import com.keno.calendar.MonthDayType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Description:
 * Date : 2023/5/8 15:45
 **/
public class CalendarItem implements Serializable {
    private MonthDayType monthDayType;
    private LocalDate localDate;

    public CalendarItem(MonthDayType monthDayType, LocalDate localDate) {
        this.monthDayType = monthDayType;
        this.localDate = localDate;
    }

    public MonthDayType getMonthDayType() {
        return monthDayType;
    }

    public void setMonthDayType(MonthDayType monthDayType) {
        this.monthDayType = monthDayType;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
