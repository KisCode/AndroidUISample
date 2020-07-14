package com.kiscode.recylerview.sample.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class MutipleTypeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_IMAGE = 592;
    private static final int TYPE_TEXT = 595;

    private Context mContext;
    private List<String> mDatas;

    public MutipleTypeViewAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (TYPE_IMAGE == viewType) {
            return new ImageViewHolder(inflater.inflate(R.layout.item_image_layout, parent, false));
        } else {
            return new TextViewHolder(inflater.inflate(R.layout.item_text_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).tvPos.setText(position + ":图片类型的item");
        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).tvText.setText(mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 10 == 0) {
            return TYPE_IMAGE;
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

    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvPos;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img_item);
            tvPos = itemView.findViewById(R.id.tv_image_item);
        }
    }
}
