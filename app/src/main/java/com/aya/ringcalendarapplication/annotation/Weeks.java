package com.aya.ringcalendarapplication.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({Weeks.SUNDAY, Weeks.MONDAY, Weeks.TUESDAY, Weeks.WEDNESDAY,
        Weeks.THURSDAY, Weeks.FRIDAY, Weeks.SATURDAY})
@Retention(RetentionPolicy.SOURCE)
public @interface Weeks {
    int SUNDAY = 1;
    int MONDAY = 2;
    int TUESDAY = 3;
    int WEDNESDAY = 4;
    int THURSDAY = 5;
    int FRIDAY = 6;
    int SATURDAY = 7;
}
