package com.kiscode.recylerview.sample.comman;


import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *
 * Description: 通用内容适配器
 * Author: keno
 * Date : 2020/2/16 11:05
 **/
public abstract class CommanAdapter<T> extends RecyclerView.Adapter<CommanViewHolder> {
    protected List<T> mDatas;
    protected Context mContext;

    protected @LayoutRes
    int mLayoutRes;
    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    public CommanAdapter(List<T> mDatas, int mLayoutRes) {
        this.mDatas = mDatas;
        this.mLayoutRes = mLayoutRes;
    }

    @NonNull
    @Override
    public CommanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CommanViewHolder commanViewHolder = CommanViewHolder.get(mContext, parent, mLayoutRes);
        bindViewClickListener(commanViewHolder);
        return commanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommanViewHolder holder, final int position) {
        convert(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private void bindViewClickListener(final CommanViewHolder commanViewHolder) {
        if (onItemClickListener != null) {
            commanViewHolder.itemView.setOnClickListener(v -> onItemClickListener.onClick(CommanAdapter.this, commanViewHolder.getLayoutPosition()));
        }

        if (onItemLongClickListener != null) {
            commanViewHolder.itemView.setOnLongClickListener(v -> {
                onItemLongClickListener.onLongClick(CommanAdapter.this, commanViewHolder.getLayoutPosition());
                return true;
            });
        }
    }

    public T getItem(int pos) {
        return mDatas.get(pos);
    }

    public abstract void convert(@NonNull CommanViewHolder holder, int pos);

    public void setNewDatas(List<T> newDatas) {
        if (newDatas != mDatas) {
            mDatas = newDatas;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener<T> {
        void onClick(CommanAdapter<T> adapter, int pos);
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(CommanAdapter<T> adapter, int pos);
    }
}
