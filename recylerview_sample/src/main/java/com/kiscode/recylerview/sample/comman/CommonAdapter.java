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
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected List<T> mDatas;
    protected Context mContext;

    protected @LayoutRes
    int mLayoutRes;
    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    public CommonAdapter(List<T> mDatas, int mLayoutRes) {
        this.mDatas = mDatas;
        this.mLayoutRes = mLayoutRes;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CommonViewHolder commonViewHolder = CommonViewHolder.get(mContext, parent, mLayoutRes);
        bindViewClickListener(commonViewHolder);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, final int position) {
        convert(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private void bindViewClickListener(final CommonViewHolder commonViewHolder) {
        if (onItemClickListener != null) {
            commonViewHolder.itemView.setOnClickListener(v -> onItemClickListener.onClick(CommonAdapter.this, commonViewHolder.getLayoutPosition()));
        }

        if (onItemLongClickListener != null) {
            commonViewHolder.itemView.setOnLongClickListener(v -> {
                onItemLongClickListener.onLongClick(CommonAdapter.this, commonViewHolder.getLayoutPosition());
                return true;
            });
        }
    }

    public T getItem(int pos) {
        return mDatas.get(pos);
    }

    public abstract void convert(@NonNull CommonViewHolder holder, int pos);

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
        void onClick(CommonAdapter<T> adapter, int pos);
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(CommonAdapter<T> adapter, int pos);
    }
}
