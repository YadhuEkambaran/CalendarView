package com.aya.ringcalendarapplication.models;

import android.os.Parcel;

import java.util.Calendar;

public class _Month implements CalendarType {

    private int id;
    private String month;
    private boolean selected;

    protected _Month(Parcel in) {
        id = in.readInt();
        month = in.readString();
        selected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }
}
