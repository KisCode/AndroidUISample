package com.kiscode.recylerview.sample;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.recylerview.sample.adapter.MutipleTypeGridAdapter;
import com.kiscode.recylerview.sample.mock.MockApi;
import com.kiscode.recylerview.sample.model.Automobile;

import java.util.List;

/**
 * Description: 多Type GridLayoutManager 的RecyclerView
 * Author: keno
 * Date : 2021/4/6 17:48
 **/
public class MutipleTypeGridRecyclerActivity extends Activity {
    private static final int COUNT_SPAN = 3;
    MutipleTypeGridAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutiple_grid_layout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_grid_mutiple);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COUNT_SPAN);
        recyclerView.setLayoutManager(gridLayoutManager);

        final List<Automobile> mockAutoMobile = MockApi.getMockAutoMobile(this);
        adapter = new MutipleTypeGridAdapter(mockAutoMobile);
        recyclerView.setAdapter(adapter);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Automobile automobile = mockAutoMobile.get(position);
                //每个元素占3列
                return automobile.getDepth() == MutipleTypeGridAdapter.DEPTH_HEAD ? COUNT_SPAN : 1;
            }
        });

    }
}