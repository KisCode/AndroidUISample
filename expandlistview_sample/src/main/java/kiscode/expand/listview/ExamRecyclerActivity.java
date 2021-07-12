package kiscode.expand.listview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kiscode.expand.listview.adapter.ExamRecyclerAdapter;
import kiscode.expand.listview.biz.MockApi;

public class ExamRecyclerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_recycler);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_exam);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExamRecyclerAdapter adapter = new ExamRecyclerAdapter(MockApi.loadMockData());
        recyclerView.setAdapter(adapter);
    }
}