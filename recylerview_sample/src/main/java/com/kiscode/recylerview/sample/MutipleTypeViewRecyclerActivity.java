package com.kiscode.recylerview.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.adapter.MutipleTypeViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MutipleTypeViewRecyclerActivity extends AppCompatActivity {

    private List<String> mDatas;
    private RecyclerView recylerViewMutiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutiple_type_view_recycler);
        initDatas();
        initView();
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("No." + i);
        }
    }

    private void initView() {
        recylerViewMutiple = findViewById(R.id.recylerView_mutiple);

        MutipleTypeViewAdapter adapter = new MutipleTypeViewAdapter(this, mDatas);
        recylerViewMutiple.setLayoutManager(new LinearLayoutManager(this));
        recylerViewMutiple.setAdapter(adapter);
    }
}
