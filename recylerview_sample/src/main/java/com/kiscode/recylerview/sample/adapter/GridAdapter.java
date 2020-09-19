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
 * @ClassName: GridAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/2/13 21:17
 * @UpdateUser: 更新者： 
 * @UpdateDate: 2020/2/13 21:17
 * @UpdateRemark: 更新说明： 
 * @Version: 1.0
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {
    private Context mContext;
    private List<String> mDatas;

    public GridAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override

    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_text_layout, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.tvText.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text_item);
        }
    }
}
