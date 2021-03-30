package com.aya.ringcalendarapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aya.ringcalendarapplication.holders.DayViewHolder;
import com.aya.ringcalendarapplication.holders.EmptyViewHolder;
import com.aya.ringcalendarapplication.holders.TitleViewHolder;
import com.aya.ringcalendarapplication.holders.WeekViewHolder;
import com.aya.ringcalendarapplication.listeners.DayInteractionListener;
import com.aya.ringcalendarapplication.listeners.TitleInteractionListener;
import com.aya.ringcalendarapplication.models.CalendarType;
import com.aya.ringcalendarapplication.models.PlaceHolder;
import com.aya.ringcalendarapplication.models._Day;
import com.aya.ringcalendarapplication.models._Title;
import com.aya.ringcalendarapplication.models._Week;

import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int VIEW_TYPE_TITLE = 0;
    private final static int VIEW_TYPE_WEEK = 1;
    private final static int VIEW_TYPE_DAY = 2;
    private final static int VIEW_TYPE_EMPTY = 3;

    private final List<CalendarType> mCalendarTypeItems;

    private TitleInteractionListener mTitleInteractionListener;
    private DayInteractionListener mDayInteractionListener;

    public CalendarAdapter(List<CalendarType> mCalendarTypeItems) {
        this.mCalendarTypeItems = mCalendarTypeItems;
    }

    public void setTitleInteractionListener(TitleInteractionListener mTitleInteractionListener) {
        this.mTitleInteractionListener = mTitleInteractionListener;
    }

    public void setDayInteractionListener(DayInteractionListener listener) {
        mDayInteractionListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == VIEW_TYPE_TITLE) {
            View view = inflater.inflate(R.layout.layout_c_title, parent, false);
            TitleViewHolder viewHolder = new TitleViewHolder(view);
            viewHolder.setTitleInteractionListener(mTitleInteractionListener);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_WEEK) {
            View view = inflater.inflate(R.layout.layout_c_week, parent, false);
            return new WeekViewHolder(view);
        } else if (viewType == VIEW_TYPE_DAY) {
            View view = inflater.inflate(R.layout.layout_c_day, parent, false);
            DayViewHolder viewHolder = new DayViewHolder(view);
            viewHolder.setDayInteractionListener(mDayInteractionListener);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_EMPTY) {
            View view = inflater.inflate(R.layout.empty_placeholder, parent, false);
            return new EmptyViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {
            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            viewHolder.setTitle((_Title) mCalendarTypeItems.get(position));
        } else if (holder instanceof WeekViewHolder) {
            WeekViewHolder viewHolder = (WeekViewHolder) holder;
            viewHolder.setWeek((_Week) mCalendarTypeItems.get(position));
        } else if (holder instanceof DayViewHolder) {
            DayViewHolder viewHolder = (DayViewHolder) holder;
            viewHolder.setDay((_Day) mCalendarTypeItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mCalendarTypeItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        CalendarType type = mCalendarTypeItems.get(position);
        if (type instanceof _Title) {
            return VIEW_TYPE_TITLE;
        } else if (type instanceof _Week) {
            return VIEW_TYPE_WEEK;
        } else if (type instanceof _Day) {
            return VIEW_TYPE_DAY;
        } else if (type instanceof PlaceHolder) {
            return VIEW_TYPE_EMPTY;
        }

        return super.getItemViewType(position);
    }

    public _Title getTitle() {
        for (CalendarType item:
             mCalendarTypeItems) {
            if (item instanceof _Title) {
                return (_Title) item;
            }
        }

        return null;
    }
}
