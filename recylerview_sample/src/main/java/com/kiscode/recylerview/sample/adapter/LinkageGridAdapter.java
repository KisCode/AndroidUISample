package com.kiscode.recylerview.sample.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.R;

import java.util.List;

public class LinkageGridAdapter extends RecyclerView.Adapter<LinkageGridAdapter.FindWordViewHolder> {

    private List<String> mDatas;
    private int itemWidth;

    public LinkageGridAdapter(List<String> data) {
        this.mDatas = data;
    }

    public LinkageGridAdapter(List<String> mDatas, int itemWidth) {
        this.mDatas = mDatas;
        this.itemWidth = itemWidth;
    }


    @NonNull
    @Override
    public FindWordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letter_layout, parent, false);
        FindWordViewHolder findWordViewHolder = new FindWordViewHolder(rootView);
        if (itemWidth != 0) {
            //动态计算ItemView宽高
            ViewGroup.LayoutParams params = findWordViewHolder.itemView.getLayoutParams();
            params.width = itemWidth;
            params.height = itemWidth;
        }
        return findWordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindWordViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String letter = mDatas.get(position);
        holder.tvText.setText(letter);
        holder.tvText.setOnClickListener(v -> {
            /*if(holder.itemView.getScaleX()>1 && holder.itemView.getScaleY()>1){
                holder.itemView.setScaleX(1);
                holder.itemView.setScaleY(1);
            }else {
                holder.itemView.setScaleX(1.3f);
                holder.itemView.setScaleY(1.3f);
            }*/

            mDatas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(0, mDatas.size());
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class FindWordViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        public FindWordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_letter);
        }
    }
}