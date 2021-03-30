package com.aya.ringcalendarapplication.models;

import java.util.ArrayList;
import java.util.List;

public class _Week implements CalendarType {

    private final static String[] WEEKS = {"Su", "Mo", "Tu", "We",
            "Th", "Fr", "Sa"};

    private int id;
    private String week;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public static List<_Week> getWeeks() {
        List<_Week> weeks = new ArrayList<>();
        for (int i = 0; i < WEEKS.length; i++) {
            _Week week = new _Week();
            week.setId(i);
            week.setWeek(WEEKS[i]);
            weeks.add(week);
        }

        return weeks;
    }
}
