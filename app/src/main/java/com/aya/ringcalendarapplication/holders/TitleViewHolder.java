package com.aya.ringcalendarapplication.holders;

import android.view.View;

import com.aya.ringcalendarapplication.R;
import com.aya.ringcalendarapplication.listeners.TitleInteractionListener;
import com.aya.ringcalendarapplication.models._Title;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class TitleViewHolder extends RecyclerView.ViewHolder {

    private final AppCompatTextView tvCurrentMonth;
    private final AppCompatTextView tvCurrentYear;

    private TitleInteractionListener mListener;

    public TitleViewHolder(@NonNull View itemView) {
        super(itemView);
        AppCompatImageView ivLeft = itemView.findViewById(R.id.iv_calendar_month_left_arrow);
        AppCompatImageView ivRight = itemView.findViewById(R.id.iv_calendar_month_right_arrow);
        tvCurrentMonth = itemView.findViewById(R.id.tv_calendar_current_month);
        tvCurrentYear = itemView.findViewById(R.id.tv_calendar_current_year);

        ivLeft.setOnClickListener(onPrevClicked);
        ivRight.setOnClickListener(onNextClicked);
    }

    public void setTitleInteractionListener(TitleInteractionListener listener) {
        mListener = listener;
    }

    public void setTitle(_Title title)  {
        tvCurrentMonth.setText(title.getMonthText());
        tvCurrentYear.setText(title.getYearText());
    }

    View.OnClickListener onPrevClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onPreviousClick(getLayoutPosition());
            }
        }
    };

    View.OnClickListener onNextClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onNextClick(getLayoutPosition());
            }
        }
    };
}
