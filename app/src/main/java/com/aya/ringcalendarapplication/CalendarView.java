package com.aya.ringcalendarapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Range;
import android.widget.Toast;

import com.aya.ringcalendarapplication.annotation.DayCondition;
import com.aya.ringcalendarapplication.annotation.Weeks;
import com.aya.ringcalendarapplication.listeners.CalendarInteractionListener;
import com.aya.ringcalendarapplication.listeners.SettingsListener;
import com.aya.ringcalendarapplication.models._Day;
import com.aya.ringcalendarapplication.models._Month;
import com.aya.ringcalendarapplication.models._Title;
import com.aya.ringcalendarapplication.models._Year;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

public class CalendarView extends CalendarParentView implements SettingsListener, CalendarInteractionListener {


    private static final String TAG = "CalendarView";


    private boolean isMultiDaySelection;

    private boolean enableFromToday;

    private int[] mDisabledWeeks;

    private int mDisabledDays = DayCondition.NONE;

    private Date mMinDate, mMaxDate;

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        isMultiDaySelection = false;
        enableFromToday = true;
        setCalendarInteractionListener(this);
    }

    public void setDisabledWeeks(@Weeks int... weeksDays) {
        mDisabledWeeks = weeksDays;
        disableWeeks();
    }

    public void setDaysLimit(Date minDate, Date maxDate) {
        if (maxDate.getTime() <= minDate.getTime()) {
            throw new IllegalArgumentException("Invalid date limit");
        }

        mMinDate = minDate;
        mMaxDate = maxDate;
        disableDaysWithLimit();
    }

    public void setDisabledDays(@DayCondition int condition) {
        mDisabledDays = condition;
        disabledDays();
    }

    @Override
    public void afterChange() {
        disableWeeks();
        disabledDays();
        if (mMinDate != null && mMaxDate != null) {
            disableDaysWithLimit();
        }
    }

    @Override
    public void disabledDays() {
        if (mDisabledDays == DayCondition.NONE) return;

        if (_Title.getCurrentMonth() == _Month.getCurrentMonth()
                && _Title.getCurrentYear() == _Year.getCurrentYear()) {
            int today = getTodayPosition();
            Log.d(TAG, "--- " + String.format("TODAY %d ", today));
            switch (mDisabledDays) {
                case DayCondition.TILL_TODAY:
                    disableDays(today);
                    break;
                case DayCondition.TILL_YESTERDAY:
                    disableDays(today - 1);
                    break;
                case DayCondition.TILL_TOMORROW:
                    disableDays(today + 1);
                    break;
                case DayCondition.NONE:
                    break;
            }
        }
    }

    private void disableDays(int tillPosition) {
        int init = getFirstDayPosition();
        Log.d(TAG, "--- " + String.format("tillPosition %d INIT %d ", tillPosition, init));

        for (int i = init; i <= tillPosition; i++) {
            _Day day = getSelectedDay(i);
            day.setEnabled(false);
            updateAdapter(i);
        }
    }

    @Override
    public void disableWeeks() {
        int initialDay = getFirstDayPosition();
        int lastDay = getLastDayPosition();
        for (int i = initialDay; i <= lastDay; i++) {
            _Day day = getSelectedDay(i);
            for (int position:
                 mDisabledWeeks) {
                if ((i - position) % 7 == 0) {
                    day.setEnabled(false);
                    updateAdapter(i);
                }
            }
        }
    }

    private void disableDaysWithLimit() {
        Calendar minCalendar = Calendar.getInstance();
        minCalendar.setTime(mMinDate);
        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.setTime(mMaxDate);

        int minYear = minCalendar.get(Calendar.YEAR);
        int minMonth = minCalendar.get(Calendar.MONTH);
        int minDay = minCalendar.get(Calendar.DAY_OF_MONTH);

        int maxYear = maxCalendar.get(Calendar.YEAR);
        int maxMonth = maxCalendar.get(Calendar.MONTH);
        int maxDay = maxCalendar.get(Calendar.DAY_OF_MONTH);

        _Title title = _Title.getInstance();
        int currentYear = title.getYear();
        int currentMonth = title.getMonth();

        for (int i = getFirstDayPosition(); i <= getLastDayPosition(); i++) {
            _Day day = getSelectedDay(i);
            if ((currentYear < minYear || currentYear > maxYear) ||
                    (currentMonth < minMonth || currentMonth > maxMonth) ||
                    ((day.getId() < minDay && currentMonth <= minMonth) ||
                            (day.getId() > maxDay && currentMonth >= maxMonth))) {
                day.setEnabled(false);
                updateAdapter(i);
            }
        }
    }

    @Override
    public void disableMonths() {

    }

    @Override
    public boolean multiDaySelection() {
        return isMultiDaySelection;
    }

    @Override
    public boolean enableFromToday() {
        return enableFromToday;
    }

    @Override
    public void onDayClicked(int position) {
        if (!isMultiDaySelection)  {
            super.onDayClicked(position);
        } else {
            List<Integer> positions = getSelectedDaysPosition();
            int lastIndex = positions.size() - 1;
            if (lastIndex >= 0 && lastIndex < 2) {
                int lastPosition = positions.get(lastIndex);
                if (position <= lastPosition) {
                    super.onDayClicked(position);
                    return;
                }

                for (int i = position; i > lastPosition ; i--) {
                    super.setSelected(i);
                }
            } else  {
                super.onDayClicked(position);
            }
        }
    }
}
