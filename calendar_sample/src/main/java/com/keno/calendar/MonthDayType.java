package com.keno.calendar;

/**
 * Description:
 * Date : 2023/5/8 15:48
 **/
public enum MonthDayType {
    // 当前月份
    CURRENT(0),

    //上月
    LAST(1),

    //下月
    NEXT(2);

    private int type;

    MonthDayType(int type) {
        this.type = type;
    }
}
