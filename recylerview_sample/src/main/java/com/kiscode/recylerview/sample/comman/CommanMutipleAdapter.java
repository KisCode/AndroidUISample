package com.kiscode.recylerview.sample.comman;


import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

/****
 * ProjectName: AndroidUISample
 * Package: com.kiscode.recylerview.sample.comman
 * ClassName: CommanMutipleAdapter
 * Description: CommanMutipleAdapter
 * Author:  Administrator
 * CreateDate: 2020/2/17 10:01
 */

public abstract class CommanMutipleAdapter<T> extends CommanAdapter<T> {
    private MutipleItemSupport mMutipleItemSupport;

    public CommanMutipleAdapter(Context mContext, List<T> mDatas, MutipleItemSupport mutipleItemSupport) {
        super(mDatas, -1);
        this.mMutipleItemSupport = mutipleItemSupport;
    }

    @NonNull
    @Override
    public CommanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layout = mMutipleItemSupport.getItemLayout(viewType);
        return CommanViewHolder.get(mContext, parent, layout);
    }

    @Override
    public int getItemViewType(int position) {
        return mMutipleItemSupport.getItemViewType(position);
    }
}
