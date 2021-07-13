package kiscode.expand.listview;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kiscode.expand.listview.adapter.ExamExpandAdapter;
import kiscode.expand.listview.biz.MockApi;
import kiscode.expand.listview.model.ExamItem;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ExamItem> examItemList = MockApi.loadMockData();
        expandableListView = findViewById(R.id.expand_listview);
        ExamExpandAdapter adapter = new ExamExpandAdapter(examItemList);
//        expandableListView.setAdapter(adapter);
        adapter.bindExpandableListView(expandableListView);

        //展开全部节点
/*        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }*/
        // 禁止收缩展开
//        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
    }
}