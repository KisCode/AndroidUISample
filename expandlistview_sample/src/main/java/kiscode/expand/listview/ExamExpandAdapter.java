package kiscode.expand.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import kiscode.expand.listview.model.ExamItem;

/**
 * Description:
 * Author: kanjianxiong
 * Date : 2021/4/14 13:05
 **/
public class ExamExpandAdapter extends BaseExpandableListAdapter {
    private List<ExamItem> mDatas;

    public ExamExpandAdapter(List<ExamItem> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDatas.get(groupPosition).getExamItemInfoList().size();
    }

    @Override
    public ExamItem getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public ExamItem.ExamAnswerItem getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getExamItemInfoList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mDatas.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getExamItemInfoList().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_group, parent, false);
        }

        ExamItem examItem = getGroup(groupPosition);
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        tvTitle.setText(examItem.getId() + "." + examItem.getQuestion());

        return convertView;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_anwsers, parent, false);
            AnwserViewHolder viewHolder = new AnwserViewHolder();
            viewHolder.radioButton = convertView.findViewById(R.id.radioButton);

            convertView.setTag(viewHolder);
        }
        AnwserViewHolder viewHolder = (AnwserViewHolder) convertView.getTag();
        RadioButton radioButton = viewHolder.radioButton;
        ExamItem examItem = getGroup(groupPosition);
        ExamItem.ExamAnswerItem answer = getChild(groupPosition, childPosition);
        radioButton.setText(answer.getContent() + ":" + answer.getId());

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    answer.setCheck(true);
                    for (ExamItem.ExamAnswerItem answerItem : examItem.getExamItemInfoList()) {
                        if (answerItem.getId() != answer.getId() && answerItem.isCheck()) {
                            answerItem.setCheck(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
        radioButton.setChecked(answer.isCheck());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class AnwserViewHolder {
        RadioButton radioButton;
    }
}