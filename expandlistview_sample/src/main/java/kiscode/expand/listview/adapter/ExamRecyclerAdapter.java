package kiscode.expand.listview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kiscode.expand.listview.R;
import kiscode.expand.listview.model.ExamItem;

/**
 * Description:
 * Author: keno
 * Date : 2021/4/14 14:53
 **/
public class ExamRecyclerAdapter extends RecyclerView.Adapter<ExamRecyclerAdapter.ExamViewHolder> {
    private List<ExamItem> mData;

    public ExamRecyclerAdapter(List<ExamItem> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_recycler, parent, false);
        return new ExamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        ExamItem examItem = mData.get(position);
        holder.tvTitle.setText(position + "." + examItem.getQuestion());

        holder.radioGroup.removeAllViews();
        for (int i = 0; i < examItem.getExamItemInfoList().size(); i++) {
            final ExamItem.ExamAnswerItem answerItem = examItem.getExamItemInfoList().get(i);
            RadioButton radioButton = new RadioButton(holder.itemView.getContext());
            String text = "radio " + (i + 1);
            radioButton.setText(text);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    answerItem.setCheck(isChecked);

                    for (ExamItem.ExamAnswerItem item : examItem.getExamItemInfoList()) {
                        if (item.getId() != answerItem.getId() && item.isCheck()) {
                            //互斥
                            item.setCheck(false);
                        }
                    }
                    notifyItemChanged(position);
                }
            });
            radioButton.setChecked(answerItem.isCheck());
            holder.radioGroup.addView(radioButton, i);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RadioGroup radioGroup;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            radioGroup = itemView.findViewById(R.id.radioGroup_anwser);
        }
    }
}