package com.kiscode.recylerview.sample.comman;


import android.content.Context;
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

    public CommanAdapter(Context mContext, List<T> mDatas, int mLayoutRes) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mLayoutRes = mLayoutRes;
    }

    @NonNull
    @Override
    public CommanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CommanViewHolder.get(mContext, parent, mLayoutRes);
    }

    @Override
    public void onBindViewHolder(@NonNull CommanViewHolder holder, int position) {
        convert(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(@NonNull CommanViewHolder holder, int pos);
}
