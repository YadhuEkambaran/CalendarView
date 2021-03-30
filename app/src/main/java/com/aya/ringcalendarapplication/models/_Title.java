package com.aya.ringcalendarapplication.models;

import android.util.Log;

import java.util.Calendar;

public class _Title implements CalendarType {

    private final String[] months = {"January", "February", "March",
            "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};

    private int month;
    private int year;

    private boolean isPrevDisabled = false;
    private boolean isNextDisabled = false;

    private static _Title sTitle;

    public static _Title getInstance() {
        if (sTitle != null) return sTitle;

        sTitle = new _Title();
        return sTitle;
    }

    private  _Title() {
        month = getCurrentMonth();
        year = getCurrentYear();
        Log.d("_Title", String.format("--- _title constructor --- %d", month));
    }

    public boolean isPrevDisabled() {
        return isPrevDisabled;
    }

    public void setPrevDisabled(boolean prevDisabled) {
        isPrevDisabled = prevDisabled;
    }

    public boolean isNextDisabled() {
        return isNextDisabled;
    }

    public void setNextDisabled(boolean nextDisabled) {
        isNextDisabled = nextDisabled;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(Calendar.MONTH));
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public String getMonthText() {
        if (month < 0 || month > 12) return null;
        return months[month];
    }

    public void incrementMonth() {
        if (month == 11) {
            ++year;
            month = 0;
            Log.d("_Title", String.format("--- _title incrementMonth --- %d", month));
            return;
        }
        ++month;
        Log.d("_Title", String.format("--- _title incrementMonth --- %d", month));
    }

    public void decrementMonth() {
        if (month == 0) {
            --year;
            month = 11;
            Log.d("_Title", String.format("--- _title decrementMonth --- %d", month));
            return;
        }
        --month;
        Log.d("_Title", String.format("--- _title decrementMonth --- %d", month));
    }

    public String getYearText() {
        return String.valueOf(year);
    }
}
