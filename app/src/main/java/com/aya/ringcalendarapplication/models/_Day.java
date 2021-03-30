package com.aya.ringcalendarapplication.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class _Day implements CalendarType {

    private int id;
    private String day;
    private boolean enabled;
    private boolean selected;
    private boolean today;

    public _Day() {
        enabled = true;
        selected = false;
        today = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getLastPlaceholderDay(int month, int year) {
        return getCalendar(month, year).get(Calendar.DAY_OF_WEEK);
    }

    public static List<PlaceHolder> getPlaceHolders(int month, int year) {
        int weekDay = getLastPlaceholderDay(month, year);
        List<PlaceHolder> placeHolders = new ArrayList<>();
        for (int i = 1; i < weekDay; i++) {
            placeHolders.add(new PlaceHolder());
        }

        return placeHolders;
    }

    public static List<_Day> getDays(int month, int year) {
        Calendar calendar = getCalendar(month, year);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        List<_Day> days = new ArrayList<>();
        for (int i = 0; i < lastDay; i++) {
            _Day day = new _Day();
            day.setId(i + 1);
            day.setDay(String.valueOf((i + 1)));
            day.setEnabled(true);
            day.setSelected(false);
            if (month == _Title.getCurrentMonth() && year == _Title.getCurrentYear() && (i + 1) == getCurrentDay()) {
                day.setToday(true);
            }
            days.add(day);
        }
        return days;
    }

    private static Calendar getCalendar(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }
}
