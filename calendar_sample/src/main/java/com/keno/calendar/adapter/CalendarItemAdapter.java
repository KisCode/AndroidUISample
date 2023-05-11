package com.keno.calendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keno.calendar.MonthDayType;
import com.keno.calendar.R;
import com.keno.calendar.pojo.CalendarItem;

import java.time.LocalDate;
import java.util.List;

/**
 * Description:
 * Date : 2023/5/8 15:05
 **/
public class CalendarItemAdapter extends RecyclerView.Adapter<CalendarItemAdapter.CalendarItemViewHolder> {
    private Context mContext;
    private List<CalendarItem> mDates;

    public CalendarItemAdapter(Context mContext, List<CalendarItem> list) {
        this.mContext = mContext;
        this.mDates = list;
    }

    @NonNull
    @Override
    public CalendarItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, parent, false);
        return new CalendarItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarItemViewHolder holder, int position) {
        LocalDate localDate = mDates.get(position).getLocalDate();
        MonthDayType monthDayType = mDates.get(position).getMonthDayType();

        holder.tvItem.setText(String.valueOf(localDate.getDayOfMonth()));
        if (!monthDayType.equals(MonthDayType.CURRENT)) {
            holder.tvItem.setTextColor(Color.GRAY);
        } else {
            holder.tvItem.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public static class CalendarItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public CalendarItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_day);
        }
    }
}
