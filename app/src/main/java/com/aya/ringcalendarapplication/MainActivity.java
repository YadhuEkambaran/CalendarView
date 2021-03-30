package com.aya.ringcalendarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aya.ringcalendarapplication.annotation.DayCondition;
import com.aya.ringcalendarapplication.annotation.Weeks;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setDisabledWeeks(Weeks.WEDNESDAY);

//        Calendar minCalendar = Calendar.getInstance();
//        minCalendar.set(Calendar.YEAR, 2021);
//        minCalendar.set(Calendar.MONTH, 1);
//        minCalendar.set(Calendar.DAY_OF_MONTH, 10);
//
//        Calendar maxCalendar = Calendar.getInstance();
//        maxCalendar.set(Calendar.YEAR, 2021);
//        maxCalendar.set(Calendar.MONTH, 4);
//        maxCalendar.set(Calendar.DAY_OF_MONTH, 29);
//
//        calendarView.setDaysLimit(minCalendar.getTime(), maxCalendar.getTime());
    }
}