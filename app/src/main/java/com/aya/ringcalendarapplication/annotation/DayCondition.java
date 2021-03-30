package com.aya.ringcalendarapplication.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({DayCondition.NONE, DayCondition.TILL_YESTERDAY, DayCondition.TILL_TODAY, DayCondition.TILL_TOMORROW, DayCondition.FROM_TODAY, DayCondition.FROM_TOMORROW, DayCondition.FROM_YESTERDAY})
@Retention(RetentionPolicy.SOURCE)
public @interface DayCondition {
        int NONE = 0;
        int TILL_YESTERDAY = -1;
        int TILL_TODAY = -2;
        int TILL_TOMORROW = -3;
        int FROM_TODAY = 1;
        int FROM_TOMORROW = 2;
        int FROM_YESTERDAY = 3;
}