package com.aya.ringcalendarapplication.listeners;

public interface SettingsListener {
    void disabledDays();
    void disableWeeks();
    void disableMonths();
    boolean multiDaySelection();
    boolean enableFromToday();
}
