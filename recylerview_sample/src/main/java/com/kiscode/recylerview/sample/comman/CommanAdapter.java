package com.kiscode.recylerview.sample.comman;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/****
 * ProjectName: AndroidUISample
 * Package: com.kiscode.recylerview.sample.comman
 * ClassName: CommanAdapter
 * Description:
 * Author:  Administrator
 * CreateDate: 2020/2/16 11:05
 */

public abstract class CommanAdapter<T> extends RecyclerView.Adapter<CommanViewHolder> {
    protected List<T> mDatas;
    protected Context mContext;

    protected @LayoutRes
    int mLayoutRes;
    private OnItemClickListener<T> onItemClickListener;

    public CommanAdapter(List<T> mDatas, int mLayoutRes) {
        this.mDatas = mDatas;
        this.mLayoutRes = mLayoutRes;
    }

    @NonNull
    @Override
    public CommanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return CommanViewHolder.get(mContext, parent, mLayoutRes);
    }

    @Override
    public void onBindViewHolder(@NonNull CommanViewHolder holder, final int position) {
        convert(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(CommanAdapter.this, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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

    public interface OnItemClickListener<T> {
        void onClick(CommanAdapter<T> adapter, int pos);
    }
}
