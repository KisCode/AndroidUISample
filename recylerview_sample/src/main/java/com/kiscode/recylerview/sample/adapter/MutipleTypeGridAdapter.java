package com.kiscode.recylerview.sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.R;
import com.kiscode.recylerview.sample.model.Automobile;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/4/6 18:17
 **/
public class MutipleTypeGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int DEPTH_HEAD = 2;
    private static final int TYPE_HEAD = 69;
    private static final int TYPE_DATA = 99;
    private  List<Automobile> datas;

    public MutipleTypeGridAdapter(List<Automobile> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (TYPE_HEAD == viewType) {
            return new HeadViewHolder(inflater.inflate(R.layout.item_car_head_layout, parent, false));
        } else {
            return new DataViewHolder(inflater.inflate(R.layout.item_car_info_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Automobile automobile = datas.get(position);
        if (TYPE_HEAD == viewType) {
            ((HeadViewHolder) holder).tvName.setText(automobile.getName());
        } else {
            ((DataViewHolder) holder).tvName.setText(automobile.getName());
            ((DataViewHolder) holder).tvFullName.setText(automobile.getFullname());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return DEPTH_HEAD == datas.get(position).getDepth() ? TYPE_HEAD : TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvMore;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_car_head);
            tvMore = itemView.findViewById(R.id.tv_more_car_head);
        }
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvFullName;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_car_info);
            tvFullName = itemView.findViewById(R.id.tv_fullname_car_info);
        }
    }
} 