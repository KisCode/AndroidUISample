package com.kiscode.recylerview.sample.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.R;

import java.util.List;

/**** @ProjectName: AndroidUISample
 * @Package: com.kiscode.recylerview.sample.adapter
 * @ClassName: MutipleTypeViewAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/2/13 22:26
 * @UpdateUser: 更新者： 
 * @UpdateDate: 2020/2/13 22:26
 * @UpdateRemark: 更新说明： 
 * @Version: 1.0
 */

public class MutipleTypeViewStickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEAD = 11;
    private static final int TYPE_TEXT = 595;

    private Context mContext;
    private List<String> mDatas;

    public MutipleTypeViewStickAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (TYPE_HEAD == viewType) {
            return new HeadViewHolder(inflater.inflate(R.layout.item_head_layout, parent, false));
        } else {
            return new TextViewHolder(inflater.inflate(R.layout.item_text_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            holder.itemView.setTag(true);
            ((HeadViewHolder) holder).tvHead.setText("Head\t" + mDatas.get(position));
        } else if (holder instanceof TextViewHolder) {
            holder.itemView.setTag(false);
            ((TextViewHolder) holder).tvText.setText(mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 20 == 0) {
            return TYPE_HEAD;
        } else {
            return TYPE_TEXT;
        }
    }

    private class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text_item);
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHead;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHead = itemView.findViewById(R.id.tv_head_item);
        }
    }
}
