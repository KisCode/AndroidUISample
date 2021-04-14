package kiscode.expand.listview;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kiscode.expand.listview.model.ExamItem;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ExamItem> examItemList = loadMockData();
        expandableListView = findViewById(R.id.expand_listview);
        ExamExpandAdapter adapter = new ExamExpandAdapter(examItemList);
        expandableListView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }

        // 禁止收缩
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
    }

    private List<ExamItem> loadMockData() {
        List<ExamItem> examItemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExamItem examItem = new ExamItem();
            examItem.setId(i + 1);
            examItem.setQuestion("题目xxxx，请选择正确答案");
            List<ExamItem.ExamAnswerItem> answerItemList = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                ExamItem.ExamAnswerItem answerItem = new ExamItem.ExamAnswerItem();
                int ansserId = (i + 1) * 1000 + j;   //确保每个答案id唯一
                answerItem.setId(ansserId);
                answerItem.setName(String.valueOf(j));
                answerItem.setContent("选项 ");
                answerItemList.add(answerItem);
            }
            examItem.setExamItemInfoList(answerItemList);

            examItemList.add(examItem);
        }

        return examItemList;
    }
}