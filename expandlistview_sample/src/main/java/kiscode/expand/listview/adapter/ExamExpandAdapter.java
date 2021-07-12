package kiscode.expand.listview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import kiscode.expand.listview.R;
import kiscode.expand.listview.model.ExamItem;

/**
 * Description: 折叠展开适配器
 * Author: keno
 * Date : 2021/4/14 13:05
 **/
public class ExamExpandAdapter extends BaseExpandableListAdapter {
    private List<ExamItem> mDatas;

    public ExamExpandAdapter(List<ExamItem> mDatas) {
        this.mDatas = mDatas;
    }

    /**
     * 父级目录数
     *
     * @return 父级目录数
     */
    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    /**
     * 指定父级目录下子级节点数
     *
     * @param groupPosition 父目录位置
     * @return 子级节点数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mDatas.get(groupPosition).getExamItemInfoList().size();
    }

    /**
     * 指定父级目录节点
     * @param groupPosition 父级目录位置
     * @return 指定父级目录节点
     */
    @Override
    public ExamItem getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    /**
     * 指定子级目录节点
     * @param groupPosition 父级目录位置
     * @param childPosition 子节点位置
     * @return 子级目录节点
     */
    @Override
    public ExamItem.ExamAnswerItem getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getExamItemInfoList().get(childPosition);
    }

    /**
     * 父目录节点id
     * @param groupPosition 父级目录位置
     * @return 父目录节点id
     */
    @Override
    public long getGroupId(int groupPosition) {
        return mDatas.get(groupPosition).getId();
    }

    /**
     * 子节点id
     * @param groupPosition 父级目录位置
     * @param childPosition 子节点位置
     * @return 子节点id
     */
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

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    static class AnwserViewHolder {
        RadioButton radioButton;
    }
}