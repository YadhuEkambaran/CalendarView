package com.aya.ringcalendarapplication;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

class CalendarLayoutManager extends GridLayoutManager {

    public CalendarLayoutManager(Context context) {
        super(context, 7);
    }


}
