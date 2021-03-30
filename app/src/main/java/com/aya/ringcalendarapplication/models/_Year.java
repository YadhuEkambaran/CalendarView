package com.aya.ringcalendarapplication.models;

import android.os.Parcel;

import java.util.Calendar;

public class _Year implements CalendarType {

    private int id;
    private String year;
    private boolean selected;

    protected _Year(Parcel in) {
        id = in.readInt();
        year = in.readString();
        selected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
}
