package com.kiscode.recylerview.sample.comman;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Description: 通用内容适配器
 * Author: keno
 * Date : 2020/2/16 11:05
 **/
public abstract class CommanWithEmptyAdapter<T> extends RecyclerView.Adapter<CommanViewHolder> {

    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_EMPTY = 0;

    protected List<T> mDatas;
    protected Context mContext;
    protected View mEmptyView;
    protected @LayoutRes
    int mLayoutRes;
    private RecyclerView mRecyclerView;
    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    public CommanWithEmptyAdapter(List<T> mDatas, int mLayoutRes) {
        this.mDatas = mDatas;
        this.mLayoutRes = mLayoutRes;
    }

    @NonNull
    @Override
    public CommanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CommanViewHolder commanViewHolder = null;
        if (viewType == VIEW_TYPE_EMPTY && mEmptyView != null) {
            commanViewHolder = new CommanViewHolder(mContext, mEmptyView);
        } else {
            commanViewHolder = CommanViewHolder.get(mContext, parent, mLayoutRes);
            bindViewClickListener(commanViewHolder);
        }
        return commanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommanViewHolder holder, final int position) {
        if (VIEW_TYPE_ITEM == getItemViewType(position)) {
            convert(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.isEmpty() && getItemCount() == 1) {
            return VIEW_TYPE_EMPTY;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (mDatas.isEmpty() && mEmptyView != null) {
            return 1;
        }
        return mDatas.size();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    private void bindViewClickListener(final CommanViewHolder commanViewHolder) {
        if (onItemClickListener != null) {
            commanViewHolder.itemView.setOnClickListener(v -> onItemClickListener.onClick(CommanWithEmptyAdapter.this, commanViewHolder.getLayoutPosition()));
        }

        if (onItemLongClickListener != null) {
            commanViewHolder.itemView.setOnLongClickListener(v -> {
                onItemLongClickListener.onLongClick(CommanWithEmptyAdapter.this, commanViewHolder.getLayoutPosition());
                return true;
            });
        }
    }

    public T getItem(int pos) {
        return mDatas.get(pos);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() != null) {
            throw new RuntimeException("Don't bind twice");
        }
        mRecyclerView = recyclerView;
        getRecyclerView().setAdapter(this);
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    public void setEmptyView(@LayoutRes int resId) {
        if (getRecyclerView() == null) {
            throw new IllegalStateException("Not bind Recyclerview");
        }
        View emptyView = LayoutInflater.from(getRecyclerView().getContext()).inflate(resId, getRecyclerView(), false);
        setEmptyView(emptyView);
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
        void onClick(CommanWithEmptyAdapter<T> adapter, int pos);
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(CommanWithEmptyAdapter<T> adapter, int pos);
    }
}
