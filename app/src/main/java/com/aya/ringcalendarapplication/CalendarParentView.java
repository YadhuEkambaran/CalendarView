package com.aya.ringcalendarapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.aya.ringcalendarapplication.listeners.CalendarInteractionListener;
import com.aya.ringcalendarapplication.listeners.DayInteractionListener;
import com.aya.ringcalendarapplication.listeners.TitleInteractionListener;
import com.aya.ringcalendarapplication.models.CalendarType;
import com.aya.ringcalendarapplication.models._Day;
import com.aya.ringcalendarapplication.models._Title;
import com.aya.ringcalendarapplication.models._Week;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarParentView extends LinearLayout implements DayInteractionListener {

    private static final String TAG = "CalendarParentView";


    public static final int DAY = 0;
    public static final int MONTH = 1;
    public static final int YEAR = 2;

    @Retention(RetentionPolicy.SOURCE)

    @IntDef({DAY, MONTH, YEAR})

    public @interface CalendarViewType {}

    private final Context mContext;

    private CalendarAdapter mAdapter;

    private List<CalendarType> mCalendarTypeItems;

    private List<Integer> mSelectedDays;

    private int mFirstDayPosition;

    private CalendarInteractionListener mCalendarInteractionListener;

    @SuppressLint("InflateParams")
    public CalendarParentView(Context context) {
        super(context);
        mContext = context;

        init();
    }

    public CalendarParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        init();
    }

    private void init() {
        mCalendarTypeItems = new ArrayList<>();
        mSelectedDays = new ArrayList<>();

        this.setGravity(Gravity.CENTER_HORIZONTAL);
        RecyclerView rvCalendar = new RecyclerView(mContext);
        mAdapter = new CalendarAdapter(mCalendarTypeItems);
        mAdapter.setTitleInteractionListener(titleInteractionListener);
        mAdapter.setDayInteractionListener(this);
        rvCalendar.setAdapter(mAdapter);
        CalendarLayoutManager manager = new CalendarLayoutManager(mContext);
        rvCalendar.setLayoutManager(manager);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mCalendarTypeItems.get(position) instanceof _Title) {
                    return 7;
                }
                return 1;
            }
        });


        getAdapterInfo();

        addView(rvCalendar);
    }

    private void getAdapterInfo() {
        mCalendarTypeItems.clear();
        mSelectedDays.clear();
        _Title title = _Title.getInstance();
        mCalendarTypeItems.add(title);
        mCalendarTypeItems.addAll(_Week.getWeeks());
        mCalendarTypeItems.addAll(_Day.getPlaceHolders(title.getMonth(), title.getYear()));
        mFirstDayPosition = mCalendarTypeItems.size();
        mCalendarTypeItems.addAll(_Day.getDays(title.getMonth(), title.getYear()));

        if (mCalendarInteractionListener != null) {
            Log.d("CALENDAR PARENT", "--- getAdapterInfo ---");
            mCalendarInteractionListener.afterChange();
        }

        mAdapter.notifyDataSetChanged();
    }

    public void setCalendarInteractionListener(CalendarInteractionListener mCalendarInteractionListener) {
        Log.d("CALENDAR PARENT", "--- setCalendarInteractionListener ---");
        this.mCalendarInteractionListener = mCalendarInteractionListener;
    }

    private final TitleInteractionListener titleInteractionListener = new TitleInteractionListener() {
        @Override
        public void onPreviousClick(int position) {
            _Title title = _Title.getInstance();
            if (title == null) return;

            title.decrementMonth();
            getAdapterInfo();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNextClick(int position) {
            _Title title = _Title.getInstance();
            if (title == null) return;

            title.incrementMonth();
            getAdapterInfo();
            mAdapter.notifyDataSetChanged();
        }
    };

    private void deselectDays() {
        for (int i = 0; i < mSelectedDays.size(); i++) {
           int position =  mSelectedDays.get(i);
           _Day day = (_Day) mCalendarTypeItems.get(position);
           day.setSelected(false);
           mAdapter.notifyItemChanged(position);
        }
        mSelectedDays.clear();
    }


    public int get(@CalendarViewType int type) {

        if (type == DAY) {
            return _Day.getCurrentDay();
        } else if (type == MONTH) {
            _Title title = _Title.getInstance();
            return title.getMonth();
        } else if (type == YEAR) {
            _Title title = _Title.getInstance();
            return title.getYear();
        }

        return -1;
    }

    public String getSelectedDay() {
        if (mSelectedDays.size() == 0) return null;
        _Day selectedDay = (_Day) mCalendarTypeItems.get(mSelectedDays.get(0));
        _Title title = _Title.getInstance();
        return title.getMonth() + "/" + selectedDay.getId() + "/" + title.getYear();
    }

    public List<Integer> getSelectedDaysPosition() {
        return mSelectedDays;
    }

    public _Day getSelectedDay(int position) {
        return (_Day) mCalendarTypeItems.get(position);
    }

    public void setSelected(int position) {
        _Day day = getSelectedDay(position);
        day.setSelected(true);
        addPosition(position);
        updateAdapter(position);
    }

    public void disableDay(int position) {
        _Day day = getSelectedDay(position);
        day.setEnabled(false);
        addPosition(position);
        updateAdapter(position);
    }

    public void updateAdapter() {
        if (mAdapter == null) return;
        mAdapter.notifyDataSetChanged();
    }

    public void updateAdapter(int position) {
        if (mAdapter == null) return;
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDayClicked(int position) {
        deselectDays();
        _Day day = (_Day) mCalendarTypeItems.get(position);
        day.setSelected(!day.isSelected());
        if (day.isSelected()) {
            addPosition(position);
        }
        mAdapter.notifyItemChanged(position);
    }

    public void addPosition(int position) {
        mSelectedDays.add(position);
    }

    public int getFirstDayPosition() {
        return mFirstDayPosition;
    }

    public int getLastDayPosition() {
        return mAdapter.getItemCount() - 1;
    }

    public int getTodayPosition() {
        Log.d(TAG, String.format("--- FIRST POSITION -- %d LAST POSITION %d", getFirstDayPosition(), getLastDayPosition()));
        for (int i = getFirstDayPosition(); i < getLastDayPosition(); i++) {
            _Day day = (_Day) mCalendarTypeItems.get(i);
            if (day.isToday()) {
                return i;
            }
        }

        return -1;
    }
}
