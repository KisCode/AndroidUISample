package kiscode.material.design.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kiscode.material.design.R;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/20 22:46
 */

public class ItemNumAdapter extends RecyclerView.Adapter<ItemNumAdapter.NumViewHolder> {


    @NonNull
    @Override
    public NumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_num, parent, false);
        return new NumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumViewHolder holder, int position) {
        holder.tvNum.setText(getNumText(position));
        holder.tvNumValue.setText(getNumValueText(position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class NumViewHolder extends RecyclerView.ViewHolder {

        TextView tvNum;
        TextView tvNumValue;

        public NumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_num);
            tvNumValue = itemView.findViewById(R.id.tv_num_value);
        }
    }


    private String getNumText(int pos) {
        return String.valueOf(pos + 1);
    }

    private String getNumValueText(int pos) {
        switch (pos % 10) {
            case 0:
                return "壹";
            case 1:
                return "贰";
            case 2:
                return "叁";
            case 3:
                return "肆";
            case 4:
                return "伍";
            case 5:
                return "陆";
            case 6:
                return "柒";
            case 7:
                return "捌";
            case 8:
                return "玖";
            case 9:
                return "拾";
        }
        return "";
    }


}
