package com.aya.ringcalendarapplication.holders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;

import com.aya.ringcalendarapplication.R;
import com.aya.ringcalendarapplication.Utils;
import com.aya.ringcalendarapplication.listeners.DayInteractionListener;
import com.aya.ringcalendarapplication.models._Day;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class DayViewHolder extends RecyclerView.ViewHolder {

    private static final String PROJECTING_COLOR = "#B90039";
    private static final String WHITE_COLOR = "#FFFFFF";
    private static final String BLACK_COLOR = "#000000";
    private static final String GRAY_COLOR = "#EDEDED";

    private final AppCompatTextView tvDay;

    private DayInteractionListener mListener;

    private final float circleRadius;


    public DayViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDay = itemView.findViewById(R.id.tv_c_day);
        tvDay.setOnClickListener(onDayClicked);
        circleRadius = Utils.getDP(tvDay.getContext(), 25);
    }

    public void setDayInteractionListener(DayInteractionListener listener) {
        mListener = listener;
    }

    public void setDay(_Day day) {
        tvDay.setText(day.getDay());

        tvDay.setEnabled(day.isEnabled());
        tvDay.setClickable(day.isEnabled());
        if (day.isEnabled()) {
            if (day.isSelected()) {
                tvDay.setTextColor(Color.parseColor(WHITE_COLOR));

                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(circleRadius);
                drawable.setColor(Color.parseColor(PROJECTING_COLOR));
                tvDay.setBackgroundDrawable(drawable);
            } else {
                if (day.isToday()) {
                    GradientDrawable _drawable = new GradientDrawable();
                    _drawable.setColor(Color.parseColor(WHITE_COLOR));
                    _drawable.setCornerRadius(circleRadius);
                    _drawable.setStroke(4, Color.parseColor(PROJECTING_COLOR));
                    tvDay.setBackgroundDrawable(_drawable);
                    tvDay.setTextColor(Color.parseColor(PROJECTING_COLOR));
                } else {
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setColor(Color.parseColor(WHITE_COLOR));
                    tvDay.setBackgroundDrawable(drawable);
                    tvDay.setTextColor(Color.parseColor(BLACK_COLOR));
                }
            }
        } else {
            tvDay.setTextColor(Color.parseColor(GRAY_COLOR));
        }


    }

    final View.OnClickListener onDayClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onDayClicked(getLayoutPosition());
            }
        }
    };
}
