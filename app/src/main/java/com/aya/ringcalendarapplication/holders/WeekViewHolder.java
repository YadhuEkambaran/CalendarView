package com.aya.ringcalendarapplication.holders;

import android.view.View;

import com.aya.ringcalendarapplication.R;
import com.aya.ringcalendarapplication.models._Week;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class WeekViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView tvWeek;

    public WeekViewHolder(@NonNull View itemView) {
        super(itemView);
        tvWeek = itemView.findViewById(R.id.tv_c_week);
    }

    public void setWeek(_Week week) {
        tvWeek.setText(week.getWeek());
    }
}
